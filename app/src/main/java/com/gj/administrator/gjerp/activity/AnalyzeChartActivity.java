package com.gj.administrator.gjerp.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.TabPagerAdapter;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.fragment.AnalyzeCombinedchartFragment;
import com.gj.administrator.gjerp.fragment.AnalyzePiechartFragment;
import com.gj.administrator.gjerp.fragment.ManageFragment;
import com.gj.administrator.gjerp.fragment.RoomFragment;
import com.gj.administrator.gjerp.view.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class AnalyzeChartActivity extends BaseActivity{
    private static final String[] titles = {"Room","Form","Guest"};
    List<BaseFragment> fragments;
    PagerAdapter mPagerAdapter;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze_chart);
        final ActionBar ab = getSupportActionBar();
        if(ab!=null)
            ab.setDisplayHomeAsUpEnabled(true);
        initViews();
        initEvents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initViews() {
        viewPager = (ViewPager) findViewById(R.id.analyze_chart_viewpager);

        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);

    }

    @Override
    protected void initEvents() {fragments = new ArrayList<>();
        fragments.add(AnalyzePiechartFragment.getInstance(this));
        fragments.add(AnalyzeCombinedchartFragment.getInstance(this));
        fragments.add(AnalyzePiechartFragment.getInstance(this));
        mPagerAdapter = new TabPagerAdapter(this.getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(mPagerAdapter);
        circleIndicator.setViewPager(viewPager);

    }

    @Override
    protected void processMessage(Message msg) {

    }

}
