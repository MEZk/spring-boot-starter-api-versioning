package io.github.mezk.spring.boot.starters.api.versioning.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api.versioning")
public class ApiVersioningProperties {

    private String apiPathVersionPrefix;
    private String defaultVersion;

    public String getApiPathVersionPrefix() {
        return apiPathVersionPrefix;
    }

    public void setApiPathVersionPrefix(String apiPathVersionPrefix) {
        this.apiPathVersionPrefix = apiPathVersionPrefix;
    }
}
