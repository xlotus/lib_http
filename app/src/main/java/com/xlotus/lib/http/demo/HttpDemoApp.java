package com.xlotus.lib.http.demo;

import android.app.Application;
import android.widget.Toast;

import com.xlotus.lib.http.IResponseInterceptor;
import com.xlotus.lib.http.RequestManager;
import com.xlotus.lib.http.ResponseWrapper;
import com.xlotus.lib.http.demo.http.Constants;

import java.util.HashMap;
import java.util.Map;

public class HttpDemoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initHttpRequest();
    }

    private void initHttpRequest() {
        RequestManager.getInstance().setRequestInterceptor(() -> {
            Map<String, String> headers = new HashMap<>();
            headers.put("accept", "application/json");
            headers.put("model", Utils.getModel());
            headers.put("os", Utils.getOs());
            headers.put("version", Utils.getAppVersion(HttpDemoApp.this));
            return headers;
        });

        RequestManager.getInstance().setResponseInterceptor(new IResponseInterceptor() {
            @Override
            public <T> void onResponseInterceptor(ResponseWrapper<T> responseWrapper) {
                if (responseWrapper.getCode() == Constants.CODE_TOKEN_EXPIRE) {
                    Toast.makeText(HttpDemoApp.this, R.string.tip_token_expire, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
