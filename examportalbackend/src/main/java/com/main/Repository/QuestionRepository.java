package com.main.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.exam.Question;
import com.main.model.exam.Quiz;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
