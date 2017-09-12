package com.leo.leomvp.test.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.leo.leomvp.R;
import com.leo.leomvp.bean.FanPianBean;
import com.leo.leomvp.bean.YingPingBean;
import com.leo.leomvp.net.HttpUrl;
import com.leo.leomvp.test.recyclerview.adapter.FanPianAdapter;
import com.leo.leomvp.test.recyclerview.info.InfoActivity;
import com.leo.mvp.base.activity.BaseActivity;
import com.leo.mvp.base.adapter.BaseRecylerAdapter;
import com.leo.mvp.util.toast.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leo on 2017/8/7.
 */

public class RecyclerActivity extends BaseActivity<RecyclerActivityPresenter> implements XRecyclerView.LoadingListener, BaseSliderView.OnSliderClickListener, BaseRecylerAdapter.OnItemClickListener {


    @BindView(R.id.xrc_list)
    XRecyclerView mXrcList;


    private int page = 1;
    private FanPianAdapter mFanPianAdapter;
    private View mHeadView;
    private SliderLayout mSlider;
    private AlertDialog.Builder mDialog;

    @Override
    protected void bindingDagger2(Bundle savedInstanceState) {
        DaggerRecyclerActivityComponent.create().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);
        initToolBarAsHome("RecyclerView");
        showProgressDialog();

        //初始化recyclerView
        initRecyclerView();


        mXrcList.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);
        mXrcList.setRefreshProgressStyle(ProgressStyle.BallClipRotate);


        LayoutInflater inflater = LayoutInflater.from(this);
        mHeadView = inflater.inflate(R.layout.head, null);
        mXrcList.addHeaderView(mHeadView);
        //设置刷新

        mXrcList.setLoadingListener(this);
        mXrcList.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        mFanPianAdapter = new FanPianAdapter(this);
        mFanPianAdapter.setOnItemClickListener(this);
        mXrcList.setAdapter(mFanPianAdapter);
    }

    private void initSlider(List<FanPianBean.data.ExplorerbannerlistBean> bannerlist) {

        if (mSlider == null) {
            mSlider = (SliderLayout) mHeadView.findViewById(R.id.head_slider);
        }
        mSlider.removeAllSliders();
        for (FanPianBean.data.ExplorerbannerlistBean bannerBean : bannerlist) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(bannerBean.getSubject())
                    .image(HttpUrl.FANPIAN_BASE + bannerBean.getImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
//            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra", name);
            mSlider.addSlider(textSliderView);

        }
        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);

    }

    private void initRecyclerView() {
        mXrcList.refresh();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mXrcList.setLayoutManager(manager);
        mXrcList.setHasFixedSize(true);
    }


    @Override
    protected void onResume() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mPresenter.initData(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        super.onResume();

    }

    @Override
    public void onRefresh() {
        mPresenter.initData(1);
    }

    @Override
    public void onLoadMore() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            page++;
                            mPresenter.initData(page);
                            ToastUtils.showShortToast("加载更多完成");
                            mXrcList.loadMoreComplete();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


    private void initList(List<FanPianBean.data.ListBean> list) {

        if (page == 1) {
            mFanPianAdapter.setData(list);
        } else {
            mFanPianAdapter.addData(list);
        }

        mFanPianAdapter.notifyDataSetChanged();

        mXrcList.refreshComplete();

        dismissProgressDialog();
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        String description = slider.getDescription();
        ToastUtils.showShortToast(description);
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        if (mSlider != null) {
            mSlider.stopAutoCycle();
        }
        super.onStop();
    }

    @Override
    public void onItemClick(int position, View view) {
        FanPianBean.data.ListBean.ItemdataBean bean = mFanPianAdapter.getData(position - 1).getItemdata();
        String tid = bean.getTid();
        mPresenter.getInfo(tid);
    }


    public void bindListData(List<FanPianBean.data.ListBean> list) {
        initList(list);
    }

    public void bindBannerData(List<FanPianBean.data.ExplorerbannerlistBean> bannerlist) {
        initSlider(bannerlist);
    }
}
