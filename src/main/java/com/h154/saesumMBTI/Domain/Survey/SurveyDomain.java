package com.h154.saesumMBTI.Domain.Survey;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SurveyDomain {
    @Id
    @GeneratedValue
    @Column(name="survey_id")
    private Long id;

    @Column(length = 50)
    private String title;

    @OneToMany(mappedBy = "surveyDomain", cascade = CascadeType.REMOVE)
    private List<SelectedQuestionDomain> selectedQuestionDomainList = new ArrayList<>();

    public void addQuestion(SelectedQuestionDomain selectedQuestionDomain){
        this.selectedQuestionDomainList.add(selectedQuestionDomain);
    }

}
