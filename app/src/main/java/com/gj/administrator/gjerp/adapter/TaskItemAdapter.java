package com.gj.administrator.gjerp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.dao.StaffDao;
import com.gj.administrator.gjerp.dao.TaskDao;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;

import java.text.SimpleDateFormat;
import java.util.List;

public class TaskItemAdapter extends BaseAdapter {
    private Context context;

    public List<Message> datas;
    Task task;
    Staff staff;
    public TaskItemAdapter(Context context, List<Message> datas) {
        this.context = context;
        this.datas = datas;

        task = DBUtil.getDaoSession(context)
                .getTaskDao()
                .queryBuilder()
                .where(TaskDao.Properties.Dialog_id.eq(
                                datas.get(0).getDialog_id())
                )
                .unique();
        staff = task.getLeader();
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Message getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message msg = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            if(msg.getIdentify() == false) {
                convertView = LayoutInflater.from(context).inflate(R.layout.listitem_receive_msg, null);
                holder.mHtvTimeStampTime = (TextView) convertView.findViewById(R.id.message_timestamp_htv_time);
                holder.mIvLeftAvatar = (ImageView) convertView.findViewById(R.id.left_message_iv_userphoto);
                holder.mTvTextContent = (TextView) convertView.findViewById(R.id.message_tv_msgtext);
            }
            else {
                convertView = LayoutInflater.from(context).inflate(R.layout.listitem_send_msg, null);
                holder.mHtvTimeStampTime = (TextView) convertView.findViewById(R.id.message_timestamp_htv_time);
                holder.mIvRightAvatar = (ImageView) convertView.findViewById(R.id.right_message_iv_userphoto);
                holder.mTvTextContent = (TextView) convertView.findViewById(R.id.message_tv_msgtext);
            }

            convertView.setTag(holder);

        }
        else
            holder = (ViewHolder) convertView.getTag();

        TextDrawable drawable = DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_RECT_BORDER)
                .build(String.valueOf(staff.getName().charAt(0)), ColorGenerator.MATERIAL.getColor(staff.getName()));
        if(msg.getIdentify() == false) {
            holder.mHtvTimeStampTime.setText(new SimpleDateFormat("HH:mm:ss").format(msg.getMsg_time()));
            holder.mTvTextContent.setText(msg.getContent());
            holder.mIvLeftAvatar.setImageDrawable(drawable);
        }
        else{
            holder.mHtvTimeStampTime.setText(new SimpleDateFormat("HH:mm:ss").format(msg.getMsg_time()));
            holder.mTvTextContent.setText(msg.getContent());
            holder.mIvRightAvatar.setImageDrawable(drawable);
        }

        return convertView;
    }

    static class ViewHolder {
        private TextView mHtvTimeStampTime; // 时间
        private TextView mTvTextContent; // 文本内容
        private ImageView mIvLeftAvatar; // 左边的头像
        private ImageView mIvRightAvatar; // 左边的头像
    }

}