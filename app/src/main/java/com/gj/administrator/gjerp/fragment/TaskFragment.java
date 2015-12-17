package com.gj.administrator.gjerp.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.activity.TaskCreateActivity;
import com.gj.administrator.gjerp.adapter.RecyclerAdapter;
import com.gj.administrator.gjerp.adapter.TaskAdapter;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.dao.TaskDao;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.SessionUtil;
import com.melnykov.fab.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Room fragment
 * Created by guojun on 2015/12/14
 */
public class TaskFragment extends BaseFragment {
    // this is the descriptions used in the main page, under the descriptions
    String[] states = new String[]{"Tasks", "Schedule"};
    protected Context context;
    private GridView gridView;
    RecyclerView todoRecycler;
    FloatingActionButton fab;
    RecyclerAdapter adapter;
    List<Task> todos;
    TaskDao taskDao;
    private int year, monthOfYear, dayOfMonth, hourOfDay, minute;


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
        fab = (FloatingActionButton) findViewById(R.id.fab);
        todoRecycler.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initEvents() {

        if (gridView != null) {
            setupGridContent();
        }
        setupTaskContent();
        setupFabContent();

    }
    private void setupTaskContent() {
        taskDao = DBUtil.getDaoSession(context).getTaskDao();
        todos= taskDao.queryBuilder()
                .where(TaskDao.Properties.Leader_id.eq(SessionUtil.getStaff().getId()),
                        TaskDao.Properties.Start_time.le(new Date()),
                        TaskDao.Properties.Finish_time.ge(new Date()))
                .list();
        final TaskAdapter adapter = new TaskAdapter(
                context,
                R.layout.task_item,
                todos);
        adapter.setOnClickListener(new TaskAdapter.OnClickListener() {
            @Override
            public void OnItemClick(int position) {

            }
        });
        todoRecycler.setAdapter(adapter);

    }

    private void setupFabContent() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(context, TaskCreateActivity.class);
            }
        });
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
                    case 0:
                        break;
                    case 1:
                        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year1, int month, int day) {
                                // TODO Auto-generated method stub
                                year = year1;
                                monthOfYear = month;
                                dayOfMonth = day;
                                String time = new StringBuilder().append(year1).append("-")
                                        .append((month + 1) < 10 ? 0 + (month + 1) : (month + 1))
                                        .append("-")
                                        .append((day < 10) ? 0 + day : day).toString();

                                Date chooseDate;
                                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    chooseDate = df.parse(time);
                                } catch (ParseException e) {
                                    LogUtil.e("TaskCreate", "wrong date");
                                }
                                //TODO
                            }
                        },year, monthOfYear,dayOfMonth );
                        datePickerDialog.setTitle("choose the data to check");
                        datePickerDialog.show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

}
