package com.gj.administrator.gjerp.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.RecyclerAdapter;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.dao.EmployeeDao;
import com.gj.administrator.gjerp.dao.GuestDao;
import com.gj.administrator.gjerp.dao.RoomDao;
import com.gj.administrator.gjerp.domain.Employee;
import com.gj.administrator.gjerp.domain.Guest;
import com.gj.administrator.gjerp.domain.Room;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;


public class ManageActivity extends BaseActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    RecyclerAdapter adapter;
    private int type;
    private GuestDao guestDao;
    private RoomDao roomDao;
    private EmployeeDao employeeDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = this.getIntent().getExtras().getInt("type");
        switch (type){
            case 0:
                roomDao = BaseApplication.getDaoSession(this).getRoomDao();
                break;
            case 1:
                guestDao = BaseApplication.getDaoSession(this).getGuestDao();
                break;
            case 2:
                guestDao = BaseApplication.getDaoSession(this).getGuestDao();
                break;
            case 3:
                employeeDao = BaseApplication.getDaoSession(this).getEmployeeDao();
                break;
        }
        setContentView(R.layout.activity_manage);
        actionBar.setDisplayHomeAsUpEnabled(true);
        initViews();
        initEvents();
    }

    @Override
    protected void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initEvents() {
        //init data
        List<RecyclerAdapter.ListData> data = new ArrayList<>();
        switch (type){
            case 0:
                List<Room> roomList = roomDao.loadAll();
                for (Room item:roomList){
                    data.add(new RecyclerAdapter.ListData(item.getNum(),item.getNum().substring(0, 1),null));
                }
                break;
            case 1:
                //TODO
            case 2:
                List<Guest> guestList = guestDao.loadAll();
                for (Guest item:guestList){
                    data.add(new RecyclerAdapter.ListData(item.getName(),item.getName().substring(0,1),null));
                }
                break;
            case 3:
                List<Employee> employeeList = employeeDao.loadAll();
                for (Employee item:employeeList){
                    data.add(new RecyclerAdapter.ListData(item.getName(),item.getName().substring(0,1),null));
                }
                break;
        }

        adapter = new RecyclerAdapter(
                mContext,
                R.layout.dp40_list_items,
                data,
                DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND_RECT_BORDER),
                true
                );
        adapter.setOnClickListener(new RecyclerAdapter.OnClickListener() {
            @Override
            public void OnImageClick(Boolean isChecked) {
                if(!adapter.getCheckedList().isEmpty()) {
                    fab.setImageResource(R.mipmap.ic_delete);
                }
                else{
                    fab.setImageResource(R.mipmap.ic_add_white);
                }
            }
            @Override
            public void OnItemClick(int position) {
                startActivity(ManageRoomActivity.class);
            }
        });
        recyclerView.setAdapter(adapter);
        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                recyclerView,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        long id = recyclerView.getChildAdapterPosition(view);
                        removeItem(id);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setIsVertical(false)
                .create();
        recyclerView.setOnTouchListener(listener);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!adapter.getCheckedList().isEmpty()) {
                    fab.setImageResource(R.mipmap.ic_add_white);
                    for (int item : adapter.getCheckedList())
                        removeItem((long) item);
                    adapter.getCheckedList().clear();
                    adapter.notifyDataSetChanged();
                } else {

                    // /startActivity(ManageRoomActivity.class);
                }
            }
        });
    }

    private void startUpdateActivity(Long id){
        switch (type){
            case 0:
                roomDao.deleteByKey(id);
                break;
            case 1:
                //TODO
                break;
            case 2:
                guestDao.deleteByKey(id);
                break;
            case 3:
                employeeDao.deleteByKey(id);
                break;
        }
        adapter.getDataList().remove(id);
    }

    private void removeItem(Long id){
        switch (type){
            case 0:
                roomDao.deleteByKey(id);
                break;
            case 1:
                //TODO
                break;
            case 2:
                guestDao.deleteByKey(id);
                break;
            case 3:
                employeeDao.deleteByKey(id);
                break;
        }
        adapter.getDataList().remove(id);
    }

    private void updateItem(Object object){

        switch (type){
            case 0:
                Room room = (Room) object;
                roomDao.insertOrReplace(room);
                adapter.getDataList().add(new RecyclerAdapter.ListData(room.getNum(),room.getNum(),null));
                break;
            case 1:
                //TODO
                break;
            case 2:
                Guest guest = (Guest) object;
                guestDao.insertOrReplace(guest);
                adapter.getDataList().add(new RecyclerAdapter.ListData(guest.getName(),guest.getName(),null));
                break;
            case 3:
                Employee employee = (Employee) object;
                employeeDao.insertOrReplace((Employee) object);
                adapter.getDataList().add(new RecyclerAdapter.ListData(employee.getName(),employee.getName(),null));
                break;
        }
        adapter.notifyDataSetChanged();
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
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
