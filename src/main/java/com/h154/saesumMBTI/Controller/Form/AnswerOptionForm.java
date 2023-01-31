package com.h154.saesumMBTI.Controller.Form;

import com.h154.saesumMBTI.Enum.AnswerType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class AnswerOptionForm {

    private String answerContents;

    private int weight;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;
}
