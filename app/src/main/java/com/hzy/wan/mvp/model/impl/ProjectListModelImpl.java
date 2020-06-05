package com.hzy.wan.mvp.model.impl;

import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.http.RetrofitManager2;
import com.hzy.wan.mvp.model.IProjectListModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectListModelImpl implements IProjectListModel {

    @Override
    public Observable<ProjectBean> getProjectList(int id, int page, HttpListener httpListener) {
        Observable<ProjectBean> observable = RetrofitManager2.getInstance().create().getProjectList(id, page);
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
