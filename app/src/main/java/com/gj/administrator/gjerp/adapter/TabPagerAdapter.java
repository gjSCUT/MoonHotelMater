package com.gj.administrator.gjerp.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gj.administrator.gjerp.base.BaseFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
	List<BaseFragment> fragments;
    String[] titles;

	public void SetDate(List<BaseFragment> fragments) {
        this.fragments = fragments;
	}

	public TabPagerAdapter(FragmentManager fm,List<BaseFragment> fragments,String[] titles) {
		super(fm);
		this.fragments = fragments;
        this.titles = titles;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

	@Override
	public int getCount() {
		return fragments.size();
	}
}
