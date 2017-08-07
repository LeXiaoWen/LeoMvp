package com.leo.mvp.base.rxactivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.leo.mvp.R;
import com.leo.mvp.base.bean.BaseBean;
import com.leo.mvp.net.HttpResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by Leo on 2017/7/23.
 */

public abstract class RxBaseActivity<P extends RxBasePresenter> extends AppCompatActivity {
    @Inject
    protected P mPresenter;
    private static final Set<RxBaseActivity> activityList = new HashSet();

    private CompositeDisposable disposables2Stop;// 管理Stop取消订阅者者
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingDagger2(savedInstanceState);
        if (disposables2Destroy != null) {
            throw new IllegalStateException("onCreate called multiple times");
        }
        disposables2Destroy = new CompositeDisposable();

        if (this.mPresenter == null) {
            throw new RuntimeException("没有设置Presenter或者没有重写onCreate()方法");
        } else {
            this.mPresenter.setActivity(this);
            toolBarInit();
            this.mPresenter.onCreate(savedInstanceState);
        }

    }

    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        this.mPresenter.onCreateView();
    }


    /**
     * Rx优雅处理服务器返回
     *
     * @param <T>
     * @return
     */
    public <T> ObservableTransformer<HttpResult<T>, T> handleResult() {
        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<HttpResult<T>> upstream) {
                upstream.flatMap(new Function<HttpResult<T>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull HttpResult<T> result) throws Exception {
                        if (result.isSuccess()) {
                            return createData(result.data);
                        } else if (result.isTokenInvalid()) {
                            //处理token失效，tokenInvalid方法在BaseActivity 实现
                            tokenInvalid();
                        } else {
                            return Observable.error(new Exception(result.msg));
                        }
                        return null;
                    }
                });
                return Observable.empty();
            }
        };
    }

    private void tokenInvalid() {

    }

    private <T> Observable<T> createData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                try {
                    subscriber.onNext(t);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }


    public boolean addRxStop(Disposable disposable) {
        if (disposables2Stop == null) {
            throw new IllegalStateException(
                    "addUtilStop should be called between onStart and onStop");
        }
        disposables2Stop.add(disposable);
        return true;
    }

    public boolean addRxDestroy(Disposable disposable) {
        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "addUtilDestroy should be called between onCreate and onDestroy");
        }
        disposables2Destroy.add(disposable);
        return true;
    }


    public void remove(Disposable disposable) {
        if (disposables2Stop == null && disposables2Destroy == null) {
            throw new IllegalStateException("remove should not be called after onDestroy");
        }
        if (disposables2Stop != null) {
            disposables2Stop.remove(disposable);
        }
        if (disposables2Destroy != null) {
            disposables2Destroy.remove(disposable);
        }
    }


    //---------------------------------- 6.0沉浸式title  start----------------------------------
    private void toolBarInit() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public Toolbar initToolBarAsHome(String title) {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            TextView toolbaTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
            toolbaTitle.setText(title);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        return toolbar;
    }

    //---------------------------------- 6.0沉浸式title  end----------------------------------


    //---------------------------------- 生命周期 start----------------------------------
    protected void onStart() {
        super.onStart();
        activityList.add(this);
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
        activityList.remove(this);


        this.mPresenter.onDetached();
        this.mPresenter = null;

        if (disposables2Destroy == null) {
            throw new IllegalStateException(
                    "onDestroy called multiple times or onCreate not called");
        }
        disposables2Destroy.dispose();
        disposables2Destroy = null;

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.mPresenter.onStop();
        if (disposables2Stop == null) {
            throw new IllegalStateException("onStop called multiple times or onStart not called");
        }
        disposables2Stop.dispose();
        disposables2Stop = null;
    }

    //---------------------------------- 生命周期 end ----------------------------------


    //---------------------------------- eventBus start ----------------------------------
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReslut(BaseBean<String> eventBaseBean) {

    }

    //---------------------------------- eventBus end----------------------------------


    //---------------------------------- activity 栈管理 start----------------------------------
    public void shutDown() {
        this.finish();
    }

    public void clearAll() {
        activityList.clear();
    }

    //---------------------------------- activity 栈管理 end ----------------------------------

    /**
     * 绑定dagger2
     *
     * @author Leo
     * created at 2017/7/23 下午10:45
     */
    protected abstract void bindingDagger2(Bundle savedInstanceState);
}
