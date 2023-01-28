package com.h154.saesumMBTI.Domain.Result;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class HashTagDomain {

    @Id @GeneratedValue
    @Column(name = "hashTag_domain")
    private Long id;

    @Column(length = 10)
    private String hashTagContents;
}
