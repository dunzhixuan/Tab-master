package com.dunzhixuan.app.libbase.adapter;

interface MultiItemTypeSupport<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);
}