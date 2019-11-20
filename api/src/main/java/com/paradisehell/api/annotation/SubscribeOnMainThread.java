package com.paradisehell.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在主线程进行订阅注解
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/19 18:02
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface SubscribeOnMainThread {

}