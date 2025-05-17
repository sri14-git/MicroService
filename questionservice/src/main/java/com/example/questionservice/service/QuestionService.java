package com.example.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.questionservice.model.QuestionWrapper;
import com.example.questionservice.model.Questions;
import com.example.questionservice.model.Response;
import com.example.questionservice.repo.QuestionsRepo;

@Service

public class QuestionService {

    @Autowired
    private QuestionsRepo questionsRepo;

    public List<Questions> getallquestion() {

        return questionsRepo.findAll();
            
    }

    public List<Questions> getQuestionsByCategory(String category) {
        return questionsRepo.findByCategory(category);
    }

    public void addquestion(Questions question) {
       questionsRepo.save(question);
       
    }

    public void addAllQuestions(List<Questions> questions) {
        questionsRepo.saveAll(questions);
        
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryname, int numQ) {
        List<Integer> questions = questionsRepo.findRandomQuestionsByCategory(categoryname, numQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionID) {

        List<QuestionWrapper> wrapper = new ArrayList<>();

        List<Questions> questions= new ArrayList<>();

        for (Integer id : questionID){
            questions.add(questionsRepo.findById(id).get());
        }

        for (Questions question : questions ){

            QuestionWrapper wrp = new QuestionWrapper(); 
            wrp.setId(question.getId());
            wrp.setQuestiontitle(question.getQuestiontitle());
            wrp.setOption1(question.getOption1());
            wrp.setOption2(question.getOption2());
            wrp.setOption3(question.getOption3());
            wrp.setOption4(question.getOption4());
            wrapper.add(wrp);
        }

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
       
        for(Response response : responses){
            Questions questions = questionsRepo.findById(response.getId()).get();
            if(response.getResponse().equals(questions.getRightanswer()))
                right++;

        }
        return new ResponseEntity<>(right, HttpStatus.OK);
        
    }




}
