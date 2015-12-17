package com.gj.administrator.gjerp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gj.administrator.gjerp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * the adapter of drawable list
 * Created by guojun on 2015/12/13
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
    private OnClickListener onClickListener;
    // list of data items
    private List<ListData> dataList;
    private List<Integer> checkedList;

    public List<Integer> getCheckedList() {
        return checkedList;
    }
    public List<ListData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ListData> dataList) {
        this.dataList = dataList;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnImageClick(Boolean isChecked);
        void OnItemClick(int position);
    }


    public RecyclerAdapter(Context context, int itemId, List<ListData> dataList, TextDrawable.IBuilder mDrawableBuilder,  boolean canCheckView) {
        this.context = context;
        this.itemId = itemId;
        this.dataList = dataList;
        this.checkedList = new ArrayList<>();
        
        this.canCheckView = canCheckView;
        this.mDrawableBuilder = mDrawableBuilder;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(itemId, parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        ListData item =  dataList.get(position);
        // provide support for selected state
        updateCheckedState(holder, item);
        holder.position = position;
        if(canCheckView && holder.imageView!=null)
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // when the image is clicked, update the selected state
                    ListData data = dataList.get(position);
                    data.setChecked(!data.isChecked);
                    updateCheckedState(holder, data);
                    onClickListener.OnImageClick(checkedList.isEmpty());
                }
            });
        if(holder.textView!=null)
            holder.textView.setText(item.data);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount()  {
        return dataList.size();
    }

    private void updateCheckedState(ViewHolder holder, ListData item) {
        if (canCheckView && item.isChecked) {
            checkedList.add(holder.position);
            if(holder.imageView!=null)
                holder.imageView.setImageDrawable(mDrawableBuilder.build(" ", 0xff616161));
            if(holder.view!=null)
                holder.view.setBackgroundColor(HIGHLIGHT_COLOR);
            if(holder.checkIcon!=null)
                holder.checkIcon.setVisibility(View.VISIBLE);
        }
        else {
            checkedList.remove((Integer)holder.position);
            int color;
            if(item.isRandomColor){
                color = mColorGenerator.getRandomColor();
            }
            else if(item.color == null)
                color = mColorGenerator.getColor(item.data);
            else
                color = item.color;
            TextDrawable drawable = mDrawableBuilder.build(item.IconText, color);
            if(holder.imageView!=null)
                holder.imageView.setImageDrawable(drawable);
            if(holder.view!=null)
                holder.view.setBackgroundColor(Color.TRANSPARENT);
            if(holder.checkIcon!=null)
                holder.checkIcon.setVisibility(View.GONE);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private View view;
        private ImageView imageView;
        private TextView textView;
        private ImageView checkIcon;
        public int position;
        private ViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = (ImageView) view.findViewById(R.id.imageView);
            textView = (TextView) view.findViewById(R.id.textView);
            checkIcon = (ImageView) view.findViewById(R.id.check_icon);
            if (textView != null) {
                textView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.OnItemClick(position);
             }
        }

    }

    public static class ListData {
        private String data;
        private String IconText;
        private Integer color;
        private boolean isChecked;
        private boolean isRandomColor;
        public ListData(String data, String iconText) {
            this.data = data;
            this.IconText = iconText;
            this.isRandomColor = true;
        }
        public ListData(String data, String iconText, Integer color){
            this.data = data;
            this.IconText = iconText;
            this.color = color;
            this.isRandomColor = false;
        }
        public void setChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}

