package com.gj.administrator.gjerp.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.domain.Room;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.SessionUtil;


public class ManageRoomActivity extends BaseActivity {

    private EditText roomNum;
    private Spinner roomType;
    private Button addRoomType;
    private Button addRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Item");
        setContentView(R.layout.activity_manage_room);
        actionBar.setDisplayHomeAsUpEnabled(true);
        initViews();
        initEvents();
    }

    @Override
    protected void initViews() {
        roomNum = (EditText)findViewById(R.id.room_num);
        roomType =(Spinner)findViewById(R.id.room_type);
        addRoomType = (Button) findViewById(R.id.addRoomTypeBt);
        addRoom = (Button) findViewById(R.id.addRoomBt);
    }

    @Override
    protected void initEvents() {
        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room room = new Room(null,roomNum.getText().toString(),0, roomType.getSelectedItemId(), SessionUtil.getHotel().getId());
                DBUtil.getDaoSession(mContext).getRoomDao().insert(room);
                finish();
            }
        });
    }

    @Override
    protected void processMessage(Message msg) {

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
