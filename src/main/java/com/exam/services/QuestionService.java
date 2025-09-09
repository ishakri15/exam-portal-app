package com.exam.services;

import com.exam.entity.exam.Questions;
import com.exam.entity.exam.Quiz;

import java.util.Set;

public interface QuestionService {
    public Questions addQuestion(Questions questions);
    public Questions updateQuestion(Questions questions);
    public Set<Questions> getAllQuestion();
    public Questions getQuestion(Long questionId);
    public Set<Questions> getQuestionsOfQuiz(Quiz quiz);

}
