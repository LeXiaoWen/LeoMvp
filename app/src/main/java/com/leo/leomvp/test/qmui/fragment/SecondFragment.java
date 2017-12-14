package com.leo.leomvp.test.qmui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.leo.leomvp.R;
import com.leo.mvp.base.mvp.fragment.BaseCompatFragment;
import com.qmuiteam.qmui.widget.QMUILoadingView;

/**
 * Created by Leo on 2017/12/13.
 */

public class SecondFragment extends BaseCompatFragment {


    QMUILoadingView mQmuiLv;

    public static SecondFragment newInstance() {
        Bundle args = new Bundle();

        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        mLoadingView.start();
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_second;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
    }




}
