package com.example.questionservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.questionservice.model.QuestionWrapper;
import com.example.questionservice.model.Questions;
import com.example.questionservice.model.Response;
import com.example.questionservice.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;







@RestController
@RequestMapping("/question")
public class QuestionController {
    
    
    @Autowired
    private QuestionService questionService;

    @GetMapping("allquestions")
    public List<Questions> getallquestion() {
        return questionService.getallquestion();

    }
    
    @GetMapping("/category/{category}")
    public List<Questions> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public String addquestion(@RequestBody Questions question) {
        questionService.addquestion(question);
        return "Success";
    }
    
    @PostMapping("/addquestions")
    public String addAllQuestions(@RequestBody List<Questions> questions){
        
        questionService.addAllQuestions(questions);
        return "Success";
    }


    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryname, @RequestParam Integer numQ) {
        return questionService.getQuestionsForQuiz(categoryname,numQ);
    }
    
    @PostMapping("getquestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionsFromId(@RequestBody List<Integer> questionID) {
        return questionService.getQuestionsFromId(questionID);
    }
    
    @PostMapping("getscore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses) {
        
        return questionService.getScore(responses);
    }
    



}
