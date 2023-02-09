package com.h154.saesumMBTI.Response.ResultResponse;

import com.h154.saesumMBTI.DTO.Result.ProsSituationDTO;
import com.h154.saesumMBTI.DTO.Result.ResultDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class OKResponseSituationDTO {

    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private ProsSituationDTO prosSituationDTO;


    public OKResponseSituationDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseSituationDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseSituationDTO(String msg, ProsSituationDTO prosSituationDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.prosSituationDTO = prosSituationDTO;
    }
}
