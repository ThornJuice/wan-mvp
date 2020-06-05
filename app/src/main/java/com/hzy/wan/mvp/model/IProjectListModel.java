package com.hzy.wan.mvp.model;

import com.example.base_mvp.base.IModel;
import com.example.base_mvp.http.HttpListener;
import com.hzy.wan.bean.ProjectBean;
import io.reactivex.Observable;

public interface IProjectListModel extends IModel {

    Observable<ProjectBean> getProjectList(int id, int page, HttpListener httpListener);
}
