package com.leo.leomvp.test.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leo on 2017/7/21.
 */
@Module
public class MainModule {
    @Provides
    public Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }
}
