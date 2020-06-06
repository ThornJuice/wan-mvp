package com.hzy.wan.mvp.model;


import com.hzy.wan.base.IModel;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.http.HttpListener;

import io.reactivex.Observable;

public interface IProjectListModel extends IModel {

    Observable<ProjectBean> getProjectList(int id, int page, HttpListener httpListener);
}
