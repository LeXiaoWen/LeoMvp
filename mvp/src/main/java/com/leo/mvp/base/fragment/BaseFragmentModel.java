package com.leo.mvp.base.fragment;

/**
 * Created by Leo on 2017/7/20.
 */

public abstract class BaseFragmentModel<T extends BaseFragmentPresenter> {

    T presenter;

    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    public void onDetached() {

    }
}
