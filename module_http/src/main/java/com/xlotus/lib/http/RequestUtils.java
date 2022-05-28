package com.xlotus.lib.http;

import static com.xlotus.lib.http.Constants.CODE_ERROR_UNKNOWN;
import static com.xlotus.lib.http.Constants.CODE_SUCCESS;
import static com.xlotus.lib.http.Constants.ERROR_UNKNOWN;

import android.text.TextUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class RequestUtils {

    /**
     * 发起请求，回包按ResponseWrapper内格式解析
     * @param call 请求
     * @param netListener 监听器
     * @param <T> ResponseWrapper内data格式
     */
    public static <T> void request(Call<ResponseWrapper<T>> call, INetListener<T> netListener) {
        call.enqueue(new Callback<ResponseWrapper<T>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ResponseWrapper<T>> call,  Response<ResponseWrapper<T>> response) {
                if (netListener != null) {
                    ResponseWrapper<T> responseWrapper;
                    if (response.isSuccessful() && response.body() != null) {
                        responseWrapper = response.body();
                    } else {
                        responseWrapper = new ResponseWrapper<>(response.code(), !TextUtils.isEmpty(response.message()) ? response.message() : ERROR_UNKNOWN);
                    }
                    if (RequestManager.getInstance().getResponseInterceptor() != null) {
                        RequestManager.getInstance().getResponseInterceptor().onResponseInterceptor(responseWrapper);
                    }
                    netListener.onResponse(responseWrapper);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ResponseWrapper<T>> call, Throwable t) {
                if (netListener != null) {
                    ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(CODE_ERROR_UNKNOWN, ERROR_UNKNOWN);
                    if (RequestManager.getInstance().getResponseInterceptor() != null) {
                        RequestManager.getInstance().getResponseInterceptor().onResponseInterceptor(responseWrapper);
                    }
                    netListener.onResponse(responseWrapper);
                }
            }
        });
    }

    /**
     * 发起请求，回包内容不是标准的ResponseWrapper格式，解析完T封装成ResponseWrapper,通知监听器
     * @param call 请求
     * @param netListener 监听器
     * @param <T> ResponseWrapper内data格式
     */
    public static <T> void request2(Call<T> call, INetListener<T> netListener) {
        call.enqueue(new Callback<T>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<T> call,  Response<T> response) {
                if (netListener != null) {
                    ResponseWrapper<T> responseWrapper;
                    if (response.isSuccessful()) {
                        responseWrapper = new ResponseWrapper<>(CODE_SUCCESS, "");
                        responseWrapper.setData(response.body());
                    } else {
                        responseWrapper = new ResponseWrapper<>(response.code(), !TextUtils.isEmpty(response.message()) ? response.message() : ERROR_UNKNOWN);
                    }
                    if (RequestManager.getInstance().getResponseInterceptor() != null) {
                        RequestManager.getInstance().getResponseInterceptor().onResponseInterceptor(responseWrapper);
                    }
                    netListener.onResponse(responseWrapper);
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<T> call, Throwable t) {
                if (netListener != null) {
                    ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(CODE_ERROR_UNKNOWN, ERROR_UNKNOWN);
                    if (RequestManager.getInstance().getResponseInterceptor() != null) {
                        RequestManager.getInstance().getResponseInterceptor().onResponseInterceptor(responseWrapper);
                    }
                    netListener.onResponse(responseWrapper);
                }
            }
        });
    }
}
