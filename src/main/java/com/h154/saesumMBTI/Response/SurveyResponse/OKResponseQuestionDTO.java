package com.h154.saesumMBTI.Response.SurveyResponse;

import com.h154.saesumMBTI.DTO.Result.HashTagDTO;
import com.h154.saesumMBTI.DTO.Survey.QuestionDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class OKResponseQuestionDTO {
    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private List<QuestionDTO> questionDTOList;


    public OKResponseQuestionDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseQuestionDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseQuestionDTO(String msg, QuestionDTO questionDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.questionDTOList = Arrays.asList(questionDTO);
    }

    public OKResponseQuestionDTO(String msg, List<QuestionDTO> questionDTOList){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.questionDTOList = questionDTOList;
    }

}
