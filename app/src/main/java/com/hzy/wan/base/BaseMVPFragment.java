package com.hzy.wan.base;

import com.example.base_mvp.base.BasePresenter;
import com.example.base_mvp.base.IView;

public abstract class BaseMVPFragment<V extends IView,P extends BasePresenter> extends BaseFragment implements IView{
    public P mPresent;
}
