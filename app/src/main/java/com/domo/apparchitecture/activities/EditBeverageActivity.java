package com.domo.apparchitecture.activities;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.domo.apparchitecture.BeverageApplication;
import com.domo.apparchitecture.R;
import com.domo.apparchitecture.contracts.Beverage;
import com.domo.apparchitecture.contracts.BeverageRecord;
import com.domo.apparchitecture.services.BeverageService;
import com.domo.apparchitecture.utils.Constants;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditBeverageActivity extends ToolbarBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.drink_name) EditText mDrinkName;

    @Inject BeverageService mBeverageService;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BeverageApplication.getApplicationComponent().inject(this);
        setContentView(R.layout.activity_edit_beverage);
        ButterKnife.bind(this);
        setupToolbar();

        String drinkId = getBeverageId();
        if (TextUtils.isEmpty(drinkId)) {
            throw new IllegalStateException("BeverageId required");
        }

        getLoaderManager().initLoader(Constants.LOADER_ID_BEVERAGES, null, this);
    }

    @OnClick(R.id.save)
    @SuppressWarnings("unused")
    public void saveClicked(View view){
        Editable drinkName = mDrinkName.getText();
        if (!validate(drinkName)) return;

        mBeverageService.update(getBeverageId(), drinkName.toString());
        finish();
    }

    @OnClick(R.id.delete)
    @SuppressWarnings("unused")
    public void deleteClicked(View view){
        mBeverageService.delete(getBeverageId());
        finish();
    }

    @Override public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        return new CursorLoader(this, Beverage.CONTENT_URI, null, Beverage.BEVERAGE_ID + "=?", new String[]{ getBeverageId()}, null);
    }

    @Override public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        if (data != null && data.moveToFirst()) {
            mDrinkName.setText(BeverageRecord.wrapCursor(data).beverageName());
        }
    }

    @Override public void onLoaderReset(final Loader<Cursor> loader) { }

    private String getBeverageId() {
        return getIntent().getStringExtra(Constants.BUNDLE_BEVERAGE_ID);
    }

    private boolean validate(final Editable drinkName) {
        if(TextUtils.isEmpty(drinkName)){
            mDrinkName.setError("Can't be blank");
            return false;
        }
        return true;
    }
}
