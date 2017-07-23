package com.leo.leomvp.net;

import com.leo.leomvp.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leo on 2017/7/9.
 */

public interface HttpService {


    //菜谱接口
    @GET("/api/cook/list")
    Observable<CookListModel> getCookList(@Query("page") int page, @Query("rows") int rows);


    @GET("api/data/福利/10/1")
    Observable<TestBean> getResult();

}
