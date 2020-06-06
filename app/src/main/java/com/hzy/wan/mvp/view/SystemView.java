package com.hzy.wan.mvp.view;

import com.hzy.wan.base.IView;
import com.hzy.wan.bean.SystemBean;

import java.util.List;

public interface SystemView extends IView {
    void setSysType(List<SystemBean.DataBean> list);
}
