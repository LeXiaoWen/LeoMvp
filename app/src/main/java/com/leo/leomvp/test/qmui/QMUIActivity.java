package com.leo.leomvp.test.qmui;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.leo.leomvp.R;
import com.leo.leomvp.test.qmui.fragment.FourthFragment;
import com.leo.leomvp.test.qmui.fragment.MainFragment;
import com.leo.leomvp.test.qmui.fragment.SecondFragment;
import com.leo.leomvp.test.qmui.fragment.ThirdFragment;
import com.leo.mvp.base.mvp.activity.BaseCompatActivity;
import com.leo.mvp.util.toast.ToastUtils;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class QMUIActivity extends BaseCompatActivity {



    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.tabs)
    QMUITabSegment mTabs;

    private SupportFragment[] mFragments = new SupportFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;


    @Override
    protected void initView(Bundle savedInstanceState) {
//        QMUIStatusBarHelper.translucent(this);
        mTopbar.setTitle("测试activity");
        View view = LayoutInflater.from(this).inflate(R.layout.title_left, null);
        mTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("返回了！");
            }
        });
//        getLoadingDialog("测试").show();


        if (savedInstanceState == null){
            mFragments[FIRST] = MainFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            mFragments[THIRD] = ThirdFragment.newInstance();
            mFragments[FOURTH] = FourthFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        }else {

            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(MainFragment.class);
            mFragments[SECOND] = findFragment(SecondFragment.class);
            mFragments[THIRD] = findFragment(ThirdFragment.class);
            mFragments[FOURTH] = findFragment(FourthFragment.class);
        }


        QMUITabSegment.Tab recommend = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.drawable.recommended_normal),
                ContextCompat.getDrawable(this, R.drawable.recommended_click),
                "主页", false
        );
        QMUITabSegment.Tab remind = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.drawable.remind_normal),
                ContextCompat.getDrawable(this, R.drawable.remind_normal),
                "提示", false
        );
        QMUITabSegment.Tab sharon = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.drawable.sharon_normal),
                ContextCompat.getDrawable(this, R.drawable.sharon_click),
                "沙龙", false
        );

        QMUITabSegment.Tab main = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(this, R.drawable.mine_normal),
                ContextCompat.getDrawable(this, R.drawable.mine_click),
                "我的", false
        );


        mTabs
                .addTab(recommend)
                .addTab(remind)
                .addTab(sharon)
                .addTab(main);

        mTabs.selectTab(0);
        mTabs.setOnTabClickListener(new QMUITabSegment.OnTabClickListener() {
            @Override
            public void onTabClick(int index) {
                showHideFragment(mFragments[index]);
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_qmui;
    }


}
