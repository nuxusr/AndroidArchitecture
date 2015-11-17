package com.domo.apparchitecture.viewholders;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.domo.apparchitecture.R;
import com.domo.apparchitecture.activities.EditBeverageActivity;
import com.domo.apparchitecture.contracts.Beverage;
import com.domo.apparchitecture.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dallindyer on 11/17/15.
 */
public class BeveragesViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.beverage_name) TextView mBeverageName;
    private String mBeverageId;

    public BeveragesViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.beverage_name)
    @SuppressWarnings("unused")
    public void beverageClicked(View view) {
        Activity context = (Activity) view.getContext();
        Intent intent = new Intent(context, EditBeverageActivity.class);
        intent.putExtra(Constants.BUNDLE_BEVERAGE_ID, mBeverageId);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void bind(Beverage beverage) {
        mBeverageId = beverage.beverageId();
        mBeverageName.setText(beverage.beverageName());
    }
}
