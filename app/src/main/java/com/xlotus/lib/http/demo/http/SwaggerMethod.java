package com.xlotus.lib.http.demo.http;

import com.xlotus.lib.http.IBaseUrl;
import com.xlotus.lib.http.demo.model.ModelPet;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

@IBaseUrl(devUrl = "http://petstore.swagger.io/v2/", prodUrl = "http://petstore.swagger.io/v2/")
public interface SwaggerMethod {
    @POST("pet")
    Call<Object> addPet(@Body ModelPet pet);

    @GET("pet/findByStatus")
    Call<List<ModelPet>> getPets(@QueryMap Map<String, String> params);
}
