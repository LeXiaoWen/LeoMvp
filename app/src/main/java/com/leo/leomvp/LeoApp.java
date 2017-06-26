package com.leo.leomvp;

import android.app.Application;

import com.leo.mvp.util.Utils;
import com.leo.mvp.util.log.LogUtils;
import com.leo.mvp.util.toast.ToastUtils;

/**
 * Created by Leo on 2017/6/26.
 */

public class LeoApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init("leo");
        Utils.init(getApplicationContext());
        ToastUtils.init(false);
    }
}
