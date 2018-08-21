package io.github.mezk.spring.boot.starters.api.versioning.annotations;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import io.github.mezk.spring.boot.starters.api.versioning.configuration.ApiVersioningAnnotationAutoConfiguration;

/**
 * Selects which api versioning autoconfiguration will be ebanled based
 * on the value of {@link EnableApiVersioning#mode} on the importing {@code @Configuration} class.
 *
 * @author Andrei Selkin
 * @see EnableApiVersioning
 * @see ApiVersioningAnnotationAutoConfiguration
 */
public class VersioningModeImportSelector implements ImportSelector {

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        final AnnotationAttributes attributes = AnnotationAttributes.fromMap(
            importingClassMetadata.getAnnotationAttributes(
                EnableApiVersioning.class.getName(), false));

        final Object modeFieldVal = attributes.get("mode");
        Assert.isInstanceOf(VersioningMode.class, modeFieldVal,
            "'mode' must have " + VersioningMode.class.getName() + " type");

        final VersioningMode mode = (VersioningMode) modeFieldVal;

        if (mode == VersioningMode.ANNOTATION) {
            return new String[]{ApiVersioningAnnotationAutoConfiguration.class.getName()};
        }

        throw new IllegalArgumentException("ivalid 'mode' value: " + mode);
    }
}
