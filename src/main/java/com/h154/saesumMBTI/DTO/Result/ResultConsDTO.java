package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.ResultConsDomain;
import com.h154.saesumMBTI.Domain.Result.ResultProsDomain;
import com.h154.saesumMBTI.Domain.Result.SelectedTipDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultConsDTO {
    public ResultConsDTO(){};

    public ResultConsDTO(ResultConsDomain resultConsDomain){
        this.id = resultConsDomain.getId();
        this.consContents = resultConsDomain.getConsContents();

        for ( SelectedTipDomain std: resultConsDomain.getSelectedTipDomainList()) {
            this.consTipDTOList.add(new ConsTipDTO(std.getConsTipDomain()));
        }
    }

    private Long id;

    private String consContents;

    private List<ConsTipDTO> consTipDTOList = new ArrayList<>();

}
