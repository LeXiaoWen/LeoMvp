package com.leo.leomvp.test;

import com.leo.mvp.base.presenter.BasePresenter;
import com.leo.mvp.util.toast.ToastUtils;

/**
 * Created by Leo on 2017/6/26.
 */

public class TestActivityPresenter extends BasePresenter<TestActivity,TestActivityModel>{
    public TestActivityPresenter() {
    }

    public void onButtonClick() {

        ToastUtils.showLongToast("点击了事件");
    }
}
