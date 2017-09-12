package com.leo.leomvp.net;

import com.leo.leomvp.bean.DetailsBean;
import com.leo.leomvp.bean.FanPianBean;
import com.leo.leomvp.bean.TestBean;
import com.leo.leomvp.bean.YingPingBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Leo on 2017/7/9.
 */

public interface HttpService {


    //菜谱接口
    @GET("/api/cook/list")
    Observable<CookListModel> getCookList(@Query("page") int page, @Query("rows") int rows);


    @POST("api/data/福利/10/1")
    Observable<TestBean> getResult();

//http://morguo.com/forum.php?mod=movieexplorer&v=4&androidflag=1&page=1
//    mod=movieexplorer&v=4&androidflag=1&page=1
    @GET("forum.php")
    Observable<FanPianBean> getFanPianResult(@Query("mod") String mod,
                                             @Query("v") int v,
                                             @Query("androidflag") int androidflag,
                                             @Query("page") int page);

    @GET("channel/movie/more/0?channel=wdj&version=4.0.2&uuid=ffffffff-a90e-706a-63f7-ccf973aae5ee&platform=android")
    Observable<DetailsBean> getDetailsResult();


    @GET("forum.php")
    Observable<YingPingBean> getAcinecism(@Query("mod") String mod,
                                          @Query("tid") String tid,
                                          @Query("androidflag") String androidflag);

}
