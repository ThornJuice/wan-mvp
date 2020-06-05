package com.hzy.wan.mvp.model.impl;

import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.bean.ProjectTypeBean;
import com.hzy.wan.bean.SystemArticleBean;
import com.hzy.wan.http.RetrofitManager2;
import com.hzy.wan.mvp.model.IProjectModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectModelImpl implements IProjectModel {
    @Override
    public Observable<ProjectTypeBean> getProjectType(HttpListener httpListener) {
        Observable<ProjectTypeBean> observable = RetrofitManager2.getInstance().create().getProjectType();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProjectTypeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProjectTypeBean data) {
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

    @Override
    public Observable<ProjectBean> getProjectList(int page, int id,HttpListener httpListener) {
        Observable<ProjectBean> observable = RetrofitManager2.getInstance().create().getProjectList(page,id);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProjectBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProjectBean data) {
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
