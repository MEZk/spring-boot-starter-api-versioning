package io.github.mezk.spring.boot.starters.api.versioning.configuration;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import io.github.mezk.spring.boot.starters.api.versioning.annotations.ApiVersion;

/**
 * Creates {@link RequestMappingInfo} instances from type and method-level
 * {@link RequestMapping @RequestMapping} annotations in
 * {@link Controller @Controller} classes taken {@link ApiVersion} annotation into respect.
 * @author Andrei Selkin
 */
class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private final String prefix;

    /**
     * Creates an instance of {@link ApiVersionRequestMappingHandlerMapping}.
     * @param prefix api version path prefix.
     */
    ApiVersionRequestMappingHandlerMapping(String prefix) {
        this.prefix = prefix;
    }

    /**
     * {@inheritDoc}
     * This method will modify the mapping for the method which is annotated with
     * {@link org.springframework.web.bind.annotation.RequestMapping} annotation in accordance with
     * the value specified by {@link ApiVersion} annotation.
     * This method currently looks only for {@link ApiVersion} annotation on classes.
     * @param method handler method.
     * @param handlerType the class to look for {@link ApiVersion} annotations on.
     */
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);

        if (info != null) {
            final ApiVersion typeAnnotation = AnnotationUtils
                .findAnnotation(handlerType, ApiVersion.class);
            if (typeAnnotation != null) {
                final RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
                info = createApiVersionInfo(typeAnnotation, typeCondition).combine(info);
            }
        }

        return info;
    }

    private RequestMappingInfo createApiVersionInfo(
        ApiVersion annotation,
        RequestCondition<?> customCondition
    ) {
        final String[] apiVersions = annotation.value();
        final String[] patterns = new String[apiVersions.length];
        for (int i = 0; i < apiVersions.length; i++) {
            patterns[i] = prefix + apiVersions[i];
        }

        return new RequestMappingInfo(
            new PatternsRequestCondition(
                patterns,
                getUrlPathHelper(),
                getPathMatcher(),
                useSuffixPatternMatch(),
                useTrailingSlashMatch(),
                getFileExtensions()
            ),
            new RequestMethodsRequestCondition(),
            new ParamsRequestCondition(),
            new HeadersRequestCondition(),
            new ConsumesRequestCondition(),
            new ProducesRequestCondition(),
            customCondition
        );
    }
}
