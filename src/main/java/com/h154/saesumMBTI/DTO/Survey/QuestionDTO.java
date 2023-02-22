package com.h154.saesumMBTI.DTO.Survey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h154.saesumMBTI.Domain.Survey.AnswerOptionDomain;
import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import com.h154.saesumMBTI.Enum.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionDTO {

    public QuestionDTO(){}

    public QuestionDTO(QuestionDomain questionDomain){
        this.id = questionDomain.getId();
        this.questionContents = questionDomain.getQuestionContents();
        this.questionType = questionDomain.getQuestionType();
    }

    public void AddAnswerOption(AnswerOptionDTO aop){
        this.answerOptionDTOList.add(aop);
    }

    @JsonIgnore
    private Long id;

    private String questionContents;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private List<AnswerOptionDTO> answerOptionDTOList = new ArrayList<>();


}
