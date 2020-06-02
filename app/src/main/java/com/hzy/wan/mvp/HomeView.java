package com.hzy.wan.mvp;

import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;

import java.util.List;

public interface HomeView {
    void setBanner(List<BannerBean.DataBean> data);
    void setHomeArticle(List<HomeArticleBean.DataBean.DatasBean> list,int page,boolean end);
    void dismissLoading();
    //showLoading();
    void loadMore();
}
