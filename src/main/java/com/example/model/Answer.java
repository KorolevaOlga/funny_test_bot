package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "answers")
@Embeddable
@Getter
@Setter
@ToString
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAnswer;

    private String letter;
    private String textAnswer;
    private int score;

    @Embedded
    @AttributeOverride(name = "idQuestions", column = @Column(name = "idQuestions"))
    private Question question;

}
