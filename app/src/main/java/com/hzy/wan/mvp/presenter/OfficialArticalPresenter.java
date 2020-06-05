package com.hzy.wan.mvp.presenter;

import com.example.base_mvp.base.BasePresenter;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.OfficialArticleBean;
import com.hzy.wan.mvp.model.IOfficialArticalModel;
import com.hzy.wan.mvp.model.impl.OfficialArticalModelImpl;
import com.hzy.wan.mvp.view.OfficialArticalView;

public class OfficialArticalPresenter extends BasePresenter {
    private OfficialArticalView mView;
    private IOfficialArticalModel mModel;
    private int page = 1;

    public OfficialArticalPresenter(OfficialArticalView view) {
        this.mView = view;
        mModel = new OfficialArticalModelImpl();
    }

    public void getData(int id, boolean refresh) {

        if (refresh) {
            page = 1;
        }

        mModel.getWxarticleList(id, page, new HttpListener<OfficialArticleBean>() {
            @Override
            public void onSuccess(OfficialArticleBean response) {
                mView.dismissLoading();
                OfficialArticleBean.DataBean data = response.getData();
                if (data.getDatas().size() > 0) {
                    mView.setData(data.getDatas(), page, false);
                    page++;
                } else {
                    mView.setData(data.getDatas(), page, true);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissLoading();
            }
        });

    }
}
