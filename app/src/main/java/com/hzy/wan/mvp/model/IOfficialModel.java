package com.hzy.wan.mvp.model;



import com.hzy.wan.base.IModel;
import com.hzy.wan.bean.OfficialAccountBean;
import com.hzy.wan.http.HttpListener;

import io.reactivex.Observable;

public interface IOfficialModel extends IModel {
    Observable<OfficialAccountBean> getWxarticle(HttpListener httpListener);
}
