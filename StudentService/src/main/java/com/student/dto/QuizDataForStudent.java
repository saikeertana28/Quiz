package com.student.dto;

public class QuizDataForStudent {
    private int quizId;
    private String quizName;
    private String teacherName;
    private String quizDescription;
    private String category;
    public QuizDataForStudent(QuizData quiz) {
        this.quizId = quiz.getQuizId();
        this.quizName = quiz.getQuizName();
        this.teacherName = quiz.getTeacherName();
        this.quizDescription = quiz.getQuizDescription();
        this.category = quiz.getCategory();
    }
    public int getQuizId() {
        return quizId;
    }
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    public String getQuizName() {
        return quizName;
    }
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getQuizDescription() {
        return quizDescription;
    }
    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
