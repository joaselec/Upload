package com.my.rest.model;

public class QuizVO {
	
	private int questionId;
	private String domainId;
	private String domain1;
	private String domain2;
	private String content;
	private String example1;
	private String example2;
	private String example3;
	private String example4;
	private String answer;
	private String commentary;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getDomain1() {
		return domain1;
	}
	public void setDomain1(String domain1) {
		this.domain1 = domain1;
	}
	public String getDomain2() {
		return domain2;
	}
	public void setDomain2(String domain2) {
		this.domain2 = domain2;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getExample1() {
		return example1;
	}
	public void setExample1(String example1) {
		this.example1 = example1;
	}
	public String getExample2() {
		return example2;
	}
	public void setExample2(String example2) {
		this.example2 = example2;
	}
	public String getExample3() {
		return example3;
	}
	public void setExample3(String example3) {
		this.example3 = example3;
	}
	public String getExample4() {
		return example4;
	}
	public void setExample4(String example4) {
		this.example4 = example4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getCommentary() {
		return commentary;
	}
	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}
	@Override
	public String toString() {
		return "QuizVO [questionId=" + questionId + ", domainId=" + domainId + ", domain1=" + domain1 + ", domain2="
				+ domain2 + ", content=" + content + ", example1=" + example1 + ", example2=" + example2 + ", example3="
				+ example3 + ", example4=" + example4 + ", answer=" + answer + ", commentary=" + commentary + "]";
	}
	
	
	
	
}
