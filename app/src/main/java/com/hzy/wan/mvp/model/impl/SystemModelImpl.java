package com.hzy.wan.mvp.model.impl;

import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.SystemArticleBean;
import com.hzy.wan.bean.SystemBean;
import com.hzy.wan.http.RetrofitManager2;
import com.hzy.wan.mvp.model.ISystemModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SystemModelImpl implements ISystemModel {

    @Override
    public Observable<SystemArticleBean> getSysArticle(int page, int id, HttpListener httpListener) {
        Observable<SystemArticleBean> observable = RetrofitManager2.getInstance().create().getSysArticle(page, id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SystemArticleBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SystemArticleBean homeArticleBean) {
                httpListener.onSuccess(homeArticleBean);
            }

            @Override
            public void onError(Throwable e) {
                httpListener.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return observable;
    }

    @Override
    public Observable<SystemBean> getSysType(HttpListener<SystemBean> httpListener) {
        Observable<SystemBean> observable = RetrofitManager2.getInstance().create().getSysType();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SystemBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SystemBean data) {
                httpListener.onSuccess(data);
            }

            @Override
            public void onError(Throwable e) {
                httpListener.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });
        return observable;
    }
}
