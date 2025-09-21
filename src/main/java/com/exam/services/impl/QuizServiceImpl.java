package com.exam.services.impl;

import com.exam.controller.exception.ResourceNotFoundException;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        // Fetch the existing quiz by ID
        Quiz existingQuiz = this.quizRepository.findById(quiz.getqId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with ID: " + quiz.getqId()));

        // Update the fields
        existingQuiz.setTitle(quiz.getTitle());
        existingQuiz.setDescription(quiz.getDescription());
        existingQuiz.setMaxMarks(quiz.getMaxMarks());
        existingQuiz.setNoOfQues(quiz.getNoOfQues());
        existingQuiz.setActive(quiz.isActive());
        existingQuiz.setCategory(quiz.getCategory());

        // Save and return the updated quiz
        return this.quizRepository.save(existingQuiz);
    }


    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {
        this.quizRepository.deleteById(quizId);
    }
}
