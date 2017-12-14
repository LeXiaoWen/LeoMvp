package com.leo.leomvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.leo.leomvp.test.dagger.Cloth;
import com.leo.leomvp.test.dagger.DaggerMainComponent;
import com.leo.leomvp.test.dagger.MainComponent;
import com.leo.leomvp.test.dagger.MainModule;
import com.leo.mvp.util.log.LogUtils;
import com.leo.mvp.util.toast.ToastUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Inject
    Cloth mCloth;
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.button_dagger)
    Button mButtonDagger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainComponent build = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        build.inject(this);



    }


    @OnClick({R.id.btn,R.id.button_dagger})
    public void onViewClicked(View view) {


        switch (view.getId()){
            case R.id.btn:

                String s = mCloth.toString();
                ToastUtils.showShortToast(s);
                LogUtils.e(s);
                break;
            case R.id.button_dagger:
                ToastUtils.showShortToast("点击了按钮2！");

                break;
        }
    }


}
