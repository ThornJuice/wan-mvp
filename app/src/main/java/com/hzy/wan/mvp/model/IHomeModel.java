package com.hzy.wan.mvp.model;


import com.hzy.wan.base.IModel;
import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.http.HttpListener;

import io.reactivex.Observable;

public interface IHomeModel extends IModel {
    Observable<BannerBean> getBanner3(HttpListener httpListener);

    Observable<HomeArticleBean> getHomeArticle3(int page, HttpListener httpListener);
}