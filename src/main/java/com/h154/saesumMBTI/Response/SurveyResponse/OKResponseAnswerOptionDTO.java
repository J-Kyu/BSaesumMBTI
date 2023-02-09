package com.h154.saesumMBTI.Response.SurveyResponse;


import com.h154.saesumMBTI.DTO.Survey.AnswerOptionDTO;
import com.h154.saesumMBTI.DTO.Survey.QuestionDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class OKResponseAnswerOptionDTO {

    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private List<AnswerOptionDTO> answerOptionDTOList;


    public OKResponseAnswerOptionDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseAnswerOptionDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseAnswerOptionDTO(String msg, AnswerOptionDTO answerOptionDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.answerOptionDTOList = Arrays.asList(answerOptionDTO);
    }

    public OKResponseAnswerOptionDTO(String msg, List<AnswerOptionDTO> answerOptionDTOList){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.answerOptionDTOList = answerOptionDTOList;
    }


}
