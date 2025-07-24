package com.student.dto;

public class IncorrectAns {
    private int qno;
    private String question;
    private String answer;
    public IncorrectAns(int qno, String question, String answer) {
        this.qno = qno;
        this.question = question;
        this.answer = answer;
    }
    public IncorrectAns() {
    }
    public int getQno() {
        return qno;
    }
    public void setQno(int qno) {
        this.qno = qno;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
