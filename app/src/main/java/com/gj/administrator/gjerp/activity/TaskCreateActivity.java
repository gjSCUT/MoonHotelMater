package com.gj.administrator.gjerp.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.ContactAdapter;
import com.gj.administrator.gjerp.base.BaseActivity;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.StaffDao;
import com.gj.administrator.gjerp.dao.SupplierDao;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Partner;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.StaffsTasks;
import com.gj.administrator.gjerp.domain.Supplier;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.gj.administrator.gjerp.util.LogUtil;
import com.gj.administrator.gjerp.util.SessionUtil;
import com.melnykov.fab.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.event.EventBus;


public class TaskCreateActivity extends BaseActivity {
    EditText taskTitle,taskContent;
    TextView taskLeader,taskStaffs,startTime,endTime,period,partnerTv,supplierTv;
    Button leaderSet,staffSet,startSet,endSet,create,periodSet,partnerSet,supplierSet;
    Date startDate,endDate;
    Long leaderId,partnerId,supplierId;
    int periodInt;
    int year, monthOfYear, dayOfMonth, hourOfDay, minute;

    List<Long> staffsIdList;

    AlertDialog aleartDialog;
    RecyclerView relationRecycler;
    EditText findEt;
    ContactAdapter adapter;
    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Create New Task");
        daoSession = DBUtil.getDaoSession(mContext);
        initViews();
        initEvents();
    }

    @Override
    protected void initViews() {
        taskTitle = (EditText)findViewById((R.id.task_title));
        taskContent = (EditText)findViewById((R.id.task_detail));
        taskLeader = (TextView)findViewById(R.id.task_leader);
        taskStaffs = (TextView)findViewById(R.id.task_staff);
        startTime = (TextView)findViewById(R.id.task_start);
        endTime = (TextView)findViewById(R.id.task_end);
        period = (TextView)findViewById(R.id.task_period);
        partnerTv = (TextView)findViewById(R.id.task_partner);
        supplierTv = (TextView)findViewById(R.id.task_supplier);

        leaderSet = (Button)findViewById(R.id.task_leader_set);
        staffSet = (Button)findViewById(R.id.task_staff_set);
        startSet = (Button)findViewById(R.id.task_start_set);
        endSet = (Button)findViewById(R.id.task_end_set);
        periodSet = (Button)findViewById(R.id.task_period_set);
        partnerSet = (Button) findViewById(R.id.task_partner_set);
        supplierSet = (Button) findViewById(R.id.task_supplier_set);
        create = (Button) findViewById(R.id.task_create);
    }


    private void setLeaderAdapter(List<ContactAdapter.ListData> datas){
        adapter = new ContactAdapter(
                mContext,
                R.layout.contact_list_items,
                datas,
                DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND_BORDER),
                false
        );
        adapter.setOnClickListener(new ContactAdapter.OnClickListener() {
            @Override
            public void OnImageClick(Boolean isChecked) {

            }

            @Override
            public void OnItemClick(int position) {
                leaderId = adapter.getDataList().get(position).contact_id;
                taskLeader.setText(daoSession.getStaffDao().load(leaderId).getName());
                aleartDialog.dismiss();
            }
        });
        relationRecycler.setAdapter(adapter);
    }

    private void setPartnerAdapter(List<ContactAdapter.ListData> datas){
        adapter = new ContactAdapter(
                mContext,
                R.layout.contact_list_items,
                datas,
                DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND_BORDER),
                false
        );
        adapter.setOnClickListener(new ContactAdapter.OnClickListener() {
            @Override
            public void OnImageClick(Boolean isChecked) {

            }

            @Override
            public void OnItemClick(int position) {
                partnerId = adapter.getDataList().get(position).contact_id;
                partnerTv.setText(daoSession.getPartnerDao().load(partnerId).getName());
                aleartDialog.dismiss();
            }
        });
        relationRecycler.setAdapter(adapter);
    }

    private void setSupplierAdapter(List<ContactAdapter.ListData> datas){
        adapter = new ContactAdapter(
                mContext,
                R.layout.contact_list_items,
                datas,
                DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND_BORDER),
                false
        );
        adapter.setOnClickListener(new ContactAdapter.OnClickListener() {
            @Override
            public void OnImageClick(Boolean isChecked) {

            }

            @Override
            public void OnItemClick(int position) {
                supplierId = adapter.getDataList().get(position).contact_id;
                supplierTv.setText(daoSession.getSupplierDao().load(supplierId).getName());
                aleartDialog.dismiss();
            }
        });
        relationRecycler.setAdapter(adapter);
    }

    private void setStaffsAdapter(List<ContactAdapter.ListData> datas){
        adapter = new ContactAdapter(
                mContext,
                R.layout.contact_list_items,
                datas,
                DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_RECT_BORDER),
                true
        );
        adapter.setOnClickListener(new ContactAdapter.OnClickListener() {
            @Override
            public void OnImageClick(Boolean isChecked) {

            }

            @Override
            public void OnItemClick(int position) {

            }
        });
        relationRecycler.setAdapter(adapter);
    }


    @Override
    protected void initEvents() {
        leaderSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_staff, null);
                findEt= (EditText) layout.findViewById(R.id.find_editer);
                relationRecycler = (RecyclerView)layout.findViewById(R.id.relationRecyclerView);

                relationRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                List<ContactAdapter.ListData> datas = new ArrayList<>();
                List<Staff> staffs = daoSession.getStaffDao().loadAll();
                for(Staff staff:staffs){
                    datas.add(new ContactAdapter.ListData(
                            staff.getName(),staff.getName().substring(0, 1),staff.getPositon(),staff.getId()
                    ));
                }
                setLeaderAdapter(datas);
                findEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        List<ContactAdapter.ListData> datas = new ArrayList<>();
                        String input = s.toString();
                        QueryBuilder qb;
                        qb = daoSession.getStaffDao().queryBuilder();
                        qb.whereOr(StaffDao.Properties.Name.like(input + "%"), StaffDao.Properties.Positon.like(input + "%"));
                        for (Staff staff : (List<Staff>) qb.list()) {
                            datas.add(new ContactAdapter.ListData(
                                    staff.getName(), staff.getName().substring(0, 1), staff.getPositon(), staff.getId()
                            ));
                        }
                        setLeaderAdapter(datas);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                aleartDialog = new AlertDialog.Builder(mContext)
                        .setTitle("Choose Leader")
                        .setView(layout)
                        .create();
                aleartDialog.show();
            }
        });

        staffSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_staff, null);
                findEt= (EditText) layout.findViewById(R.id.find_editer);
                relationRecycler = (RecyclerView)layout.findViewById(R.id.relationRecyclerView);

                relationRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                List<ContactAdapter.ListData> datas = new ArrayList<>();
                List<Staff> staffs = daoSession.getStaffDao().loadAll();
                for(Staff staff:staffs){
                    datas.add(new ContactAdapter.ListData(
                            staff.getName(),staff.getName().substring(0, 1),staff.getPositon(),staff.getId()
                    ));
                }
                setStaffsAdapter(datas);
                findEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        List<ContactAdapter.ListData> datas = new ArrayList<>();
                        String input = s.toString();
                        QueryBuilder qb;
                        qb = daoSession.getStaffDao().queryBuilder();
                        qb.whereOr(StaffDao.Properties.Name.like(input + "%"), StaffDao.Properties.Positon.like(input + "%"));
                        for (Staff staff : (List<Staff>) qb.list()) {
                            datas.add(new ContactAdapter.ListData(
                                    staff.getName(), staff.getName().substring(0, 1), staff.getPositon(), staff.getId()
                            ));
                        }
                        setStaffsAdapter(datas);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                aleartDialog = new AlertDialog.Builder(mContext)
                        .setTitle("Choose Leader")
                        .setView(layout)
                        .setNegativeButton("sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String staffsString = "Staffs ID:";
                                staffsIdList = new ArrayList<Long>();
                                for(int positon:adapter.getCheckedList()) {
                                    Long staffIdChoose = adapter.getDataList().get(positon).contact_id;
                                    staffsIdList.add(staffIdChoose);
                                    staffsString += staffIdChoose + " ";
                                }
                                taskStaffs.setText(staffsString);
                            }
                        })
                        .create();
                aleartDialog.show();

            }
        });

        startSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        String time = (hourOfDay < 10 ? "0" +hourOfDay :hourOfDay) + ":" + (minute < 10 ? "0" + minute : minute);
                        SimpleDateFormat df=new SimpleDateFormat("hh:mm");
                        try {
                            startDate = df.parse(time);
                        } catch (ParseException e) {
                            LogUtil.e("TaskCreate","wrong date");
                        }
                        startTime.setText("Time: " + time);
                    }
                }, hourOfDay, minute, true);
                timePickerDialog.show();
            }
        });

        endSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        String time = (hourOfDay < 10 ? "0" +hourOfDay :hourOfDay) + ":" + (minute < 10 ? "0" + minute : minute);
                        SimpleDateFormat df=new SimpleDateFormat("hh:mm");
                        try {
                            endDate = df.parse(time);
                        } catch (ParseException e) {
                            LogUtil.e("TaskCreate","wrong date");
                        }
                        endTime.setText("Time: " + time);
                    }
                }, hourOfDay, minute, true);
                timePickerDialog.show();
            }
        });

        periodSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Task Type","which do you want to create between instant and period task?",
                        "Period",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AlertDialog.Builder(mContext)
                                    .setTitle("Choose date")
                                    .setIcon(android.R.drawable.ic_dialog_info)
                                    .setMultiChoiceItems(
                                            new String[]{"Mon", "Tues", "Wed", "Thurs", "Fri", "SAT", "SUN"},
                                            new boolean[]{false, false, false, false, false, false, false},
                                            new DialogInterface.OnMultiChoiceClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                                    if (isChecked)
                                                        periodInt |= Task.PERIODS[which];
                                                    else
                                                        periodInt &=  Task.PERIODS[~which];
                                                }
                                            })
                                    .setNegativeButton("sure", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            period.setText(Task.getPeriodString(periodInt));
                                        }
                                    }).show();
                            }
                        },
                        "Instant",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                periodInt = Task.INSTANT;
                                period.setText(Task.getPeriodString(periodInt));
                            }
                        }
                ).show();
            }
        });

        supplierSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_staff, null);
                findEt= (EditText) layout.findViewById(R.id.find_editer);
                relationRecycler = (RecyclerView)layout.findViewById(R.id.relationRecyclerView);

                relationRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                List<ContactAdapter.ListData> datas = new ArrayList<>();
                List<Supplier> suppliers = daoSession.getSupplierDao().loadAll();
                for(Supplier supplier:suppliers){
                    datas.add(new ContactAdapter.ListData(
                            supplier.getName(),supplier.getName().substring(0, 1),supplier.getCompany(),supplier.getId()
                    ));
                }
                setSupplierAdapter(datas);
                findEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        List<ContactAdapter.ListData> datas = new ArrayList<>();
                        String input = s.toString();
                        QueryBuilder qb;
                        qb = daoSession.getSupplierDao().queryBuilder();
                        qb.whereOr(SupplierDao.Properties.Name.like(input + "%"), SupplierDao.Properties.Company.like(input + "%"));
                        for (Supplier supplier : (List<Supplier>) qb.list()) {
                            datas.add(new ContactAdapter.ListData(
                                    supplier.getName(),supplier.getName().substring(0, 1),supplier.getCompany(),supplier.getId()
                            ));
                        }
                        setSupplierAdapter(datas);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                aleartDialog = new AlertDialog.Builder(mContext)
                        .setTitle("Choose Leader")
                        .setView(layout)
                        .create();
                aleartDialog.show();
            }
        });

        partnerSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_staff, null);
                findEt= (EditText) layout.findViewById(R.id.find_editer);
                relationRecycler = (RecyclerView)layout.findViewById(R.id.relationRecyclerView);

                relationRecycler.setLayoutManager(new LinearLayoutManager(mContext));
                List<ContactAdapter.ListData> datas = new ArrayList<>();
                List<Partner> staffs = daoSession.getPartnerDao().loadAll();
                for(Partner partner:staffs){
                    datas.add(new ContactAdapter.ListData(
                            partner.getName(),partner.getName().substring(0, 1),partner.getCompany(),partner.getId()
                    ));
                }
                setPartnerAdapter(datas);
                findEt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        List<ContactAdapter.ListData> datas = new ArrayList<>();
                        String input = s.toString();
                        QueryBuilder qb;
                        qb = daoSession.getStaffDao().queryBuilder();
                        qb.whereOr(StaffDao.Properties.Name.like(input + "%"), StaffDao.Properties.Positon.like(input + "%"));
                        for (Partner partner : (List<Partner>) qb.list()) {
                            datas.add(new ContactAdapter.ListData(
                                    partner.getName(),partner.getName().substring(0, 1),partner.getCompany(),partner.getId()
                            ));
                        }
                        setPartnerAdapter(datas);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

                aleartDialog = new AlertDialog.Builder(mContext)
                        .setTitle("Choose Leader")
                        .setView(layout)
                        .create();
                aleartDialog.show();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(
                        null,
                        Dialog.TYPE_TASK,
                        null
                );
                daoSession.getDialogDao().insert(dialog);
                Task task = new Task(
                        null,
                        taskTitle.getText().toString(),
                        taskContent.getText().toString(),
                        leaderId,
                        periodInt,
                        "Create",
                        startDate,
                        endDate,
                        0L,
                        partnerId,
                        supplierId,
                        dialog.getId()
                );
                daoSession.getTaskDao().insert(task);
                for(Long staffId:staffsIdList) {
                    StaffsTasks staffsTasks = new StaffsTasks(
                            null,
                            staffId,
                            task.getId()
                    );
                    daoSession.getStaffsTasksDao().insert(staffsTasks);
                }
                com.gj.administrator.gjerp.domain.Message message = new com.gj.administrator.gjerp.domain.Message(
                        null,
                        task.getTitle() + " is created",
                        com.gj.administrator.gjerp.domain.Message.CREATED,
                        true,
                        new Date(),
                        dialog.getId()
                );
                daoSession.getMessageDao().insert(message);

                dialog.setLast_time(message.getMsg_time());
                daoSession.getDialogDao().insertOrReplace(dialog);

                EventBus.getDefault().post(message);
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
