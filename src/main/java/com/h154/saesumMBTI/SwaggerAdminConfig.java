package com.h154.saesumMBTI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@OpenAPIDefinition(
        info = @Info(title = "새섬미 Admin API 명세서",
                description = "H154 새섬미 Admin 서비스 API 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerAdminConfig {

    @Bean
    public GroupedOpenApi saesumAdminAPI() {
        String[] paths = {"/v1/admin/api/**"};

        return GroupedOpenApi.builder()
                .group("새섬미 Admin API v1")
                .pathsToMatch(paths)
                .build();
    }
}
