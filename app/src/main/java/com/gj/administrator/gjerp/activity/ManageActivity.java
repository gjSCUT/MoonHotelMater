package com.gj.administrator.gjerp.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.RecyclerAdapter;
import com.gj.administrator.gjerp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;


public class ManageActivity extends BaseActivity {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        final ActionBar ab = getSupportActionBar();
        if(ab!=null)
            ab.setDisplayHomeAsUpEnabled(true);
        initViews();
        initEvents();
    }

    @Override
    protected void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<RecyclerAdapter.ListData> data = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            data.add(new RecyclerAdapter.ListData("item" + i,"i",null));
        }
        adapter = new RecyclerAdapter(
                mContext,
                R.layout.dp40_list_items,
                data,
                RecyclerAdapter.DRAWABLE_TYPE.SAMPLE_ROUND_RECT_BORDER,
                true
                );
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void initEvents() {
        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                recyclerView,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        int id = (int) recyclerView.getChildItemId(view);
                        adapter.mDataList.remove(id);
                        adapter.notifyDataSetChanged();

                    }
                })
                .setIsVertical(false)
                .setItemTouchCallback(
                        new SwipeDismissRecyclerViewTouchListener.OnItemTouchCallBack() {
                            @Override
                            public void onTouch(int index) {

                            }
                        })
                .create();

        recyclerView.setOnTouchListener(listener);
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
