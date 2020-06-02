package com.hzy.wan.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.hzy.baselib.base.BaseFragment;
import com.hzy.baselib.widget.BaseTitleBar;
import com.hzy.wan.R;
import com.hzy.wan.activity.AgentWebView;
import com.hzy.wan.adapter.BannerViewHolder;
import com.hzy.wan.adapter.HomeAdapter;
import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.IndicatorSlideMode;
import com.zhpan.bannerview.constants.IndicatorStyle;
import com.zhpan.bannerview.constants.PageStyle;
import com.zhpan.bannerview.holder.HolderCreator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragmentMVP extends BaseFragment implements HomeView {
    HomePresent mPresent;
    BaseTitleBar title_bar;
    BannerViewPager<BannerBean.DataBean, BannerViewHolder> bannerViewPager;
    View bannerView;
    RecyclerView recyclerView;
    HomeAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    swipeRefreshLayout.setRefreshing(false);
                    break;
                case 1:
                    bannerViewPager.create((List<BannerBean.DataBean>) msg.obj);
                    break;
                case 2:
                    adapter.setNewData((List<HomeArticleBean.DataBean.DatasBean>) msg.obj);
                    break;

            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView(@NotNull View view) {
        title_bar = view.findViewById(R.id.title_bar);
        title_bar.setPageTitle("首页");
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

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

        initBanner();
        adapter.addHeaderView(bannerView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresent.getHomeArticle(true);
            }
        });
    }

    @Override
    protected void initData() {
        mPresent = new HomePresent(this);
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
    public void setHomeArticle(List<HomeArticleBean.DataBean.DatasBean> list,int page,boolean end) {

        if(page == 1){
            adapter.setNewData(list);
            if(end){
                adapter.loadMoreEnd();
            }else{
                adapter.loadMoreComplete();
            }
        }else{
            adapter.addData(list);
            if(end){
                adapter.loadMoreEnd();
            }else{
                adapter.loadMoreComplete();
            }
        }


    }

    @Override
    public void dismissLoading() {
       // Message msg = handler.obtainMessage(0, "");
       // handler.sendMessag swipeRefreshLayout.setRefreshing(false);wwe(msg);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadMore() {

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
