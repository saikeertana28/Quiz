package com.student.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StudentAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int studentId;
    private int quizId;
    private int score;
    private LocalDateTime submittedAt;
    public StudentAttempt() {
    }
    public StudentAttempt(int studentId, int quizId, int score, LocalDateTime submittedAt) {
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
        this.submittedAt = submittedAt;
    }
    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getQuizId() {
        return quizId;
    }
    public int getScore() {
        return score;
    }
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
