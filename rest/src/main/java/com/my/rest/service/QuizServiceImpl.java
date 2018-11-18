package com.my.rest.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import com.my.rest.model.QuizDao;
import com.my.rest.model.QuizVO;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Inject
	QuizDao quizDao;

	

	@Override
	public int countQuiz() throws Exception{
		return quizDao.countQuiz();
	}

	@Override
	public QuizVO getQuiz(int quizNum) {
		
		return quizDao.getQuiz(quizNum);
	}

	@Override
	public boolean answerCheck(QuizVO quizVO, int answer) {
		if(quizVO.getAnswer().equals(Integer.toString(answer)))
			return true;
		else 
			return false;
			
		//return quizDao.answerCheck(quizNum, answer);
	}

	@Override
	public QuizVO generateQuiz() {
		
		QuizVO quizVO = new QuizVO();
		//문제 추출
		Map<String,Object> map = quizDao.getGuideLine();
		System.out.println(map);
		
		quizVO.setCommentary((String)map.get("commentary"));
		quizVO.setContent((String)map.get("content"));
		quizVO.setDomain1((String)map.get("domain1"));
		quizVO.setDomain2((String)map.get("domain2"));
		
		int temp = (Integer)map.get("domainId");
		String domainId = Integer.toString(temp);
		
		
		quizVO.setDomainId(domainId);
		
		//정답 번호 생성
		int answer = (int)(Math.random()*1000)%4+1;
		quizVO.setAnswer(Integer.toString(answer));
		//정답 보기 추출
		String rightCase = quizDao.getRightCase(domainId);
		//오답 보기 추출
		List<Map<String, Object>> list = quizDao.getWrongCase(domainId);
		System.out.println(list);
		//정답 보기 설정
		switch(answer){
		case 1: 
			quizVO.setExample1(rightCase);
			quizVO.setExample2((String)list.get(0).get("content"));
			quizVO.setExample3((String)list.get(1).get("content"));
			quizVO.setExample4((String)list.get(2).get("content"));
			break;
		case 2:
			quizVO.setExample2(rightCase);
			quizVO.setExample1((String)list.get(0).get("content"));
			quizVO.setExample3((String)list.get(1).get("content"));
			quizVO.setExample4((String)list.get(2).get("content"));
			break;
		case 3:
			quizVO.setExample3(rightCase);
			quizVO.setExample1((String)list.get(0).get("content"));
			quizVO.setExample2((String)list.get(1).get("content"));
			quizVO.setExample4((String)list.get(2).get("content"));
			break;
		case 4: 
			quizVO.setExample4(rightCase);
			quizVO.setExample1((String)list.get(0).get("content"));
			quizVO.setExample2((String)list.get(1).get("content"));
			quizVO.setExample3((String)list.get(2).get("content"));
			break;
		}	
		
		
		
		//문제 return
		return quizVO;
	}

}
