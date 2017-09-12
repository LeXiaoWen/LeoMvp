package com.leo.leomvp.test.exception;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.util.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExceptionActivity extends AppCompatActivity {

    @BindView(R.id.btn_test)
    Button mBtnTest;
    private List<String> mList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_test)
    public void onViewClicked() {
//        showList();
        handleString();
    }

    private void handleString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ Leo , ");
        stringBuilder.append("Tom , ");
        stringBuilder.append("Jimmy ]");
        String s = stringBuilder.toString() + super.toString();
        ToastUtils.showShortToast(s);
    }

    private void showList() {
        try {
            for (String s : mList) {
                ToastUtils.showShortToast(s);
            }
        }catch (Exception e){
            mList = new ArrayList<>();
            mList.add("Leo");
            mList.add("Tom");
            mList.add("Jimmy");
            mList.add("Lin");
            e.printStackTrace();
            ToastUtils.showShortToast(e.toString());
        }

    }
}
