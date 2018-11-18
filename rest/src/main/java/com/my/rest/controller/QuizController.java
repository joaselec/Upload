package com.my.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.rest.common.LoggerInterceptor;
import com.my.rest.model.QuizVO;
import com.my.rest.service.QuizService;



@Controller    
public class QuizController {
	 private static Logger logger = LoggerFactory.getLogger(QuizController.class);
	
	 
	
	 @Inject
	 QuizService quizService;
	 
	 int quizNumber = 0;
	 boolean right = false;
	 boolean init = true;

	 QuizVO quizVO;
	 
	 @RequestMapping("quiz")
	 public ModelAndView showQuiz() throws Exception
	 {
		 ModelAndView mav = new ModelAndView();
		 
		 if(init)
		 {
			 quizVO = quizService.generateQuiz();
			 init = false;
		 }
		 
		 if(right){			 
		
			 quizVO = quizService.generateQuiz();
		 }
		
		 
		 mav.addObject("dto", quizVO);
		 mav.setViewName("quiz");
		 
	  	 return mav;
	 }
	 
	 /*
	  *  @RequestMapping("quiz")
	 public ModelAndView showQuiz() throws Exception
	 {
		 ModelAndView mav = new ModelAndView();
		 //전체 퀴즈 숫자
		 
		 if(right)
		 {		 
			 int count = quizService.countQuiz();		 
			 //랜덤 숫자 선택
			 double rand = (int)(Math.random()*1000);
			 quizNumber = (int) (rand%count)+1;			 
		 }
		 //문제 쿼링
		 
		
		 QuizVO quizVO = quizService.getQuiz(quizNumber);
		 
		 if(right)
			 mav.addObject("msg","success");
		 else
			 mav.addObject("msg","fail");
		 
		 mav.addObject("dto", quizVO);
		 mav.setViewName("quiz");
		 
	  	 return mav;
	 }
	  */
	 
	 //답 체크를 서버에서 수행
	 @RequestMapping(value="checkAnswer", method=RequestMethod.GET)
	 @ResponseBody
	 public String checkAnswer(@RequestParam int answer, HttpSession session) throws Exception{
	
		 	right = quizService.answerCheck(quizVO, answer);	
		 	String msg;
		 	if(right){
		 		session.setAttribute("isRight", "true");
		 		msg = "success";
		 	}
		 	else
		 	{
		 		session.setAttribute("isRight", "false");
		 		msg = "fail";
		 	}
		 	
		 	 
		 	return msg;
	     
	    }
	 
	 
}


