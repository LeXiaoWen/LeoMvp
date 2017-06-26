package com.leo.leomvp.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.base.activity.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leo on 2017/6/26.
 */

public class TestActivity extends BaseActivity<TestActivityPresenter> {
    @BindView(R.id.subscrib)
    Button mSubscrib;

    @Override
    protected TestActivityPresenter createPresenter() {
        return new TestActivityPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initToolBarAsHome("test");
    }

    @OnClick(R.id.subscrib)
    public void onViewClicked() {

        mPresenter.onButtonClick();
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onReslut(String userName){
//
//    }
}
