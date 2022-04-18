package com.tucker.admingroup.command;

import java.lang.annotation.*;

/**
 * The method is defined as a bit instruction module
 * @author lingqi
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecuteAnnotationBase {
    int Id() default 0;
    String Description() default "Null";
}
