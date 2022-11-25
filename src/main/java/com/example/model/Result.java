package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "result")
@Embeddable
@Getter
@Setter
@ToString
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResult;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "idAnswer", column = @Column(name = "idAnswers")),
            @AttributeOverride(name = "letter", column = @Column(name = "letter")),
            @AttributeOverride(name = "score", column = @Column(name = "score"))
    })
    private Answer answer;

    @Embedded
    @AttributeOverride(name = "idTests", column = @Column(name = "idTests"))
    private Test test;
}
