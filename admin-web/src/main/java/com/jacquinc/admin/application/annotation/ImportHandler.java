package com.jacquinc.admin.application.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author cjq
 * created on  2020/09/01
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ImportHandler {

    String name() default "";

}
