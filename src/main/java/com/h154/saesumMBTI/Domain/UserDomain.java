package com.h154.saesumMBTI.Domain;

import com.h154.saesumMBTI.Controller.Form.UserForm;
import com.h154.saesumMBTI.Domain.Result.ResultRecordDomain;
import com.h154.saesumMBTI.Enum.OAuthTypeEnum;
import com.h154.saesumMBTI.Enum.UserRoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class UserDomain {

    public void SetDomainData(UserForm form) {
        // for creating user
        this.nickname = form.getNickname();
        this.accessToken = form.getAccessToken();
        this.oAuthType = form.getOauthType();
        this.uuid = form.getOauthType() + "_" + form.getUid();
        this.userRoleType = UserRoleType.USER;

        this.genDateTime = new Date();
    }


    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(length = 10)
    @NotNull
    private String nickname;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OAuthTypeEnum oAuthType;

    private String uuid;


    private String accessToken;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRoleType userRoleType;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date genDateTime;

    @OneToMany(mappedBy = "userDomain")
    private List<LetterDomain> letterDomainList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ResultRecordDomain> resultRecordDomainList;


}
