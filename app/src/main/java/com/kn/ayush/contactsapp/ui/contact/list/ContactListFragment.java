package com.kn.ayush.contactsapp.ui.contact.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.data.ContactInfo;
import com.kn.ayush.contactsapp.data.ContactList;
import com.kn.ayush.contactsapp.network.DataProvider;
import com.kn.ayush.contactsapp.ui.contact.detail.ContactDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by ayush on 31/5/16
 */
public class ContactListFragment extends Fragment {

    private static final String TAG = "ContactListFragment";
    private List<ContactInfo> mContactList;

    @Bind(R.id.contact_list)
    ListView mListView;

    public ContactListFragment() {
        mContactList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_contacts, container, false);
        ButterKnife.bind(this, view);

        try {
            Gson gson = new Gson();
            ContactList contactList = gson.fromJson(DataProvider.SAMPLE_JSON_DATA, ContactList.class);
            mContactList = contactList.getContacts();

            ContactListAdapter adapter = new ContactListAdapter();
            adapter.setDataSource(mContactList);
            mListView.setAdapter(adapter);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage(), ex);
        }

        return view;
    }

    @OnItemClick(R.id.contact_list)
    void onItemClick(int position) {
        ContactInfo contact = mContactList.get(position);
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getActivity(), ContactDetailActivity.class);
        bundle.putString("fullName", contact.getFullName());
        bundle.putString("number", contact.getNumber());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
