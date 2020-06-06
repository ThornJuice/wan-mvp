package com.hzy.wan.base;



public abstract class BaseLazyMVPFragment<V extends IView,P extends BasePresenter> extends BaseLazyFragment implements IView{
    public P mPresent;
}
