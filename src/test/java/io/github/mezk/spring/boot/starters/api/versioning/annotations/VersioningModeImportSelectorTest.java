package io.github.mezk.spring.boot.starters.api.versioning.annotations;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.type.AnnotationMetadata;

import io.github.mezk.spring.boot.starters.api.versioning.configuration.ApiVersioningAnnotationAutoConfiguration;

public class VersioningModeImportSelectorTest {

    private static final String MODE_ANNOTATION_FIELD_NAME = "mode";

    private VersioningModeImportSelector selector;

    @Before
    public void setUp() throws Exception {
        selector = new VersioningModeImportSelector();
    }

    @Test
    public void selectImportsWhenVersionModeIsAnnotation() {
        // ARRANGE
        final Map<String, Object> annotationAttributes = new HashMap<>(1);
        annotationAttributes.put(MODE_ANNOTATION_FIELD_NAME, VersioningMode.ANNOTATION);

        final AnnotationMetadata importingClassMetadata = mock(AnnotationMetadata.class);
        when(importingClassMetadata.getAnnotationAttributes(
            eq(EnableApiVersioning.class.getName()), eq(false))
        ).thenReturn(annotationAttributes);

        // ACT
        final String[] imports = selector.selectImports(importingClassMetadata);

        // ASSERT
        assertArrayEquals(
            "Imports selected by import selector are not correct",
            new String[]{ApiVersioningAnnotationAutoConfiguration.class.getName()},
            imports
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectImportsThrowsIllegalArgumentException() {
        // ARRANGE
        final Map<String, Object> annotationAttributes = new HashMap<>(1);
        annotationAttributes.put(MODE_ANNOTATION_FIELD_NAME, "UNKNOWN");

        final AnnotationMetadata importingClassMetadata = mock(AnnotationMetadata.class);
        when(importingClassMetadata.getAnnotationAttributes(
            eq(EnableApiVersioning.class.getName()), eq(false))
        ).thenReturn(annotationAttributes);

        // ACT
        selector.selectImports(importingClassMetadata);
    }

}
