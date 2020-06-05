package com.hzy.wan.mvp.model;

import com.example.base_mvp.base.IModel;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.OfficialAccountBean;
import com.hzy.wan.bean.OfficialArticleBean;

import io.reactivex.Observable;

public interface IOfficialModel extends IModel {
    Observable<OfficialAccountBean> getWxarticle(HttpListener httpListener);
}
