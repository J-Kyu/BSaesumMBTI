package com.h154.saesumMBTI.Domain.Survey;

import com.h154.saesumMBTI.Enum.AnswerType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AnswerOptionDomain {

    @Id
    @GeneratedValue
    @Column(name="answerOption_id")
    private Long id;

    private String answerContents;

    private int weight;

    @Enumerated(EnumType.ORDINAL)
    private AnswerType answerType;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionDomain questionDomain;


}
