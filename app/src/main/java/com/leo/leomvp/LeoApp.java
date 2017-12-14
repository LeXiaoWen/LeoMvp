package com.leo.leomvp;

import com.leo.leomvp.net.HttpUrl;
import com.leo.mvp.BaseApp;
import com.leo.mvp.net.Api;

/**
 * Created by Leo on 2017/6/26.
 */

public class LeoApp extends BaseApp{
    @Override
    public void onCreate() {
        super.onCreate();
        Api.init(getApplicationContext(), HttpUrl.FANPIAN_BASE);
    }
}
