package io.github.mezk.spring.boot.starters.api.versioning.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@EnableConfigurationProperties(ApiVersioningProperties.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class ApiVersioningAnnotationAutoConfiguration {

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(
        ApiVersioningProperties properties
    ) {
        return new ApiVersionRequestMappingHandlerMapping(properties.getApiPathPrefix());
    }

}
