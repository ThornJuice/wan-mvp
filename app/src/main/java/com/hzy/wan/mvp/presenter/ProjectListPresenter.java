package com.hzy.wan.mvp.presenter;


import com.hzy.wan.base.BasePresenter;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.http.HttpListener;
import com.hzy.wan.mvp.model.IProjectListModel;
import com.hzy.wan.mvp.model.impl.ProjectListModelImpl;
import com.hzy.wan.mvp.view.ProjectListView;

public class ProjectListPresenter extends BasePresenter<ProjectListView> {
    private IProjectListModel mModel;
    private int page = 1;

    public ProjectListPresenter(ProjectListView view) {
        super(view);
        mModel = new ProjectListModelImpl();
    }

    public void getData(int id, boolean refresh) {

        if (refresh) {
            page = 1;
        }

        mModel.getProjectList(id, page, new HttpListener<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean response) {
                mView.dismissLoading();
                ProjectBean.DataBean data = response.getData();
                if (data.getDatas().size() > 0) {
                    mView.setData(data.getDatas(), page, false);
                    page++;
                } else {
                    mView.setData(data.getDatas(), page, true);
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.dismissLoading();
            }
        });

    }
}
