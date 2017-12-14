package com.leo.mvp.base.mvp.fragment;

/**
 * Created by Leo on 2017/12/15.
 */

public abstract class BaseCompatFragmentModel<P extends BaseCompatFragmentPresent> {
    protected P mPresenter;

    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }
}
