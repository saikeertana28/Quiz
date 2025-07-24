package com.Teacher.Repository;

import com.Teacher.Model.QuestionDao;
import com.Teacher.Model.QuizData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface QuizRepo extends JpaRepository<QuizData,Integer> {
    List<QuizData> findByTeacherId(int teacherId);
    QuizData findByTeacherNameAndQuizName(String teacherName, String quizName);
    @Query("SELECT q FROM QuizData q WHERE :email MEMBER OF q.Emails")
    List<QuizData> findQuizzesByStudentEmail(@Param("email") String email);
    List<QuizData> findByQuizId(int quizId);
}
