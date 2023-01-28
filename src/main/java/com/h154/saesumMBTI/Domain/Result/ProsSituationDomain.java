package com.h154.saesumMBTI.Domain.Result;

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

    @Id
    @GeneratedValue
    @Column(name="prosSituation_id")
    private Long id;

    private String situationContents;
}
