package com.hzy.wan.mvp.model.impl;

import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.NaviBean;
import com.hzy.wan.bean.OfficialAccountBean;
import com.hzy.wan.http.RetrofitManager2;
import com.hzy.wan.mvp.model.INaviModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NaviModelImpl implements INaviModel {
    @Override
    public Observable<NaviBean> getNavi(HttpListener httpListener) {
        Observable<NaviBean> observable = RetrofitManager2.getInstance().create().getNavi();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NaviBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NaviBean data) {
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
