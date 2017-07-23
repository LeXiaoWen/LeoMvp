package com.leo.leomvp.test.fragment;

import com.leo.mvp.base.fragment.BaseFragmentPresenter;
import com.leo.mvp.util.toast.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/7/20.
 */

public class TestFragmentPresenter extends BaseFragmentPresenter<TestFragment,TestFragmentModel>{
    @Inject
    public TestFragmentPresenter(){}


    public void onClickButton() {
//        ToastUtils.showShortToast("presenter点击了按钮！");
        mModel.setOnClickButton();
    }
}
