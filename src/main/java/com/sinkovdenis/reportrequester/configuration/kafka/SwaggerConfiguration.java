package com.sinkovdenis.reportrequester.configuration.kafka;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI marketplaceRequesterOpenAPI(ServletContext servletContext) {
        return new OpenAPI()
                .info(new Info().title("Report requester API"))
                .components(new Components())
                .servers(Collections.singletonList(new Server().url(servletContext.getContextPath())));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("report")
                .packagesToScan("com.sinkovdenis.reportrequester.controller")
                .build();
    }
}
