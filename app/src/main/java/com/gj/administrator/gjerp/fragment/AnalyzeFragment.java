package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.activity.AnalyzeChartActivity;
import com.gj.administrator.gjerp.adapter.RecyclerAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;

/**
 * manage fragment
 * Created by guojun on 2015/12/07
 */
public class AnalyzeFragment extends BaseFragment{
    private static final String[] items = {"Hotel Analysis", "Room Analysis","Product Analysis","Guest Analysis"};
    protected Context context;
    private ListView listView;

    public static AnalyzeFragment getInstance(Context context) {
        AnalyzeFragment mf = new AnalyzeFragment();
        mf.context = context;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_manage, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        listView = (ListView) findViewById(R.id.manage_list);
    }

    @Override
    protected void initEvents() {
        if(listView != null){
            listView.setAdapter(new RecyclerAdapter(context, R.layout.manage_list_items, items, RecyclerAdapter.DRAWABLE_TYPE.SAMPLE_ROUND_RECT_BORDER, true, false));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        //TODO
                        default:
                            startActivity(context, AnalyzeChartActivity.class);
                            break;
                    }
                }
            });
        }
    }



}
