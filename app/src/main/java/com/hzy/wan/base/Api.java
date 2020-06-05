package com.hzy.wan.base;

import com.hzy.wan.bean.BannerBean;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.bean.NaviBean;
import com.hzy.wan.bean.OfficialAccountBean;
import com.hzy.wan.bean.OfficialArticleBean;
import com.hzy.wan.bean.ProjectBean;
import com.hzy.wan.bean.ProjectTypeBean;
import com.hzy.wan.bean.SystemArticleBean;
import com.hzy.wan.bean.SystemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://www.wanandroid.com";
    //首页banner retrofit2 + rxjava 方式
    @GET("/banner/json")
    Observable<BannerBean> getHomeBanner3();

    //首页文章列表 retrofit2 +rxjava 方式
    @GET("/article/list/{page}/json")
    Observable<HomeArticleBean> getHomeArticle3(@Path("page") Integer page);


    //公众号列表
    @GET("/wxarticle/chapters/json")
    Observable<OfficialAccountBean> getWxarticle();

    //查看某个公众号历史数据
    @GET("/wxarticle/list/{id}/{page}/json")
    Observable<OfficialArticleBean> getWxarticleList(@Path("id") Integer id, @Path("page") Integer page);

    //项目分类
    @GET("/project/tree/json")
    Observable<ProjectTypeBean> getProjectType();

    //项目列表数据
    @GET("/project/list/{page}/json")
    Observable<ProjectBean> getProjectList(@Path("page") Integer page, @Query("cid") Integer cid);

    //体系
    @GET("/tree/json")
    Observable<SystemBean> getSysType();

    //体系下的文章
    @GET("/article/list/{page}/json")
    Observable<SystemArticleBean> getSysArticle(@Path("page") Integer page, @Query("cid") Integer cid);

    //导航
    @GET("/navi/json")
    Observable<NaviBean> getNavi();


}
