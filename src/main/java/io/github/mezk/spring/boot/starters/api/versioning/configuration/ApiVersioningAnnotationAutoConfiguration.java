package io.github.mezk.spring.boot.starters.api.versioning.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * {@code @Configuration} class that registers the Spring infrastructure beans necessary
 * to enable api versioning feature.
 *
 * @author Andrei Selkin
 * @see io.github.mezk.spring.boot.starters.api.versioning.annotations.EnableApiVersioning
 * @see io.github.mezk.spring.boot.starters.api.versioning.annotations.VersioningModeImportSelector
 */
@Configuration
@EnableConfigurationProperties(ApiVersioningProperties.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class ApiVersioningAnnotationAutoConfiguration {

    /**
     * Creates {@link RequestMappingHandlerMapping} bean.
     * @param properties {@link ApiVersioningProperties}.
     * @return {@link RequestMappingHandlerMapping} instance.
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(
        ApiVersioningProperties properties
    ) {
        return new ApiVersionRequestMappingHandlerMapping(properties.getApiPathVersionPrefix());
    }

}
