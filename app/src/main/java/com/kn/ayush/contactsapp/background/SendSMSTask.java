package com.kn.ayush.contactsapp.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

import com.kn.ayush.contactsapp.R;
import com.kn.ayush.contactsapp.data.SMSInfo;
import com.kn.ayush.contactsapp.network.NetworkManger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;


/**
 * Created by ayush on 1/6/16
 */
public class SendSMSTask extends AsyncTask<String, String, Integer> {

    public static final String ACCOUNT_KEY = "23503df2";
    public static final String ACCOUNT_SECRET = "9a69c724713a25f3";
    public static final String URL = "https://rest.nexmo.com/sms/json";

    // ERROR CODES
    private static final int CODE_NETWORK_ERROR = 1;
    private static final int CODE_SUCCESS = 0;
    private static final int CODE_NO_INTERNET = -1;

    private String destNumber;
    private NetworkManger mNetworkManger;
    private Context mContext;
    private ProgressDialog mProgress;
    private String mOTP;

    public SendSMSTask(Context context, String otp, String number) {
        destNumber = number;
        mOTP = otp;
        mContext = context;
        mNetworkManger = new NetworkManger();
    }

    //function to if internet is available
    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(mContext); //progress dialog
        mProgress.setMessage("Sending OTP ...");
        mProgress.setIndeterminate(false);
        mProgress.setCancelable(true);
        mProgress.show();
    }

    @Override
    protected Integer doInBackground(String... strings) {
        int status = CODE_NO_INTERNET;
        HashMap<String, String> hashMap = new HashMap<>(); //used to build required parameters for NEXMO API
        String to = "91" + destNumber;
        String from = "Anonymous";
        String text = "Hi. Your OTP is:  "+ mOTP;
        hashMap.put("api_key", ACCOUNT_KEY);
        hashMap.put("api_secret", ACCOUNT_SECRET);
        hashMap.put("to", to);
        hashMap.put("from", from);
        hashMap.put("text", text);
        if (isInternetAvailable()) {
            //making post http request for sending sms using NEXMO
            JSONObject json = mNetworkManger.makeHttpRequest(URL,
                    "POST", hashMap);
            try {
                //Get the instance of JSONArray that contains JSONObjects
                if (json == null) {
                    //if json is null then device is connected to wifi but without internet
                    status = CODE_NO_INTERNET;
                } else {
                    JSONArray jsonArray = json.optJSONArray("messages");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    status = jsonObject.optInt("status");
                    if (status == CODE_SUCCESS) {
                        SMSInfo sms = new SMSInfo();
                        sms.setContactName(strings[0]);
                        sms.setOtp(mOTP);
                        sms.setTimestamp(System.currentTimeMillis());
                        sms.save();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            status = CODE_NO_INTERNET;
        }
        return status;
    }

    @Override
    protected void onPostExecute(Integer status) {
        mProgress.dismiss();
        int errorRes = R.string.label_network_error;
        switch (status) {
            case CODE_SUCCESS:
                errorRes = R.string.label_otp_send_success;
                break;
            case CODE_NETWORK_ERROR:
                errorRes = R.string.label_no_internet;
                break;
            default:
                break;
        }
        Toast.makeText(mContext, errorRes, Toast.LENGTH_SHORT).show();
    }
}
