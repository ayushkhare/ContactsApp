package com.kn.ayush.contactsapp.ui.message.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.background.GetSentMessageListTask;
import com.kn.ayush.contactsapp.background.TaskExecutor;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by ayush on 31/5/16
 */
public class MessageListFragment extends Fragment {

    private MessageListAdapter mAdapter;

    @Bind(R.id.message_list)
    ListView mListView;

    public MessageListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sent_messages, container, false);
        ButterKnife.bind(this, view);
        registerEvents();

        mAdapter = new MessageListAdapter();
        mListView.setAdapter(mAdapter);

        //load data
        refreshDataFromDB();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //refresh list when fragment is swiped in
        refreshDataFromDB();
    }

    private void registerEvents() {
        EventBus.getDefault().register(this);
    }

    private void unregisterEvents() {
        EventBus.getDefault().unregister(this);
    }

    private void refreshDataFromDB() {
        TaskExecutor.execute(new GetSentMessageListTask());
    }

    public void onEventMainThread(GetSentMessageListTask.SMSListEvent event) {
        mAdapter.setDataSource(event.smsList);
        mAdapter.notifyDataSetChanged();
    }

    //unregister event bus on destroy
    @Override
    public void onDestroy() {
        unregisterEvents();
        super.onDestroy();
    }
}
