package com.hzy.wan.mvp.view;


import com.hzy.wan.base.IView;
import com.hzy.wan.bean.OfficialArticleBean;

import java.util.List;

public interface OfficialArticleView extends IView {
    void setData(List<OfficialArticleBean.DataBean.DatasBean> list, int page, boolean end);
}
