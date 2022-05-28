package com.xlotus.lib.http;

import android.text.TextUtils;

import com.xlotus.lib.http.utils.CollectionsUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 请求统一入口，管理retrofit实例
 * okhttp配置
 */
public class RequestManager {

    private static final RequestManager instance = new RequestManager();
    private IRequestInterceptor requestInterceptor;
    private IResponseInterceptor responseInterceptor;
    private final Map<Class<?>, Object> requestMap = new HashMap<>();

    private RequestManager() {

    }

    public static RequestManager getInstance() {
        return instance;
    }

    public void setRequestInterceptor(IRequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    public <T> T getRequest(Class<T> cls) {
        Object obj = requestMap.get(cls);
        if (obj != null) {
            return (T) obj;
        }
        IBaseUrl baseUrl = cls.getAnnotation(IBaseUrl.class);
        String url = "";
        if (baseUrl != null) {
            url = !BuildConfig.DEBUG ? baseUrl.prodUrl() : baseUrl.devUrl();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url) //设置网络请求的Url地址
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
        T t = retrofit.create(cls);
        requestMap.put(cls, t);
        return t;
    }

    private OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(paramsInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private final Interceptor paramsInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            if (requestInterceptor != null) {
                Map<String, String> headers = requestInterceptor.getCommonHeader();
                if (!CollectionsUtil.isEmpty(headers)) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                            builder.header(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }

            return chain.proceed(builder.build());
        }
    };

    private final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug") ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

    public IResponseInterceptor getResponseInterceptor() {
        return responseInterceptor;
    }

    public void setResponseInterceptor(IResponseInterceptor responseInterceptor) {
        this.responseInterceptor = responseInterceptor;
    }
}
