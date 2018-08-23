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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation allows to specify API version suffix for the specific controlller class.
 * For example:
 * <pre class="code">
 * &#064;ApiVersion("1")
 * &#064;RestController(value = "/test")
 * public class MyConfiguration {
 *
 * }
 * </pre>
 *
 * <p>will add '1' to the value specified by 'apiPathVersionPrefix' property.
 * If 'apiPathVersionPrefix' is equal to 'v' the above example will result in '/v1/test' API path.
 *
 * @auhor Andrei Selkin
 * @see org.springframework.web.bind.annotation.RestController
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    /**
     * Api version suffix.
     * For example, if 'apiPathVersionPrefix' variable is set to 'v', and the value of the property
     * is set to '1', it will result in 'v1' api prefix.
     * @return api version suffix.
     */
    String[] value();

}
