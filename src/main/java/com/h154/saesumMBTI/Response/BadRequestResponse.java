package com.h154.saesumMBTI.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BadRequestResponse {

    @Schema(defaultValue = "400")
    private Integer code;

    @Schema(defaultValue = "BAD REQUEST")
    private HttpStatus httpStatus;
    private String message;

    public BadRequestResponse(){
        this.code = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BadRequestResponse(String msg){
        this.code = 400;
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = msg;
    }

}
