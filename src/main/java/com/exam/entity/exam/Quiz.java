package com.exam.entity.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long qId;

    private String title;
    private String description;
    String maxMarks;
    private String NoOfQues;
    private boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Questions> questions = new HashSet<>();

    public Quiz() {
    }

    public Quiz(Long qId, String title, String description, String maxMarks, String noOfQues, boolean active) {
        this.qId = qId;
        this.title = title;
        this.description = description;
        this.maxMarks = maxMarks;
        NoOfQues = noOfQues;
        this.active = active;
    }

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNoOfQues() {
        return NoOfQues;
    }

    public void setNoOfQues(String noOfQues) {
        NoOfQues = noOfQues;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Questions> questions) {
        this.questions = questions;
    }
}
