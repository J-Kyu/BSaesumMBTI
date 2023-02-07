package com.h154.saesumMBTI.Domain.Result;


import com.h154.saesumMBTI.Controller.Form.ResultConsForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ResultConsDomain {

    public void SetDomainData(ResultConsForm form){
        this.consContents = form.getConsContents();
    }

    @Id
    @GeneratedValue
    @Column(name="resultCons_id")
    private Long id;

    private String consContents;

    @OneToMany(mappedBy = "resultConsDomain", cascade = CascadeType.REMOVE)
    private List<SelectedTipDomain> selectedTipDomainList = new ArrayList<>();
}
