package com.leo.mvp.base.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Leo on 2017/12/14.
 */

public  abstract class BaseCompatPresenter<A extends BaseCompatActivity,M extends BaseCompatModel> {

   protected A mActivity;
   @Inject
   protected M mModel;
    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    @Inject
    public void setPresenter() {
        this.mModel.setPresenter(this);
    }

    public void setActivity(A mActivity) {
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
        mModel.onStart();
    }

    public void onResume() {
        mModel.onResume();
    }

    public void onPause() {
        mModel.onPause();
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
