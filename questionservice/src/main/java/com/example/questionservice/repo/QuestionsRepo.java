package com.example.questionservice.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.questionservice.model.Questions;


@Repository
public interface QuestionsRepo extends JpaRepository<Questions,Integer>{

    List<Questions> findByCategory(String category);

    @Query(value = "SELECT q.id FROM questions q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);


}
