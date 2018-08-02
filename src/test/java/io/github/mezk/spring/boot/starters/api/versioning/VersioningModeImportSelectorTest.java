package io.github.mezk.spring.boot.starters.api.versioning;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class VersioningModeImportSelectorTest {

    private VersioningModeImportSelector selector;

    @Before
    public void setUp() throws Exception {
        selector = new VersioningModeImportSelector();
    }

    @Test
    public void selectImportsWhenVersionModeIsAnnotation() throws Exception {
        // ARRANGE
        final Map<String, Object> annotationAttributes = new HashMap<>(1);
        annotationAttributes.put("mode", VersioningMode.ANNOTATION);

        final AnnotationMetadata importingClassMetadata = mock(AnnotationMetadata.class);
        when(importingClassMetadata.getAnnotationAttributes(
            eq(EnableApiVersioning.class.getName()), eq(false))
        ).thenReturn(annotationAttributes);


        // ACT
        final String[] imports = selector.selectImports(importingClassMetadata);

        // ASSERT
        assertArrayEquals(
            new String[]{ApiVersioningAnnotationAutoConfiguration.class.getName()},
            imports
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectImportsThrowsIllegalArgumentException() throws Exception {
        // ARRANGE
        final Map<String, Object> annotationAttributes = new HashMap<>(1);
        annotationAttributes.put("mode", "UNKNOWN");

        final AnnotationMetadata importingClassMetadata = mock(AnnotationMetadata.class);
        when(importingClassMetadata.getAnnotationAttributes(
            eq(EnableApiVersioning.class.getName()), eq(false))
        ).thenReturn(annotationAttributes);


        // ACT
        selector.selectImports(importingClassMetadata);
    }

}