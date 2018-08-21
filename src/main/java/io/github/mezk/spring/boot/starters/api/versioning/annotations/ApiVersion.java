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
