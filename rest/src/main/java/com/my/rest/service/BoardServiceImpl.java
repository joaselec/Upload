package com.my.rest.service;
 
import java.util.List;
 
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
 
import org.springframework.stereotype.Service;

import com.my.rest.model.BoardDAO;
import com.my.rest.model.BoardVO;
 

 
@Service
public class BoardServiceImpl implements BoardService {
    
    @Inject
    BoardDAO boardDao;
    
  
    @Override
    public void create(BoardVO vo) throws Exception {
        String title = vo.getTitle();
        String content = vo.getContent();
        String writer = vo.getWriter();
        
        title = title.replace("<", "&lt;");
        title = title.replace("<", "&gt;");
        writer = writer.replace("<", "&lt;");
        writer = writer.replace("<", "&gt;");
     
        title = title.replace("  ",    "&nbsp;&nbsp;");
        writer = writer.replace("  ",    "&nbsp;&nbsp;");
       
        content = content.replace("\n", "<br>");
        vo.setTitle(title);
        vo.setContent(content);
        vo.setWriter(writer);
        boardDao.create(vo);
    }
   
    @Override
    public BoardVO read(int bno) throws Exception {
        return boardDao.read(bno);
    }

    @Override
    public void update(BoardVO vo) throws Exception {
        boardDao.update(vo);
    }

    @Override
    public void delete(int bno) throws Exception {
        boardDao.delete(bno);
    }
   
    
 
    @Override
    public void increaseViewcnt(int bno, HttpSession session) throws Exception {
        long update_time = 0;
        
        long current_time = System.currentTimeMillis();
        if(current_time - update_time > 5*1000){
            boardDao.increaseViewcnt(bno);
           
            session.setAttribute("update_time_"+bno, current_time);
            
        }
    }
    // 
    @Override
    public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
        return boardDao.listAll(start, end, searchOption, keyword);
    }

    // 07. �Խñ� ���ڵ� ���� boardDao.countArticle�޼��� 
    @Override
    public int countArticle(String searchOption, String keyword) throws Exception {
        return boardDao.countArticle(searchOption, keyword);
    }

 
}