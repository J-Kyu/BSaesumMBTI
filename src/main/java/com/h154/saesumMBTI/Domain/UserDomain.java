package com.h154.saesumMBTI.Domain;

import com.h154.saesumMBTI.Domain.Result.ResultRecordDomain;
import com.h154.saesumMBTI.Enum.OAuthTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class UserDomain {


    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(length = 10)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private OAuthTypeEnum oAuthType;

    private String uuid;

    private String accessToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date genDateTime;

    @OneToOne
    @JoinColumn(name = "letter_id")
    private LetterDomain letterDomain;

    @OneToMany(mappedBy = "userDomain")
    private List<ResultRecordDomain> resultRecordDomainList;


}
