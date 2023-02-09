package com.h154.saesumMBTI.Controller.Form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultProsForm {
    @Schema(description = "장점")
    private String prosContents;
}
