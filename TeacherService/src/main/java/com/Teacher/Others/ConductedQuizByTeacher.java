package com.Teacher.Others;

import com.Teacher.Model.QuestionDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConductedQuizByTeacher {
    private int quizId;
    private String quizName;
    private String teacherName;
    private String quizDescription;
    private List<QuestionDao> questions;
    private List<String> studentemails;
    public ConductedQuizByTeacher() {
    }
    public ConductedQuizByTeacher(int quizId, String quizName, String teacherName, String quizDescription, List<QuestionDao> questions, List<String> studentemails) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.teacherName = teacherName;
        this.quizDescription = quizDescription;
        this.questions = questions;
        this.studentemails = studentemails;
    }

    public List<String> getStudentemails() {
        return studentemails;
    }

    public void setStudentemails(List<String> studentemails) {
        this.studentemails = studentemails;
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

    public List<QuestionDao> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDao> questions) {
        this.questions = questions;
    }
}
