package com.h154.saesumMBTI.Domain;


import com.h154.saesumMBTI.Enum.ResultRecordState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ResultRecordDomain {

    @Id
    @GeneratedValue
    @Column(name="resultRecord_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDomain userDomain;

    @Enumerated(EnumType.ORDINAL)
    private ResultRecordState resultRecordState;

    @OneToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyDomain surveyDomain;

    @OneToMany(mappedBy = "resultRecordDomain")
    private List<SelectedAnswerDomain> selectedAnswerDomainList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "resultRecord_id",nullable = true)
    private ResultDomain resultDomain;
}
