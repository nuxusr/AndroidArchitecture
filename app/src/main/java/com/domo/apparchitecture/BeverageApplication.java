package com.domo.apparchitecture;

import android.app.Application;
import android.content.Context;

import com.domo.apparchitecture.injection.components.ApplicationComponent;
import com.domo.apparchitecture.injection.components.ApplicationComponentDebug;

/**
 * Created by dallindyer on 11/17/15.
 */
public class BeverageApplication  extends Application {
    private static ApplicationComponent sApplicationComponent;
    private static BeverageApplication sInstance;

    public BeverageApplication() {
        sInstance = this;
    }

    @Override public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    public static ApplicationComponent getApplicationComponent() {
        if (sApplicationComponent == null) {
            initializeInjector();
        }
        return sApplicationComponent;
    }

    public static void initializeWithInjector(ApplicationComponent applicationComponent) {
        sApplicationComponent = applicationComponent;
    }

    private static void initializeInjector() {
        if (BuildConfig.DEBUG) {
            sApplicationComponent = ApplicationComponentDebug.Initializer.init();
        }
        else {
            // todo:// add a release module..
        }
    }

    public static Context get() {
        return sInstance;
    }
}
