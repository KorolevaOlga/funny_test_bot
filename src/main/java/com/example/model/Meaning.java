package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "meanings")
@Embeddable
@Getter
@Setter
@ToString
public class Meaning {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idMeaning;

    @Embedded
    @AttributeOverride(name = "idTests", column = @Column(name = "idTests"))
    private Test test;

    @Embedded
    @AttributeOverride(name = "idResult", column = @Column(name = "idResult"))
    private Result result;

    private String textResult;

    private int scoreResult;//add - result
}
