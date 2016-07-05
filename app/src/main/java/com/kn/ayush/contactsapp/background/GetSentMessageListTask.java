package com.kn.ayush.contactsapp.background;

import android.os.AsyncTask;

import com.kn.ayush.contactsapp.data.SMSInfo;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by ayush on 1/6/16
 */
public class GetSentMessageListTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        SMSListEvent event = new SMSListEvent();
        event.smsList = SMSInfo.listAll(SMSInfo.class, "timestamp DESC");
        EventBus.getDefault().post(event);
        return null;
    }

    public static class SMSListEvent {
        public List<SMSInfo> smsList;
    }
}
