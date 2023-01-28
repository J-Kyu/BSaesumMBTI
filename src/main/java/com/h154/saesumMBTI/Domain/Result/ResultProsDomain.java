package com.h154.saesumMBTI.Domain.Result;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ResultProsDomain {

    @Id
    @GeneratedValue
    @Column(name="resultPros_id")
    private Long id;


    private String prosContents;


}
