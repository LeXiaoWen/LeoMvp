package com.leo.mvp;

import android.app.Application;

import com.leo.mvp.util.Utils;
import com.leo.mvp.util.log.LogUtils;
import com.leo.mvp.util.toast.ToastUtils;

/**
 * Created by Leo on 2017/6/26.
 */

public class BaseApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
        LogUtils.init("leo");
        ToastUtils.init(false);
    }
}
