package com.student.repository;

import com.student.model.StudentAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentAttemptRepository extends JpaRepository<StudentAttempt, Integer> {
    List<StudentAttempt> findByStudentId(int studentId);
    boolean existsByStudentIdAndQuizId(int studentId, int quizId);
}