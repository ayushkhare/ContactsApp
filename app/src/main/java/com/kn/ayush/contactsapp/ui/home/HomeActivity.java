package com.kn.ayush.contactsapp.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kn.ayush.contactsapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.pager)
    ViewPager mPager;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    //comment1
    //comment2
    //comment3&4
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        HomeTabAdapter mTabsAdapter = new HomeTabAdapter(this, getSupportFragmentManager());
        mPager.setAdapter(mTabsAdapter);
        mTabLayout.setupWithViewPager(mPager);
    }
}
