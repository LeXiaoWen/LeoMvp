package com.leo.leomvp.test.qmui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.leo.leomvp.R;
import com.leo.mvp.base.mvp.fragment.BaseCompatFragment;


/**
 * Created by Leo on 2017/12/13.
 */

public class MainFragment extends BaseCompatFragment {

    private static final int REQ_MSG = 10;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

//    private SupportFragment[] mFragments = new SupportFragment[3];


    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initUI(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_main;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    //    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    @Override
//    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
//        super.onFragmentResult(requestCode, resultCode, data);
//        if (requestCode == REQ_MSG && resultCode == RESULT_OK) {
//
//        }
//    }

    /**
     * start other BrotherFragment
     */
//    public void startBrotherFragment(SupportFragment targetFragment) {
//        start(targetFragment);
//    }
}
