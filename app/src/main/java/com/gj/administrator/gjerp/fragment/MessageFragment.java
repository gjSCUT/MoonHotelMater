package com.gj.administrator.gjerp.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.activity.ChatActivity;
import com.gj.administrator.gjerp.activity.TaskItemActivity;
import com.gj.administrator.gjerp.adapter.MessageAdapter;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.dao.DialogDao;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DBUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * manage fragment
 * Created by guojun on 2015/12/14
 */
public class MessageFragment extends BaseFragment{
    private static final String[] items = {"Rooms Manager","Travels Manager","Guest Manager","Employees Manager"};
    protected Context context;
    private ListView listView;
    private MessageAdapter adapter;
    private NotificationManager manager;

    public static MessageFragment getInstance(Context context) {
        MessageFragment mf = new MessageFragment();
        mf.context = context;
        return mf;
    }

    public void listenMessage(Message message) {
        Dialog dialog = message.getDialog();
        boolean find = false;
        for(Dialog data:adapter.mDataList) {
            if (dialog.getId() == data.getId()) {
                adapter.mDataList.remove(data);
                adapter.mDataList.add(0, dialog);
                adapter.notifyDataSetChanged();
                find = true;
                break;
            }
        }
        if(!find) {
            adapter.mDataList.add(0, dialog);
            adapter.notifyDataSetChanged();
        }

        Notification notification = new NotificationCompat.Builder(context)
                .setTicker(new SimpleDateFormat("HH:mm:ss").format(message.getMsg_time()))
                .setContentTitle("News : " + Message.MESSAGE_TYPE[message.getMsg_type()])
                .setContentText(message.getContent())
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .build();;
        manager.notify(0, notification);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_message, container, false);
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onStop(){
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

        List<Dialog> datas = DBUtil.getDaoSession(context)
                .getDialogDao()
                .queryBuilder()
                .orderDesc(DialogDao.Properties.Last_time)
                .list();

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
                    Dialog dialog =  adapter.mDataList.get(i);
                    bundle.putLong("dialogID", adapter.mDataList.get(i).getId());
                    if (dialog.getDialog_type() == Dialog.TYPE_CHAT)
                        startActivity(context, ChatActivity.class, bundle);
                    else if(dialog.getDialog_type()== Dialog.TYPE_TASK)
                        startActivity(context, TaskItemActivity.class, bundle);

                }
            });
        }
    }



}
