package com.h154.saesumMBTI.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SelectedAnswerDomain {

    @Id
    @GeneratedValue
    @Column(name="selectedAnswer_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "answerOption_id", nullable = false)
    private AnswerOptionDomain answerOptionDomain;

    @ManyToOne
    @JoinColumn(name="resultRecord_id", nullable = false)
    private ResultRecordDomain resultRecordDomain;

}
