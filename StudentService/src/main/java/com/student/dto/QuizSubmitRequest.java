package com.student.dto;
import java.util.List;
public class QuizSubmitRequest {
    private int studentId;
    private int quizId;
    private List<Response> responses;
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getQuizId() {
        return quizId;
    }
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
    public List<Response> getResponses() {
        return responses;
    }
    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
