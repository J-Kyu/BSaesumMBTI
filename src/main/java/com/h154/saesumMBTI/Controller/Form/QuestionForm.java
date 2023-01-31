package com.h154.saesumMBTI.Controller.Form;

import com.h154.saesumMBTI.Enum.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class QuestionForm {

    private String questionContents;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
}
