package com.leo.mvp.base.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leo.mvp.base.activity.BaseActivity;
import com.leo.mvp.base.model.BaseModel;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Leo on 2017/6/26.
 */

public abstract class BasePresenter<T extends BaseActivity,M extends BaseModel> {
    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    protected T mActivity;
    protected M mModel;

    public BasePresenter() {
    }

    public void setPresenter() {
        this.mModel.setPresenter(this);
    }

    public void onCreateView() {
    }
    public void setActivity(T mActivity) {
        this.mActivity = mActivity;
    }

    public void onDetached() {
        this.mModel.onDetached();
        this.mActivity = null;
        this.mCompositeSubscription.unsubscribe();
        this.mCompositeSubscription = null;
    }

    public void execute(Subscription subscription) {
        this.mCompositeSubscription.add(subscription);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
    }

    public void onStart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }
}
