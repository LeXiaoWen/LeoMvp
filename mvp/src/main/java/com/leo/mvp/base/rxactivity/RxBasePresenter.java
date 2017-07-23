package com.leo.mvp.base.rxactivity;

import android.os.Bundle;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/7/23.
 */

public abstract class RxBasePresenter<A extends RxBaseActivity,M extends RxBaseModel> {
    protected A mActivity;
    @Inject
    protected M mModel;


    public void setActivity(A mActivity) {
        this.mActivity = mActivity;
    }

    public void onCreate(Bundle savedInstanceState) {

    }

    //---------------------------------- 生命周期 start ----------------------------------
    public void onStart() {

    }

    public void onResume() {
    }

    public void onPause() {

    }

    public void onDetached() {

    }

    public void onCreateView() {

    }

    public void onStop() {

    }

    //---------------------------------- 生命周期 end ----------------------------------
}
