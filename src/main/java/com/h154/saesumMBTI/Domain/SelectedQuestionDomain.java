package com.h154.saesumMBTI.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SelectedQuestionDomain {
    @Id
    @GeneratedValue
    @Column(name="selectedQuestion_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "question_id")
    private QuestionDomain questionDomain;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyDomain surveyDomain;

}
