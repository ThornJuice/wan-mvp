package com.hzy.wan.mvp;

import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.http.RetrofitManager;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HomeModel {
    public Call getBanner(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        return client.newCall(request);
    }

    public Call getHomeArticle(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        return client.newCall(request);
    }

    public retrofit2.Call<HomeArticleBean> getHomeArticle2(int page) {
        retrofit2.Call<HomeArticleBean> call = RetrofitManager.getInstance().create().getHomeArticle2(page);
        return call;
    }

    public retrofit2.Call<BannerBean> getBanner2() {
        retrofit2.Call<BannerBean> call = RetrofitManager.getInstance().create().getHomeBanner2();
        return call;
    }
}
