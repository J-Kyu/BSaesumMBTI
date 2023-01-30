package com.h154.saesumMBTI.Controller.Form;

import com.h154.saesumMBTI.Enum.OAuthTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserForm {

    private String nickname;

    private String uid;

    private String accessToken;

    @Enumerated(EnumType.STRING)
    private OAuthTypeEnum oAuthType;
}
