package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.activity.ChatActivity;
import com.gj.administrator.gjerp.activity.ManageActivity;
import com.gj.administrator.gjerp.adapter.DrawableAdapter;
import com.gj.administrator.gjerp.adapter.MessageAdapter;
import com.gj.administrator.gjerp.adapter.RecyclerAdapter;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.gj.administrator.gjerp.util.RandomUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.greenrobot.event.EventBus;

/**
 * manage fragment
 * Created by guojun on 2015/12/07
 */
public class MessageFragment extends BaseFragment{
    private static final String[] items = {"Rooms Manager","Travels Manager","Guest Manager","Employees Manager"};
    protected Context context;
    private ListView listView;
    private MessageAdapter adapter;

    public static MessageFragment getInstance(Context context) {
        MessageFragment mf = new MessageFragment();
        mf.context = context;
        return mf;
    }

    public void onEventMainThread(Dialog dialog) {
        for(Dialog data:adapter.mDataList)
            if(dialog.getId() == data.getId()) {
                adapter.mDataList.remove(data);
                adapter.mDataList.add(0,dialog);;
                adapter.notifyDataSetChanged();
                return;
            }
        adapter.mDataList.add(0,dialog);
        adapter.notifyDataSetChanged();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_message, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onStart(){
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop(){
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        listView = (ListView) findViewById(R.id.message_list);
    }

    @Override
    protected void initEvents() {
        List<Dialog> datas = BaseApplication.getDaoSession(context).getDialogDao().loadAll();

        adapter = new MessageAdapter(
                context,
                R.layout.message_item,
                datas);
        if(listView != null){
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("dialogID", adapter.mDataList.get(i).getId());
                    startActivity(context, ChatActivity.class, bundle);
                }
            });
        }
    }



}
