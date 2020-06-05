package com.hzy.wan.mvp.model.impl;

import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.OfficialAccountBean;
import com.hzy.wan.bean.OfficialArticleBean;
import com.hzy.wan.http.RetrofitManager2;
import com.hzy.wan.mvp.model.IOfficialModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OfficialModelImpl implements IOfficialModel {
    @Override
    public Observable<OfficialAccountBean> getWxarticle(HttpListener httpListener) {
        Observable<OfficialAccountBean> observable = RetrofitManager2.getInstance().create().getWxarticle();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<OfficialAccountBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(OfficialAccountBean data) {
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
