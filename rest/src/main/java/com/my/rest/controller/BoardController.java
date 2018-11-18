package com.my.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.rest.common.XmlExtraction;
import com.my.rest.model.BoardVO;
import com.my.rest.model.JsonObj;
import com.my.rest.service.BoardPager;
import com.my.rest.service.BoardService;


@Controller    // 현재 클래스를 컨트롤러 빈(bean)으로 등록
//@RequestMapping("/board/*")
public class BoardController {
    

    @Inject
    BoardService boardService;
    
    @RequestMapping("aaa")
    public String aaa() throws Exception
    {
    	XmlExtraction.getXmlData();
    	return "list";
    }
 // 01. 게시글 목록
    @RequestMapping("list1")
    @ResponseBody
    // @RequestParam(defaultValue="") ==> 기본값 할당 : 현재페이지를 1로 초기화
    public List<BoardVO> list(@RequestParam(defaultValue="title") String searchOption,
                            @RequestParam(defaultValue="") String keyword,
                            @RequestParam(defaultValue="1") int curPage) throws Exception{
        
        // 레코드의 갯수 계산
        int count = boardService.countArticle(searchOption, keyword);
        
        // 페이지 나누기 관련 처리
        BoardPager boardPager = new BoardPager(count, curPage);
        int start = boardPager.getPageBegin();
        int end = boardPager.getPageEnd();
        
        List<BoardVO> list = boardService.listAll(start, end, searchOption, keyword);
        System.out.println(list);
        
        /*
        // 데이터를 맵에 저장
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list); // list
        map.put("count", count); // 레코드의 갯수
        map.put("searchOption", searchOption); // 검색옵션
        map.put("keyword", keyword); // 검색키워드
        map.put("boardPager", boardPager);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
        mav.setViewName("list"); // 뷰를 list.jsp로 설정
        
        JsonObj jsonObj = new JsonObj();
        
        return jsonObj; // list.jsp로 List가 전달된다.
        */
        
        return list;
    }
    
    /*
 // 01. 게시글 목록
    @RequestMapping("list")
    // @RequestParam(defaultValue="") ==> 기본값 할당 : 현재페이지를 1로 초기화
    public ModelAndView list(@RequestParam(defaultValue="title") String searchOption,
                            @RequestParam(defaultValue="") String keyword,
                            @RequestParam(defaultValue="1") int curPage) throws Exception{
        
        // 레코드의 갯수 계산
        int count = boardService.countArticle(searchOption, keyword);
        
        // 페이지 나누기 관련 처리
        BoardPager boardPager = new BoardPager(count, curPage);
        int start = boardPager.getPageBegin();
        int end = boardPager.getPageEnd();
        
        List<BoardVO> list = boardService.listAll(start, end, searchOption, keyword);
        
        // 데이터를 맵에 저장
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list); // list
        map.put("count", count); // 레코드의 갯수
        map.put("searchOption", searchOption); // 검색옵션
        map.put("keyword", keyword); // 검색키워드
        map.put("boardPager", boardPager);
        
        ModelAndView mav = new ModelAndView();
        mav.addObject("map", map); // 맵에 저장된 데이터를 mav에 저장
        mav.setViewName("list"); // 뷰를 list.jsp로 설정
        
        return mav; // list.jsp로 List가 전달된다.
    }
*/
    
    // 02_01. 게시글 작성화면
    // @RequestMapping("board/write.do")
    // value="", method="전송방식"
    @RequestMapping(value="write", method=RequestMethod.GET)
    public String write(){
        return "write"; // write.jsp로 이동
    }
    
    // 02_02. 게시글 작성처리
    @RequestMapping(value="insert", method=RequestMethod.POST)
    public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception{
        // session에 저장된 userId를 writer에 저장
        String writer = (String) session.getAttribute("userId");
        // vo에 writer를 세팅
        vo.setWriter(writer);
        boardService.create(vo);
        return "redirect:list";
    }

    
    // 03. 게시글 상세내용 조회, 게시글 조회수 증가 처리
    // @RequestParam : get/post방식으로 전달된 변수 1개
    // HttpSession 세션객체
    @RequestMapping(value="view", method=RequestMethod.GET)
    public ModelAndView view(@RequestParam int bno, HttpSession session) throws Exception{
        // 조회수 증가 처리
        boardService.increaseViewcnt(bno, session);
        // 모델(데이터)+뷰(화면)를 함께 전달하는 객체
        ModelAndView mav = new ModelAndView();
        // 뷰의 이름
        mav.setViewName("view");
        // 뷰에 전달할 데이터
        mav.addObject("dto", boardService.read(bno));
        return mav;
    }
    
    // 04. 게시글 수정
    // 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
    @RequestMapping(value="update", method=RequestMethod.POST)
    public String update(@ModelAttribute BoardVO vo) throws Exception{
        boardService.update(vo);
        return "redirect:list";
    }
    
    // 05. 게시글 삭제
    @RequestMapping("delete")
    public String delete(@RequestParam int bno) throws Exception{
        boardService.delete(bno);
        return "redirect:list";
    }
    
 
    
}
