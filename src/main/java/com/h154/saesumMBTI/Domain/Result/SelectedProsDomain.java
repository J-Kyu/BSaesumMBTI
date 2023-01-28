package com.h154.saesumMBTI.Domain.Result;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SelectedProsDomain {

    @Id
    @GeneratedValue
    @Column(name="selectedPros_id")
    private Long id;


    @OneToOne
    @JoinColumn(name="resultCons_id")
    private ResultConsDomain resultConsDomain;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private ResultDomain resultDomain;

}
