package com.hzy.wan.mvp.model;


import com.hzy.wan.base.IModel;
import com.hzy.wan.bean.OfficialArticleBean;
import com.hzy.wan.http.HttpListener;

import io.reactivex.Observable;

public interface IOfficialArticleModel extends IModel {

    Observable<OfficialArticleBean> getWxarticleList(int id, int page, HttpListener httpListener);
}
