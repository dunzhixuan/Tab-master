package com.dunzhixuan.app.libbase.baseui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者： 敦志轩 on 2018/1/30.
 * 邮箱： 997681136@qq.com
 */

public abstract class MvpBaseFragment<P extends MvpBasePresenter> extends Fragment implements MvpView {

    protected P mPresenter;
    private View mRootView;
    private boolean isFirstLoad = true;//用于懒加载

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(createPresenter());
        if (getPresenter() != null) getPresenter().attachView(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(setContentView(), container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        setListener();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && isFirstLoad) {
            isFirstLoad = false;
            onLazyLoadData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) getPresenter().detachView();
    }


    protected void onLazyLoadData() {
    }

    protected abstract int setContentView();


    protected void initView() {
    }

    protected void setListener() {
    }

    protected void initData() {

    }

    protected abstract P createPresenter();

    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    public P getPresenter() {
        return mPresenter;
    }

    protected <T extends View> T findViewById(int id) {
        if (mRootView == null) {
            return null;
        }
        return (T) mRootView.findViewById(id);
    }
}
