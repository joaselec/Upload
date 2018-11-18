package com.my.rest.model;

import java.util.List;
import java.util.Map;

public interface QuizDao {
	public int countQuiz() throws Exception;

	public QuizVO getQuiz(int quizNum);

	public boolean answerCheck(int quizNum, int answer);

	public Map<String,Object> getGuideLine();

	public String getRightCase(String domainId);

	public List<Map<String, Object>> getWrongCase(String domainId);
}
