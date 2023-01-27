package com.h154.saesumMBTI.Domain;

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

    @Enumerated(EnumType.ORDINAL)
    private QuestionType questionType;

    @OneToMany(mappedBy = "questionDomain")
    private List<AnswerOptionDomain> answerOptionDomainList;

}
