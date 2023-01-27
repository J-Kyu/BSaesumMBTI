package com.h154.saesumMBTI.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SelectedConsDomain {

    @Id
    @GeneratedValue
    @Column(name="selectedCons_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="resultPros_id")
    private ResultProsDomain resultProsDomain;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private ResultDomain resultDomain;


}
