package com.h154.saesumMBTI.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Enum.OAuthTypeEnum;
import com.h154.saesumMBTI.Enum.UserRoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class UserDTO {

    public UserDTO(){}

    public UserDTO(UserDomain userDomain){
        this.id = userDomain.getId();
        this.nickname = userDomain.getNickname();
        this.uuid = userDomain.getUuid();
        this.oAuthType = userDomain.getOAuthType();
        this.genDateTime = userDomain.getGenDateTime();
        this.accessToken = userDomain.getAccessToken();
        this.userRoleType = userDomain.getUserRoleType();
    }

    @JsonIgnore
    private Long id;

    private String nickname;

    private OAuthTypeEnum oAuthType;

    private String uuid;

    private String accessToken;

    private UserRoleType userRoleType;

    private Date genDateTime;


}
