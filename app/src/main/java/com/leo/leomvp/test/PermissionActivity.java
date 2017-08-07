package com.leo.leomvp.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.util.toast.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class PermissionActivity extends AppCompatActivity {

    @BindView(R.id.b_check)
    Button mBCheck;
    @BindView(R.id.b_apply)
    Button mBApply;

    private static final int MY_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
        new RxPermissions(this).request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull Boolean aBoolean) throws Exception {
               if (aBoolean){
                   ToastUtils.showShortToast("权限打开！");
               }else {
                   ToastUtils.showShortToast("权限被拒绝！");
               }
            }
        });
//        test();
    }

    private void test() {
        Subscriber<String> subscriber = new Subscriber<String>(){

            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @OnClick({R.id.b_check, R.id.b_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.b_check:
                check();
                break;
            case R.id.b_apply:
                apply();
                break;
        }
    }

    /**
     * 申请权限
     *
     * @author Leo
     * created at 2017/7/22 下午11:42
     */
    private void apply() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION);
    }

    /**
     * 检查权限
     *
     * @author Leo
     * created at 2017/7/22 下午11:42
     */
    private void check() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            apply();
        } else {
            call();
        }
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    call();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission
                    ToastUtils.showShortToast("权限被拒绝！");
                }

                return;
        }
    }
}
