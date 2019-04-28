package com.dunzhixuan.app.libbase.baseui;

import java.lang.ref.WeakReference;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者： 敦志轩 on 2018/1/30.
 * 邮箱： 997681136@qq.com
 */

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V>  {

    private WeakReference<V> mViewReference;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public void attachView(V view) {
        mViewReference = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (mViewReference != null) {
            mViewReference.clear();
            mViewReference = null;
        }
        unSubscribe();
    }

    public V getView() {
        return mViewReference != null ? mViewReference.get() : null;
    }

    public boolean isViewAttached() {
        return mViewReference != null ? true : false;
    }

    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    private void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
