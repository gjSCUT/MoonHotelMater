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
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.gj.administrator.gjerp.util.SessionUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * the adapter of drawable list
 * Created by guojun on 2015/12/08
 */
public class MessageAdapter extends BaseAdapter {
    protected Context context;
    private static final int HIGHLIGHT_COLOR = 0x999be6ff;
    // this is the descriptions used in the main page, under the descriptions
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private int itemId;
    // list of data items
    public List<Dialog> mDataList;

    public MessageAdapter(Context context, int itemId, List<Dialog> datas) {
        this.context = context;
        this.itemId = itemId;
        this.mDataList = datas;
    }


    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Dialog getItem(int position) {
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

        Dialog item = getItem(position);
        Message message = item.getMessages().get(0);
        for(Message msg : item.getMessages())
            if(msg.getName().compareTo(SessionUtil.getUser().getUsername())!=0){
                message  = msg;
                break;
            }


        // provide support for selected state
        String msgType = message.getMsg_type();
        TextDrawable.IBuilder mDrawableBuilder;
        String iconName;
        TextDrawable drawable;
        if(msgType.compareTo("Chat")==0) {
            mDrawableBuilder = DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_RECT);
            iconName = message.getName().substring(0, 1);
            drawable = mDrawableBuilder.build(iconName, mColorGenerator.getColor(message.getName()));
        }
        else {
            mDrawableBuilder = DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND);
            iconName = message.getMsg_type().substring(0,1);
            drawable = mDrawableBuilder.build(iconName, mColorGenerator.getColor(message.getMsg_type()));
        }
        holder.avater.setImageDrawable(drawable);
        holder.view.setBackgroundColor(Color.TRANSPARENT);
        holder.name.setText(message.getName());
        holder.type.setText(message.getName_type()!=null? message.getName_type():"");
        holder.message.setText(message.getMsg_type() + " : " +
                (message.getContent().length() > 30 ? message.getContent().substring(0, 15) + "..." : message.getContent()));
        holder.time.setText(new SimpleDateFormat("HH:mm").format(message.getMsg_time()));
        return convertView;
    }


    private static class ViewHolder {
        private View view;
        private ImageView avater;
        private TextView name;
        private TextView type;
        private TextView message;
        private TextView time;
        private ViewHolder(View view) {
            this.view = view;
            avater = (ImageView) view.findViewById(R.id.avatar);
            name = (TextView) view.findViewById(R.id.name);
            type = (TextView) view.findViewById(R.id.type);
            message = (TextView) view.findViewById(R.id.message);
            time = (TextView) view.findViewById(R.id.time);
        }

    }

}

