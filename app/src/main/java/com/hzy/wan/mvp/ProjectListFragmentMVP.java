package com.hzy.wan.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzy.baselib.listener.RetryClickListener;
import com.hzy.baselib.widget.gloading.Gloading;
import com.hzy.wan.R;
import com.hzy.wan.activity.AgentWebView;
import com.hzy.wan.base.BaseLazyMVPFragment;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.mvp.presenter.ProjectListPresenter;
import com.hzy.wan.mvp.view.ProjectListView;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProjectListFragmentMVP extends BaseLazyMVPFragment<ProjectListView, ProjectListPresenter> implements ProjectListView {

    RecyclerView recyclerView;
    MyAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    int mId = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresent.getData(mId, true);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", adapter.getItem(position).getLink()+"");
                startActivity(new Intent(mContext, AgentWebView.class).putExtras(bundle));
            }
        });
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adapter = new MyAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(
                () -> {
                    mPresent.getData(mId, false);
                }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    protected void initView(@NotNull View view) {
        if (getArguments() != null) {
            mId = getArguments().getInt("id");
        }

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.theme));
        initRecyclerView(view);

        mLoadHolder = Gloading.getDefault().wrap(swipeRefreshLayout).withRetry(new RetryClickListener() {
            @Override
            public void retry() {

            }
        });
    }

    @Override
    protected void lazyLoad() {
        mPresent = new ProjectListPresenter(this);
        mLoadHolder.showLoading();
        mPresent.getData(mId, false);
    }


    @Override
    public void setData(List<ProjectBean.DataBean.DatasBean> list, int page, boolean end) {

        if (page == 1) {
            adapter.setNewData(list);
            if (end) {
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
        } else {
            adapter.addData(list);
            if (end) {
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
        }


    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showLoading() {
        mLoadHolder.showLoading();
    }

    @Override
    public void dismissLoading() {
        swipeRefreshLayout.setRefreshing(false);
        mLoadHolder.showLoadSuccess();
    }


    public class MyAdapter extends BaseQuickAdapter<ProjectBean.DataBean.DatasBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<ProjectBean.DataBean.DatasBean> data) {
            super(R.layout.item_official_accounts, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProjectBean.DataBean.DatasBean item) {
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_date, item.getNiceDate());
        }
    }


}
