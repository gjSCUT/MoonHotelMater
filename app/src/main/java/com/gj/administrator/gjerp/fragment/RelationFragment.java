package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.activity.ChatActivity;
import com.gj.administrator.gjerp.adapter.ContactAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.dao.BookRecordDao;
import com.gj.administrator.gjerp.dao.CustomerDao;
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.PartnerDao;
import com.gj.administrator.gjerp.dao.StaffDao;
import com.gj.administrator.gjerp.dao.SupplierDao;
import com.gj.administrator.gjerp.domain.Customer;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.domain.Partner;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.Supplier;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.gj.administrator.gjerp.util.SessionUtil;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * manage fragment
 * Created by guojun on 2015/12/14
 */
public class RelationFragment extends BaseFragment{
    private static final String[] states = {"Staff","Guest","Partner","Supplier"};
    protected Context context;
    private GridView gridView;
    int type;
    RecyclerView relationRecycler;
    EditText findEt;
    FloatingActionButton fab;
    ContactAdapter adapter;
    DaoSession daoSession;

    public static RelationFragment getInstance(Context context) {
        RelationFragment mf = new RelationFragment();
        mf.context = context;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_relation, container, false);
        daoSession = DBUtil.getDaoSession(context);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        gridView = (GridView) findViewById(R.id.gird_view);
        relationRecycler = (RecyclerView)findViewById(R.id.relationRecyclerView);
        relationRecycler.setLayoutManager(new LinearLayoutManager(context));
        findEt = (EditText)findViewById(R.id.find_editer);
        fab = (FloatingActionButton)findViewById(R.id.fab);
    }

    @Override
    protected void initEvents() {
        ArrayList<HashMap<String, Object>> lstImageItems = new ArrayList<>();
        for (int i = 0; i < states.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("itemText", states[i]);
            lstImageItems.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(
                context,
                lstImageItems,
                R.layout.grid_items_relation,
                new String[]{"itemText"},
                new int[]{R.id.task_test}
        );

        gridView.setAdapter(sa);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                type = i;
                List<ContactAdapter.ListData> datas = getDatas(i);
                setAdapter(datas);
            }
        });
        List<ContactAdapter.ListData> datas = getDatas(0);
        setAdapter(datas);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<ContactAdapter.ListData> datas = new ArrayList<>();
                String input = s.toString();
                QueryBuilder qb;
                switch (type){
                    case 0:
                        qb = daoSession.getStaffDao().queryBuilder();
                        qb.whereOr(StaffDao.Properties.Name.like(input + "%"), StaffDao.Properties.Positon.like(input + "%"));
                        for(Staff staff: (List<Staff>)qb.list()){
                            datas.add(new ContactAdapter.ListData(
                                    staff.getName(),staff.getName().substring(0, 1),staff.getPositon(),staff.getId()
                            ));
                        }
                        setAdapter(datas);
                        break;
                    case 1:
                        qb = daoSession.getCustomerDao().queryBuilder();
                        qb.whereOr(CustomerDao.Properties.Name.like(input + "%"), CustomerDao.Properties.Nationality.like(input + "%"));
                        for(Customer customer:(List<Customer>)qb.list()){
                            datas.add(new ContactAdapter.ListData(
                                    customer.getName(),customer.getName().substring(0, 1),customer.getNationality(),customer.getId()
                            ));
                        }
                        break;
                    case 2:
                        qb = daoSession.getPartnerDao().queryBuilder();
                        qb.whereOr(PartnerDao.Properties.Name.like(input + "%"), PartnerDao.Properties.Company.like(input + "%"));
                        for(Partner partner:(List<Partner>)qb.list()){
                            datas.add(new ContactAdapter.ListData(
                                    partner.getName(),partner.getName().substring(0, 1),partner.getCompany(),partner.getId()
                            ));
                        }
                        break;
                    case 3:
                        qb = daoSession.getSupplierDao().queryBuilder();
                        qb.whereOr(SupplierDao.Properties.Name.like(input + "%"), SupplierDao.Properties.Company.like(input + "%"));
                        for(Supplier supplier:(List<Supplier>)qb.list()){
                            datas.add(new ContactAdapter.ListData(
                                    supplier.getName(),supplier.getName().substring(0, 1),supplier.getCompany(),supplier.getId()
                            ));
                        }
                        break;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private List<ContactAdapter.ListData> getDatas(int i){
        List<ContactAdapter.ListData> datas = new ArrayList<>();
        switch (i) {
            case 0:
                List<Staff> staffs = daoSession.getStaffDao().loadAll();
                for(Staff staff:staffs){
                    datas.add(new ContactAdapter.ListData(
                            staff.getName(),staff.getName().substring(0, 1),staff.getPositon(),staff.getId()
                    ));
                }
                break;
            case 1:
                List<Customer> customers = daoSession.getCustomerDao().loadAll();
                for(Customer customer:customers){
                    datas.add(new ContactAdapter.ListData(
                            customer.getName(),customer.getName().substring(0, 1),customer.getNationality(),customer.getId()
                    ));
                }
                break;
            case 2:
                List<Partner> partners = daoSession.getPartnerDao().loadAll();
                for(Partner partner:partners){
                    datas.add(new ContactAdapter.ListData(
                            partner.getName(),partner.getName().substring(0, 1),partner.getCompany(),partner.getId()
                    ));
                }
                break;
            case 3:
                List<Supplier> suppliers = daoSession.getSupplierDao().loadAll();
                for(Supplier supplier:suppliers){
                    datas.add(new ContactAdapter.ListData(
                            supplier.getName(),supplier.getName().substring(0, 1),supplier.getCompany(),supplier.getId()
                    ));
                }
                break;
            default:
                break;
        }
        return datas;
    }

    private void setAdapter(List<ContactAdapter.ListData> datas){
        adapter = new ContactAdapter(
                context,
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
                switch (type) {
                    case 0:
                        Staff staff = daoSession.getStaffDao().load(adapter.getDataList().get(position).contact_id);
                        Dialog dialog = staff.getDialog();
                        if(dialog == null){
                            dialog = new Dialog(null,Dialog.TYPE_CHAT,null);
                            daoSession.getDialogDao().insert(dialog);
                            Message message = new Message(
                                    null,
                                    "Begin to chat",
                                    Message.CREATED,
                                    true,
                                    new Date(),
                                    dialog.getId()
                            );
                            daoSession.getMessageDao().insert(message);

                            dialog.setLast_time(message.getMsg_time());
                            daoSession.getDialogDao().insertOrReplace(dialog);

                            staff.setDialog(dialog);
                            daoSession.getStaffDao().insertOrReplace(staff);
                        }

                        Bundle bundle = new Bundle();
                        bundle.putLong("dialogID", dialog.getId());
                        startActivity(context, ChatActivity.class, bundle);;
                        break;
                    case 1:


                    case 2:

                        break;
                    case 3:

                        break;
                    default:
                        break;
                }
            }
        });
        relationRecycler.setAdapter(adapter);
    }
}
