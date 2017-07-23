package com.leo.mvp.base.activity;

/**
 * Created by Leo on 2017/6/26.
 */

public abstract class BaseModel<T extends BasePresenter> {
    protected T mPresenter;

    public BaseModel() {
    }

    public void setPresenter(T mPresenter) {
        this.mPresenter = mPresenter;
    }

    public void onDetached() {
    }


}
