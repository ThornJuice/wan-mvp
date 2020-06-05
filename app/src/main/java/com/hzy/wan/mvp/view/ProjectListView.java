package com.hzy.wan.mvp.view;

import com.example.base_mvp.base.IView;
import com.hzy.wan.bean.ProjectBean;

import java.util.List;

public interface ProjectListView extends IView {
    void setData(List<ProjectBean.DataBean.DatasBean> list, int page, boolean end);
}
