package com.leo.leomvp.test.recyclerview;

import com.leo.leomvp.bean.FanPianBean;
import com.leo.mvp.base.activity.BaseModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Leo on 2017/8/7.
 */

public class RecyclerActivityModel extends BaseModel<RecyclerActivityPresenter>{
    @Inject
    public RecyclerActivityModel() {
    }

    public void handeData(FanPianBean.data data) {
        List<FanPianBean.data.ExplorerbannerlistBean> explorerbannerlist = data.getExplorerbannerlist();
        List<FanPianBean.data.ListBean> list = data.getList();
        mPresenter.bindBannerData(explorerbannerlist);
        mPresenter.bindListData(list);
    }
}
