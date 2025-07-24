
package com.Teacher.Repository;

import com.Teacher.Model.QuestionDao;
import com.Teacher.Model.QuizData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<QuestionDao, Integer> {
    List<QuestionDao> findQuestionsByCategory(String category);
    List<QuestionDao> findByQuestion(String question);
    @Query(value = "SELECT * FROM question_dao WHERE category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
    List<QuestionDao> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);
    List<QuestionDao> findByQuestionAndCategory(String question, String category);
    List<QuestionDao> findByQnoIn(List<Integer> qnos);
}
