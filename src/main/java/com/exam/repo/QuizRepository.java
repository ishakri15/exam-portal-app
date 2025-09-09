package com.exam.repo;

import com.exam.entity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
