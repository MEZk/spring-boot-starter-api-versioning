package io.github.mezk.spring.boot.starters.api.versioning.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Method;
import java.util.Collections;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import io.github.mezk.spring.boot.starters.api.versioning.annotations.ApiVersion;

public class ApiVersionRequestMappingHandlerMappingTest {

    private static final String API_PATH_VERSION_PREFIX = "v";
    private static final String ACTUAL_API_PATH_IS_NOT_CORRECT_MSG =
        "Actual API path is not correct";

    private ApiVersionRequestMappingHandlerMapping handlerMapping;

    @Test
    public void getMappingForMethodWhenRequestMappingInfoIsNull() {
        // ARRANGE
        handlerMapping = new ApiVersionRequestMappingHandlerMapping(API_PATH_VERSION_PREFIX);

        final Method method = Object.class.getMethods()[0];

        // ACT
        final RequestMappingInfo mappingForMethod = handlerMapping.getMappingForMethod(
            method, Object.class);

        // ASSERT
        assertNull("Mapping for method must be null", mappingForMethod);
    }

    @Test
    public void getMappingForMethodWhenApiVersionAnnotationIsOnClass()
            throws NoSuchMethodException {
        // ARRANGE
        handlerMapping = new ApiVersionRequestMappingHandlerMapping(API_PATH_VERSION_PREFIX);

        final Method method = ClassWithApiVersionAnnotationOnClass.class.getMethod("bar");

        // ACT
        final RequestMappingInfo mappingForMethod = handlerMapping.getMappingForMethod(
            method, ClassWithApiVersionAnnotationOnClass.class);

        // ASSERT
        assertEquals(
            ACTUAL_API_PATH_IS_NOT_CORRECT_MSG,
            Collections.singleton("/" + API_PATH_VERSION_PREFIX + "1"),
            mappingForMethod.getPatternsCondition().getPatterns()
        );
    }

    @Test
    public void getMappingForMethodWhenApiVersionAnnotationIsAbsent() throws NoSuchMethodException {
        // ARRANGE
        handlerMapping = new ApiVersionRequestMappingHandlerMapping(API_PATH_VERSION_PREFIX);

        final Method method = ClassWithoutApiVersionAnnotationOnClass.class.getMethod("foo");

        // ACT
        final RequestMappingInfo mappingForMethod = handlerMapping.getMappingForMethod(
            method, ClassWithoutApiVersionAnnotationOnClass.class);

        // ASSERT
        assertEquals(
            ACTUAL_API_PATH_IS_NOT_CORRECT_MSG,
            Collections.singleton("/test"),
            mappingForMethod.getPatternsCondition().getPatterns()
        );
    }

    @ApiVersion("1")
    @RequestMapping
    private class ClassWithApiVersionAnnotationOnClass {
        @RequestMapping
        public void bar() { }
    }

    @RequestMapping("/test")
    private class ClassWithoutApiVersionAnnotationOnClass {

        @RequestMapping
        public void foo() { }

    }
}
