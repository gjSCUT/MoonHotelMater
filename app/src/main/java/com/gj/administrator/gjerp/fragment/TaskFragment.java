package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.RecyclerAdapter;
import com.gj.administrator.gjerp.adapter.RecyclerListAdapter;
import com.gj.administrator.gjerp.adapter.TaskAdapter;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.TaskDao;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.github.codefalling.recyclerviewswipedismiss.SwipeDismissRecyclerViewTouchListener;

/**
 * Room fragment
 * Created by guojun on 2015/12/07
 */
public class TaskFragment extends BaseFragment {
    // this is the descriptions used in the main page, under the descriptions
    String[] states = new String[]{"Tasks", "Schedule"};
    protected Context context;
    private GridView gridView;
    RecyclerView todoRecycler;
    RecyclerView dataRecycler;
    FloatingActionButton fab;
    RecyclerAdapter adapter;
    List<Task> todos;
    List<Task> datas;
    TaskDao taskDao;


    public static TaskFragment getInstance(Context context) {
        TaskFragment mf = new TaskFragment();
        mf.context = context;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_task, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void initViews() {
        gridView = (GridView) findViewById(R.id.gird_view);
        todoRecycler = (RecyclerView)findViewById(R.id.todoRecyclerView);
        dataRecycler = (RecyclerView)findViewById(R.id.dataRecyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        todoRecycler.setLayoutManager(new LinearLayoutManager(context));
        dataRecycler.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initEvents() {

        if (gridView != null) {
            setupGridContent();
        }
        setupTaskContent();
        setupScheduleContent();
        setupFabContent();

    }
    private void setupTaskContent() {
        taskDao = BaseApplication.getDaoSession(context).getTaskDao();
        todos= taskDao.loadAll();
        final TaskAdapter adapter = new TaskAdapter(
                context,
                R.layout.task_item,
                todos);
        adapter.setOnClickListener(new TaskAdapter.OnClickListener() {
           @Override
           public void OnItemClick(int position) {

           }
        });
        SwipeDismissRecyclerViewTouchListener listener = new SwipeDismissRecyclerViewTouchListener.Builder(
                todoRecycler,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(View view) {
                        int position = todoRecycler.getChildAdapterPosition(view);
                        taskDao.delete(adapter.dataList.get(position));
                        adapter.dataList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setIsVertical(false)
                .create();
        todoRecycler.setOnTouchListener(listener);
        todoRecycler.setAdapter(adapter);

    }

    private void setupScheduleContent() {

    }


    private void setupFabContent() {

    }

    private void setupGridContent() {
        ArrayList<HashMap<String, Object>> lstImageItems = new ArrayList<>();
        for (int i = 0; i < states.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("itemText", states[i]);
            lstImageItems.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(
                context,
                lstImageItems,
                R.layout.grid_items_task,
                new String[]{"itemText"},
                new int[]{R.id.task_test}
        );

        gridView.setAdapter(sa);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    //TODO
                    default:
                        break;
                }
            }
        });
    }

}
