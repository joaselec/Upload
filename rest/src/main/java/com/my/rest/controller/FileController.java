package com.my.rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.rest.service.BoardPager;
import com.my.rest.service.BoardService;



@Controller
public class FileController{
	
	@Inject
    BoardService boardService;
	
	@RequestMapping("upload")
    public String write(){
        return "upload"; 
    }
	
	

	@RequestMapping("upload1")
    public ModelAndView upolad1(@RequestParam("hakbun") String num, 
    		@RequestParam("report") MultipartFile file)
	{
        ModelAndView mav = new ModelAndView();
        String filename = file.getOriginalFilename();
        //실제 파일을 업로드하기 위한 파일 객체 생성
        File f = new File("c:\\upload\\"+num+"_"+filename);
        //한번에 한해서 동일한 파일이 존재하면 이름에 (1) ,
        //(나중에)2번째에도 검사해서 이름을 편해보고, 확장자 앞에 다른 이름을 추가하도록 해보자 
        if(f.exists()){
            f = new File("c:\\upload\\"+num+"_"+filename+"(1)");
        }        
    
        try {
            file.transferTo(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        return mav;
    }
	

}
