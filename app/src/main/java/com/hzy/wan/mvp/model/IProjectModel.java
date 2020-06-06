package com.hzy.wan.mvp.model;


import com.hzy.wan.base.IModel;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.bean.ProjectTypeBean;
import com.hzy.wan.http.HttpListener;

import io.reactivex.Observable;

public interface IProjectModel extends IModel {
    Observable<ProjectTypeBean> getProjectType(HttpListener httpListener);

    Observable<ProjectBean> getProjectList(int page, int id, HttpListener httpListener);
}
