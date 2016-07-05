package com.kn.ayush.contactsapp.ui.home;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.ui.contact.list.ContactListFragment;
import com.kn.ayush.contactsapp.ui.message.list.MessageListFragment;

/**
 * Created by ayush on 31/5/16
 */
public class HomeTabAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;
    private static final int CONTACT_PAGE_INDEX = 0;
    private static final int SENT_PAGE_INDEX = 1;
    private final Context mContext;

    public HomeTabAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case CONTACT_PAGE_INDEX:
                return new ContactListFragment();
            case SENT_PAGE_INDEX:
                return new MessageListFragment();
            default:
                return new ContactListFragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case CONTACT_PAGE_INDEX:
                return getString(R.string.label_contants);
            case SENT_PAGE_INDEX:
                return getString(R.string.label_sent_messages);
            default:
                return getString(R.string.label_contants);
        }
    }

    private String getString(@StringRes int id) {
        return mContext.getResources().getString(id);
    }
}