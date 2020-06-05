package com.hzy.wan.mvp.model;

import com.example.base_mvp.base.IModel;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.bean.ProjectTypeBean;

import io.reactivex.Observable;

public interface IProjectModel extends IModel {
    Observable<ProjectTypeBean> getProjectType(HttpListener httpListener);

    Observable<ProjectBean> getProjectList(int page, int id, HttpListener httpListener);
}
