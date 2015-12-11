package com.gj.administrator.gjerp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gj.administrator.gjerp.R;

import java.util.List;

/**
 * Created by Kenny on 2015/12/9.
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {

    private static String TAG = "MyAdapter";

    protected Context context;
    private List<rowData> rowDataList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout mLinearLayout;
        public IMyViewHolderClicks mListener;
        public int position;

        public ViewHolder(LinearLayout linearLayout, IMyViewHolderClicks listener) {
            super(linearLayout);
            mListener = listener;
            mLinearLayout = linearLayout;
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof LinearLayout) {
                //TODO
            }
        }

        public void setPosition(int p) {
            position = p;
        }

        public static interface IMyViewHolderClicks {
            public void onClick(LinearLayout caller, int position);
        }
    }

    public RecyclerListAdapter(Context context, List<rowData> rowDataList) {
        this.context = context;
        this.rowDataList = rowDataList;
    }

    @Override
    public RecyclerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_list_item, parent, false);
        ViewHolder holder = new ViewHolder(
                (LinearLayout) v,
                new RecyclerListAdapter.ViewHolder.IMyViewHolderClicks() {
                    @Override
                    public void onClick(LinearLayout caller, int position) {
                        //TODO
                    }
                }
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setPosition(position);
        TextView guestRmIdTextView =
                (TextView) holder.mLinearLayout.findViewById(R.id.first_row_text);
        TextView guestNameTextView =
                (TextView) holder.mLinearLayout.findViewById(R.id.second_row_text);
        TextView guestIncomeTextView =
                (TextView) holder.mLinearLayout.findViewById(R.id.third_row_text);
        TextView guestTimeSpanTextView =
                (TextView) holder.mLinearLayout.findViewById(R.id.forth_row_text);
        guestRmIdTextView.setText(rowDataList.get(position).first);
        guestNameTextView.setText(rowDataList.get(position).second);
        guestIncomeTextView.setText(rowDataList.get(position).third);
        guestTimeSpanTextView.setText(rowDataList.get(position).forth);
    }

    @Override
    public int getItemCount() {
        if (null != rowDataList) return rowDataList.size();
        else return 0;
    }

    public static class rowData {
        private String first;
        private String second;
        private String third;
        private String forth;

        public rowData(String firstString, String secondString, String thirdString, String forthString) {
            first = firstString;
            second = secondString;
            third = thirdString;
            forth = forthString;
        }
    }
}
