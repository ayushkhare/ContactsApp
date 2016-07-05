package com.kn.ayush.contactsapp.data;

import com.orm.SugarRecord;

/**
 * Created by ayush on 1/6/16
 */
public class SMSInfo extends SugarRecord {

    private String contactName;
    private String otp;
    private long timestamp;

    public SMSInfo() {

    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
