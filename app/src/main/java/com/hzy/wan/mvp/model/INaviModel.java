package com.hzy.wan.mvp.model;

import com.example.base_mvp.base.IModel;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.NaviBean;

import io.reactivex.Observable;

public interface INaviModel extends IModel {
    Observable<NaviBean> getNavi(HttpListener httpListener);
}
