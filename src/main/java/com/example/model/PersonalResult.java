package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "personalresult")
@Getter
@Setter
@ToString
public class PersonalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResult;

    @Embedded
    @AttributeOverride(name = "chatId", column = @Column(name = "chatId"))
    private User user;

    @Embedded
    @AttributeOverride(name = "idTests", column = @Column(name = "idTests"))
    private Test test;

    @Embedded
    @AttributeOverride(name = "idMeaning", column = @Column( name = "idMeaning"))
    private Meaning meaning;

    private boolean passages;
}
