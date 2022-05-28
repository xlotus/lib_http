package com.xlotus.lib.http;

/**
 * 回包拦截器，可以处理通用的错误，比如token失效，需要调起登录之类的
 */
public interface IResponseInterceptor {
    <T> void onResponseInterceptor(ResponseWrapper<T> responseWrapper);
}
