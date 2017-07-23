package com.leo.leomvp.test.fragment;

import com.leo.mvp.base.fragment.BaseFragmentModel;
import com.leo.mvp.util.toast.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/7/20.
 */

public class TestFragmentModel extends BaseFragmentModel<TestFragmentPresenter> {
   @Inject
    public TestFragmentModel(){}




    public void setOnClickButton() {
        ToastUtils.showShortToast("model点击");
    }
}
