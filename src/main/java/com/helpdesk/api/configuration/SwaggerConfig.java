package com.helpdesk.api.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI springHelpDeskOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HelpDesk API")
                        .description("HelpDesk API - Mercado Livre")
                        .version("1.0")
                        .license(new License()
                                .name("Mercado Livre")
                                .url("https://github.com/helpdesk/helpdesk"))
                        .contact(new Contact()
                                .name("HelpDesk")
                                .url("https://helpdesk.helpdesk.com")
                                .email("helpdesk@helpdesk.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub")
                        .url("https://helpdesk.github.io/helpdesk"));
    }
}
