package com.my.rest.service;

import java.util.List;

import com.my.rest.model.QuizVO;



public interface QuizService {
	
	//퀴즈 보기
	public int countQuiz() throws Exception;

	public QuizVO getQuiz(int quizNum);

	public boolean answerCheck(QuizVO quizVO, int answer);

	public QuizVO generateQuiz();
}


