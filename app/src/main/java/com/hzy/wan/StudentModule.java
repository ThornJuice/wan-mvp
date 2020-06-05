package com.hzy.wan;

import com.hzy.wan.adapter.HomeAdapter;
import com.hzy.wan.bean.HomeArticleBean;
import com.hzy.wan.mvp.HomeFragmentMVP;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {
    private HomeFragmentMVP homeFragment;

    public StudentModule(HomeFragmentMVP homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Provides
    @ActivityScope
    Student provideStudent() {
        return new Student();
    }

    @ActivityScope
    @Provides
    HomeAdapter provideHomeAdapter() {
        return new HomeAdapter(null);
    }

    @Provides
    @ActivityScope
    Student2 provideStudent2(int type) {
        return new Student2(type);
    }

    @Provides
    public Integer providerInt() {
        return 5;
    }

}
