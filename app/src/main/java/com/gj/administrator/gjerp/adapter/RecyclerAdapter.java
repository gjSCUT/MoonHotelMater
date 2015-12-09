package com.gj.administrator.gjerp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gj.administrator.gjerp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * the adapter of drawable list
 * Created by guojun on 2015/12/09
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    protected Context context;
    private static final int HIGHLIGHT_COLOR = 0x999be6ff;
    // this is the descriptions used in the main page, under the descriptions
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder;
    private boolean isRandomColor;
    private boolean canCheckView;
    private int itemId;
    // list of data items
    public List<ListData> mDataList;
    public enum DRAWABLE_TYPE{
        SAMPLE_RECT, SAMPLE_ROUND_RECT,SAMPLE_ROUND,
        SAMPLE_RECT_BORDER,SAMPLE_ROUND_RECT_BORDER, SAMPLE_ROUND_BORDER
    }
    public RecyclerAdapter(Context context, int itemId, String[] datas, DRAWABLE_TYPE type, boolean isRandomColor, boolean canCheckView) {
        this.context = context;
        this.itemId = itemId;
        this.mDataList = new ArrayList<>();
        for(String s:datas)
            this.mDataList.add(new ListData(s));
        this.isRandomColor = isRandomColor;
        this.canCheckView = canCheckView;
        // initialize the builder based on the "TYPE"
        switch (type) {
            case SAMPLE_RECT:
                mDrawableBuilder = TextDrawable.builder()
                        .rect();
                break;
            case SAMPLE_ROUND_RECT:
                mDrawableBuilder = TextDrawable.builder()
                        .roundRect(10);
                break;
            case SAMPLE_ROUND:
                mDrawableBuilder = TextDrawable.builder()
                        .round();
                break;
            case SAMPLE_RECT_BORDER:
                mDrawableBuilder = TextDrawable.builder()
                        .beginConfig()
                        .withBorder(4)
                        .endConfig()
                        .rect();
                break;
            case SAMPLE_ROUND_RECT_BORDER:
                mDrawableBuilder = TextDrawable.builder()
                        .beginConfig()
                        .withBorder(4)
                        .endConfig()
                        .roundRect(10);
                break;
            case SAMPLE_ROUND_BORDER:
                mDrawableBuilder = TextDrawable.builder()
                        .beginConfig()
                        .withBorder(4)
                        .endConfig()
                        .round();
                break;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, itemId , null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        ListData item =  mDataList.get(position);
        // provide support for selected state
        updateCheckedState(holder, item);
        if(canCheckView)
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // when the image is clicked, update the selected state
                    ListData data = mDataList.get(position);
                    data.setChecked(!data.isChecked);
                    updateCheckedState(holder, data);
                }
            });
        holder.textView.setText(item.data);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount()  {
        return mDataList.size();
    }

    private void updateCheckedState(ViewHolder holder, ListData item) {
        if (item.isChecked) {
            holder.imageView.setImageDrawable(mDrawableBuilder.build(" ", 0xff616161));
            holder.view.setBackgroundColor(HIGHLIGHT_COLOR);
            holder.checkIcon.setVisibility(View.VISIBLE);
        }
        else {
            TextDrawable drawable = mDrawableBuilder.build(String.valueOf(item.data.charAt(0)), isRandomColor ? mColorGenerator.getRandomColor() : mColorGenerator.getColor(item.data));
            holder.imageView.setImageDrawable(drawable);
            holder.view.setBackgroundColor(Color.TRANSPARENT);
            holder.checkIcon.setVisibility(View.GONE);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView imageView;
        private TextView textView;
        private ImageView checkIcon;
        private ViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textView = (TextView) view.findViewById(R.id.textView);
            checkIcon = (ImageView) view.findViewById(R.id.check_icon);
        }
    }

    private static class ListData {
        private String data;
        private boolean isChecked;
        public ListData(String data) {
            this.data = data;
        }

        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}

