package io.github.mezk.spring.boot.starters.api.versioning.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(VersioningModeImportSelector.class)
public @interface EnableApiVersioning {

    VersioningMode mode() default VersioningMode.ANNOTATION;

}
