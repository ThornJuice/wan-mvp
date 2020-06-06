package com.hzy.wan.mvp.model;


import com.hzy.wan.base.IModel;
import com.hzy.wan.bean.SystemArticleBean;
import com.hzy.wan.bean.SystemBean;
import com.hzy.wan.http.HttpListener;

import io.reactivex.Observable;

public interface ISystemModel extends IModel {
    Observable<SystemArticleBean> getSysArticle(int page, int id, HttpListener<SystemArticleBean> httpListener);

    Observable<SystemBean> getSysType(HttpListener<SystemBean> httpListener);
}
