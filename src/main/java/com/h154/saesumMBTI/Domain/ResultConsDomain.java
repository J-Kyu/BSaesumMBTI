package com.h154.saesumMBTI.Domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ResultConsDomain {

    @Id
    @GeneratedValue
    @Column(name="resultCons_id")
    private Long id;

    private String consContents;

}
