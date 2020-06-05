package com.example.base_mvp.http;

public interface HttpListener<T> {
    void onSuccess(T response);
    void onError(Throwable e);
}
