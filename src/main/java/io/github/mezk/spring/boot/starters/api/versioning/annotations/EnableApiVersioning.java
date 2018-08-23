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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * Adding this annotation to an {@code @Configuration} class imports the API versioning
 * configuration from {@link io.github.mezk.spring.boot.starters.api.versioning.configuration.ApiVersioningAnnotationAutoConfiguration}, e.g.:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableApiVersioning
 * &#064;EnableWebMvc
 * &#064;ComponentScan(basePackageClasses = MyConfiguration.class)
 * public class MyConfiguration {
 *
 * }
 * </pre>
 *
 * <p>To customize the imported configuration, you can set {@link VersioningMode} mode value.
 *
 * <p><strong>Note:</strong> only one {@code @Configuration} class may have the
 * {@code @EnableApiVersioning} annotation to import the API versioning
 * configuration.
 *
 * <p><strong>Attention:</strong> {@code @EnableApiVersioning} annotation works only in
 * conjunction with {@code @EnableWebMvc} annotation. Thus, one of your configuration classes must
 * be annotated with {@code @EnableWebMcv} annoation.
 *
 * @author Andrei Selkin
 * @see io.github.mezk.spring.boot.starters.api.versioning.annotations.VersioningModeImportSelector
 * @see io.github.mezk.spring.boot.starters.api.versioning.configuration.ApiVersioningAnnotationAutoConfiguration
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(VersioningModeImportSelector.class)
public @interface EnableApiVersioning {

    /**
     * Sets the api version mode.
     * See {@link VersioningMode}.
     * @return api version mode.
     */
    VersioningMode mode() default VersioningMode.ANNOTATION;

}
