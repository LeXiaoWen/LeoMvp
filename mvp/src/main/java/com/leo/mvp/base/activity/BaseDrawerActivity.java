package com.leo.mvp.base.activity;

import android.view.Menu;


/**
 * Created by Leo on 2017/8/7.
 */

public abstract class BaseDrawerActivity<T> extends BaseActivity {

    public boolean onCreateOptionsMenu(int layout, Menu menu) {
        getMenuInflater().inflate(layout, menu);
        return true;
    }
}
