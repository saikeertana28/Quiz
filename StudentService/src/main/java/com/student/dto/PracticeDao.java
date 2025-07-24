package com.student.dto;

import java.util.List;

public class PracticeDao {
    private int score;
    List<IncorrectAns> wrongans;
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public List<IncorrectAns> getWrongans() {
        return wrongans;
    }
    public void setWrongans(List<IncorrectAns> wrongans) {
        this.wrongans = wrongans;
    }
}
