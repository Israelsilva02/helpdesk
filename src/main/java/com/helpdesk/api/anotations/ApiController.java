package com.helpdesk.api.anotations;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@RestController
@RequestMapping
@Tag(name = "", description = "")
public @interface ApiController {

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};

    @AliasFor(annotation = Tag.class, attribute = "description")
    String description() default "";

    @AliasFor(annotation = Tag.class, attribute = "name")
    String name() default "";
}
