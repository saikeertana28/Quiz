package com.student.dto;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class QuizData {
    private int quizId;
    private String quizName;
    private int teacherId;
    private String teacherName;
    private String quizDescription;
    private String category;
    private List<Integer> question_numbers;
    private List<String> Emails;
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
    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
    public List<Integer> getQuestion_numbers() {
        return question_numbers;
    }
    public void setQuestion_numbers(List<Integer> question_numbers) {
        this.question_numbers = question_numbers;
    }
    public List<String> getEmails() {
        return Emails;
    }
    public void setEmails(List<String> emails) {
        Emails = emails;
    }
}
