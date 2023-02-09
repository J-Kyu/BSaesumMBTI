package com.h154.saesumMBTI.Controller.Form;

import com.h154.saesumMBTI.Enum.OAuthTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    @NotEmpty
    @Schema(description = "닉네임", defaultValue = "nickname")
    private String nickname;

    @NotEmpty
    @Schema(description = "UID", defaultValue = "23945921")
    private String uid;

    @NotEmpty
    @Schema(description = "Access Token", defaultValue = "ABCD12345")
    private String accessToken;

    @Enumerated(EnumType.STRING)
    @Schema(description = "OAuthType", defaultValue = "KAKAO")
    private OAuthTypeEnum oAuthType;
}
