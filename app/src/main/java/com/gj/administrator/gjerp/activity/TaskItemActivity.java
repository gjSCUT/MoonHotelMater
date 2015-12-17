package com.gj.administrator.gjerp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.ChatAdapter;
import com.gj.administrator.gjerp.adapter.TaskItemAdapter;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.StaffsTasksDao;
import com.gj.administrator.gjerp.dao.TaskDao;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.StaffsTasks;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DBUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;


public class TaskItemActivity extends BaseActivity {
    ListView chatListView;
    Button sendBtn;
    EditText editText;
    TextView contentTv,groupTv,contactTv,timeTv;

    long dialogID;
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_item);
        setTitle("Task");
        actionBar.setDisplayHomeAsUpEnabled(true);
        dialogID = this.getIntent().getExtras().getLong("dialogID");
        task = DBUtil.getDaoSession(mContext)
                .getTaskDao()
                .queryBuilder()
                .where(TaskDao.Properties.Dialog_id.eq(
                                dialogID)
                )
                .unique();
        setTitle(task.getTitle());

        initViews();
        initEvents();

    }

    @Override
    protected void initViews() {
        chatListView = (ListView) findViewById(R.id.chat_list);
        sendBtn = (Button) findViewById(R.id.drawtab_chat_send);
        editText = (EditText) findViewById(R.id.drawtab_chat_editer);
        contentTv = (TextView)findViewById(R.id.task_content);
        contactTv = (TextView)findViewById(R.id.task_contact);
        groupTv = (TextView)findViewById(R.id.task_group);
        timeTv = (TextView)findViewById(R.id.task_time);
    }

    @Override
    protected void initEvents() {
        contentTv.setText(task.getContent());
        StringBuilder contact = new StringBuilder();
        if(task.getLeader()!=null)
            contact.append("leader:"+task.getLeader().getName()+"  ");
        if(task.getParenter()!=null)
            contact.append("partner:"+task.getParenter().getName()+"  ");
        if(task.getSupplier()!=null)
            contact.append("supplier:"+task.getSupplier().getName()+"  ");
        contactTv.setText(contact.toString());

        List<StaffsTasks> staffids =  DBUtil.getDaoSession(mContext)
                .getStaffsTasksDao()
                .queryBuilder()
                .where(StaffsTasksDao.Properties.Task_id
                        .eq(task.getId())).list();
        StringBuilder group = new StringBuilder();
        group.append("Group: ");
        for(StaffsTasks staffsTasks:staffids){
            group.append(staffsTasks.getStaff().getName()).append(", ");
        }
        groupTv.setText(group.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        timeTv.setText("Start time:" +
                simpleDateFormat.format(task.getStart_time()) +
                "   Deadline time:" +
                simpleDateFormat.format(task.getFinish_time()));

        DaoSession session = DBUtil.getDaoSession(mContext);
        final Dialog dialog = session.getDialogDao().load(dialogID);
        List<Message> datas = dialog.getMessages();
        final TaskItemAdapter adapter = new TaskItemAdapter(mContext,datas);

        chatListView.setAdapter(adapter);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message data = new Message(
                        null,
                        editText.getText().toString(),
                        Message.PROCESS,
                        true,
                        new Date(),
                        dialogID
                );

                dialog.setLast_time(data.getMsg_time());
                DBUtil.getDaoSession(mContext).getDialogDao().insertOrReplace(dialog);
                DBUtil.getDaoSession(mContext).getMessageDao().insert(data);
                EventBus.getDefault().post(data);

                adapter.datas.add(data);
                adapter.notifyDataSetChanged();
                editText.setText("");
                View view = getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

            }
        });
    }

    @Override
    protected void processMessage(android.os.Message msg) {

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
}
