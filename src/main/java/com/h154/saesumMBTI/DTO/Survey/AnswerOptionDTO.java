package com.h154.saesumMBTI.DTO.Survey;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h154.saesumMBTI.Domain.Survey.AnswerOptionDomain;
import com.h154.saesumMBTI.Enum.AnswerType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class AnswerOptionDTO {

    public AnswerOptionDTO(){};

    public AnswerOptionDTO(AnswerOptionDomain answerOptionDomain){
        this.id = answerOptionDomain.getId();
        this.answerContents = answerOptionDomain.getAnswerContents();
        this.weight = answerOptionDomain.getWeight();
        this.answerType = answerOptionDomain.getAnswerType();
    };


    private Long id;
    private String answerContents;

    private int weight;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

}
