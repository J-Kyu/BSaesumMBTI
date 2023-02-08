package com.h154.saesumMBTI.DTO.Result;


import com.h154.saesumMBTI.DTO.Survey.AnswerOptionDTO;
import com.h154.saesumMBTI.DTO.Survey.SurveyDTO;
import com.h154.saesumMBTI.Domain.Result.ResultRecordDomain;
import com.h154.saesumMBTI.Domain.Survey.SelectedAnswerDomain;
import com.h154.saesumMBTI.Enum.ResultRecordState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultRecordDTO {
    public ResultRecordDTO(){}

    public ResultRecordDTO(ResultRecordDomain resultRecordDomain){
        this.id = resultRecordDomain.getId();
        this.resultRecordState = resultRecordDomain.getResultRecordState();

        this.surveyDTO = new SurveyDTO(resultRecordDomain.getSurveyDomain());
        this.resultDTO = new ResultDTO(resultRecordDomain.getResultDomain());

        for (SelectedAnswerDomain sad: resultRecordDomain.getSelectedAnswerDomainList() ) {
            this.answerOptionDTOList.add(new AnswerOptionDTO(sad.getAnswerOptionDomain()));
        }
    }

    private Long id;

    @Enumerated(EnumType.STRING)
    private ResultRecordState resultRecordState;

    private SurveyDTO surveyDTO;

    private List<AnswerOptionDTO> answerOptionDTOList = new ArrayList<>();

    private ResultDTO resultDTO;

}
