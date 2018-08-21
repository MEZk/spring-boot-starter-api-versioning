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
