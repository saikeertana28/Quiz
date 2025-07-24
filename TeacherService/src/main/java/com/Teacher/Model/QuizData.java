package com.Teacher.Model;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "quiz_data")
public class QuizData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;
    private String quizName;
    private int teacherId;
    private String teacherName;
    private String quizDescription;
    private String category;
    @ElementCollection
    private List<Integer> question_numbers;
    @ElementCollection
    private List<String> Emails;
    public QuizData() {
    }

    public QuizData(int quizId, String quizName,int teacherId, String teacherName, String quizDescription, String category, List<Integer> question_numbers,List<String> emails) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.quizDescription = quizDescription;
        this.category = category;
        this.question_numbers = question_numbers;
        this.Emails = emails;
    }

    public List<String> getEmails() {
        return Emails;
    }

    public void setEmails(List<String> emails) {
        this.Emails = emails;
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
}
