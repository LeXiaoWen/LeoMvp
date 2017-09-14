package com.leo.mvp.base.fragment;

/**
 * Created by Leo on 2017/7/20.
 */

public abstract class BaseFragmentModel<T extends BaseFragmentPresenter> {
    protected T mPresenter;

    public void setPresenter(T presenter) {
        this.mPresenter = presenter;
    }

    public void onDetached() {

    }
}
