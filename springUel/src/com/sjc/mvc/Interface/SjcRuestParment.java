package com.sjc.mvc.Interface;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)//≤Œ ˝…œ
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SjcRuestParment {

	String value() default "";
}
