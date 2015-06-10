package com.xjp.androidmddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.xjp.androidmddemo.R;

/**
 * Description:
 * User: xjp
 * Date: 2015/6/8
 * Time: 11:03
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected TextView title;
    protected Spinner spinner;
    protected Button add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        toolbar = findView(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            title = findView(R.id.toolbar_title);
            spinner = findView(R.id.toolbar_category);
            add = findView(R.id.button_add);
            if (null != title) {
                title.setText(getTitle());
            }
        }

    }

    public abstract int getContentView();


    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
