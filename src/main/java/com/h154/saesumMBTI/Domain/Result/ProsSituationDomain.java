package com.h154.saesumMBTI.Domain.Result;

import com.h154.saesumMBTI.Controller.Form.ProsSituationForm;
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
public class ProsSituationDomain {

    public void SetDomainData(ProsSituationForm form){
        this.situationContents = form.getSituationContents();
    }


    @Id
    @GeneratedValue
    @Column(name="prosSituation_id")
    private Long id;

    private String situationContents;
}
