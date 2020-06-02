package com.hzy.wan.adapter;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzy.wan.R;
import com.hzy.wan.bean.HomeArticleBean;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeArticleBean.DataBean.DatasBean, BaseViewHolder> {

    public HomeAdapter(@Nullable List<HomeArticleBean.DataBean.DatasBean> data) {
        super(R.layout.item_home_article, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticleBean.DataBean.DatasBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        if (item.getShareUser() != null) {
            helper.setText(R.id.tv_share_name, "分享者：" + item.getShareUser());
        } else {
            helper.setText(R.id.tv_share_name, "作者：" + item.getAuthor());
        }
        helper.setText(R.id.tv_type, "分类：" + item.getSuperChapterName());
        helper.setText(R.id.tv_date, item.getNiceShareDate());
    }
}
