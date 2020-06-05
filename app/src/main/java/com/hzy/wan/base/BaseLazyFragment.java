package com.hzy.wan.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hzy.baselib.widget.gloading.Gloading;

public abstract class BaseLazyFragment extends Fragment {
    protected Gloading.Holder mLoadHolder;
    protected Context mContext;
    /**
     * 视图是否加载完毕
     */
    private boolean isViewPrepared = false;
    /**
     * 是否已经执行过懒加载
     */
    private boolean isLazyLoaded = false;

    /**
     * 加载布局
     */
    protected abstract int getLayoutId();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    protected abstract void initListener();

    protected abstract void initView(View view);

    protected abstract void lazyLoad();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        initView(view);
        lazyLoadDataIfPrepared();
        initListener();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared();
        }
    }

    private void lazyLoadDataIfPrepared() {
        if (getUserVisibleHint() && isViewPrepared && !isLazyLoaded) {
            lazyLoad();
            isLazyLoaded = true;
        }
    }
}
