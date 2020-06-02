package com.hzy.wan.mvp;

import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresent {
    HomeView mView;
    HomeModel mModel;
    int page = 1;
    public HomePresent(HomeView view){
        this.mView = view;
        mModel = new HomeModel();
    }
    public void getBanner(){
//        Call call = mModel.getBanner("https://www.wanandroid.com/banner/json");
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json = response.body().string();
//                BannerBean bannerBean =  new Gson().fromJson(json, BannerBean.class);
//               // Log.e("HomePresent","response:"+json);
//               mView.setBanner(bannerBean.getData());
//            }
//        });
        mModel.getBanner2().enqueue(new retrofit2.Callback<BannerBean>() {
            @Override
            public void onResponse(retrofit2.Call<BannerBean> call, retrofit2.Response<BannerBean> response) {
                String json = null;
                try {
//                    json = response.body().string();
//                    BannerBean bannerBean =  new Gson().fromJson(json, BannerBean.class);
                    // Log.e("HomePresent","response:"+json);
                    mView.setBanner(response.body().getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(retrofit2.Call<BannerBean> call, Throwable t) {

            }
        });
    }
    public void getHomeArticle(boolean refresh){
//        Call call = mModel.getHomeArticle("https://www.wanandroid.com/article/list/1/json");
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                mView.dismissLoading();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                mView.dismissLoading();
//                String json = response.body().string();
//                HomeArticleBean articleBean =  new Gson().fromJson(json, HomeArticleBean.class);
//                mView.setHomeArticle(articleBean.getData().getDatas());
//            }
//        });
        if(refresh){
            page = 1;
        }
        Call<HomeArticleBean> call = mModel.getHomeArticle2(page);
        call.enqueue(new Callback<HomeArticleBean>() {
            @Override
            public void onResponse(Call<HomeArticleBean> call, Response<HomeArticleBean> response) {
                mView.dismissLoading();
                String json = null;
                try {
//                    json = response.body().string();
//                    HomeArticleBean articleBean =  new Gson().fromJson(json, HomeArticleBean.class);
                    HomeArticleBean.DataBean data = response.body().getData();

                    if(data.getDatas().size()>0){
                        mView.setHomeArticle(data.getDatas(),page,false);
                        page++;
                    }else{
                        mView.setHomeArticle(data.getDatas(),page,true);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<HomeArticleBean> call, Throwable t) {
                mView.dismissLoading();
            }
        });
    }
}
