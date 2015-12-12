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

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.ChatAdapter;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.util.SessionUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ChatActivity extends BaseActivity {
    ListView chatListView;
    Button sendBtn;
    EditText editText;
    long dialogID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle("Chat");
        actionBar.setDisplayHomeAsUpEnabled(true);
        dialogID = this.getIntent().getExtras().getLong("dialogID");
        initViews();
        initEvents();

    }

    @Override
    protected void initViews() {
        chatListView = (ListView) findViewById(R.id.chat_list);
        sendBtn = (Button) findViewById(R.id.drawtab_chat_send);
        editText = (EditText) findViewById(R.id.drawtab_chat_editer);
    }

    @Override
    protected void initEvents() {
        DaoSession session = BaseApplication.getDaoSession(mContext);
        List<Message> datas = session.getDialogDao().load(dialogID).getMessages();

        final ChatAdapter adapter = new ChatAdapter(mContext,datas);

        chatListView.setAdapter(adapter);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Message data = new Message(
                        null,
                        editText.getText().toString(),
                        SessionUtil.getUser().getUsername(),
                        "Manager",
                        new Date(),
                        null,
                        null,
                        dialogID
                );
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
