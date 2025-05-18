package com.example.quizservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.QuizDTO;
import com.example.quizservice.model.Response;
import com.example.quizservice.service.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        System.out.println("the dto is"+quizDTO.getCategoryName());
         System.out.println("the dto is"+quizDTO.getNumQ());
          System.out.println("the dto is"+quizDTO.getTitle());
        

        return quizService.createQuiz(quizDTO.getCategoryName(),quizDTO.getNumQ(),quizDTO.getTitle());
        
    }

    
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }
    
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

    
}

