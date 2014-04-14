package com.nasa.earthquiz.model;

public class Quiz {

	int QuizId;
	private String QuizTitle;
	private String QuizAns;
	private String QuizPicture;
	private String QuizHint;
	private String QuizDescription;
	private String QuizCategory;
	private String QuizDifficultyType;
	private String QuizOp1;
	private String QuizOp2;
	private String QuizOp3;

	public String getQuizOp1() {
		return QuizOp1;
	}

	public void setQuizOp1(String quizOp1) {
		QuizOp1 = quizOp1;
	}

	public String getQuizOp2() {
		return QuizOp2;
	}

	public void setQuizOp2(String quizOp2) {
		QuizOp2 = quizOp2;
	}

	public String getQuizOp3() {
		return QuizOp3;
	}

	public void setQuizOp3(String quizOp3) {
		QuizOp3 = quizOp3;
	}

	public String getQuizCategory() {
		return QuizCategory;
	}

	public void setQuizCategory(String quizCategory) {
		QuizCategory = quizCategory;
	}

	public int getQuizId() {
		return QuizId;
	}

	public void setQuizId(int quizId) {
		QuizId = quizId;
	}

	public String getQuizTitle() {
		return QuizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		QuizTitle = quizTitle;
	}

	public String getQuizAns() {
		return QuizAns;
	}

	public void setQuizAns(String quizAns) {
		QuizAns = quizAns;
	}

	public String getQuizPicture() {
		return QuizPicture;
	}

	public void setQuizPicture(String quizPicture) {
		QuizPicture = quizPicture;
	}

	public String getQuizHint() {
		return QuizHint;
	}

	public void setQuizHint(String quizHint) {
		QuizHint = quizHint;
	}

	public String getQuizDescription() {
		return QuizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		QuizDescription = quizDescription;
	}

	public String getQuizDifficultyType() {
		return QuizDifficultyType;
	}

	public void setQuizDifficultyType(String quizDifficultyType) {
		QuizDifficultyType = quizDifficultyType;
	}

}
