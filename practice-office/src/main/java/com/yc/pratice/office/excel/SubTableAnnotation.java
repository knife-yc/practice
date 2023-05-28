package com.yc.pratice.office.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SubTableAnnotation {
    //子表对应的模型
    Class subModule();
    //从哪一列作为子表的开始列
    int startColumn();
}
