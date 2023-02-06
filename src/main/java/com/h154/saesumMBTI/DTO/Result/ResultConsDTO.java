package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.ResultConsDomain;
import com.h154.saesumMBTI.Domain.Result.ResultProsDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultConsDTO {
    public ResultConsDTO(){};

    public ResultConsDTO(ResultConsDomain resultConsDomain){
        this.id = resultConsDomain.getId();
        this.consContents = resultConsDomain.getConsContents();
    }

    private Long id;

    private String consContents;
}
