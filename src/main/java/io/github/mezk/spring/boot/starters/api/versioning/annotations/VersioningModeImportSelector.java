////////////////////////////////////////////////////////////////////////////////////////////////////
// Spring Boot Api Versioning Starter: allows rest api versioning with Spring MVC REST controllers.
// Copyright (C) 2018 the original author or authors.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
////////////////////////////////////////////////////////////////////////////////////////////////////

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
