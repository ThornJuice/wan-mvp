package com.hzy.wan.mvp.presenter;


import com.hzy.wan.base.BasePresenter;
import com.hzy.wan.bean.NaviBean;
import com.hzy.wan.http.HttpListener;
import com.hzy.wan.mvp.model.impl.NaviModelImpl;
import com.hzy.wan.mvp.view.NaviView;

public class NaviPresenter extends BasePresenter<NaviView> {
    private NaviModelImpl mModel;

    public NaviPresenter(NaviView view) {
        super(view);
        mModel = new NaviModelImpl();
    }
    public void getNavi(){
        mModel.getNavi(new HttpListener<NaviBean>() {
            @Override
            public void onSuccess(NaviBean response) {
                mView.setData(response);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
