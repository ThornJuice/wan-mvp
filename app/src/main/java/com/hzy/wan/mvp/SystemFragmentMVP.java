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
import com.hzy.baselib.widget.BaseTitleBar;
import com.hzy.baselib.widget.gloading.Gloading;
import com.hzy.wan.R;
import com.hzy.wan.activity.SysActivity2;
import com.hzy.wan.base.BaseMVPFragment;
import com.hzy.wan.bean.SystemBean;
import com.hzy.wan.mvp.presenter.SystemPresenter;
import com.hzy.wan.mvp.view.SystemView;

import java.io.Serializable;
import java.util.List;

public class SystemFragmentMVP extends BaseMVPFragment<SystemView, SystemPresenter> implements SystemView {
    BaseTitleBar title_bar;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    SystemAdapter mAdapter;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresent.getSysType();
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object object = adapter.getItem(position);
                if (object instanceof SystemBean.DataBean) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", mAdapter.getItem(position).getName());
                    bundle.putSerializable("child", (Serializable) mAdapter.getItem(position).getChildren());
                    startActivity(new Intent(mContext, SysActivity2.class).putExtras(bundle));
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        title_bar = view.findViewById(R.id.title_bar);
        title_bar.setPageTitle("体系");
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.theme));
        mLoadHolder = Gloading.getDefault().wrap(swipeRefreshLayout).withRetry(new RetryClickListener() {
            @Override
            public void retry() {

            }
        });
        initRecyclerView(view);
    }

    @Override
    protected void initData() {
        mPresent = new SystemPresenter(this);
        mPresent.getSysType();
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mAdapter = new SystemAdapter(null);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setSysType(List<SystemBean.DataBean> list) {
        mAdapter.setNewData(list);
    }

    public class SystemAdapter extends BaseQuickAdapter<SystemBean.DataBean, BaseViewHolder> {

        public SystemAdapter(@Nullable List<SystemBean.DataBean> data) {
            super(R.layout.item_official_accounts, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SystemBean.DataBean item) {
            helper.setText(R.id.tv_title, item.getName());
            StringBuilder stringBuilder = new StringBuilder();
            List<SystemBean.DataBean.ChildrenBean> childen = item.getChildren();
            if (childen != null)
                for (int i = 0; i < childen.size(); i++) {
                    stringBuilder.append(childen.get(i).getName());
                    stringBuilder.append("   ");
                }
            helper.setText(R.id.tv_date, stringBuilder.toString());
        }
    }
}
