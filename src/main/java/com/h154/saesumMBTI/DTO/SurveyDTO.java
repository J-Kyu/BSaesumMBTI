package com.h154.saesumMBTI.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h154.saesumMBTI.Domain.Survey.SelectedQuestionDomain;
import com.h154.saesumMBTI.Domain.Survey.SurveyDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SurveyDTO {

    public SurveyDTO(){}

    public  SurveyDTO(SurveyDomain surveyDomain){
        this.id = surveyDomain.getId();
        this.title = surveyDomain.getTitle();

        List<SelectedQuestionDomain> selectedQuestionDomainList = surveyDomain.getSelectedQuestionDomainList();
        for (SelectedQuestionDomain sqd: selectedQuestionDomainList ) {
            this.questionDTOList.add(new QuestionDTO(sqd.getQuestionDomain()));
        }

    }

    @JsonIgnore
    private Long id;

    private String title;

    private List<QuestionDTO> questionDTOList = new ArrayList<>();

}
