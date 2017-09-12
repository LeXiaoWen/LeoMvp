package com.leo.leomvp.test.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.leo.leomvp.R;
import com.leo.leomvp.bean.DetailsBean;
import com.leo.mvp.base.adapter.BaseRecylerAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Leo on 2017/8/7.
 */

public class MyAdapter extends BaseRecylerAdapter<DetailsBean.Data,MyAdapter.MyHolder>{

    private Context mContext;
    public MyAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected MyHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mFrom.inflate(R.layout.item_recycler,parent,false));
    }


    @Override
    protected void onBindHolder(MyHolder holder, int position) {
        holder.mTvTitle.setText(getData(position).getTitle());

        Picasso.with(mContext)
                .load(getData(position)
                        .getShare_info().getImage()).into(holder.mSdvImage);

    }



    class MyHolder extends BaseRecylerAdapter.BaseViewHolder{


        private  TextView mTvTitle;
        private final ImageView mSdvImage;

        public MyHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mSdvImage = (ImageView) itemView.findViewById(R.id.iv_info);
        }
    }
}
