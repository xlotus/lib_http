package com.xlotus.lib.http;

import java.util.Map;

/**
 * 请求拦截器，添加公共header
 */
public interface IRequestInterceptor {
    Map<String, String> getCommonHeader();
}
