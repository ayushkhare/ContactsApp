package com.kn.ayush.contactsapp.ui.message.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.data.SMSInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ayush on 1/6/16
 */
public class MessageListAdapter extends BaseAdapter {

    private List<SMSInfo> mDataSource = new ArrayList<>();
    private SimpleDateFormat mTimeFormat;

    public void setDataSource(List<SMSInfo> dataList) {
        mDataSource = dataList;
        mTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public SMSInfo getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.sent_messages_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // bind data
        SMSInfo item = getItem(position);
        holder.mName.setText(item.getContactName());
        holder.mOTP.setText(item.getOtp());
        holder.mTime.setText(getFormattedTime(item.getTimestamp()));

        return convertView;
    }

    private String getFormattedTime(long timestamp) {
        //timestamp formatted in date and time format
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(timestamp);
        Date date = calendar.getTime();
        return mTimeFormat.format(date);
    }

    static class ViewHolder {

        @Bind(R.id.name)
        public TextView mName;

        @Bind(R.id.otp)
        public TextView mOTP;

        @Bind(R.id.time)
        public TextView mTime;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }

}
