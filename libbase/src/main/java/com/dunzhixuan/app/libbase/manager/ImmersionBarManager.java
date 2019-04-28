package com.dunzhixuan.app.libbase.manager;

import android.app.Activity;

import com.dunzhixuan.app.libbase.R;
import com.dunzhixuan.app.libbase.headerbar.BarHide;
import com.dunzhixuan.app.libbase.headerbar.ImmersionBar;

/**
 * 作者： 敦志轩 on 2018/2/2.
 * 邮箱： 997681136@qq.com
 * 系统状态栏背景，文字颜色设置
 */

public class ImmersionBarManager {


    //状态栏颜色设置，字体颜色设置
    public static void initStatusBar(Activity context, boolean isFullScreen) {

        //全屏设置
        if (isFullScreen) {
            ImmersionBar.with(context)
                    .transparentBar()
                    .statusBarDarkFont(false)
                    .init();
        } else {
            //支持状态栏字体为黑色
            if (ImmersionBar.isSupportStatusBarDarkFont()) {
                ImmersionBar.with(context)
                        .statusBarView(R.id.statusBarView)
                        .statusBarColor(R.color.mts_background_navigation)
                        .statusBarDarkFont(true)
                        .init();
            } else {
                ImmersionBar.with(context)
                        .statusBarView(R.id.statusBarView)
                        .statusBarColor(R.color.black)
                        .statusBarDarkFont(true)
                        .init();
            }
        }
    }


    //设置白底黑字(状态栏是否支持)
    public static void setWhiteStatusBar(Activity context) {
        if (ImmersionBar.isSupportStatusBarDarkFont()) {
            ImmersionBar.with(context)
                    .statusBarView(R.id.statusBarView)
                    .statusBarColor(R.color.white)
                    .statusBarDarkFont(true)
                    .init();
        }
    }


    //设置白色字体，其他背景颜色（白色背景除外）(状态栏是否支持)
    public static void setStatusBarColor(Activity context, int color) {
        ImmersionBar.with(context)
                .statusBarView(R.id.statusBarView)
                .statusBarColor(color)
                .statusBarDarkFont(false)
                .init();
    }


    //设置黑字背景色可自定义(状态栏是否支持)
    public static void setDarkFontStatusBarColor(Activity context, int color) {
        if (ImmersionBar.isSupportStatusBarDarkFont()) {
            ImmersionBar.with(context)
                    .statusBarView(R.id.statusBarView)
                    .statusBarColor(color)
                    .statusBarDarkFont(true)
                    .init();
        }
    }


    //设置黑字背景色可自定义(状态栏是否支持)
    public static void hideNavigationBar(Activity context) {
        if (ImmersionBar.hasNavigationBar(context))
            ImmersionBar.with(context).hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init();

    }

    //设置白底黑字(状态栏是否支持)
    public static boolean isSupportStatusBarDarkFont() {
        return ImmersionBar.isSupportStatusBarDarkFont();
    }

}
