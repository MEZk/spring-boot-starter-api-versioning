package io.github.mezk.spring.boot.starters.api.versioning.annotations;

/**
 * The constatnts of this enum set the api versioning mode.
 * The current implementation supports only {@link VersioningMode#ANNOTATION} mode, which allows
 * to use {@link ApiVersion} annotation to specify the api version.
 *
 * @author Andrei Selkin
 */
public enum VersioningMode {
    /**
     * Allows to specify api version via {@link ApiVersion} annotation.
     */
    ANNOTATION
}
