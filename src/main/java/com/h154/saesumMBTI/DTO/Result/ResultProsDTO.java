package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.ResultProsDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultProsDTO {

    public ResultProsDTO(){};

    public ResultProsDTO(ResultProsDomain resultProsDomain){
        this.id = resultProsDomain.getId();
        this.prosContents = resultProsDomain.getProsContents();
    }

    private Long id;

    private String prosContents;

}
