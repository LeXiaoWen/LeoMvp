package com.leo.leomvp.test.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.util.toast.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.dialog1)
    Button mDialog1;
    @BindView(R.id.dialog2)
    Button mDialog2;
    @BindView(R.id.dialog3)
    Button mDialog3;
    @BindView(R.id.dialog4)
    Button mDialog4;
    private AlertDialog mDialog11;
    private AlertDialog.Builder mNormalDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        initDialog();
    }

    private void initDialog() {
        mDialog11 = new AlertDialog.Builder(this)
                .setTitle("Dialog样式一")
                .setMessage("确认信息")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShortToast("确定");
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShortToast("取消");
                        dialog.dismiss();
                    }
                }).create();

        mNormalDialog = new AlertDialog.Builder(this);
        mNormalDialog.setIcon(R.drawable.ic_loading_rotate);
        mNormalDialog.setTitle("我是一个普通Dialog").setMessage("你要点击哪一个按钮呢?");
        mNormalDialog.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                        ToastUtils.showShortToast("确认按钮！");
                        dialog.dismiss();
                    }
                });
        mNormalDialog.setNeutralButton("中间",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ...To-do
                        ToastUtils.showShortToast("中间按钮！");
                        dialog.dismiss();
                    }
                });
        mNormalDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ...To-do
                ToastUtils.showShortToast("取消按钮！");
                dialog.dismiss();
            }
        });
    }

    @OnClick({R.id.dialog1, R.id.dialog2, R.id.dialog3, R.id.dialog4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dialog1:
                showDialog1();
                break;
            case R.id.dialog2:
                showDialog2();
                break;
            case R.id.dialog3:
                showDialog3();
                break;
            case R.id.dialog4:
                showDialog4();
                break;
        }
    }

    private void showDialog1() {
        mDialog11.show();
    }

    private void showDialog2() {
        mNormalDialog.show();
    }

    private void showDialog3() {

    }

    private void showDialog4() {

    }
}
