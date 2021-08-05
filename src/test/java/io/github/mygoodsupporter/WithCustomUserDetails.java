package io.github.mygoodsupporter;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory =
WithCustomUserDetailsSecurityContextFactory.class)
public @interface WithCustomUserDetails {
    long id() default 1L;
    String username() default "mocha";
    String email() default "mocha@safecornerscoffee.com";
    String name() default "mocha";
}
