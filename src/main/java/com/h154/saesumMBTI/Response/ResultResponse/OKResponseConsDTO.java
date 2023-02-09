package com.h154.saesumMBTI.Response.ResultResponse;

import com.h154.saesumMBTI.DTO.Result.ResultConsDTO;
import com.h154.saesumMBTI.DTO.Result.ResultDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class OKResponseConsDTO {

    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private ResultConsDTO resultConsDTO;


    public OKResponseConsDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseConsDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseConsDTO(String msg, ResultConsDTO resultConsDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.resultConsDTO = resultConsDTO;
    }
}
