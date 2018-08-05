package io.github.mezk.spring.boot.starters.api.versioning.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("api.versioning")
public class ApiVersioningProperties {

    private String apiPathPrefix;
    private String defaultVersion;

    public String getApiPathPrefix() {
        return apiPathPrefix;
    }

    public void setApiPathPrefix(String apiPathPrefix) {
        this.apiPathPrefix = apiPathPrefix;
    }
}
