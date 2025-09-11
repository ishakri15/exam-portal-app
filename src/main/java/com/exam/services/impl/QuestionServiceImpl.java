package com.exam.services.impl;

import com.exam.entity.exam.Questions;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Questions addQuestion(Questions questions) {
        return this.questionRepository.save(questions);
    }

    @Override
    public Questions updateQuestion(Questions questions) {
        return this.questionRepository.save(questions);
    }

    @Override
    public Set<Questions> getAllQuestion() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Questions getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Questions> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long quesId) {
        Questions questions = new Questions();
        questions.setQuesId(quesId);
        this.questionRepository.delete(questions);
    }
}
