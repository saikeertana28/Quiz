package com.student.dto;

import java.util.List;

public class PracticeSubmitRequest {
    private int studentId;
    private List<Response> responses;
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public List<Response> getResponses() {
        return responses;
    }
    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
