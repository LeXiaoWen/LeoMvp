package com.leo.leomvp.net;

import com.leo.leomvp.bean.DetailsBean;
import com.leo.leomvp.bean.FanPianBean;
import com.leo.leomvp.bean.YingPingBean;
import com.leo.mvp.net.Api;
import com.leo.mvp.net.ApiService;
import com.leo.mvp.net.RxSchedulers;
import com.leo.mvp.net.factory.ApiFactory;

import io.reactivex.Observable;

/**
 * Created by Leo on 2017/7/9.
 */

public class HttpFactory {

    private static HttpService  service= Api.getInstance().create(HttpService.class);
    public static Observable<DetailsBean> getDetailsResult(){
        return service.getDetailsResult().compose(RxSchedulers.<DetailsBean>io_main());
    }

    public static Observable<FanPianBean> getFanPianResult(int page){
        return service.getFanPianResult("movieexplorer",4,1,page).compose(RxSchedulers.<FanPianBean>io_main());
    }

    public static Observable<YingPingBean> getAcinecism(String tid){
        return service.getAcinecism("viewthread",tid,"1").compose(RxSchedulers.<YingPingBean>io_main());
    }

}
