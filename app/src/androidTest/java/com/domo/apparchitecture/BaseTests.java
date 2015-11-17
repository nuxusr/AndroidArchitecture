package com.domo.apparchitecture;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.test.uiautomator.UiDevice;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by dallindyer on 11/17/15.
 */
public class BaseTests {
    protected boolean waitForRecyclerAdapterToBePopulated(int recyclerid, int seconds, int expectedSize)  {
        final long startTime = System.nanoTime();
        while(true) {
            try {
                final RecyclerView recyclerView = (RecyclerView) getCurrentActivity().findViewById(recyclerid);
                if (recyclerView != null) {
                    final RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    if (adapter != null) {
                        final int itemCount = adapter.getItemCount();
                        if (itemCount >= expectedSize) return true;
                    }
                }

            } catch (Throwable e) {
                // whatevs.. keep waiting
            }
            UiDevice device = UiDevice.getInstance(getInstrumentation());
            device.waitForWindowUpdate(null, 500);
            if ((TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime)) > seconds) {
                break;
            }
        }
        return false;
    }

    protected Activity getCurrentActivity() throws InterruptedException {
        getInstrumentation().waitForIdleSync();
        final Activity[] activity = { null };
        final CountDownLatch latch = new CountDownLatch(1);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override public void run() {
                java.util.Collection<Activity> activites = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                activity[0] = Iterables.getOnlyElement(activites);
                latch.countDown();
            }
        });
        latch.await();
        return activity[0];
    }
}
