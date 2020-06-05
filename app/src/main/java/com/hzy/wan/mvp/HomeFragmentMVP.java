package com.hzy.wan.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hzy.baselib.listener.RetryClickListener;
import com.hzy.baselib.util.ToastUtil;
import com.hzy.baselib.widget.BaseTitleBar;
import com.hzy.baselib.widget.gloading.Gloading;
import com.hzy.wan.R;
import com.hzy.wan.activity.AgentWebView;
import com.hzy.wan.adapter.BannerViewHolder;
import com.hzy.wan.adapter.HomeAdapter;
import com.hzy.wan.base.BaseMVPFragment;
import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.mvp.presenter.HomePresenter;
import com.hzy.wan.mvp.view.HomeView;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import com.zhpan.bannerview.constants.PageStyle;
import com.zhpan.bannerview.holder.HolderCreator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragmentMVP extends BaseMVPFragment<HomeView, HomePresenter> implements HomeView  {
    BaseTitleBar title_bar;
    BannerViewPager<BannerBean.DataBean, BannerViewHolder> bannerViewPager;
    View bannerView;
    RecyclerView recyclerView;
    HomeAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresent.getHomeArticle(true);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object object = adapter.getItem(position);
                if(object instanceof  HomeArticleBean.DataBean.DatasBean){
                    Bundle bundle = new Bundle();
                    bundle.putString("url",  ((HomeArticleBean.DataBean.DatasBean) object).getLink());
                    startActivity(new Intent(mContext,AgentWebView.class).putExtras(bundle));
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adapter = new HomeAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(
                () -> {
                    mPresent.getHomeArticle(false);
                }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.addHeaderView(bannerView);
    }

    @Override
    protected void initView(@NotNull View view) {

        title_bar = view.findViewById(R.id.title_bar);
        title_bar.setPageTitle("首页");
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(mContext, R.color.theme));
        initBanner();
        initRecyclerView(view);

        mLoadHolder = Gloading.getDefault().wrap(swipeRefreshLayout).withRetry(new RetryClickListener() {
            @Override
            public void retry() {

            }
        });
    }

    @Override
    protected void initData() {
        mPresent = new HomePresenter(this);
        getViewLifecycleOwner().getLifecycle().addObserver(mPresent);
        mLoadHolder.showLoading();
        mPresent.getBanner();
        mPresent.getHomeArticle(false);
    }

    @Override
    public void setBanner(List<BannerBean.DataBean> data) {
        //  Message msg = handler.obtainMessage(1, data);
        //handler.sendMessage(msg);
        bannerViewPager.create(data);
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                bannerViewPager.create(data);
//            }
//        });
    }

    @Override
    public void setHomeArticle(List<HomeArticleBean.DataBean.DatasBean> list, int page, boolean end) {

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
        ToastUtil.INSTANCE.showToast("...");
    }

    @Override
    public void showLoading() {
       mLoadHolder.showLoading();
    }

    @Override
    public void dismissLoading() {
        // Message msg = handler.obtainMessage(0, "");
        // handler.sendMessag swipeRefreshLayout.setRefreshing(false);wwe(msg);
        swipeRefreshLayout.setRefreshing(false);
        mLoadHolder.showLoadSuccess();
    }


    private void initBanner() {
        bannerView = getLayoutInflater().inflate(R.layout.layout_home_banner, null);
        bannerViewPager = bannerView.findViewById(R.id.bannerViewPager);
        bannerViewPager.setAutoPlay(true)
                .setPageStyle(PageStyle.MULTI_PAGE_SCALE)
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setInterval(5000)
                .setScrollDuration(1200)
                .setIndicatorColor(ContextCompat.getColor(getActivity(), R.color.white7f), ContextCompat.getColor(getActivity(), R.color.white))
                .setHolderCreator(new HolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                }).setOnPageClickListener(new BannerViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url", bannerViewPager.getList().get(position).getUrl());
                startActivity(new Intent(getActivity(), AgentWebView.class).putExtras(bundle));
            }
        });
    }
}
