package com.h154.saesumMBTI.Controller.Body;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyBody {
    private String surveyTitle;
    private List<Long> selectedQuestionsId;
}
