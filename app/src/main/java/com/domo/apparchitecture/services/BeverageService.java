package com.domo.apparchitecture.services;

import android.content.ContentResolver;
import android.os.AsyncTask;

import com.domo.apparchitecture.BeverageApplication;
import com.domo.apparchitecture.BuildConfig;
import com.domo.apparchitecture.contracts.Beverage;
import com.domo.apparchitecture.contracts.BeverageRecord;

import java.util.UUID;

/**
 * Created by dallindyer on 11/17/15.
 */
public class BeverageService {

    public void insert(final String name) {
        executeInBackground(new Runnable() {
            @Override public void run() {
                ContentResolver contentResolver = getContentResolver();
                contentResolver.insert(Beverage.CONTENT_URI, BeverageRecord.contentValuesBuilder().beverageId(UUID.randomUUID().toString()).beverageName(name).build());
            }
        }, null);
    }

    public void update(final String beverageId, final String updatedName) {
        executeInBackground(new Runnable() {
            @Override public void run() {
                ContentResolver contentResolver = getContentResolver();
                contentResolver.update(Beverage.CONTENT_URI, BeverageRecord.contentValuesBuilder().beverageId(UUID.randomUUID().toString()).beverageName(updatedName).build(), Beverage.BEVERAGE_ID + "=?", new String[]{beverageId});
            }
        }, null);
    }


    public void delete(final String beverageId) {
        executeInBackground(new Runnable() {
            @Override public void run() {
                ContentResolver contentResolver = getContentResolver();
                contentResolver.delete(Beverage.CONTENT_URI, Beverage.BEVERAGE_ID + "=?", new String[]{beverageId});
            }
        }, null);
    }

    private void executeInBackground(final Runnable runnable, final Runnable onCompleteRunnable) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {
                if (BuildConfig.DEBUG){ Thread.currentThread().setName("BeverageService - ExecuteInBackground"); }
                try {
                    if (runnable != null) runnable.run();
                } catch (Exception ex) {
                    // handle exception here..
                }
                return null;
            }

            @Override
            protected void onPostExecute(final Void aVoid) {
                if (onCompleteRunnable != null) {
                    onCompleteRunnable.run();
                }
            }

        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private ContentResolver getContentResolver() {
        return BeverageApplication.get().getContentResolver();
    }
}
