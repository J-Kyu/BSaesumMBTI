package com.h154.saesumMBTI.Response.ResultResponse;

import com.h154.saesumMBTI.DTO.Result.ResultDTO;
import com.h154.saesumMBTI.DTO.Result.ResultProsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class OKResponseProsDTO {

    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;

    private ResultProsDTO resultProsDTO;


    public OKResponseProsDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseProsDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseProsDTO(String msg, ResultProsDTO resultProsDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.resultProsDTO = resultProsDTO;
    }
}
