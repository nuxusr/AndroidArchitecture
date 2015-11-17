package com.domo.apparchitecture.adapters;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.domo.apparchitecture.R;
import com.domo.apparchitecture.contracts.BeverageRecord;
import com.domo.apparchitecture.viewholders.BeveragesViewHolder;

/**
 * Created by dallindyer on 11/17/15.
 */
public class BeveragesAdapter extends CursorRecyclerViewAdapter<BeveragesViewHolder> {
    public BeveragesAdapter(final Cursor cursor) {
        super(cursor);
        setHasStableIds(true);
    }

    @Override
    public void onBindViewHolder(final BeveragesViewHolder viewHolder, final Cursor cursor) {
        viewHolder.bind(BeverageRecord.wrapCursor(cursor));
    }

    @Override
    public BeveragesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new BeveragesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.beverage_row, parent, false));
    }
}
