package com.h154.saesumMBTI.Domain.Result;

import com.h154.saesumMBTI.Enum.MBTIType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SelectedMBTIDomain {

    @Id
    @GeneratedValue
    @Column(name="selected_MBTI_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MBTIType mbtiType;

    @ManyToOne
    @JoinColumn(name = "result_id")
    private ResultDomain resultDomain;
}
