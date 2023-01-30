package com.h154.saesumMBTI.Domain.Survey;

import com.h154.saesumMBTI.Enum.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class QuestionDomain {

    @Id
    @GeneratedValue
    @Column(name="question_id")
    private Long id;

    private String questionContents;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(mappedBy = "questionDomain")
    private List<AnswerOptionDomain> answerOptionDomainList;

}
