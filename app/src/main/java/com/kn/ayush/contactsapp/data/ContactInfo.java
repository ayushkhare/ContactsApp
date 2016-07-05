package com.kn.ayush.contactsapp.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ayush on 1/6/16
 */
public class ContactInfo {

    private int id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    private String number;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
