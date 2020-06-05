package com.hzy.wan.mvp.view;

import com.example.base_mvp.base.IView;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.bean.OfficialArticleBean;

import java.util.List;

public interface OfficialArticalView extends IView {
    void setData(List<OfficialArticleBean.DataBean.DatasBean> list, int page, boolean end);
}
