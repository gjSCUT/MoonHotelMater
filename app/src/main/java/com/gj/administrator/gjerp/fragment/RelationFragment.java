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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.activity.ManageActivity;
import com.gj.administrator.gjerp.adapter.DrawableAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * manage fragment
 * Created by guojun on 2015/12/07
 */
public class RelationFragment extends BaseFragment{
    private static final String[] states = {"Employee","Partner","Supplier","Guest"};
    protected Context context;
    private GridView gridView;
    RecyclerView relationRecycler;

    public static RelationFragment getInstance(Context context) {
        RelationFragment mf = new RelationFragment();
        mf.context = context;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_relation, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        gridView = (GridView) findViewById(R.id.gird_view);
        relationRecycler = (RecyclerView)findViewById(R.id.relationRecyclerView);
        relationRecycler.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initEvents() {
        ArrayList<HashMap<String, Object>> lstImageItems = new ArrayList<>();
        for (int i = 0; i < states.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("itemText", states[i]);
            lstImageItems.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(
                context,
                lstImageItems,
                R.layout.grid_items_relation,
                new String[]{"itemText"},
                new int[]{R.id.task_test}
        );

        gridView.setAdapter(sa);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    //TODO
                    default:
                        break;
                }
            }
        });
    }
}
