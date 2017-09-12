package com.leo.mvp.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Leo on 2017/8/9.
 */

public abstract class BaseHeaderAdapter<T,E> extends BaseRecylerAdapter {


    private List<T> mList;

    public BaseHeaderAdapter(Context context) {
        super(context);
    }
}