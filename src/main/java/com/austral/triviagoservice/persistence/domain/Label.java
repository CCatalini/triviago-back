package com.austral.triviagoservice.persistence.domain;


import com.austral.triviagoservice.presentation.dto.LabelCreateDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "Label")
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Column(unique = true, nullable = false)
    String value;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn,
            inverseJoinColumns = @JoinColumn)
    List<Quiz> quizzes = new ArrayList<>();


    public Label() {}

    public Label(LabelCreateDto labelCreateDto){
        this.value = labelCreateDto.getValue();
    }

}
