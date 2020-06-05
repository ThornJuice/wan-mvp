package com.hzy.wan.mvp.model;

import com.example.base_mvp.base.IModel;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;

import io.reactivex.Observable;

public interface IHomeModel extends IModel {
    Observable<BannerBean> getBanner3(HttpListener httpListener);

    Observable<HomeArticleBean> getHomeArticle3(int page, HttpListener httpListener);
}