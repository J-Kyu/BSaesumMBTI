package com.h154.saesumMBTI.Domain.Result;

import com.h154.saesumMBTI.Domain.Result.ConsTipDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class SelectedTipDomain {

    @Id @GeneratedValue
    @Column(name = "selectedTip_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "consTip_id")
    private ConsTipDomain consTipDomain;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private ResultDomain resultDomain;
}
