package com.exam.repo;

import com.exam.entity.exam.Questions;
import com.exam.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Questions,Long> {
    Set<Questions> findByQuiz(Quiz quiz);
}
