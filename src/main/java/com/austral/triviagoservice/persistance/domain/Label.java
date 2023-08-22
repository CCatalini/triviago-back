package com.austral.triviagoservice.persistance.domain;

import javax.persistence.*;

@Entity(name = "Label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Column
    String value;

    public Label(){}

    public Long getId() {
        return Id;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
