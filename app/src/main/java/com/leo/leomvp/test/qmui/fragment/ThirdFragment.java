package com.leo.leomvp.test.qmui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.leo.leomvp.R;
import com.leo.mvp.base.mvp.fragment.BaseCompatFragment;

/**
 * Created by Leo on 2017/12/13.
 */

public class ThirdFragment extends BaseCompatFragment {


    public static ThirdFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ThirdFragment fragment = new ThirdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initUI(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fg_third;
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
