package com.leo.mvp.base.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.leo.mvp.R;
import com.leo.mvp.base.bean.EventBaseBean;
import com.leo.mvp.base.presenter.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leo on 2017/6/26.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T mPresenter;
    private static final Set<BaseActivity> activityList = new HashSet();

    public BaseActivity() {
    }

    protected abstract T createPresenter();

    protected void onCreate(@Nullable Bundle savedInstanceState) throws RuntimeException {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (this.mPresenter == null) {
            throw new RuntimeException("没有设置Presenter或者没有重写onCreate()方法");
        } else {
            this.mPresenter.setActivity(this);
            toolBarInit();
            this.mPresenter.onCreate(savedInstanceState);
        }
    }


    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        this.mPresenter.onCreateView();
    }

    public void startActivity(Class<?> cls) {
        this.startActivity(new Intent(this, cls));
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void toolBarInit() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void shutDown() {
        this.finish();
    }

    public void clearAll() {
        activityList.clear();
    }
    //---------------------------------- 生命周期 ----------------------------------
    protected void onStart() {
        super.onStart();
        activityList.add(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        this.mPresenter.onStart();
    }

    protected void onResume() {
        super.onResume();
        this.mPresenter.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.mPresenter.onPause();
    }


    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        activityList.remove(this);


        this.mPresenter.onDetached();
        this.mPresenter = null;
    }

    public Toolbar initToolBarAsHome(String title) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbaTitle.setText(title);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }
    public Toolbar initToolBarAsHomeIcon(String title) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbaTitle.setText(title);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReslut(EventBaseBean<String> eventBaseBean){

    }
}
