package com.hzy.wan.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzy.baselib.base.BaseApp;
import com.hzy.baselib.widget.BaseTitleBar;
import com.hzy.wan.R;
import com.hzy.wan.activity.AgentWebView;
import com.hzy.wan.base.BaseMVPFragment;
import com.hzy.wan.bean.NaviBean;
import com.hzy.wan.mvp.presenter.NaviPresenter;
import com.hzy.wan.mvp.view.NaviView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class NaviFragmentMVP extends BaseMVPFragment<NaviView, NaviPresenter> implements NaviView {
    BaseTitleBar title_bar;
    RecyclerView recyclerView;
    RecyclerView recyclerViewLeft;
    MyAdapter mAdapter;
    AdapterLeft leftAdapter;

    @Override
    public void showMessage() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navi;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView(View view) {
        title_bar = view.findViewById(R.id.title_bar);
        title_bar.setPageTitle("导航");
        initRecyclerView(view);
    }

    @Override
    protected void initData() {
        mPresent = new NaviPresenter(this);
        mPresent.getNavi();
    }

    @Override
    public void setData(NaviBean data) {
        leftAdapter.setNewData(data.getData());
        mAdapter.setNewData(data.getData());
        bindTabAndPager();
    }

    private void bindTabAndPager() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager != null) {
                        int position = layoutManager.findFirstVisibleItemPosition();
                        recyclerViewLeft.smoothScrollToPosition(position);
                        leftAdapter.setSelected(position);
                    }
                }
            }

        });
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewLeft = view.findViewById(R.id.recyclerViewLeft);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        recyclerViewLeft.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mAdapter = new MyAdapter(null);
        leftAdapter = new AdapterLeft(null);
        recyclerView.setAdapter(mAdapter);
        recyclerViewLeft.setAdapter(leftAdapter);
    }

    public class MyAdapter extends BaseQuickAdapter<NaviBean.DataBean, BaseViewHolder> {

        public MyAdapter(@Nullable List<NaviBean.DataBean> data) {
            super(R.layout.item_navi_content, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NaviBean.DataBean item) {
            helper.setText(R.id.tv_title, item.getName());
            TagFlowLayout flowLayout = helper.getView(R.id.flowLayout);
            flowLayout.setAdapter(new TagAdapter<NaviBean.DataBean.ArticlesBean>(item.getArticles()) {
                @Override
                public View getView(FlowLayout parent, int position, NaviBean.DataBean.ArticlesBean articlesBean) {

                    TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag,
                            parent, false);
                    tv.setText(articlesBean.getTitle());
                    return tv;
                }

                @Override
                public int getCount() {
                    return item.getArticles().size();
                }
            });
            flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", item.getArticles().get(position).getLink());
                    startActivity(new Intent(mContext, AgentWebView.class).putExtras(bundle));
                    return true;
                }
            });
        }
    }

    public class AdapterLeft extends BaseQuickAdapter<NaviBean.DataBean, BaseViewHolder> {
        private int position = 0;

        public AdapterLeft(@Nullable List<NaviBean.DataBean> data) {
            super(R.layout.item_simple_text, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NaviBean.DataBean item) {
            helper.setText(R.id.tv_title, item.getName());
            int p = helper.getLayoutPosition();
            if (position == p) {
                helper.setTextColor(R.id.tv_title, ContextCompat.getColor(BaseApp.instance, R.color.colorPrimary));
            } else {
                helper.setTextColor(R.id.tv_title, ContextCompat.getColor(BaseApp.instance, R.color.black_text));
            }
        }

        public void setSelected(int position) {
            this.position = position;
            notifyDataSetChanged();

        }
    }
}
