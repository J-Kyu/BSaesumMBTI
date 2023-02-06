package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.ConsTipDomain;
import com.h154.saesumMBTI.Domain.Result.ResultConsDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsTipDTO {

    public ConsTipDTO(){};

    public ConsTipDTO(ConsTipDomain consTipDomain){
        this.id = consTipDomain.getId();
        this.tipContents = consTipDomain.getTipContents();
    }


    private Long id;

    private String tipContents;
}
