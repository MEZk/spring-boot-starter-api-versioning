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

/**
 * The constatnts of this enum set the api versioning mode.
 * The current implementation supports only {@link VersioningMode#ANNOTATION} mode, which allows
 * to use {@link ApiVersion} annotation to specify the api version.
 *
 * @author Andrei Selkin
 */
public enum VersioningMode {
    /**
     * Allows to specify api version via {@link ApiVersion} annotation.
     */
    ANNOTATION
}
