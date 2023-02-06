package com.h154.saesumMBTI.Domain.Result;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ResultProsDomain {

    @Id
    @GeneratedValue
    @Column(name="resultPros_id")
    private Long id;

    private String prosContents;

    @OneToMany(mappedBy = "resultProsDomain", cascade = CascadeType.REMOVE)
    private List<SelectedSituationDomain> selectedSituationDomainList = new ArrayList<>();

}
