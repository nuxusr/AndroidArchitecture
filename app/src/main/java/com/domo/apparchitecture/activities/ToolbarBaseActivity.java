package com.domo.apparchitecture.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.domo.apparchitecture.R;

/**
 * Created by dallindyer on 11/17/15.
 */
public abstract class ToolbarBaseActivity extends AppCompatActivity {

    @Override public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
