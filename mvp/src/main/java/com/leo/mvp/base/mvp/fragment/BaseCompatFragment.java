package com.leo.mvp.base.mvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.mvp.BaseApp;
import com.leo.mvp.base.bean.BaseBean;
import com.leo.mvp.util.data.AppUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUILoadingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Leo on 2017/12/14.
 */

public abstract class BaseCompatFragment<P extends BaseCompatFragmentPresent> extends SupportFragment {
    @Inject
    protected P mPresenter;

    protected String TAG;
    protected Context mContext;
    protected Activity mActivity;
    protected QMUILoadingView mLoadingView;
    protected BaseApp mApplication;
    protected Fragment mFragment;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindDagger2(savedInstanceState);
        if (this.mPresenter == null) {
            throw new RuntimeException("没有设置Presenter或者没有重写onCreate()方法");
        } else {
            mFragment = this;
            this.mPresenter.setFragment(this);
            this.mPresenter.onCreate(savedInstanceState);
            //沉浸式title
            QMUIStatusBarHelper.translucent(mActivity);
        }
    }

    protected abstract void bindDagger2(Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            //            return inflater.inflate(getLayoutId(), null);
            return inflater.inflate(getLayoutId(), container, false);
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = getClass().getSimpleName();
        getBundle(getArguments());
        initData();
        initUI(view, savedInstanceState);
    }


    /**
     * 初始化UI
     */
    protected abstract void initUI(View view, Bundle savedInstanceState);

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {
        mLoadingView = new QMUILoadingView(mActivity);
        mContext = AppUtils.getContext();
        mApplication = (BaseApp) mActivity.getApplication();
    }

    /**
     * 得到Activity传进来的值
     */
    private void getBundle(Bundle arguments) {

    }

    @LayoutRes
    protected abstract int getLayoutId();

    public View getLayoutView() {
        return null;
    }


    //---------------------------生命周期---------------------------------


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

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
        mPresenter.onDestroyView();

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mPresenter.onDetached();
        //---------------------------leakcanary---------------------------------
//        Application application = getActivity().getApplication();
//        if (application instanceof BaseApplication) {
//            RefWatcher refWatcher = ((BaseApplication) application).getRefWatcher(getActivity());
//            refWatcher.watch(this);
//        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBaseEventMessage(BaseBean baseBean){

    }

}
