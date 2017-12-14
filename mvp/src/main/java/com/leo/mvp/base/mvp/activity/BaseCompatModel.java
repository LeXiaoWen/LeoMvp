package com.leo.mvp.base.mvp.activity;

/**
 * Created by Leo on 2017/12/14.
 */

public abstract class BaseCompatModel<P extends BaseCompatPresenter> {
    protected P mPresenter;

    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    public void onDetached() {

    }

    public void onStart() {

    }

    public void onResume() {

    }

    public void onPause() {

    }
}
