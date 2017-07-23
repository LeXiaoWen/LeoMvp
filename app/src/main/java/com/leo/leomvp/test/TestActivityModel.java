package com.leo.leomvp.test;

import com.leo.mvp.base.activity.BaseModel;
import com.leo.mvp.util.toast.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/6/26.
 */

public class TestActivityModel extends BaseModel<TestActivityPresenter>{
    @Inject
    public TestActivityModel() {
    }

    public void onClick() {
        ToastUtils.showShortToast("Model click");
    }
}
