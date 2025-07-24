package com.Teacher.Service;
import com.Teacher.Model.QuestionDao;
import com.Teacher.Model.QuizData;
import com.Teacher.Repository.QuestionRepo;
import com.Teacher.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class QuizService {
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo questionRepo;
    public List<QuizData> findQuizzesByStudentEmail(String email) {
        return quizRepo.findQuizzesByStudentEmail(email);
    }
    public ResponseEntity<List<QuestionDao>> findQuestionsByQnos(List<Integer> qnos) {
        List<QuestionDao> result = questionRepo.findByQnoIn(qnos);
        return ResponseEntity.ok(result);
    }
}
