package com.xlotus.lib.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retrofit接口扩展，在接口类中定义多环境baseUrl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IBaseUrl {
    String devUrl();
    String prodUrl();
}
