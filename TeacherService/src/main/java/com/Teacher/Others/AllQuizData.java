
package com.Teacher.Others;
import com.Teacher.Model.QuestionDao;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class AllQuizData {
    private String quizName;
    private String quizDescription;
    private int teacherId;
    private String teacherName;
    private String category;
    private List<String> Emails;
    private List<QuestionDao> questions;
    public AllQuizData() {
    }
    public AllQuizData(String quizName, String quizDescription, List<QuestionDao> questions,String category,List<String> emails) {
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.questions = questions;
        this.category = category;
        this.Emails = emails;
    }
    public List<String> getEmails() {
        return Emails;
    }

    public void setEmails(List<String> emails) {
        this.Emails = emails;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
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
}
