package com.leo.leomvp.test.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.util.toast.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class RxPermissionActivity extends AppCompatActivity {

    @BindView(R.id.camera)
    Button mCamera;
    @BindView(R.id.call)
    Button mCall;
    private RxPermissions mPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_permission);
        ButterKnife.bind(this);
        mPermissions = new RxPermissions(this);
    }

    @OnClick({R.id.camera, R.id.call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.camera:
                openCamera();
                break;
            case R.id.call:
                callPhone();
                break;
        }
    }

    private void callPhone() {
        mPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Boolean granted) {
                        if (granted){
                            ToastUtils.showShortToast("取得权限！");
                        }else {
                            ToastUtils.showShortToast("未取得权限！");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void openCamera() {

    }
}
