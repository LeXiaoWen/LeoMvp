package com.leo.mvp.base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.mvp.R;

import java.util.ArrayList;
import java.util.List;


/**
*
*
*@author Leo
*created at 2017/7/24 下午9:33
*/
public abstract class BaseRecylerAdapter<T, E extends BaseRecylerAdapter.BaseViewHolder> extends RecyclerView.Adapter<E> {
    protected  Context mContext;

    protected final LayoutInflater mFrom;
    List<T> mDataBeen;
    OnItemClickListener mOnItemClickListener;

    public BaseRecylerAdapter(Context context) {
        mContext = context;
        mFrom = LayoutInflater.from(context);
    }


    public void setData(List<T> dataBeen) {
        mDataBeen = dataBeen;
    }

    public List<T> getData() {
        isArrayNull();
        return mDataBeen;
    }

    public T getData(int position) {
        isArrayNull();
        if (position >= mDataBeen.size())
            return null;
        return mDataBeen.get(position);
    }

    public void replaceData(List<T> dataBeen) {
        isArrayNull();
        mDataBeen.clear();
        mDataBeen.addAll(dataBeen);
    }

    public void add(T dataBean) {
        isArrayNull();
        mDataBeen.add(dataBean);
    }

    public void addAll(List<T> dataBeen) {
        isArrayNull();
        mDataBeen.addAll(dataBeen);
    }

    public void clear() {
        isArrayNull();
        mDataBeen.clear();
    }


    public void isArrayNull() {
        if (mDataBeen == null)
            mDataBeen = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public E onCreateViewHolder(ViewGroup parent, int viewType) {
        final E holder = onCreateHolder(parent, viewType);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.getLayoutPosition()-1,holder.itemView);
                }
            });
        }

        Drawable background = holder.itemView.getBackground();
        if (background == null)
            holder.itemView.setBackgroundResource(R.drawable.l_mvp_touch_bg);
        return holder;
    }

    @Override
    public void onBindViewHolder(E holder, int position) {
        onBindHolder(holder, position);
    }

    /**
     * 创建holder
     *
     * @param parent   列表的父布局
     * @param viewType 视图类型
     * @return
     */
    protected abstract E onCreateHolder(ViewGroup parent, int viewType);

    /**
     * 绑定数据
     *
     * @param holder   视图缓存类
     * @param position item位置
     */
    protected abstract void onBindHolder(E holder, int position);

    @Override
    public int getItemCount() {
        if (mDataBeen == null)
            return 0;
        return mDataBeen.size();
    }

    public static abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

    }


    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

}
