package com.student.dto;
import java.time.LocalDateTime;

public class AttemptedQuizDto {
    private int quizId;
    private String quizName;
    private int score;
    private LocalDateTime submittedAt;
    public AttemptedQuizDto(int quizId, String quizName, int score, LocalDateTime submittedAt) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.score = score;
        this.submittedAt = submittedAt;
    }
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    public void setquizName(String quizName) {
        this.quizName = quizName;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
    public int getQuizId() {
        return quizId;
    }
    public String getquizName() {
        return quizName;
    }
    public int getScore() {
        return score;
    }
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
}

