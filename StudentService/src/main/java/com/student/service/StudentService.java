package com.student.service;
import com.student.dto.*;
import com.student.feign.SecurityFeign;
import com.student.feign.TeacherFeign;
import com.student.model.StudentAttempt;
import com.student.repository.StudentAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StudentService {
    @Autowired
    private TeacherFeign teacherFeign;
    @Autowired
    SecurityFeign securityFeign;
    @Autowired
    private StudentAttemptRepository studentAttemptRepo;

    public List<QuizDataForStudent> getQuizzesByEmail(String studentEmail) {
        List<QuizData> quizzes = teacherFeign.getQuizzesByEmail(studentEmail);
        return quizzes.stream()
                .map(QuizDataForStudent::new)
                .toList();
    }
    public ResponseEntity<List<QuestionDisplay>> getQuestionsforQuiz(int quizid) {
        ResponseEntity<List<QuestionDao>> fullQuestionsResponse = teacherFeign.getQuizQuestions(quizid);
        List<QuestionDao> fullQuestions = fullQuestionsResponse.getBody();

        if (fullQuestions == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        List<QuestionDisplay> studentQuestions = new ArrayList<>();

        for (QuestionDao q : fullQuestions) {
            QuestionDisplay dto = new QuestionDisplay();
            dto.setQno(q.getQno());
            dto.setQuestion(q.getQuestion());
            dto.setOption1(q.getOption1());
            dto.setOption2(q.getOption2());
            dto.setOption3(q.getOption3());
            dto.setOption4(q.getOption4());
            studentQuestions.add(dto);
        }

        return new ResponseEntity<>(studentQuestions, HttpStatus.OK);
    }

    public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmitRequest request) {
        int studentId = request.getStudentId();
        int quizId = request.getQuizId();
        if (studentAttemptRepo.existsByStudentIdAndQuizId(studentId, quizId)) {
            return ResponseEntity.badRequest().body("Already attempted");
        }
        List<QuestionDao> questions = teacherFeign.getQuizQuestions(quizId).getBody();
        Map<Integer, String> correctAnswers = new HashMap<>();
        for (QuestionDao q : questions) {
            correctAnswers.put(q.getQno(), q.getAnswer());
        }
        int score = 0;
        for (Response response : request.getResponses()) {
            String correct = correctAnswers.get(response.getQno());
            String selected = response.getAnswer();
            if (correct != null && correct.equalsIgnoreCase(selected)) {
                score++;
            }
        }
        StudentAttempt attempt = new StudentAttempt(studentId, quizId, score, LocalDateTime.now());
        studentAttemptRepo.save(attempt);
        return ResponseEntity.ok("Quiz submitted successfully. Score: " + score);
    }

    public ResponseEntity<List<QuestionDisplay>> getRandomquesByCategory(String category, int numQ) {
        List<QuestionDao> questions = teacherFeign.getRandomQuestions(category, numQ).getBody();
        List<QuestionDisplay> studentQuestions = new ArrayList<>();
        for (QuestionDao q : questions) {
            QuestionDisplay dto = new QuestionDisplay();
            dto.setQno(q.getQno());
            dto.setQuestion(q.getQuestion());
            dto.setOption1(q.getOption1());
            dto.setOption2(q.getOption2());
            dto.setOption3(q.getOption3());
            dto.setOption4(q.getOption4());
            studentQuestions.add(dto);
        }
        return new ResponseEntity<>(studentQuestions, HttpStatus.OK);
    }
    public ResponseEntity<PracticeDao> submitPractice(PracticeSubmitRequest request) {
        List<Response> userResponses = request.getResponses();
        List<Integer> attemptedQ = new ArrayList<>();
        for (Response r : userResponses) {
            attemptedQ.add(r.getQno());
        }
        List<QuestionDao> completeQuestions=teacherFeign.getQuestionsByQnos(attemptedQ).getBody();
        int studentId = request.getStudentId();
        List<IncorrectAns> wrongans=new ArrayList<>();
        Map<Integer,QuestionDao> questionMap=new HashMap<>();
        for(QuestionDao q:completeQuestions){
            questionMap.put(q.getQno(),q);
        }
        int score=0;
        for (Response response : request.getResponses()) {
            QuestionDao q = questionMap.get(response.getQno());
            if (q!= null && q.getAnswer().equalsIgnoreCase(response.getAnswer())) {
                score++;
            }
            else
            {
                IncorrectAns wrong=new IncorrectAns();
                wrong.setQno(response.getQno());
                wrong.setQuestion(q.getQuestion());
                wrong.setAnswer(q.getAnswer());
                wrongans.add(wrong);
            }
        }
        PracticeDao res=new PracticeDao();
        res.setScore(score);
        res.setWrongans(wrongans);
        return ResponseEntity.ok(res);
    }
    public List<AttemptedQuizDto> getAttemptedQuizDetails(int studentId)
    {
        List<StudentAttempt> attempts = studentAttemptRepo.findByStudentId(studentId);
        List<AttemptedQuizDto> result = new ArrayList<>();
        for (StudentAttempt attempt : attempts) {
            try {
                QuizData quiz = teacherFeign.getQuizById(attempt.getQuizId());
                result.add(new AttemptedQuizDto(
                        attempt.getQuizId(),
                        quiz.getQuizName(),
                        attempt.getScore(),
                        attempt.getSubmittedAt()
                ));
            } catch (Exception e) {
                result.add(new AttemptedQuizDto(
                        attempt.getQuizId(), "Unknown Quiz", attempt.getScore(), attempt.getSubmittedAt()
                ));
            }
        }
        return result;
    }
    public ResponseEntity<String> updateProfilePic(@PathVariable String email, @RequestParam("file") MultipartFile file)
    {
        return securityFeign.updateProfilePic(email, file);
    }
    public ResponseEntity<String> updateUserProfile(@PathVariable String email, @RequestBody UsersDto updatedData)
    {
        return securityFeign.updateUserProfile(email, updatedData);
    }
}
