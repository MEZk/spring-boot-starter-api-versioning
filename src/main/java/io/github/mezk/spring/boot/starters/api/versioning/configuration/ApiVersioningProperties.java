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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The following class contains api versioning configuration properties.
 *
 * @author Andrei Selkin
 */
@ConfigurationProperties("api.versioning")
public class ApiVersioningProperties {

    private String apiPathVersionPrefix;
    private String defaultVersion;

    /**
     * Returns api path version prefix.
     * This prefix will be inserted before api version.
     * For example, if the value of the property is set to 'v' and the api version is set to '1',
     * the final path will be '/v1'.
     * @return api path version prefix.
     */
    public String getApiPathVersionPrefix() {
        return apiPathVersionPrefix;
    }

    /**
     * Sets api path version prefix.
     * This prefix will be inserted before api version.
     * For example, if the value of the property is set to 'v' and the api version is set to '1',
     * the final path will be '/v1'.
     * @param apiPathVersionPrefix api version prefix value.
     */
    public void setApiPathVersionPrefix(String apiPathVersionPrefix) {
        this.apiPathVersionPrefix = apiPathVersionPrefix;
    }

    /**
     * Returns default api version.
     * @return default api version.
     */
    public String getDefaultVersion() {
        return defaultVersion;
    }

    /**
     * Sets default api version.
     * @param defaultVersion defalt api version.
     */
    public void setDefaultVersion(String defaultVersion) {
        this.defaultVersion = defaultVersion;
    }
}
