package com.hzy.wan.mvp.view;

import com.example.base_mvp.base.IView;
import com.hzy.wan.bean.NaviBean;
import com.hzy.wan.bean.OfficialAccountBean;

public interface OfficialView extends IView {
    void setData(OfficialAccountBean data);
}
