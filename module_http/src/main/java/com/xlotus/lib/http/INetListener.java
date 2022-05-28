package com.xlotus.lib.http;

/**
 * 通讯回包监听
 * @param <T>
 */
public interface INetListener<T> {
    void onResponse(ResponseWrapper<T> responseWrapper);
}
