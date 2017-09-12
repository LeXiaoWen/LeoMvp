package com.leo.leomvp.test.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.leomvp.R;
import com.leo.leomvp.bean.FanPianBean;
import com.leo.leomvp.net.HttpUrl;
import com.leo.leomvp.test.recyclerview.TypeUtils;
import com.leo.mvp.base.adapter.BaseRecylerAdapter;
import com.leo.mvp.util.log.LogUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Leo on 2017/8/8.
 */

public class FanPianAdapter extends BaseRecylerAdapter<FanPianBean.data.ListBean, FanPianAdapter.FanPianViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    public FanPianAdapter(Context context) {
        super(context);
    }


    @Override
    public int getItemViewType(int position) {
        String type = getData(position).getType();
//        if (mHeaderView == null) return TYPE_NORMAL;
//        if (position == 0) return TYPE_HEADER;
        if (type.equals(TypeUtils.COLLECTION)){//影单
            LogUtils.e("type" + type);
            return TypeUtils.TYPE_COLLECTION;
        }else if (type.equals(TypeUtils.THREAD_ARTICLE)){//杂粮
            LogUtils.e("type" + type);
            return TypeUtils.TYPE_THREAD_ARTICLE;
        }else if (type.equals(TypeUtils.ACINECISM)){//影评
            LogUtils.e("type" + type);
            return TypeUtils.TYPE_ACINECISM;
        }else if (type.equals(TypeUtils.THREAD_VIDEO)){//短片
            LogUtils.e("type" + type);
            return TypeUtils.TYPE_THREAD_VIDEO;
        }else if (type.equals(TypeUtils.THREAD_GALLERY)){//画报
            LogUtils.e("type" + type);
            return TypeUtils.TYPE_THREAD_GALLERY;
        }else if (type.equals(TypeUtils.THREAD_MUSIC)){//音乐
            LogUtils.e("type" + type);
            return TypeUtils.TYPE_THREAD_MUSIC;
        }else if (type.equals(TypeUtils.MOVIE_LINES)){//图文
            LogUtils.e("type" + type);
            return TypeUtils.TYPE_MOVIE_LINES;
        }
        return TYPE_NORMAL;
    }


//    public void setHeaderView(View headerView) {
//        mHeaderView = headerView;
//        notifyItemInserted(0);
//    }
//
//    public View getHeaderView() {
//        return mHeaderView;
//    }


    @Override
    protected FanPianViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new FanPianViewHolder(mFrom.inflate(R.layout.item_fanpian, parent, false));
    }


    @Override
    protected void onBindHolder(FanPianViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case TypeUtils.TYPE_COLLECTION://影单
                bindViews(holder, position,"影单");
                break;
            case TypeUtils.TYPE_THREAD_ARTICLE://杂粮
                bindViews(holder, position,"杂粮");
                break;
            case TypeUtils.TYPE_ACINECISM://影评
                bindViews(holder, position,"影评");
                break;
            case TypeUtils.TYPE_THREAD_VIDEO://短片
                bindViews(holder, position,"短片");
                break;
            case TypeUtils.TYPE_THREAD_GALLERY://画报
                bindViews(holder, position,"画报");
                break;
            case TypeUtils.TYPE_THREAD_MUSIC://音乐
                bindViews(holder, position,"音乐");
                break;
            case TypeUtils.TYPE_MOVIE_LINES://
                bindViews(holder, position,"什么鬼");
                break;
        }


    }

    private void bindViews(FanPianViewHolder holder, int position,String type) {
        FanPianBean.data.ListBean.ItemdataBean itemdata = getData(position).getItemdata();
        Picasso.with(mContext)
                .load(HttpUrl.FANPIAN_BASE + itemdata.getImage())
                .into(holder.mImageView);

        holder.mTvSubject.setText(itemdata.getSubject());
        holder.mTvIntro.setText(itemdata.getIntro());
        holder.mTvType.setText(type);
    }

    public void addData(List<FanPianBean.data.ListBean> list) {
        for (FanPianBean.data.ListBean listBean : list) {
            getData().add(listBean);
        }
        notifyDataSetChanged();
    }

    class FanPianViewHolder extends BaseRecylerAdapter.BaseViewHolder {

        private final TextView mTvSubject;
        private final TextView mTvIntro;
        private final ImageView mImageView;
        private final TextView mTvType;

        public FanPianViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_fp);
            mTvSubject = (TextView) itemView.findViewById(R.id.tv_fp_subject);
            mTvIntro = (TextView) itemView.findViewById(R.id.tv_fp_intro);
            mTvType = (TextView) itemView.findViewById(R.id.tv_type);

        }
    }



}
