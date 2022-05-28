package com.xlotus.lib.http.demo.http;

import com.xlotus.lib.http.INetListener;
import com.xlotus.lib.http.RequestManager;
import com.xlotus.lib.http.RequestUtils;
import com.xlotus.lib.http.demo.model.ModelPet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class SwaggerApi {

    public static void addPet(ModelPet pet, INetListener<Object> listener) {
        Call<Object> call = RequestManager.getInstance().getRequest(SwaggerMethod.class).addPet(pet);
        RequestUtils.request2(call, listener);
    }

    public static void getPets(String status, INetListener<List<ModelPet>> listener) {
        Map<String, String> params = new HashMap<>();
        params.put("status", status);

        Call<List<ModelPet>> call = RequestManager.getInstance().getRequest(SwaggerMethod.class).getPets(params);
        RequestUtils.request2(call, listener);
    }
}
