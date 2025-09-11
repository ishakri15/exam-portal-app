package com.exam.controller;

import com.exam.entity.exam.Questions;
import com.exam.entity.exam.Quiz;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Questions> add(@RequestBody Questions questions){
        return ResponseEntity.ok(this.questionService.addQuestion(questions));
    }

    //update question
    @PutMapping("/")
    public ResponseEntity<Questions> update(@RequestBody Questions questions){
        return ResponseEntity.ok(this.questionService.updateQuestion(questions));
    }

    //get all questions of any qid
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
//        Quiz quiz = new Quiz();
//        quiz.setqId(qid);
//        Set<Questions> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Questions> questions =  quiz.getQuestions();
        List list = new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNoOfQues())){
            list = list.subList(0, Integer.parseInt(quiz.getNoOfQues()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //get single question
    @GetMapping("/{quesId}")
    public Questions get(@PathVariable("quesId")Long quesId){
        return this.questionService.getQuestion(quesId);
    }

    //delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId")Long quesId){
        this.questionService.deleteQuestion(quesId);
    }
}
