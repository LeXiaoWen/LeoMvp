package com.leo.leomvp.test.rxjava2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.util.log.LogUtils;
import com.leo.mvp.util.toast.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJava2Activity extends AppCompatActivity {

    @BindView(R.id.simple_use)
    Button mSimpleUse;
    @BindView(R.id.operator)
    Button mOperator;
    @BindView(R.id.more_use)
    Button mMoreUse;
    @BindView(R.id.more_simple)
    Button mMoreSimple;
    @BindView(R.id.change)
    Button mChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.simple_use, R.id.operator, R.id.more_use, R.id.more_simple,R.id.change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.simple_use:
                simpleUser();
                break;
            case R.id.operator:
                operator();
                break;
            case R.id.more_use:
                useMore();
                break;
            case R.id.more_simple:
                moreSimple();
                break;
            case R.id.change:
                changeThread();
                break;

        }
    }

    private void changeThread() {
        Flowable
                .just("Leo")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {

            }
        });
    }

    private void moreSimple() {
        Flowable.just("93").map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String s) throws Exception {
                return Integer.parseInt(s);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                LogUtils.e(integer.intValue() + "");
                ToastUtils.showShortToast(integer.intValue() + "");
            }
        });
    }

    private void operator() {

    }


    private void useMore() {
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                LogUtils.e("onNext : " + s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("Leo");
            }
        }, BackpressureStrategy.BUFFER).subscribe(subscriber);
    }

    private void simpleUser() {
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                LogUtils.e("observer    " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                LogUtils.e("observer    完成了！");
            }
        };

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("Leo");
            }
        });

        observable.subscribe(observer);

    }
}
