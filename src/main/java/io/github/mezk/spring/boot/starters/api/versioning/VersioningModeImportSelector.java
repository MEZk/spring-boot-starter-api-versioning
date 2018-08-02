package io.github.mezk.spring.boot.starters.api.versioning;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class VersioningModeImportSelector implements ImportSelector {

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
