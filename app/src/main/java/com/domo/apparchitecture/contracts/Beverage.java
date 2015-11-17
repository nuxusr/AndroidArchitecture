package com.domo.apparchitecture.contracts;

import android.net.Uri;

import com.domo.apparchitecture.providers.BeverageProvider;
import com.madebyatomicrobot.vinyl.annotations.Record;
import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by dallindyer on 11/17/15.
 */

@Record
public interface Beverage extends ProviGenBaseContract {
    String TABLE_NAME = "beverages";

    @ContentUri Uri CONTENT_URI = Uri.parse(BeverageProvider.HOST + BeverageProvider.AUTHORITY + "/" + TABLE_NAME);

    @Column(Column.Type.TEXT) String BEVERAGE_ID = "beverageId";
    String beverageId();

    @Column(Column.Type.TEXT) String BEVERAGE_NAME = "beverageName";
    String beverageName();

}
