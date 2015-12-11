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
import com.gj.administrator.gjerp.dao.DaoMaster;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.GuestDao;
import com.gj.administrator.gjerp.dao.RoomDao;
import com.gj.administrator.gjerp.domain.Room;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;


public class ManageActivity extends BaseActivity {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    private int type;
    private GuestDao guestDao;
    private RoomDao roomDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = this.getIntent().getExtras().getInt("type");
        switch (type){
            case 0:
            case 1:
            case 2:
            case 3:
                roomDao = BaseApplication.getDaoSession(this).getRoomDao();
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
        List<RecyclerAdapter.ListData> data = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            data.add(new RecyclerAdapter.ListData("item" + i,"i",null));
        }
        final RecyclerAdapter adapter = new RecyclerAdapter(
                mContext,
                R.layout.dp40_list_items,
                data,
                RecyclerAdapter.DRAWABLE_TYPE.SAMPLE_ROUND_RECT_BORDER,
                true
        );
        adapter.setOnClickListener(new RecyclerAdapter.OnClickListener() {
            @Override
            public void OnImageClick(Boolean isChecked) {
                //TODO
            }

            @Override
            public void OnItemClick(int position) {
                startActivity(ManageItemActivity.class);
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
                        int id = recyclerView.getChildAdapterPosition(view);
                        adapter.getDataList().remove(id);
                        adapter.notifyDataSetChanged();


                    }
                })
                .setIsVertical(false)
                .create();

        recyclerView.setOnTouchListener(listener);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!adapter.getCheckedList().isEmpty()){
                    adapter.removeChecked();
                    adapter.notifyDataSetChanged();
                }
                else {

                    /*Guest guest = new Guest(1L, "Guo jun",1, "man","ID card","360728199410300098","15918770336",new Date(),null,null,null,null);
                    long id = Dao.insert(guest);
                    guest = Dao.load(id);*/
                    //startActivity(ManageItemActivity.class);
                }
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
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
