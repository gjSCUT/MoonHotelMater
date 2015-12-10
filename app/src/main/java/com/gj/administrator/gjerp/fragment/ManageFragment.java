package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.activity.ManageActivity;
import com.gj.administrator.gjerp.adapter.DrawableAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;

/**
 * manage fragment
 * Created by guojun on 2015/12/07
 */
public class ManageFragment extends BaseFragment{
    private static final String[] items = {"Rooms Manager","Travels Manager","Members Manager","Employees Manager"};
    protected Context context;
    private ListView listView;

    public static ManageFragment getInstance(Context context) {
        ManageFragment mf = new ManageFragment();
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
            listView.setAdapter(new DrawableAdapter(context, R.layout.dp60_list_items, items, DrawableAdapter.DRAWABLE_TYPE.SAMPLE_ROUND_RECT_BORDER, true, false));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type",i);
                    switch (i) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            startActivity(context, ManageActivity.class, bundle);
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }



}
