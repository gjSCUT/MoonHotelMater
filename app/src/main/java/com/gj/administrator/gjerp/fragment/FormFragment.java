package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.RecyclerListAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Form fragment
 * Created by guojun on 2015/12/07
 */
public class FormFragment extends BaseFragment {
    // this is the fragment representing data of income
    protected Context context;
    private static RecyclerView mRecyclerView;
    private static RecyclerListAdapter mAdapter;

    private List<RecyclerListAdapter.rowData> mRowDataList;

    public FormFragment() {
        mRowDataList = new ArrayList<>();
    }

    public static FormFragment getInstance(Context context) {
        FormFragment mf = new FormFragment();
        mf.context = context;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_form, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.form_recyclerView);
    }

    @Override
    protected void initEvents() {
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setHasFixedSize(true);
        fetchFormData();
        mAdapter = new RecyclerListAdapter(context, mRowDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

    }


    public void fetchFormData() {
        mRowDataList.clear();
        String[] rowTypeName = new String[]{"Total Income", "Room sold", "Average room price", "Cash", "Credit card"};
        float[] yesterdayValue = new float[]{3600.0f, 10.0f, 360.0f, 720.0f, 2880.0f};
        float[] monthlyValue = new float[]{48000.0f, 160.0f, 300.0f, 1920.0f, 46080.0f};
        for (int i = 0; i < 5; i++) {
            mRowDataList.add(new RecyclerListAdapter.rowData(rowTypeName[i], "",  "" + yesterdayValue[i], "" + monthlyValue[i]));
        }
    }

}
