package com.example.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Response;

@FeignClient("QUESTIONSERVICE")
public interface QuizFeign {
    
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryname, @RequestParam Integer numQ);
    
    @PostMapping("/question/getquestions")
    public ResponseEntity<List<QuestionWrapper>>getQuestionsFromId(@RequestBody List<Integer> questionID);

    @PostMapping("/question/getscore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response> responses);



}
