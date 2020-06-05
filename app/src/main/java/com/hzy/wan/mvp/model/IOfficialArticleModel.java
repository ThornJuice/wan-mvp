package com.hzy.wan.mvp.model;

import com.example.base_mvp.base.IModel;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.OfficialArticleBean;

import io.reactivex.Observable;

public interface IOfficialArticleModel extends IModel {

    Observable<OfficialArticleBean> getWxarticleList(int id, int page, HttpListener httpListener);
}
