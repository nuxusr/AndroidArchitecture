package com.domo.apparchitecture.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.domo.apparchitecture.BeverageApplication;
import com.domo.apparchitecture.R;
import com.domo.apparchitecture.services.BeverageService;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBeverageActivity extends ToolbarBaseActivity {
    @Bind(R.id.drink_name) EditText mDrinkName;

    @Inject BeverageService mBeverageService;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BeverageApplication.getApplicationComponent().inject(this);
        setContentView(R.layout.activity_add_beverage);
        ButterKnife.bind(this);
        setupToolbar();
    }

    @OnClick(R.id.save)
    @SuppressWarnings("unused")
    public void saveClicked(View view){
        Editable drinkName = mDrinkName.getText();
        if (!validate(drinkName)) return;

        mBeverageService.insert(drinkName.toString());
        finish();
    }

    private boolean validate(final Editable drinkName) {
        if(TextUtils.isEmpty(drinkName)){
            mDrinkName.setError("Can't be blank");
            return false;
        }
        return true;
    }

}
