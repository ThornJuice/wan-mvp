package com.hzy.wan.mvp.presenter;

import com.example.base_mvp.base.BasePresenter;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.NaviBean;
import com.hzy.wan.bean.OfficialAccountBean;
import com.hzy.wan.mvp.model.IOfficialModel;
import com.hzy.wan.mvp.model.impl.OfficialModelImpl;
import com.hzy.wan.mvp.view.OfficialView;

public class OfficialPresenter extends BasePresenter {
    private OfficialView mView;
    private IOfficialModel mModel;

    public OfficialPresenter(OfficialView view) {
        this.mView = view;
        mModel = new OfficialModelImpl();
    }
    public void getWxarticle(){
        mModel.getWxarticle(new HttpListener<OfficialAccountBean>() {
            @Override
            public void onSuccess(OfficialAccountBean response) {
                mView.setData(response);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
