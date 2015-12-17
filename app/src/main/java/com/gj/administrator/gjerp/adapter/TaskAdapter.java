package com.gj.administrator.gjerp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * the adapter of drawable list
 * Created by guojun on 2015/12/13
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{
    protected Context context;
    private static final int HIGHLIGHT_COLOR = 0x999be6ff;
    // this is the descriptions used in the main page, under the descriptions
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder;
    private boolean canCheckView;
    private int itemId;
    private OnClickListener onClickListener;
    // list of data items
    public List<Task> dataList;


    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnItemClick(int position);
    }


    public TaskAdapter(Context context, int itemId, List<Task> dataList) {
        this.context = context;
        this.itemId = itemId;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(itemId, parent,false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        Task item =  dataList.get(position);
        TextDrawable.IBuilder mDrawableBuilder = DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND_RECT_BORDER);
        String iconName = item.getState().substring(0, 1);
        TextDrawable drawable = mDrawableBuilder.build(iconName, mColorGenerator.getColor(item.getState()));
        holder.imageView.setImageDrawable(drawable);
        holder.position = position;
        holder.textView.setText(item.getTitle());
        holder.stateView.setText(item.getState());
        holder.startTime.setText("deadline time : " + new SimpleDateFormat("MM-dd HH:mm").format(item.getFinish_time()));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount()  {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView textView;
        private TextView stateView;
        private TextView startTime;
        private TextView endTime;
        public int position;
        private ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.avatar);
            textView = (TextView) view.findViewById(R.id.titleTv);
            stateView = (TextView) view.findViewById(R.id.stateTv);
            startTime = (TextView) view.findViewById(R.id.startTime);
            endTime = (TextView) view.findViewById(R.id.endTime);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.OnItemClick(position);
             }
        }
    }

}

