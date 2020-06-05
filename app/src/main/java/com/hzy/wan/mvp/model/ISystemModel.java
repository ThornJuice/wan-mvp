package com.hzy.wan.mvp.model;

import com.example.base_mvp.base.IModel;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.SystemArticleBean;
import com.hzy.wan.bean.SystemBean;

import io.reactivex.Observable;

public interface ISystemModel extends IModel {
    Observable<SystemArticleBean> getSysArticle(int page, int id,HttpListener<SystemArticleBean> httpListener);

    Observable<SystemBean> getSysType(HttpListener<SystemBean> httpListener);
}
