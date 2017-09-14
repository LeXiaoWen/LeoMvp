package com.leo.mvp.base.fragment;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/7/20.
 */

public abstract class BaseFragmentModel<T extends BaseFragmentPresenter> {
    @Inject
    protected T mPresenter;

    public void setPresenter(T presenter) {
        this.mPresenter = presenter;
    }

    public void onDetached() {

    }
}
