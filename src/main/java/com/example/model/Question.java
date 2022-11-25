package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "questions")
@Embeddable
@Getter
@Setter
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuestions;

    private int number;
    private String text;

    @Embedded
    @AttributeOverride(name = "idTests", column = @Column(name = "idTests"))
    private Test test;


}


