package com.domo.apparchitecture.injection.components;

import com.domo.apparchitecture.injection.modules.ApplicationModuleTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dallindyer on 11/17/15.
 */
@Singleton
@Component(modules = {
        ApplicationModuleTest.class
})
public interface ApplicationComponentTest extends ApplicationComponent {
    final class Initializer {
        private Initializer() {
        } // No instances.

        public static ApplicationComponent init() {
            return DaggerApplicationComponentTest.builder()
                    .applicationModuleTest(new ApplicationModuleTest())
                    .build();
        }
    }
}
