package com.leo.mvp.base.mvp.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.leo.mvp.BaseApp;
import com.leo.mvp.util.AppManager;
import com.leo.mvp.util.data.AppUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Leo on 2017/12/14.
 */

public abstract class BaseCompatActivity<T extends BaseCompatPresenter> extends SupportActivity {
    protected BaseApp mApplication;
    protected Context mContext;
    private Activity mActivity;
    protected boolean isTransAnim;
    @Inject
    public T mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingDagger2(savedInstanceState);
        if (this.mPresenter == null) {
            throw new RuntimeException("没有设置Presenter或者没有重写onCreate()方法");
        } else {
            mActivity = this;
            this.mPresenter.setActivity(this);
            this.mPresenter.onCreate(savedInstanceState);
            //沉浸式title
            QMUIStatusBarHelper.translucent(this);
        }
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
//        setTheme(ThemeUtils.themeArr[SpUtils.getThemeIndex(this)][
//                SpUtils.getNightModel(this) ? 1 : 0]);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initData();
        initView(savedInstanceState);

    }


    /**
     * 初始化View
     * 子类实现，控件绑定，视图初始化等内容
     *
     * @author Leo
     * created at 2017/12/14 下午2:18
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 获取当前layout的布局id，用于设置当前布局
     *
     * @author Leo
     * created at 2017/12/14 下午2:12
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     * 子类可以复写此方法初始化子类数据
     *
     * @author Leo
     * created at 2017/12/14 下午2:10
     */
    protected void initData() {
        mContext = AppUtils.getContext();
        mApplication = (BaseApp) getApplication();

        isTransAnim = true;
    }

    /**
    * 绑定dagger2
    *
    *@author Leo
    *created at 2017/12/14 下午11:55
    */
    protected abstract void bindingDagger2(Bundle savedInstanceState);

    //---------------------------------- 生命周期 ----------------------------------
    protected void onStart() {
        super.onStart();
        //activity加入栈管理
        AppManager.getAppManager().addActivity(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        this.mPresenter.onStart();
    }

    protected void onResume() {
        super.onResume();
        this.mPresenter.onResume();
    }

    protected void onPause() {
        super.onPause();
        this.mPresenter.onPause();
    }


    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        AppManager.getAppManager().finishActivity(this);


        this.mPresenter.onDetached();
        this.mPresenter = null;
    }

    /**
     * 显示加载框
     *
     * @param msg 加载内容字符串
     */
    public QMUITipDialog getLoadingDialog(String msg) {
        QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(msg).create();
        return dialog;
    }


    /**
    * 重复点击的dialog
    *
    *@author Leo
    *created at 2017/12/14 下午3:09
    */
    public QMUITipDialog getClickFastDialog(){
        QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                .setTipWord("请勿重复操作")
                .create();
        return dialog;
    }




}
