package com.example.dunzhixuan.Infrastructure;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dunzhixuan.app.libbase.baseui.BaseActivity;
import com.example.dunzhixuan.Infrastructure.mainpage.Fragment_Chat;
import com.example.dunzhixuan.Infrastructure.mainpage.Fragment_Main;
import com.example.dunzhixuan.Infrastructure.mainpage.Fragment_Mine;
import com.example.dunzhixuan.Infrastructure.mainpage.Fragment_Video;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    private Fragment_Main fragment_main;
    private Fragment_Mine fragment_mine;
    private Fragment_Video fragment_video;
    private Fragment_Chat fragment_chat;
    private String[] mTitles = new String[]{"首页", "视频", "聊天", "我的"};
    private int lastSelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        showFragment(savedInstanceState);
        setTitle("生活小助手");
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.gray);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home_pressed,mTitles[0])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.home_normal))).setActiveColor(R.color.red)
                .addItem(new BottomNavigationItem(R.drawable.video_pressed,mTitles[1])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.video_normal))).setActiveColor(R.color.red)
                .addItem(new BottomNavigationItem(R.drawable.chat_pressed,mTitles[2])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.chat_normal))).setActiveColor(R.color.red)
                .addItem(new BottomNavigationItem(R.drawable.mine_pressed,mTitles[3])
                        .setInactiveIcon(ContextCompat.getDrawable(this, R.drawable.mine_normal))).setActiveColor(R.color.red)
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();//所有的设置需在调用该方法前完成
    }

    private void showFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            addFragment();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            fragment_main = (Fragment_Main) fm.findFragmentByTag("main");
            fragment_mine = (Fragment_Mine) fm.findFragmentByTag("mine");
            fragment_video = (Fragment_Video) fm.findFragmentByTag("video");
            fragment_chat = (Fragment_Chat) fm.findFragmentByTag("chat");
        }
    }

    private void addFragment() {
        if (fragment_main == null) {
            fragment_main = new Fragment_Main();
        }
        if (fragment_mine == null) {
            fragment_mine = new Fragment_Mine();
        }
        if(fragment_video == null) {
            fragment_video = new Fragment_Video();
        }
        if(fragment_chat == null) {
            fragment_chat = new Fragment_Chat();
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.home_activity_frag_container, fragment_main, "main");
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        setScrollableText(position);
    }

    private void setScrollableText(int position) {
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment_main).commitAllowingStateLoss();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment_video).commitAllowingStateLoss();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment_chat).commitAllowingStateLoss();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.home_activity_frag_container, fragment_mine).commitAllowingStateLoss();
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {
    }

    @Override
    public void onTabReselected(int position) {
    }
}
