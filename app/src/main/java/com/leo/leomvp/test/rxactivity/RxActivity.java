package com.leo.leomvp.test.rxactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leo.leomvp.R;
import com.leo.mvp.base.rxactivity.RxBaseActivity;

/**
 * Created by Leo on 2017/7/23.
 */

public class RxActivity extends RxBaseActivity<RxActivityPresenter>{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_test);
    }

    @Override
    protected void bindingDagger2(Bundle savedInstanceState) {
        DaggerRxActivityComponent.create().inject(this);
    }
}
