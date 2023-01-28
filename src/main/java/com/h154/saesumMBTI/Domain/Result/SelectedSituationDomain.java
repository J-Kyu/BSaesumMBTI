package com.h154.saesumMBTI.Domain.Result;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class SelectedSituationDomain {

    @Id @GeneratedValue
    @Column(name = "selectedSituation_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "prosSituation_id")
    private ProsSituationDomain prosSituationDomain;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private ResultDomain resultDomain;
}
