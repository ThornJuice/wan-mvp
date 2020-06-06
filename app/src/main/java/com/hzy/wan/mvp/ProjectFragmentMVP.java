package com.hzy.wan.mvp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hzy.baselib.widget.BaseTitleBar;
import com.hzy.wan.R;
import com.hzy.wan.adapter.ViewPageAdapter;
import com.hzy.wan.base.BaseMVPFragment;
import com.hzy.wan.bean.ProjectTypeBean;
import com.hzy.wan.mvp.presenter.ProjectPresenter;
import com.hzy.wan.mvp.view.ProjectView;

import java.util.ArrayList;
import java.util.List;

import fragment.OfficialArticleFragment;

public class ProjectFragmentMVP extends BaseMVPFragment<ProjectView, ProjectPresenter> implements ProjectView {
    BaseTitleBar title_bar;
    ArrayList<OfficialArticleFragmentMVP> fragmentsList = new ArrayList<>();
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_official_accounts;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView(View view) {
        title_bar = view.findViewById(R.id.title_bar);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        title_bar.setPageTitle("项目");
    }

    @Override
    protected void initData() {
        mPresent = new ProjectPresenter(this);
        mPresent.getWxarticle();
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void setData(ProjectTypeBean data) {
        setVpData(data.getData());
    }

    private void setVpData(List<ProjectTypeBean.DataBean> list) {
        ArrayList<String> tabs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            tabs.add(list.get(i).getName());
            OfficialArticleFragmentMVP fragment = new OfficialArticleFragmentMVP();
            Bundle bundle = new Bundle();
            bundle.putInt("id", list.get(i).getId());
            fragment.setArguments(bundle);
            fragmentsList.add(fragment);
        }
        ViewPageAdapter adapter = new ViewPageAdapter(getChildFragmentManager(), fragmentsList) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs.get(position);
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(tabs.size());
        tabLayout.setupWithViewPager(viewPager);
    }

}
