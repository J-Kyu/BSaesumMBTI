package com.h154.saesumMBTI.DTO;

import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import com.h154.saesumMBTI.Enum.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class QuestionDTO {

    public QuestionDTO(){}

    public QuestionDTO(QuestionDomain questionDomain){
        this.questionContents = questionDomain.getQuestionContents();
        this.questionType = questionDomain.getQuestionType();

    }

    private String questionContents;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

}
