package com.domo.apparchitecture.injection.modules;

import com.domo.apparchitecture.services.BeverageService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dallindyer on 11/17/15.
 */
@Module(includes = ApplicationModule.class)
public class ApplicationModuleLive {
    @Provides
    @Singleton BeverageService provideBeverageService() {
        return new BeverageService();
    }
}
