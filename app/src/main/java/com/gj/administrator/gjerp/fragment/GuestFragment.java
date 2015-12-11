package com.gj.administrator.gjerp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.RecyclerListAdapter;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class GuestFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String TAG = "GuestFragment";
    protected Context context;
    private static RecyclerView mRecyclerView;
    private static RecyclerListAdapter mAdapter;

    private List<RecyclerListAdapter.GuestData> mGuestDataList;
    private int todayIncome;
    private int guestCount;

    public GuestFragment() {
        // Required empty public constructor
        mGuestDataList = new ArrayList<>();
    }

    public static GuestFragment getInstance(Context context) {
        GuestFragment fragment = new GuestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.context = context;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_guest, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.guest_state_recyclerView);
    }

    @Override
    protected void initEvents() {
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setHasFixedSize(true);
        fetchGuestList();
        mAdapter = new RecyclerListAdapter(context, mGuestDataList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        TextView textView = (TextView) findViewById(R.id.guest_state_in_summary);
        textView.setText(new String(
                String.format(
                        getString(R.string.guest_state_in_summary_textView),
                        guestCount,
                        todayIncome
                )
        ));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void fetchGuestList() {
        //TODO: replace placeholder data generator with proper operations.
        int temp = 0;
        todayIncome = 0;
        guestCount = 0;
        for (int i = 0; i < 10; i ++) {
            temp = 800 + i;
            String name = "Guest No." + i;
            String timeSpanPlaceHolder = "PlaceHolder";
            todayIncome += temp;
            mGuestDataList.add(new RecyclerListAdapter.GuestData("" + temp, name, "" + temp, timeSpanPlaceHolder));
        }
        guestCount = mGuestDataList.size();

    }

}
