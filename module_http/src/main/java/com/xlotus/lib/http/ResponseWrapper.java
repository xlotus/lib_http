package com.xlotus.lib.http;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Http响应内容封装解析类
  {
  	"code":200
  	"msg":""
  	"data":{}
    "timestamp":0
  }
 * @param <T>
 */
public class ResponseWrapper<T> implements Serializable {

    @SerializedName("code")
    private int code;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private T data;

    @SerializedName("timestamp")
    private long timestamp;

    public ResponseWrapper() {

    }

    public ResponseWrapper(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccessful() {
        return code == Constants.CODE_SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
