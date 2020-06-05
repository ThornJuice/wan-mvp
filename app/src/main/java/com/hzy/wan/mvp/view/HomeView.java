package com.hzy.wan.mvp.view;

import com.example.base_mvp.base.IView;
import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;

import java.util.List;

public interface HomeView extends IView {
    void setBanner(List<BannerBean.DataBean> data);
    void setHomeArticle(List<HomeArticleBean.DataBean.DatasBean> list,int page,boolean end);

}
