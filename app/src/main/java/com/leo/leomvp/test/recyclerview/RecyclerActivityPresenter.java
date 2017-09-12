package com.leo.leomvp.test.recyclerview;

import android.content.Intent;

import com.leo.leomvp.bean.FanPianBean;
import com.leo.leomvp.bean.YingPingBean;
import com.leo.leomvp.net.HttpFactory;
import com.leo.leomvp.test.recyclerview.info.InfoActivity;
import com.leo.mvp.base.activity.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Leo on 2017/8/7.
 */

public class RecyclerActivityPresenter extends BasePresenter<RecyclerActivity,RecyclerActivityModel>{
    @Inject
    public RecyclerActivityPresenter() {
    }


    public void initData(int page){


        HttpFactory.getFanPianResult(page).subscribe(new Consumer<FanPianBean>() {
            @Override
            public void accept(@NonNull FanPianBean fanPianBean) throws Exception {
//                mActivity.bindFanPianBean(fanPianBean.getData());
                mModel.handeData(fanPianBean.getData());
            }
        });
    }

    public void bindBannerData(List<FanPianBean.data.ExplorerbannerlistBean> bannerlist) {
            mActivity.bindBannerData(bannerlist);
    }

    public void bindListData(List<FanPianBean.data.ListBean> list) {
        mActivity.bindListData(list);
    }

    public void getInfo(String tid) {
        HttpFactory.getAcinecism(tid).subscribe(new Consumer<YingPingBean>() {

            @Override
            public void accept(@NonNull YingPingBean yingPingBean) throws Exception {
                bindInfoData(yingPingBean);
            }
        });
    }


    public void bindInfoData(YingPingBean bean) {
        String webviewurl = bean.getData().getWebviewurl();
        String forum = bean.getData().getForum();
        Intent intent = new Intent(mActivity, InfoActivity.class);
        intent.putExtra("infoTitle", forum);
        intent.putExtra("infoUrl", webviewurl);
        mActivity.startActivity(intent);
    }
}
