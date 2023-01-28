package com.h154.saesumMBTI.Domain.Result;


import com.h154.saesumMBTI.Enum.ResultType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ResultDomain {

    @Id
    @GeneratedValue
    @Column(name="result_id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private ResultType resultType;

    private String resultContents;

    @OneToMany(mappedBy = "resultDomain")
    private List<SelectedProsDomain> selectedProsDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain")
    private List<SelectedSituationDomain> selectedSituationDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain")
    private List<SelectedConsDomain> selectedConsDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain")
    private List<SelectedTipDomain> selectedTipDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain")
    private List<SelectedHashTagDomain> selectedHashTagDomainList = new ArrayList<>();

}
