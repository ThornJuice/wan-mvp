package com.hzy.wan.http;



import com.hzy.wan.base.Api;
import com.hzy.wan.http.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager2 {
    private static final long DEFAULT_TIME_OUT = 60;
    private static volatile OkHttpClient mOkHttpClient;
    private Api mService;
    private static RetrofitManager2 instance;

    private RetrofitManager2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mService = retrofit.create(Api.class);
    }

    public static RetrofitManager2 getInstance() {
        if (instance == null) {
            instance = new RetrofitManager2();
        }
        return instance;
    }

    public Api create() {
        return mService;
    }

    /**
     * 配置OKHttpClient
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager2.class) {
                if (mOkHttpClient == null) {
                    //指定缓存路径
                    //A.e("----------"+BaseApp.getApp().getCacheDir());
                    //File file = new File(BaseApp.getApp().getCacheDir().getAbsolutePath(), "HttpCache");
                    //Cache cache = new Cache(file, 1024 * 1024 * 10);
                    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("response");
                    loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
                    loggingInterceptor.setColorLevel(Level.WARNING);
                    mOkHttpClient = new OkHttpClient.Builder()
                            //.cache(cache)
                            .addInterceptor(loggingInterceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }
}
