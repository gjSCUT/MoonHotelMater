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
import com.gj.administrator.gjerp.dao.DaoSession;
import com.gj.administrator.gjerp.dao.DialogDao;
import com.gj.administrator.gjerp.dao.StaffDao;
import com.gj.administrator.gjerp.dao.TaskDao;
import com.gj.administrator.gjerp.domain.Dialog;
import com.gj.administrator.gjerp.domain.Message;
import com.gj.administrator.gjerp.domain.Staff;
import com.gj.administrator.gjerp.domain.Task;
import com.gj.administrator.gjerp.util.DBUtil;
import com.gj.administrator.gjerp.util.DrawbalBuilderUtil;
import com.gj.administrator.gjerp.util.SessionUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import jp.co.worksap.intern.entities.staff.StaffDTO;

/**
 * the adapter of drawable list
 * Created by guojun on 2015/12/15
 */
public class MessageAdapter extends BaseAdapter {
    protected Context context;
    private static final int HIGHLIGHT_COLOR = 0x999be6ff;
    // this is the descriptions used in the main page, under the descriptions
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private int itemId;
    // list of data items
    public List<Dialog> mDataList;
    private DaoSession daoSession;

    public MessageAdapter(Context context, int itemId, List<Dialog> datas) {
        this.context = context;
        this.itemId = itemId;
        this.mDataList = datas;
        daoSession = DBUtil.getDaoSession(context);
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
        Message message = item.getMessages().get(item.getMessages().size()-1);

        // provide support for selected state
        TextDrawable.IBuilder mDrawableBuilder;
        String iconName;
        TextDrawable drawable;

        if(item.getDialog_type()== Dialog.TYPE_CHAT) {
            Staff staff = daoSession.getStaffDao()
                    .queryBuilder()
                    .where(StaffDao.Properties.Dialog_id
                            .eq(item.getId()))
                    .unique();

            mDrawableBuilder = DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_RECT);
            iconName = staff.getName().substring(0, 1);
            drawable = mDrawableBuilder.build(iconName, mColorGenerator.getColor(staff.getName()));
            holder.avater.setImageDrawable(drawable);
            holder.view.setBackgroundColor(Color.TRANSPARENT);
            holder.name.setText(staff.getName());
            holder.type.setText(Dialog.TYPE[item.getDialog_type()]);
            holder.message.setText(Message.MESSAGE_TYPE[message.getMsg_type()] + " : " +
                    (message.getContent().length() > 30 ? message.getContent().substring(0, 30) + "..." : message.getContent()));
            holder.time.setText(new SimpleDateFormat("HH:mm").format(message.getMsg_time()));
        }
        else if(item.getDialog_type()== Dialog.TYPE_TASK){
            Task task = daoSession.getTaskDao()
                    .queryBuilder()
                    .where(TaskDao.Properties.Dialog_id
                            .eq(item.getId()))
                    .unique();
            mDrawableBuilder = DrawbalBuilderUtil.getDrawbalBuilder(DrawbalBuilderUtil.DRAWABLE_TYPE.SAMPLE_ROUND);
            iconName = "T";
            drawable = mDrawableBuilder.build(iconName, mColorGenerator.getColor(message.getMsg_type()));
            holder.avater.setImageDrawable(drawable);
            holder.view.setBackgroundColor(Color.TRANSPARENT);
            holder.name.setText(task.getTitle());
            holder.type.setText(Dialog.TYPE[item.getDialog_type()]);
            holder.message.setText(Message.MESSAGE_TYPE[message.getMsg_type()] + " : " +
                    (message.getContent().length() > 30 ? message.getContent().substring(0, 30) + "..." : message.getContent()));
            holder.time.setText(new SimpleDateFormat("HH:mm").format(message.getMsg_time()));
        }
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

