package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.ProsSituationDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProsSituationDTO {

    public ProsSituationDTO(){}

    public ProsSituationDTO(ProsSituationDomain prosSituationDomain){
        this.id = prosSituationDomain.getId();
        this.situationContents = prosSituationDomain.getSituationContents();
    }


    private Long id;

    private String situationContents;
}
