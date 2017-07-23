package com.leo.leomvp.test;

import com.leo.leomvp.bean.TestBean;
import com.leo.leomvp.net.CookListModel;
import com.leo.leomvp.net.HttpService;
import com.leo.mvp.base.activity.BasePresenter;
import com.leo.mvp.net.Api;
import com.leo.mvp.net.ApiCallback;
import com.leo.mvp.net.RxSchedulers;
import com.leo.mvp.util.log.LogUtils;
import com.leo.mvp.util.toast.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Leo on 2017/6/26.
 */

public class TestActivityPresenter extends BasePresenter<TestActivity,TestActivityModel>{
    @Inject
    public TestActivityPresenter() {
    }

    public void onButtonClick() {
//        Observable<TestBean> result = Api.getInstance().retrofit.create(HttpService.class).getResult();
//
//        addSubscription(result, new Consumer<TestBean>() {
//            @Override
//            public void accept(@NonNull TestBean testBean) throws Exception {
//                List<TestBean.ResultsBean> results = testBean.getResults();
//                LogUtils.e(results.size()+"");
//            }
//        });


        HttpService service = Api.getInstance().create(HttpService.class);
        Observable<TestBean> result = service.getResult();

//        Observable.create().subscribe()
        Flowable.create(new FlowableOnSubscribe<TestBean>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<TestBean> e) throws Exception {

            }
        },BackpressureStrategy.BUFFER).subscribe(new Consumer<TestBean>() {
            @Override
            public void accept(@NonNull TestBean testBean) throws Exception {

            }
        });



       result.compose(RxSchedulers.<TestBean>io_main()).subscribe(new Consumer<TestBean>() {
            @Override
            public void accept(@NonNull TestBean testBean) throws Exception {

            }
        });

        mModel.onClick();
//        ToastUtils.showLongToast("点击了事件");
    }

}
