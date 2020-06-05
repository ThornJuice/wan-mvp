package com.hzy.wan;

import com.hzy.wan.mvp.HomeFragmentMVP;

import dagger.Component;
@ActivityScope
@Component (modules = StudentModule.class)
public interface StudentComponent {
    void inject(HomeFragmentMVP fragmentMVP);
}
