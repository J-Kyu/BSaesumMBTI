package com.h154.saesumMBTI.Domain.Result;

import com.h154.saesumMBTI.Controller.Form.ConsTipForm;
import com.h154.saesumMBTI.Controller.Form.HashTagForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class HashTagDomain {

    public void SetDomainData(HashTagForm form){
        this.hashTagContents = form.getHashTagContents();
    }


    @Id @GeneratedValue
    @Column(name = "hashTag_domain")
    private Long id;

    @Column(length = 10)
    private String hashTagContents;
}
