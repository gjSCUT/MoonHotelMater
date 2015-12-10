package com.gj.administrator.gjerp.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.gj.administrator.gjerp.R;
import com.gj.administrator.gjerp.base.BaseActivity;


public class ManageItemActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Item");
        setContentView(R.layout.activity_manage_item);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void processMessage(Message msg) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
