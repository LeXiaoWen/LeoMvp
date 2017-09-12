package com.leo.leomvp.test.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.leo.leomvp.R;
import com.leo.mvp.util.log.LogUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JavaActivity extends AppCompatActivity {

    @BindView(R.id.btn_java_test)
    Button mBtnJavaTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_java_test)
    public void onViewClicked() {
        test();
    }

    private void test() {
//        Random random = new Random(47);
//        int i = random.nextInt(100);
//        ToastUtils.showShortToast(i+"");

//        LogUtils.e(boolean_exp(1) + "");
//        LogUtils.e(boolean_exp(2) + "");

//        LogUtils.e(transition(0.7)+"");
//        LogUtils.e(transition2(0.7)+"");
//        mathUtils();
        breakAndContinue();

        Spiciness hot = Spiciness.HOT;
        LogUtils.e(hot.toString());

    }


    private int boolean_exp(int a) {
        return a < 2 ? a * 2 : a * 3;
    }

    private int transition(double a) {
        return (int) a;
    }

    private long transition2(double a) {
        return Math.round(a);
    }


    private void mathUtils() {
        String[] name = {"leo", "Tom", "Jimmy", "Tony"};
        for (String s : name) {
            LogUtils.e(s);
        }
    }

    private void breakAndContinue() {
        for (int i = 0; i < 100; i++) {
            if (i == 50) break;
            if (i % 9 != 0) continue;
            LogUtils.e(i+"");
        }
    }

    public enum Spiciness{
        NOT, MILD, MEDIUM, HOT, FLAMING
    }

    private void okHttpTest(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();

            }
        });


    }
}
