package com.Teacher.Controller;

import com.Teacher.Model.QuestionDao;
import com.Teacher.Model.QuizData;
import com.Teacher.Repository.QuizRepo;
import com.Teacher.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @Autowired
    QuizRepo quizRepo;
    @GetMapping("get-quizzes-byemail/{email}")
    public List<QuizData> getQuizzesSharedWithEmail(@PathVariable String email) {
        return quizService.findQuizzesByStudentEmail(email);
    }
    @PostMapping("/get-questions-by-qnos")
    public ResponseEntity<List<QuestionDao>> getQuestionsByQnos(@RequestBody List<Integer> qnos) {
        return quizService.findQuestionsByQnos(qnos);
    }
    @GetMapping("get-quiz-by-id/{quizId}")
    public QuizData getQuizById(@PathVariable int quizId) {
        return quizRepo.findById(quizId).orElse(null);
    }
}
