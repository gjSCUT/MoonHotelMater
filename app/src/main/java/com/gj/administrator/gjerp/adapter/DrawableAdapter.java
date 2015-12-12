package com.gj.administrator.gjerp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gj.administrator.gjerp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * the adapter of drawable list
 * Created by guojun on 2015/12/08
 */
public class DrawableAdapter extends BaseAdapter {
    protected Context context;
    private static final int HIGHLIGHT_COLOR = 0x999be6ff;
    // this is the descriptions used in the main page, under the descriptions
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder;
    private boolean canCheckView;
    private int itemId;
    // list of data items
    public List<ListData> mDataList;
    public DrawableAdapter(Context context, int itemId, String[] datas, TextDrawable.IBuilder mDrawableBuilder, boolean isRandomColor, boolean canCheckView) {
        this.context = context;
        this.itemId = itemId;
        this.mDataList = new ArrayList<>();
        this.canCheckView = canCheckView;
        for(String s:datas)
            this.mDataList.add(new ListData(s,mDrawableBuilder,mColorGenerator,isRandomColor));
        this.mDrawableBuilder = mDrawableBuilder;
    }


    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public ListData getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, itemId, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListData item = getItem(position);

        // provide support for selected state
        updateCheckedState(holder, item);
        if(canCheckView)
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // when the image is clicked, update the selected state
                    ListData data = getItem(position);
                    data.setChecked(!data.isChecked);
                    updateCheckedState(holder, data);
                }
            });
        holder.textView.setText(item.data);

        return convertView;
    }

    private void updateCheckedState(ViewHolder holder, ListData item) {
        if (item.isChecked) {
            holder.imageView.setImageDrawable(mDrawableBuilder.build(" ", 0xff616161));
            holder.view.setBackgroundColor(HIGHLIGHT_COLOR);
            holder.checkIcon.setVisibility(View.VISIBLE);
        }
        else {
            holder.imageView.setImageDrawable(item.drawable);
            holder.view.setBackgroundColor(Color.TRANSPARENT);
            holder.checkIcon.setVisibility(View.GONE);
        }
    }

    private static class ViewHolder {
        private View view;
        private ImageView imageView;
        private TextView textView;
        private ImageView checkIcon;
        private ViewHolder(View view) {
            this.view = view;
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textView = (TextView) view.findViewById(R.id.textView);
            checkIcon = (ImageView) view.findViewById(R.id.check_icon);
        }

    }

    private static class ListData {
        private String data;
        private TextDrawable drawable;
        private boolean isChecked;
        public ListData(String data, TextDrawable.IBuilder mDrawableBuilder, ColorGenerator mColorGenerator, boolean isRandomColor) {
            this.data = data;
            drawable = mDrawableBuilder.build(String.valueOf(data.charAt(0)), isRandomColor ? mColorGenerator.getRandomColor() : mColorGenerator.getColor(data));
        }
        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}

