package com.leo.mvp.base.mvp.fragment;

import android.os.Bundle;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/12/15.
 */

public abstract class BaseCompatFragmentPresent<F extends BaseCompatFragment,M extends BaseCompatFragmentModel> {

    protected F mFragment;
    @Inject
    protected M mModel;

    @Inject
    public void setPresenter() {
        this.mModel.setPresenter(this);
    }

    public void setFragment(F fragment) {
        mFragment = fragment;
    }

    public void onCreate(Bundle savedInstanceState) {

    }


    public void onDestroyView() {

    }

    public void onDetached() {

    }

    public void onPause() {

    }

    public void onResume() {

    }
}
