package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.TabPagerAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * analyze chart
 * Created by guojun on 2015/12/14
 */
public class HomeFragment extends BaseFragment{
    protected Context context;
    private static final String[] titles = {"Message","Task","Relation"};
    SlidingTabLayout slidingTabLayout;
    List<BaseFragment> fragments;
    TabPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    MessageFragment messageFragment;

    public static HomeFragment getInstance(Context context) {
        HomeFragment mf = new HomeFragment();
        mf.context = context;
        return mf;
    }

    public void listenMessage(Message message){
        if(messageFragment!=null){
            messageFragment.listenMessage(message);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        // 加入Fragment
        fragments = new ArrayList<>();
        messageFragment = MessageFragment.getInstance(context);
        fragments.add(messageFragment);
        fragments.add(TaskFragment.getInstance(context));
        fragments.add(RelationFragment.getInstance(context));
        mPagerAdapter = new TabPagerAdapter(this.getChildFragmentManager(), fragments,titles);
        mViewPager = (ViewPager) findViewById(R.id.main_viewpager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected void initEvents() {

    }



}
