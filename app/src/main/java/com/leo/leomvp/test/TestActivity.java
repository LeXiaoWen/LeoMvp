package com.leo.leomvp.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.FrameLayout;

import com.leo.leomvp.R;
import com.leo.leomvp.test.fragment.TestFragment;
import com.leo.mvp.base.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leo on 2017/6/26.
 */

public class TestActivity extends BaseActivity<TestActivityPresenter> {
    @BindView(R.id.subscrib)
    Button mSubscrib;
    @BindView(R.id.fl_content)
    FrameLayout mFlContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initToolBarAsHome("test");
    }

    @Override
    protected void bindingDagger2(Bundle savedInstanceState) {
        DaggerTestActivityComponent.create().inject(this);
    }

    @OnClick(R.id.subscrib)
    public void onViewClicked() {

        mPresenter.onButtonClick();

       FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        TestFragment instance = TestFragment.getInstance();
        fragmentTransaction.replace(R.id.fl_content, instance);
        fragmentTransaction.commit();
    }



}
