package com.h154.saesumMBTI.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;

@Getter
@Setter
public class InternalServerErrorResponse{

    @Schema(defaultValue = "500")
    private Integer code;

    @Schema(defaultValue = "Internal Server Error")
    private HttpStatus httpStatus;

    private String message;

    public InternalServerErrorResponse(){
        this.code = 500;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public InternalServerErrorResponse(String msg){
        this.code = 500;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = msg;
    }

}
