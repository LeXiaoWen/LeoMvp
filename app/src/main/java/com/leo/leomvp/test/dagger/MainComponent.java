package com.leo.leomvp.test.dagger;

import com.leo.leomvp.MainActivity;

import dagger.Component;

/**
 * Created by Leo on 2017/7/21.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
