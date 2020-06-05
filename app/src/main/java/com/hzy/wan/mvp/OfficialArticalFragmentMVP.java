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
import com.hzy.baselib.util.ToastUtil;
import com.hzy.baselib.widget.gloading.Gloading;
import com.hzy.wan.R;
import com.hzy.wan.activity.AgentWebView;
import com.hzy.wan.base.BaseLazyMVPFragment;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.bean.OfficialArticleBean;
import com.hzy.wan.mvp.presenter.OfficialArticalPresenter;
import com.hzy.wan.mvp.view.OfficialArticalView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OfficialArticalFragmentMVP extends BaseLazyMVPFragment<OfficialArticalView, OfficialArticalPresenter> implements OfficialArticalView {

    RecyclerView recyclerView;
    MyAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    int mId = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_official_list;
    }

    @Override
    protected void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresent.getData(mId, true);
            }
        });
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Object object = adapter.getItem(position);
//                if (object instanceof HomeArticleBean.DataBean.DatasBean) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("url", ((HomeArticleBean.DataBean.DatasBean) object).getLink());
//                    startActivity(new Intent(mContext, AgentWebView.class).putExtras(bundle));
//                }
//            }
//        });
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
        mPresent = new OfficialArticalPresenter(this);
        mLoadHolder.showLoading();
        mPresent.getData(mId, false);
    }


    @Override
    public void setData(List<OfficialArticleBean.DataBean.DatasBean> list, int page, boolean end) {

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

    public class MyAdapter extends BaseQuickAdapter<OfficialArticleBean.DataBean.DatasBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<OfficialArticleBean.DataBean.DatasBean> data) {
            super(R.layout.item_official_accounts, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OfficialArticleBean.DataBean.DatasBean item) {
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_date, item.getNiceDate());
        }
    }


}
