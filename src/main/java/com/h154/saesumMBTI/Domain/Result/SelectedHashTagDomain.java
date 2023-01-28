package com.h154.saesumMBTI.Domain.Result;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class SelectedHashTagDomain {

    @Id @GeneratedValue
    @Column(name = "selectedHashTag_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "hashTag_id")
    private HashTagDomain hashTagDomain;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private ResultDomain resultDomain;
}
