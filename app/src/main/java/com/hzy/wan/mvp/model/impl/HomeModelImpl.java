package com.hzy.wan.mvp.model.impl;

import com.hzy.wan.http.HttpListener;
import com.hzy.wan.http.RetrofitManager2;
import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.http.RetrofitManager;
import com.hzy.wan.mvp.model.IHomeModel;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HomeModelImpl implements IHomeModel {
    public Call getBanner(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }

    public Call getHomeArticle(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        return client.newCall(request);
    }

    public retrofit2.Call<HomeArticleBean> getHomeArticle2(int page) {
        retrofit2.Call<HomeArticleBean> call = RetrofitManager.getInstance().create().getHomeArticle2(page);
        return call;
    }

    public retrofit2.Call<BannerBean> getBanner2() {
        retrofit2.Call<BannerBean> call = RetrofitManager.getInstance().create().getHomeBanner2();
        return call;
    }
    @Override
    public Observable<HomeArticleBean> getHomeArticle3(int page, HttpListener httpListener) {
        Observable<HomeArticleBean> observable = RetrofitManager2.getInstance().create().getHomeArticle3(page);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomeArticleBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeArticleBean homeArticleBean) {
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
    public Observable<BannerBean> getBanner3(HttpListener httpListener) {
        Observable<BannerBean> observable= RetrofitManager.getInstance().create().getHomeBanner3();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BannerBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BannerBean bannerBean) {
                httpListener.onSuccess(bannerBean);
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
