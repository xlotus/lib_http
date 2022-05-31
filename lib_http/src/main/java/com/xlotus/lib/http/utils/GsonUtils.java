package com.xlotus.lib.http.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtils {
    private static final String TAG = "GsonUtils";

    public GsonUtils() {
    }

    public static <T> T createModel(JSONObject jsonObj, Class<T> cls) {
        if (jsonObj != null && !TextUtils.isEmpty(jsonObj.toString())) {
            try {
                Gson gson = new Gson();
                T model = gson.fromJson(jsonObj.toString(), cls);
                return model;
            } catch (Exception var4) {
                Logger.d("GsonUtils" + "createModel error : " + var4.getLocalizedMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> T createModel(String jsonStr, Class<T> cls) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        } else {
            try {
                Gson gson = new Gson();
                T model = gson.fromJson(jsonStr, cls);
                return model;
            } catch (Exception var4) {
                var4.printStackTrace();
                Logger.d("GsonUtils %s", "createModel error : " + var4.getLocalizedMessage());
                return null;
            }
        }
    }

    public static <T> T createModel(String jsonStr, Type type) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        } else {
            try {
                Gson gson = new Gson();
                T model = gson.fromJson(jsonStr, type);
                return model;
            } catch (Exception var4) {
                var4.printStackTrace();
                Logger.d("GsonUtils %s", "createModel error : " + var4.getLocalizedMessage());
                return null;
            }
        }
    }

    public static <T> List<T> createModels(JSONArray jsonArr, Class<T> cls) {
        List<T> result = new ArrayList();
        if (jsonArr == null) {
            return result;
        } else {
            try {
                for(int i = 0; i < jsonArr.length(); ++i) {
                    try {
                        if (jsonArr.getString(i) != null) {
                            T temp = createModel(jsonArr.getString(i), cls);
                            if (temp != null) {
                                result.add(temp);
                            }
                        }
                    } catch (Exception var5) {
                        Logger.d("GsonUtils %s", "createModels error : " + var5.getLocalizedMessage());
                    }
                }
            } catch (Exception var6) {
                Logger.d("GsonUtils %s", "createModel error : " + var6.getLocalizedMessage());
            }

            return result;
        }
    }

    public static <T> List<T> createModels(String jsonStr, Class<T> cls) {
        if (TextUtils.isEmpty(jsonStr)) {
            return new ArrayList();
        } else {
            try {
                return createModels(new JSONArray(jsonStr), cls);
            } catch (JSONException var3) {
                return new ArrayList();
            }
        }
    }

    public static <T> String models2Json(List<T> models) {
        try {
            Gson gson = new Gson();
            return gson.toJson(models);
        } catch (Exception var2) {
            return "";
        }
    }

    public static String modelToJsonStr(Object o) {
        try {
            Gson gson = new Gson();
            return gson.toJson(o);
        } catch (Exception var2) {
            return "";
        }
    }
}
