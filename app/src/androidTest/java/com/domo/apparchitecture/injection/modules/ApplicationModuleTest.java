package com.domo.apparchitecture.injection.modules;

import com.domo.apparchitecture.services.BeverageService;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dallindyer on 11/17/15.
 */
@Module(includes = ApplicationModule.class)
public class ApplicationModuleTest {
    @Provides
    @Singleton BeverageService provideBeverageService() {
        BeverageService mock = Mockito.mock(BeverageService.class);
        return mock;
    }
}
