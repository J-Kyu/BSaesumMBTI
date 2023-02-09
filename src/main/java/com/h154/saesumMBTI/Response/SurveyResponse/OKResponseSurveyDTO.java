package com.h154.saesumMBTI.Response.SurveyResponse;

import com.h154.saesumMBTI.DTO.Survey.AnswerOptionDTO;
import com.h154.saesumMBTI.DTO.Survey.SurveyDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class OKResponseSurveyDTO {
    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private SurveyDTO surveyDTO;


    public OKResponseSurveyDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseSurveyDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseSurveyDTO(String msg, SurveyDTO surveyDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.surveyDTO = surveyDTO;
    }

}
