package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.*;
import com.h154.saesumMBTI.Enum.ResultType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultDTO {

    public ResultDTO(){};

    public ResultDTO(ResultDomain resultDomain){
        this.id = resultDomain.getId();
        this.resultType = resultDomain.getResultType();
    }

    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ResultType resultType;

    private String resultContents;

    private List<ResultProsDTO> resultProsDTOList = new ArrayList<>();

//    private List<ProsSituationDTO> prosSituationDTOList = new ArrayList<>();

    private List<ResultConsDTO> resultConsDTOList = new ArrayList<>();

//    private List<ConsTipDTO> consTipDTOList = new ArrayList<>();

    private List<HashTagDTO> hashTagDTOList = new ArrayList<>();
}
