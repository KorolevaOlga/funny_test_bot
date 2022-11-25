package com.example.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "tests")
@Embeddable
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTests;

    private String title;
    private String category;

}
