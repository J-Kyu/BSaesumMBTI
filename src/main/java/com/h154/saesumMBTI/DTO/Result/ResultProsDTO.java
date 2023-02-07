package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.ResultProsDomain;
import com.h154.saesumMBTI.Domain.Result.SelectedSituationDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultProsDTO {

    public ResultProsDTO(){};

    public ResultProsDTO(ResultProsDomain resultProsDomain){
        this.id = resultProsDomain.getId();
        this.prosContents = resultProsDomain.getProsContents();

        // prosSituationDTOList
        for ( SelectedSituationDomain ssd: resultProsDomain.getSelectedSituationDomainList()) {
            this.prosSituationDTOList.add(new ProsSituationDTO(ssd.getProsSituationDomain()));
        }
    }
    private Long id;

    private String prosContents;

    private List<ProsSituationDTO> prosSituationDTOList = new ArrayList<>();


}
