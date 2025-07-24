package com.student.feign;
import com.student.dto.QuestionDao;
import com.student.dto.QuestionDisplay;
import com.student.dto.QuizData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@FeignClient(name = "TEACHER")
public interface TeacherFeign {
    @GetMapping("/quiz/get-quizzes-byemail/{email}")
    List<QuizData> getQuizzesByEmail(@PathVariable("email") String email);
    @GetMapping("/teachers/questionsforquiz/{quizid}")
    ResponseEntity<List<QuestionDao>> getQuizQuestions(@PathVariable("quizid") int quizid);
    @GetMapping("teachers/randquestions")
    ResponseEntity<List<QuestionDao>> getRandomQuestions(@RequestParam String category, @RequestParam int numQ);
    @PostMapping("quiz/get-questions-by-qnos")
    ResponseEntity<List<QuestionDao>> getQuestionsByQnos(@RequestBody List<Integer> qnos);
    @GetMapping("quiz/get-quiz-by-id/{quizId}")
    QuizData getQuizById(@PathVariable("quizId") int quizId);
}
