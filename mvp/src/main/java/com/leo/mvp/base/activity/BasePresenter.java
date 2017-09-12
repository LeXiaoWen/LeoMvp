package com.leo.mvp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.leo.mvp.base.activity.BaseActivity;
import com.leo.mvp.base.activity.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Leo on 2017/6/26.
 */

public abstract class BasePresenter<T extends BaseActivity,M extends BaseModel> {
    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();
    protected T mActivity;
    @Inject
    protected M mModel;

    public BasePresenter() {
    }

    @Inject
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
        this.mCompositeSubscription.clear();
        this.mCompositeSubscription = null;
    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
    }

    public void onStart() {
    }

    public void onResume() {

    }

    public void onPause() {
    }


    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null ) {
            mCompositeSubscription.clear();
        }
    }


    public void addSubscription(Observable observable, Consumer consumer) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer));

    }


}
