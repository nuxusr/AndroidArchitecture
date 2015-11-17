package com.domo.apparchitecture.utils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by dallindyer on 11/17/15.
 */
public class Constants {

    public static final String BUNDLE_BEVERAGE_ID = "bBeverageId";

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LOADER_ID_BEVERAGES})
    public @interface LoaderIds{ }
    public static final int LOADER_ID_BEVERAGES = 1;

}
