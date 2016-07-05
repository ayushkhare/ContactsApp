package com.kn.ayush.contactsapp.ui.contact.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.ui.message.compose.ComposeMessageActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ayush on 1/6/16
 */
public class ContactDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.name)
    TextView mContactName;

    @Bind(R.id.number)
    TextView mContactNumber;

    @Bind(R.id.send_button)
    Button mSendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_contact_details);
        ButterKnife.bind(this);

        // action bar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        // display
        Bundle bundle = getIntent().getExtras();
        String contactName = bundle.getString("fullName");
        String contactNumber = bundle.getString("number");
        mContactName.setText(contactName);
        mContactNumber.setText(contactNumber);
    }

    @OnClick(R.id.send_button)
    void onSendButtonClick() {
        Intent intent = new Intent(ContactDetailActivity.this, ComposeMessageActivity.class);
        intent.putExtra("contactNumber", mContactNumber.getText().toString()); //passing contact number to compose message activity
        intent.putExtra("contactName", mContactName.getText().toString()); //passing contact name to compose message activity
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
