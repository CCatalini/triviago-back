package com.austral.triviagoservice.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Long userId;
    @Column(nullable = false)
    String title;
    @Column
    String description;
    @Column(nullable = false)
    @JsonFormat(pattern= "dd-MM-yyyy", timezone = "Argentina")
    LocalDate creationDate;
    @Column
    Double rating;
    @Column
    String invitationCode;
    @Column
    Integer questionQty;

    public Quiz(){}
}
