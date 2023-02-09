package com.h154.saesumMBTI.Controller.Form;

import com.h154.saesumMBTI.Enum.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class QuestionForm {

    @NotEmpty
    private String questionContents;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
}
