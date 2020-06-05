package com.hzy.wan.base;

import com.example.base_mvp.base.BasePresenter;
import com.example.base_mvp.base.IView;

public abstract class BaseLazyMVPFragment<V extends IView,P extends BasePresenter> extends BaseLazyFragment implements IView{
    public P mPresent;
}
