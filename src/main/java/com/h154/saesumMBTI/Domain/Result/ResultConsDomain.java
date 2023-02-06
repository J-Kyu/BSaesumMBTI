package com.h154.saesumMBTI.Domain.Result;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ResultConsDomain {

    @Id
    @GeneratedValue
    @Column(name="resultCons_id")
    private Long id;

    private String consContents;

    @OneToMany(mappedBy = "resultConsDomain", cascade = CascadeType.REMOVE)
    private List<SelectedTipDomain> selectedTipDomainList = new ArrayList<>();
}
