package com.h154.saesumMBTI.Response.ResultResponse;

import com.h154.saesumMBTI.DTO.Result.HashTagDTO;
import com.h154.saesumMBTI.DTO.Result.ResultConsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class OKResponseHashTagDTO {

    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private HashTagDTO hashTagDTO;


    public OKResponseHashTagDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseHashTagDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseHashTagDTO(String msg, HashTagDTO hashTagDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.hashTagDTO = hashTagDTO;
    }

}
