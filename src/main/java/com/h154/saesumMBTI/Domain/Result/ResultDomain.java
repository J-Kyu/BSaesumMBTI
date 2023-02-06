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

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    private String resultContents;

    @OneToMany(mappedBy = "resultDomain", cascade = CascadeType.REMOVE)
    private List<SelectedProsDomain> selectedProsDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain", cascade = CascadeType.REMOVE)
    private List<SelectedSituationDomain> selectedSituationDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain", cascade = CascadeType.REMOVE)
    private List<SelectedConsDomain> selectedConsDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain", cascade = CascadeType.REMOVE)
    private List<SelectedTipDomain> selectedTipDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "resultDomain", cascade = CascadeType.REMOVE)
    private List<SelectedHashTagDomain> selectedHashTagDomainList = new ArrayList<>();

}
