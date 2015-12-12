package com.gj.administrator.gjerp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.adapter.DrawableAdapter;
import com.gj.administrator.gjerp.base.BaseApplication;
import com.gj.administrator.gjerp.base.BaseFragment;
import com.gj.administrator.gjerp.domain.Hotel;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.gj.administrator.gjerp.util.SessionUtil;

/**
 * manage fragment
 * Created by guojun on 2015/12/09
 */
public class SelectFragment extends BaseFragment{
    protected Context context;
    private ListView listView;


    private OnSelectHotelListener hotelListener;

    public interface OnSelectHotelListener{
        void setHotel(String hotel);
    }

    public static SelectFragment getInstance(Context context, OnSelectHotelListener hotelListener) {
        SelectFragment mf = new SelectFragment();
        mf.context = context;
        mf.hotelListener = hotelListener;
        return mf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_select, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void initViews() {
        listView = (ListView) findViewById(R.id.select_list);
    }

    @Override
    protected void initEvents() {
        if(listView != null){
            listView.setAdapter(new DrawableAdapter(
                    context,
                    R.layout.dp60_list_items,
                    SessionUtil.getHotelnames(),
                    DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND_RECT_BORDER),
                    true, false));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Hotel hotel = BaseApplication.getDaoSession(context).getHotelDao().load((long)i+1);
                    SessionUtil.setHotel(hotel);

                    SharedPreferences preferences = context.getSharedPreferences("gjerp",context.MODE_PRIVATE);
                    preferences.edit().putString("loginHotel", String.valueOf(i));
                    hotelListener.setHotel(SessionUtil.getHotelnames()[i]);

                    //TODO
                }
            });
        }
    }



}
