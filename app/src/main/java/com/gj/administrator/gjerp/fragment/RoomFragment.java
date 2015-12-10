package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;

import com.activeandroid.util.Log;
import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.domain.Room;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Room fragment
 * Created by guojun on 2015/12/07
 */
public class RoomFragment extends BaseFragment {
    // this is the descriptions used in the main page, under the descriptions
    String[] states = new String[]{"Empty", "Checking", "Booking", "Outing", "Blocking"};
    Integer[] counts = new Integer[]{0, 0, 0, 0, 0};
    protected Context context;
    private RadioGroup roomGroup;
    private GridView gridView;

    private static Room[] mRoomArray;

    private String TAG = "RoomFragment";

    public static RoomFragment getInstance(Context context) {
        RoomFragment mf = new RoomFragment();
        mf.context = context;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_room, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        gridView = (GridView) findViewById(R.id.room_state_grid);
        roomGroup = (RadioGroup) findViewById(R.id.room_style_group);
    }

    @Override
    protected void initEvents() {
        if (gridView != null) {
            setupGridContent();
        }
        fetchRoomArray();
        roomGroup.check(R.id.room_style_num);
        Fragment roomDisplayOrderedFragment = RoomDisplayOrderedFragment.getInstance(mRoomArray,context);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.room_container, roomDisplayOrderedFragment)
                .commit();

        roomGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.room_style_num:
                        //TODO
                        Fragment roomDisplayOrderedFragment = RoomDisplayOrderedFragment.getInstance(mRoomArray,context);
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.replace(R.id.room_container, roomDisplayOrderedFragment)
                                .commit();
                        break;
                    case R.id.room_style_type:
                        //TODO
                        break;
                    default:
                        break;
                }
            }

        });
    }

    private void setupGridContent() {
        ArrayList<HashMap<String, Object>> lstImageItems = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < states.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemText", states[i]);
            String temp = String.format(
                    getString(R.string.room_string_model),
                    counts[i]
            );
            map.put("roomText", temp);
            lstImageItems.add(map);
        }
        SimpleAdapter sa = new SimpleAdapter(
                context,
                lstImageItems,
                R.layout.room_state_grid_items,
                new String[]{"itemText", "roomText"},
                new int[]{R.id.room_state_text, R.id.room_state_count}
        );

        gridView.setAdapter(sa);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    default:
                        break;
                }
            }
        });
    }

    public void fetchRoomArray() {
        //TODO: insert database operation?
        mRoomArray = new Room[100];
        int temp = 0;
        for(int i = 0; i < mRoomArray.length; i++) {
            mRoomArray[i] = new Room();
            temp = 800 + i;
            mRoomArray[i].rmId = "" + temp;
        }
    }

    public void setCounts(int[] inputCounts) {
        for (int i = 0; i < counts.length; i++) {
            counts[i] = inputCounts[i];
        }
        Log.i(TAG, "Room counts for different types are set.");
    }

}
