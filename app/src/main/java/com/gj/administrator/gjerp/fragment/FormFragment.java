package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Form fragment
 * Created by guojun on 2015/12/07
 */
public class FormFragment extends BaseFragment{
    // this is the descriptions used in the main page, under the descriptions
    String[] states = new String[] { "Empty", "Checking", "Booking", "Outing", "Blocking" };
    protected Context context;
    private GridView gridView;

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
        gridView = (GridView) findViewById(R.id.room_state_grid);
    }

    @Override
    protected void initEvents() {
        if(gridView != null){
            setupGridContent();
        }
    }

    private void setupGridContent() {
        ArrayList<HashMap<String, Object>> lstImageItems = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < states.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemText", states[i]);
            lstImageItems.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(context, lstImageItems,R.layout.room_state_grid_items,
                new String[] {"itemText" },new int[] {R.id.textView});

        gridView.setAdapter(sa);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    default:
                        break;
                }
            }
        });
    }

}
