package com.example.quizservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizservice.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz,Integer>{



}