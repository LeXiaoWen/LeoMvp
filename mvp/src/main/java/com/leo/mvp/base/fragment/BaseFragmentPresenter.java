package com.leo.mvp.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Leo on 2017/7/20.
 */

public abstract class BaseFragmentPresenter<T extends BaseFragment,E extends BaseFragmentModel> {
    protected T mFragment;
    @Inject
    protected E mModel;


    @Inject
    void setPresenter() {
        mModel.setPresenter(this);
    }

    public void setFragment(T fragment) {
        this.mFragment = fragment;
    }

    public void execute(Subscription subscription) {
    }


    public void onAttach(Context context) {

    }

    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    public void onCreateView() {

    }

    public void onResume() {
    }

    public void onPause() {
    }


    public void onDestroyView() {
    }


    public void onDetached() {
        mModel.onDetached();
        mFragment = null;

    }
}
