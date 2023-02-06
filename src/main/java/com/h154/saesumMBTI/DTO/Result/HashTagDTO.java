package com.h154.saesumMBTI.DTO.Result;

import com.h154.saesumMBTI.Domain.Result.HashTagDomain;
import com.h154.saesumMBTI.Domain.Result.ResultProsDomain;

import javax.persistence.Column;

public class HashTagDTO {

    public HashTagDTO(){};

    public HashTagDTO(HashTagDomain hashTagDomain){
        this.id = hashTagDomain.getId();
        this.hashTagContents = hashTagDomain.getHashTagContents();
    }

    private Long id;

    private String hashTagContents;
}
