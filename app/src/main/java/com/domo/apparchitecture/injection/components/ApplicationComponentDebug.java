package com.domo.apparchitecture.injection.components;

import com.domo.apparchitecture.injection.modules.ApplicationModule;
import com.domo.apparchitecture.injection.modules.ApplicationModuleLive;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dallindyer on 6/24/15.
 */
@Singleton
@Component(modules = {
        ApplicationModuleLive.class
})
public interface ApplicationComponentDebug extends ApplicationComponent {
    final class Initializer {
        private Initializer() {
        } // No instances.

        public static ApplicationComponent init() {
            return DaggerApplicationComponentDebug.builder()
                    .applicationModule(new ApplicationModule())
                    .build();
        }
    }
}
