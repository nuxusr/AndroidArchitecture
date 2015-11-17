package com.domo.apparchitecture.injection.modules;

import android.os.Handler;
import android.os.Looper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dallindyer on 11/17/15.
 */

@Module
public class ApplicationModule {

    @Provides Handler provideHandler() {
        return new Handler(Looper.getMainLooper());
    }

}
