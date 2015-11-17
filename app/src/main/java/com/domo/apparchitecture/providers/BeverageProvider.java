package com.domo.apparchitecture.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.domo.apparchitecture.contracts.Beverage;
import com.tjeannin.provigen.ProviGenProvider;
import com.tjeannin.provigen.helper.TableBuilder;

/**
 * Created by dallindyer on 11/17/15.
 */
public class BeverageProvider extends ProviGenProvider {
    public static String HOST = "content://";
    public static String AUTHORITY = "com.dallindyer.beverageprovider";
    private static final int VERSION = 1;

    private static Class[] sContracts = new Class[]{
            Beverage.class
    };

    @Override public SQLiteOpenHelper openHelper(final Context context) {
        return new SQLiteOpenHelper(getContext(), "beverages", null, VERSION) {

            @Override public void onCreate(final SQLiteDatabase database) {
                new TableBuilder(Beverage.class) .createTable(database);
            }

            @Override
            public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) { }
        };
    }

    @Override
    public Class[] contractClasses() {
        return sContracts;
    }
}
