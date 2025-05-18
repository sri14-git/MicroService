package com.example.quizservice.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizservice.feign.QuizFeign;
import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.Response;

import com.example.quizservice.repo.QuizRepo;

@Service
public class QuizService {


    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizFeign quizFeign;

    public ResponseEntity<String> createQuiz(String categoryname, int numQ, String title) {
        System.out.println("CALLED**********************************************************************************************");
        
        List<Integer> questions = quizFeign.getQuestionsForQuiz(categoryname, numQ).getBody();
        Quiz quiz =new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsID(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizRepo.findById(id).get();
          List<Integer> questionIds = quiz.getQuestionsID();
          ResponseEntity<List<QuestionWrapper>> questions = quizFeign.getQuestionsFromId(questionIds);
          return questions;

        
    }

     public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizFeign.getScore(responses);
        return score;
    }






}
