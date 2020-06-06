package com.hzy.wan.http;

public interface HttpListener<T> {
    void onSuccess(T response);
    void onError(Throwable e);
}
