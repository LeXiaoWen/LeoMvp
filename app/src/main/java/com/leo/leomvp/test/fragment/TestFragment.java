package com.leo.leomvp.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.base.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Leo on 2017/7/20.
 */

public class TestFragment extends BaseFragment<TestFragmentPresenter> {



    private static TestFragment mTestFragment = null;


    private TestFragment() {
    }

    public static TestFragment getInstance() {
        synchronized (TestFragment.class) {
            if (mTestFragment == null) {
                mTestFragment = new TestFragment();
            }
            return mTestFragment;
        }
    }

    @Override
    protected void bindingDagger2() {
        DaggerTestFragmentComponent.create().inject(this);
    }

    @Override
    protected void findView(View view) {
//        ButterKnife.bind(getActivity());
//        Button button = (Button) view.findViewById(R.id.b_test);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.onClickButton();
//            }
//        });

    }



    @Override
    protected int onLayoutId() {
        return R.layout.fr_test;
    }


}
