package com.kn.ayush.contactsapp.background;

import android.os.AsyncTask;
import android.os.Build;

/**
 * Created by ayush on 1/6/16
 */
public class TaskExecutor {

    public static <T> void execute(AsyncTask<T, ?, ?> task, T... params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        } else {
            task.execute(params);
        }
    }
}
