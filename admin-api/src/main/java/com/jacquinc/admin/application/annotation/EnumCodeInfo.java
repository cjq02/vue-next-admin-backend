package com.jacquinc.admin.application.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author cjq
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumCodeInfo {

    String typeCode() default "";

    String typeName() default "";

}
