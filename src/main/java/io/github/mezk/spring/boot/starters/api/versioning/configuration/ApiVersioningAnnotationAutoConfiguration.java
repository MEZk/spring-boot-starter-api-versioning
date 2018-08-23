////////////////////////////////////////////////////////////////////////////////////////////////////
// Spring Boot Api Versioning Starter: allows rest api versioning with Spring MVC REST controllers.
// Copyright (C) 2018 the original author or authors.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
////////////////////////////////////////////////////////////////////////////////////////////////////

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
