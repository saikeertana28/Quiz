package com.Teacher.Service;
import com.Teacher.Model.QuestionDao;
import com.Teacher.Model.QuizData;
import com.Teacher.Others.AllQuizData;
import com.Teacher.Others.ConductedQuizByTeacher;
import com.Teacher.Others.UserDto;
import com.Teacher.Repository.QuestionRepo;
import com.Teacher.Repository.QuizRepo;
import com.Teacher.feign.SecurityFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    QuizRepo quizRepo;
    @Autowired
    SecurityFeign securityFeign;
    @Autowired
    private JavaMailSender mailSender;
    public ResponseEntity<List<QuestionDao>> getQuestionsByCategory(String category) {
        List <QuestionDao>questions = questionRepo.findQuestionsByCategory(category);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDao>> getAllQuestions() {
        return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDao>> findRandQuestions(String category, int numQ) {
        List<QuestionDao> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<String> createQuiz(AllQuizData data) {
        this.sendEmails(data.getEmails(), data.getCategory(), data.getQuizName(), data.getQuizDescription());

        List<Integer> q_nums = new ArrayList<>();
        QuizData quizData = new QuizData();
        quizData.setEmails(data.getEmails());
        quizData.setQuizName(data.getQuizName());
        quizData.setTeacherId(data.getTeacherId());
        quizData.setTeacherName(data.getTeacherName());
        quizData.setQuizDescription(data.getQuizDescription());
        quizData.setCategory(data.getCategory());

        List<QuestionDao> questions = data.getQuestions();
        if (questions == null || questions.isEmpty()) {
            return new ResponseEntity<>("No questions provided", HttpStatus.BAD_REQUEST);
        }

        for (QuestionDao question : questions) {
            List<QuestionDao> existingQuestions = questionRepo.findByQuestionAndCategory(
                    question.getQuestion(), question.getCategory()
            );
            if (!existingQuestions.isEmpty()) {
                q_nums.add(existingQuestions.get(0).getQno());
            } else {
                QuestionDao newQuestion = new QuestionDao();
                newQuestion.setQuestion(question.getQuestion());
                newQuestion.setCategory(question.getCategory());
                newQuestion.setDifficulty(question.getDifficulty());
                newQuestion.setAnswer(question.getAnswer());
                newQuestion.setOption1(question.getOption1());
                newQuestion.setOption2(question.getOption2());
                newQuestion.setOption3(question.getOption3());
                newQuestion.setOption4(question.getOption4());
                QuestionDao saved = questionRepo.save(newQuestion);
                q_nums.add(saved.getQno());
            }
        }
        try
        {
            quizData.setQuestion_numbers(q_nums);
            quizRepo.save(quizData);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private void sendEmails(List<String> emails,String category,String quizName,String quizDescription) {
        String mess = "You are invited to participate in a quiz on '" + category + "' titled '" + quizName + "'.\n"
                + "Description: " + quizDescription + ". Please join and test your knowledge!";
        String subject = "Invitation: Participate in '" + quizName + "' Quiz on " + category;
        for (String email : emails) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(mess);
            mailSender.send(message);
        }
    }
    public ResponseEntity<List<ConductedQuizByTeacher>> getConductedQuestions(int teacherId) {
        List<QuizData> qz = quizRepo.findByTeacherId(teacherId);
        List<ConductedQuizByTeacher> conductedQuiz = new ArrayList<>();
        for(QuizData quiz : qz) {
            ConductedQuizByTeacher conquiz = new ConductedQuizByTeacher();
            conquiz.setQuizName(quiz.getQuizName());
            conquiz.setTeacherName(quiz.getTeacherName());
            conquiz.setQuizDescription(quiz.getQuizDescription());
            conquiz.setQuizId(quiz.getQuizId());
            conquiz.setStudentemails(quiz.getEmails());
            List<QuestionDao> ques = this.getQuestionsbyNumber(quiz.getQuestion_numbers());
            conquiz.setQuestions(ques);
            conductedQuiz.add(conquiz);
        }
        return new ResponseEntity<>(conductedQuiz, HttpStatus.OK);
    }
    private List<QuestionDao> getQuestionsbyNumber(List<Integer> questionNumbers) {
        List<QuestionDao> questions = new ArrayList<>();
        for(Integer questionNumber : questionNumbers) {
            QuestionDao question = questionRepo.findById(questionNumber).get();
            questions.add(question);
        }
        return questions;
    }
    public ResponseEntity<List<String>> getEmailsOfParticularTeacher(String teachername, String quizname) {
        QuizData qz = quizRepo.findByTeacherNameAndQuizName(teachername,quizname);
        return new ResponseEntity<>(qz.getEmails(), HttpStatus.OK);
    }
    public ResponseEntity<List<QuestionDao>> getQuestionsforQuiz(int id) {
        QuizData data = quizRepo.findById(id).get();
        return new ResponseEntity<>(this.getQuestionsbyNumber(data.getQuestion_numbers()), HttpStatus.OK);
    }
    public List<QuizData> findQuizzesByStudentEmail(String email) {
        return quizRepo.findQuizzesByStudentEmail(email);
    }
    public ResponseEntity<String> updateProfilePic(@PathVariable String email, @RequestParam("file") MultipartFile file)
    {
        return securityFeign.updateProfilePic(email, file);
    }
    public ResponseEntity<String> updateUserProfile(@PathVariable String email, @RequestBody UserDto updatedData)
    {
        return securityFeign.updateUserProfile(email, updatedData);
    }
}

