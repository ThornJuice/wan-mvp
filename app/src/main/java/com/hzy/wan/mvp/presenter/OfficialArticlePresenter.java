package com.hzy.wan.mvp.presenter;

import com.example.base_mvp.base.BasePresenter;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.OfficialArticleBean;
import com.hzy.wan.mvp.model.IOfficialArticleModel;
import com.hzy.wan.mvp.model.impl.OfficialArticleModelImpl;
import com.hzy.wan.mvp.view.OfficialArticleView;

public class OfficialArticlePresenter extends BasePresenter {
    private OfficialArticleView mView;
    private IOfficialArticleModel mModel;
    private int page = 1;

    public OfficialArticlePresenter(OfficialArticleView view) {
        this.mView = view;
        mModel = new OfficialArticleModelImpl();
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
