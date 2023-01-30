package com.h154.saesumMBTI.Domain;

import com.h154.saesumMBTI.Controller.Form.UserForm;
import com.h154.saesumMBTI.Domain.Result.ResultRecordDomain;
import com.h154.saesumMBTI.Enum.OAuthTypeEnum;
import com.h154.saesumMBTI.Enum.UserRoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class UserDomain {

    public UserDomain(){}

    public UserDomain(UserForm form){
        this.nickname = form.getNickname();
        this.accessToken = form.getAccessToken();
        this.oAuthType = form.getOAuthType();
        this.uuid = form.getOAuthType()+"_"+form.getUid();
    }


    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(length = 10)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private OAuthTypeEnum oAuthType;

    private String uuid;

    private String accessToken;

    @Enumerated(EnumType.STRING)
    private UserRoleType userRoleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date genDateTime;

    @OneToMany(mappedBy = "userDomain")
    private List<LetterDomain> letterDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "userDomain")
    private List<ResultRecordDomain> resultRecordDomainList;


}
