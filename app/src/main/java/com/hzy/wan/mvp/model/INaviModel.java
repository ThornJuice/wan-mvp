package com.hzy.wan.mvp.model;


import com.hzy.wan.base.IModel;
import com.hzy.wan.bean.NaviBean;
import com.hzy.wan.http.HttpListener;

import io.reactivex.Observable;

public interface INaviModel extends IModel {
    Observable<NaviBean> getNavi(HttpListener httpListener);
}
