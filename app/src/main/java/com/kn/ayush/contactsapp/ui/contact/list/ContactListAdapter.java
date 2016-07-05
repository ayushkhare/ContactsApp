package com.kn.ayush.contactsapp.ui.contact.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.data.ContactInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ayush on 1/6/16
 */
public class ContactListAdapter extends BaseAdapter {

    private List<ContactInfo> mContactList = new ArrayList<>();

    public void setDataSource(List<ContactInfo> contactList) {
        mContactList = contactList;
    }

    @Override
    public int getCount() {
        return mContactList.size();
    }

    @Override
    public ContactInfo getItem(int position) {
        return mContactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.contact_list_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // bind data
        ContactInfo contactData = getItem(position);
        String contactName = contactData.getFullName();
        holder.mTextView.setText(contactName);

        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.contact_name)
        public TextView mTextView;

        public ViewHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }

}
