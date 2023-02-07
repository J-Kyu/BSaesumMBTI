package com.h154.saesumMBTI.Domain.Result;

import com.h154.saesumMBTI.Controller.Form.ResultProsForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ResultProsDomain {

    public void SetDomainData(ResultProsForm form){
        this.prosContents = form.getProsContents();
    }

    @Id
    @GeneratedValue
    @Column(name="resultPros_id")
    private Long id;

    private String prosContents;

    @OneToMany(mappedBy = "resultProsDomain", cascade = CascadeType.REMOVE)
    private List<SelectedSituationDomain> selectedSituationDomainList = new ArrayList<>();

}
