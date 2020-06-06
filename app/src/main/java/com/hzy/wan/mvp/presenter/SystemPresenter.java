package com.hzy.wan.mvp.presenter;


import com.hzy.wan.base.BasePresenter;
import com.hzy.wan.bean.SystemBean;
import com.hzy.wan.http.HttpListener;
import com.hzy.wan.mvp.model.impl.SystemModelImpl;
import com.hzy.wan.mvp.view.ProjectListView;
import com.hzy.wan.mvp.view.SystemView;

public class SystemPresenter extends BasePresenter<SystemView> {
    private SystemModelImpl mModel;

    public SystemPresenter(SystemView view) {
        super(view);
        mModel = new SystemModelImpl();
    }

    public void getSysType() {
        mModel.getSysType(new HttpListener<SystemBean>() {
            @Override
            public void onSuccess(SystemBean response) {
                mView.dismissLoading();
                mView.setSysType(response.getData());
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissLoading();
            }
        });
    }
}
