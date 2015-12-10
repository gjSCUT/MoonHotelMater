package com.gj.administrator.gjerp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.gj.administrator.gjerp.R;

/**
 * Created by Kenny on 2015/12/9.
 */
public class RoomStateAdapter extends BaseAdapter {

    private Context mContext;

    public RoomStateAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mTypeIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(40, 80));
            textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(
                String.format(
                        mContext.getString(mTypeIds[position]),
                        position
                ));
        textView.setTextSize(1, 16);
        return textView;
    }

    private Integer[] mTypeIds = {
            R.string.room_type_blank, R.string.room_type_dirty, R.string.room_type_in_use,
            R.string.room_type_repair, R.string.room_type_in_use_dirty, R.string.room_type_total
    };
}
