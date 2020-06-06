package com.hzy.wan.mvp.presenter;

import com.hzy.wan.base.BasePresenter;
import com.hzy.wan.bean.OfficialAccountBean;
import com.hzy.wan.http.HttpListener;
import com.hzy.wan.mvp.model.IOfficialModel;
import com.hzy.wan.mvp.model.impl.OfficialModelImpl;
import com.hzy.wan.mvp.view.OfficialView;

public class OfficialPresenter extends BasePresenter<OfficialView> {
    private IOfficialModel mModel;

    public OfficialPresenter(OfficialView view) {
        super(view);
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
