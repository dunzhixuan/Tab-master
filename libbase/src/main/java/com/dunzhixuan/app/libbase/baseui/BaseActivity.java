package com.dunzhixuan.app.libbase.baseui;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dunzhixuan.app.libbase.R;
import com.dunzhixuan.app.libbase.headerbar.CtsiHeaderBar;
import com.dunzhixuan.app.libbase.log.Logger;
import com.dunzhixuan.app.libbase.manager.ImmersionBarManager;

/**
 * 作者： 敦志轩 on 2018/2/1.
 * 邮箱： 997681136@qq.com
 */

public class BaseActivity extends AppCompatActivity{

    protected RelativeLayout layout_main;
    protected CtsiHeaderBar headBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_ui);
        initStatusBar();
        headBar = findView(R.id.headBar);
        layout_main = (RelativeLayout) findViewById(R.id.layout_activity_container);
        setHeadBar(headBar);
        setTheme(R.style.Theme_Activity_Ctsi);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        ViewGroup.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(view, params);
    }

    @Override
    public void setContentView(View view) {
        ViewGroup.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(view, params);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) params;
        layout_main.addView(view, params1);
    }

    public void setTitle(CharSequence title){
        if (isLoadHeaderBar())
            headBar.setTitle(title);
    }

    private boolean isLoadHeaderBar() {
        return this.headBar != null;
    }

    //隐藏返回按钮
    public void hideBackButton() {
        headBar.hideBackButton();
    }

    //隐藏返回按钮
    public void hideBackButtonIcon() {
        headBar.hideBackButtonIcon();

    }

    public void setHeadBar(CtsiHeaderBar headBar) {
        this.headBar = headBar;
        headBar.setOnBackButtonClickListener(onBackButtonClickListener);
    }

    //状态栏颜色设置，字体颜色设置
    protected void initStatusBar() {
        ImmersionBarManager.initStatusBar(this, isFullScreen());
    }

    //设置白底黑字(状态栏是否支持)
    public void setWhiteStatusBar() {
        ImmersionBarManager.setWhiteStatusBar(this);
    }

    //设置状态栏背景色
    public void setStatusBarColor(int color) {
        ImmersionBarManager.setStatusBarColor(this, color);
    }

    //设置白底黑字(状态栏是否支持)
    public void setDarkFontStatusBarColor(int color) {
        ImmersionBarManager.setDarkFontStatusBarColor(this, color);
    }

    protected boolean isFullScreen() {
        return false;
    }

    private View.OnClickListener onBackButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            overridePendingTransition(R.anim.activity_push_right_in, R.anim.activity_push_right_out);
            finish();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected <T extends View> T findView(int resId) {
        return (T) (findViewById(resId));
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        } catch (Exception ex) {
            Logger.e("设置字体不随系统变化报错：" + ex.getLocalizedMessage());
        }
        return resources;
    }
}
