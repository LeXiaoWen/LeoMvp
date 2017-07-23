package com.leo.mvp.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/7/19.
 */

public abstract class BaseFragment<T extends BaseFragmentPresenter> extends Fragment{


    @Inject
    protected T mPresenter;
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

//    protected abstract T createPresenter();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bindingDagger2();
        this.mPresenter.setFragment(this);
        this.mPresenter.onCreate(savedInstanceState);
    }

    protected abstract void bindingDagger2();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mPresenter = createPresenter();


        if (mRootView != null)
            return mRootView;
        if ((mRootView = onLayoutView(inflater, container, savedInstanceState)) != null)
            return mRootView;
        mRootView = inflater.inflate(onLayoutId(), container, false);
        //绑定framgent
        findView(mRootView);
        mPresenter.onCreateView();

        return mRootView;
    }

    protected abstract void findView(View rootView);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint())
            showFragment();
        else
            hideFragment();
    }

    protected  void showFragment(){

    }

    protected void hideFragment(){

    }

    /**
     * 如果要代码里面创建view调用这个方法
     * 如果不是特殊需求只需要实现onLayoutId就可以了,这个两个方法只用实现一个
     * 一般都不会用这个方法,所以并没有把这个方法抽象化
     *
     * @param inflater           解析控件的
     * @param container          fragment的父控件
     * @param savedInstanceState 假如acivity被强制销毁或者复现的时候就会调用这个方法
     * @return
     */
    protected View onLayoutView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    /**
     * 实现方法返回需要加载的xml布局
     * 如果需要手动代码创建布局,则实现{@link #(LayoutInflater, Bundle)}
     *
     * @return LayoutRes
     */
    protected abstract int onLayoutId();

    /**
     * 当fragment创建并且butterknife解析控件完成后回调这个方法
     * 在这里面写一些数据初始化操作
     *
     */

    //---------------------------生命周期---------------------------------

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRootView = null;
        mPresenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetached();
    }
}
