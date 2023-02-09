package com.h154.saesumMBTI.Response.ResultResponse;

import com.h154.saesumMBTI.DTO.Result.ConsTipDTO;
import com.h154.saesumMBTI.DTO.Result.ProsSituationDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class OKResponseTipDTO {

    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private ConsTipDTO consTipDTO;


    public OKResponseTipDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseTipDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseTipDTO(String msg, ConsTipDTO consTipDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.consTipDTO = consTipDTO;
    }
}
