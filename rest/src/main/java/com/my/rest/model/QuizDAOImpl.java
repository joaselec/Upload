package com.my.rest.model;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class QuizDAOImpl implements QuizDao {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public int countQuiz() throws Exception {
		return sqlSession.selectOne("quiz.countQuiz");
	}

	@Override
	public QuizVO getQuiz(int quizNum) {
		return sqlSession.selectOne("quiz.getQuiz", quizNum);
	}

	@Override
	public boolean answerCheck(int quizNum, int answer) {
		
		int result = sqlSession.selectOne("quiz.getAnswer", quizNum);
		
		if(result==answer)
			return true;
		else
			return false;
		
		//return sqlSession.selectOne("quiz.getAnswer", quizNum) ? true:false;
	}

	@Override
	public Map<String,Object> getGuideLine() {
		return sqlSession.selectOne("quiz.getGuideLine");
		
	}

	@Override
	public String getRightCase(String domainId) {
		return sqlSession.selectOne("quiz.getRightCase", domainId);
		
	}

	@Override
	public List<Map<String, Object>> getWrongCase(String domainId) {
		return sqlSession.selectList("quiz.getWrongCase", domainId);
		
	}

	

}
