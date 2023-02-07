package com.h154.saesumMBTI.Domain.Result;


import com.h154.saesumMBTI.Controller.Form.ConsTipForm;
import com.h154.saesumMBTI.Controller.Form.ResultConsForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ConsTipDomain {

    public void SetDomainData(ConsTipForm form){
        this.tipContents = form.getTipContents();
    }

    @Id
    @GeneratedValue
    @Column(name="consTip_id")
    private Long id;

    private String tipContents;
}
