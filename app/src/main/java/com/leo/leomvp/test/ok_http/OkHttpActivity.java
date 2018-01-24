package com.leo.leomvp.test.ok_http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.gson.Gson;
import com.leo.leomvp.R;
import com.leo.mvp.util.toast.ToastUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpActivity extends AppCompatActivity {

    @BindView(R.id.btn_okhttp)
    Button mBtnOkhttp;
    private String url = "http://morguo.com/forum.php?mod=movieexplorer&v=4&androidflag=1&page=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);

    }

    private void initOkHttp() {
        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Headers headers = response.headers();
                int code = response.code();
                if (code != 200) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ToastUtils.showShortToast("请求成功！");
                    }
                });
                ResponseBody body = response.body();
                Gson gson = new Gson();
                TestBean testBean = gson.fromJson(response.body().string(), TestBean.class);
                List<TestBean.DataBean.ExplorerbannerlistBean> explorerbannerlist = testBean.getData().getExplorerbannerlist();

            }
        });
    }

    @OnClick(R.id.btn_okhttp)
    public void onViewClicked() {
        initOkHttp();

    }
}
