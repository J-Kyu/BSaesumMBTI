package com.h154.saesumMBTI.Response;


import com.h154.saesumMBTI.DTO.LetterDTO;
import com.h154.saesumMBTI.DTO.UserDTO;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Enum.LetterState;
import com.h154.saesumMBTI.Enum.LetterType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

public class OKResponseLetterDTO {


    @Schema(defaultValue = "200")
    private Integer code;

    @Schema(defaultValue = "OK")
    private HttpStatus httpStatus;
    private String message;
    private LetterDTO letterDTO;


    public OKResponseLetterDTO(){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
    }

    public OKResponseLetterDTO(String msg){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
    }

    public OKResponseLetterDTO(String msg, LetterDTO letterDTO){
        this.code = 200;
        this.httpStatus = HttpStatus.OK;
        this.message = msg;
        this.letterDTO = letterDTO;
    }

}
