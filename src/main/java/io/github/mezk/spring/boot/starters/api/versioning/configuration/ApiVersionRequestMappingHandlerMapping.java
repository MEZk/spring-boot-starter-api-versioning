package io.github.mezk.spring.boot.starters.api.versioning.configuration;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
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

class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private final String prefix;

    ApiVersionRequestMappingHandlerMapping(String prefix) {
        this.prefix = prefix;
    }

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
