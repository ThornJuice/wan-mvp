package com.hzy.wan.mvp.presenter;


import com.hzy.wan.base.BasePresenter;
import com.hzy.wan.bean.ProjectTypeBean;
import com.hzy.wan.http.HttpListener;
import com.hzy.wan.mvp.model.IProjectModel;
import com.hzy.wan.mvp.model.impl.ProjectModelImpl;
import com.hzy.wan.mvp.view.ProjectListView;
import com.hzy.wan.mvp.view.ProjectView;

public class ProjectPresenter extends BasePresenter<ProjectView> {
    private IProjectModel mModel;

    public ProjectPresenter(ProjectView view) {
        super(view);
        mModel = new ProjectModelImpl();
    }
    public void getWxarticle(){
        mModel.getProjectType(new HttpListener<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean response) {
                mView.setData(response);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
