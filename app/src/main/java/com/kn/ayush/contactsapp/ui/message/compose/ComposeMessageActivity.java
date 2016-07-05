package com.kn.ayush.contactsapp.ui.message.compose;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.background.SendSMSTask;
import com.kn.ayush.contactsapp.background.TaskExecutor;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ayush on 1/6/16
 */
public class ComposeMessageActivity extends AppCompatActivity {

    private String contactName = "";
    private String contactNumber = "";

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.otp)
    TextView mTextView;

    @Bind(R.id.send_otp_button)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);
        ButterKnife.bind(this);

        // action bar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        String otp = generateOTP();
        mTextView.setText(otp);
        contactNumber = getIntent().getExtras().getString("contactNumber");
        contactName = getIntent().getExtras().getString("contactName");
    }

    @OnClick(R.id.send_otp_button)
    void onSendOTPClick() {
        TaskExecutor.execute(new SendSMSTask(this, mTextView.getText().toString(), contactNumber), contactName);
    }

    //function to generate 6 digit OTP
    private String generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(900000 - 100000) + 100000;
        return String.valueOf(otp);
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
