package com.leo.leomvp;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leo on 2017/7/20.
 */

@Module
public class AppModel {
    Application mApplication;

    public AppModel(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }
}
