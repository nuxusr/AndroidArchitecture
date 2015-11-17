package com.domo.apparchitecture.activities;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.domo.apparchitecture.BeverageApplication;
import com.domo.apparchitecture.R;
import com.domo.apparchitecture.adapters.BeveragesAdapter;
import com.domo.apparchitecture.contracts.Beverage;
import com.domo.apparchitecture.services.BeverageService;
import com.domo.apparchitecture.utils.Constants;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeveragesActivity extends ToolbarBaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.beverage_recycle_view) RecyclerView mRecyclerView;

    @Inject BeverageService mBeverageService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BeverageApplication.getApplicationComponent().inject(this);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);

        setupToolbar();
        setupRecycleView();

        getLoaderManager().initLoader(Constants.LOADER_ID_BEVERAGES, null, this);

        // also fire off network request here...
        mBeverageService.loadRemoteBeverages();
    }

    @Override
   public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        return new CursorLoader(this, Beverage.CONTENT_URI, null, null, null, Beverage.BEVERAGE_NAME);
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        BeveragesAdapter adapter = (BeveragesAdapter) mRecyclerView.getAdapter();
        if (adapter == null) {
            mRecyclerView.setAdapter(new BeveragesAdapter(data));
        }
        else {
            adapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        BeveragesAdapter adapter = (BeveragesAdapter) mRecyclerView.getAdapter();
        if (adapter != null) {
            adapter.swapCursor(null);
        }
    }

    @OnClick(R.id.fab)
    @SuppressWarnings("unused")
    public void fabClicked(View view) {
        Intent intent = new Intent(view.getContext(), AddBeverageActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    private void setupRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
