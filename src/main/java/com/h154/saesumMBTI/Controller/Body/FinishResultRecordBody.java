package com.h154.saesumMBTI.Controller.Body;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FinishResultRecordBody {
    private List<Long> selectedAnswerOptions;
}
