package com.hzy.wan.mvp.model.impl;

import com.hzy.wan.bean.OfficialArticleBean;
import com.hzy.wan.http.HttpListener;
import com.hzy.wan.http.RetrofitManager2;
import com.hzy.wan.mvp.model.IOfficialArticleModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OfficialArticleModelImpl implements IOfficialArticleModel {


    @Override
    public Observable<OfficialArticleBean> getWxarticleList(int id,int page, HttpListener httpListener) {
        Observable<OfficialArticleBean> observable = RetrofitManager2.getInstance().create().getWxarticleList(id,page);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OfficialArticleBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OfficialArticleBean data) {
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
