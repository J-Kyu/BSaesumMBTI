package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.ProsSituationDomain;

public class ProsSituationDTO {

    public ProsSituationDTO(){}

    public ProsSituationDTO(ProsSituationDomain prosSituationDomain){
        this.id = prosSituationDomain.getId();
        this.situationContents = prosSituationDomain.getSituationContents();
    }


    private Long id;

    private String situationContents;
}
