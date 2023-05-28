package com.yc.pratice.office.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportHeaderAnnotation {
    String headerName();

    int index();

    Class dataType() default Object.class;
}
