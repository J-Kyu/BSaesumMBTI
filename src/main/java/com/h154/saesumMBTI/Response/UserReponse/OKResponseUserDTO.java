package com.h154.saesumMBTI.Response.UserReponse;

import com.h154.saesumMBTI.DTO.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class OKResponseUserDTO{


    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private UserDTO userDTO;


    public OKResponseUserDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseUserDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseUserDTO(String msg, UserDTO userDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.userDTO = userDTO;
    }

}
