package com.hzy.wan.mvp.view;

import com.example.base_mvp.base.IView;
import com.hzy.wan.bean.SystemBean;

import java.util.ArrayList;
import java.util.List;

public interface SystemView extends IView {
    void setSysType(List<SystemBean.DataBean> list);
}
