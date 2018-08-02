package io.github.mezk.spring.boot.starters.api.versioning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
import java.util.Collections;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

public class ApiVersionRequestMappingHandlerMappingTest {

    private ApiVersionRequestMappingHandlerMapping handlerMapping;

    @Test
    public void getMappingForMethodWhenRequestMappingInfoIsNull() throws Exception {
        // ARRANGE
        handlerMapping = new ApiVersionRequestMappingHandlerMapping("v");

        final Method method = Object.class.getMethods()[0];

        // ACT
        final RequestMappingInfo mappingForMethod = handlerMapping.getMappingForMethod(
            method, Object.class);

        // ASSERT
        assertNull(mappingForMethod);
    }

    @Test
    public void getMappingForMethodWhenApiVersionAnnotationIsOnClass() throws Exception {
        // ARRANGE
        final String apiPathPrefix = "v";
        handlerMapping = new ApiVersionRequestMappingHandlerMapping(apiPathPrefix);

        final Method method = ClassWithApiVersionAnnotationOnClass.class.getMethod("foo");

        // ACT
        final RequestMappingInfo mappingForMethod = handlerMapping.getMappingForMethod(
            method, ClassWithApiVersionAnnotationOnClass.class);

        // ASSERT
        assertNotNull(mappingForMethod);

        assertEquals(
            Collections.singleton("/" + apiPathPrefix + "1"),
            mappingForMethod.getPatternsCondition().getPatterns()
        );
    }

    @Test
    public void getMappingForMethodWhenApiVersionAnnotationIsAbsent() throws Exception {
        // ARRANGE
        final String apiPathPrefix = "v";
        handlerMapping = new ApiVersionRequestMappingHandlerMapping(apiPathPrefix);

        final Method method = ClassWithoutApiVersionAnnotationOnClass.class.getMethod("foo");

        // ACT
        final RequestMappingInfo mappingForMethod = handlerMapping.getMappingForMethod(
            method, ClassWithoutApiVersionAnnotationOnClass.class);

        // ASSERT
        assertNotNull(mappingForMethod);

        assertEquals(
            Collections.singleton("/test"),
            mappingForMethod.getPatternsCondition().getPatterns()
        );
    }

    @ApiVersion("1")
    @RequestMapping
    private class ClassWithApiVersionAnnotationOnClass {
        @RequestMapping
        public void foo() { }
    }

    @RequestMapping("/test")
    private class ClassWithoutApiVersionAnnotationOnClass {

        @RequestMapping
        public void foo() { }

    }
}