package com.hzy.wan.base;



public abstract class BaseMVPFragment<V extends IView,P extends BasePresenter> extends BaseFragment implements IView{
    public P mPresent;
}
