package com.dunzhixuan.app.libbase.headerbar;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dunzhixuan.app.libbase.R;
import com.dunzhixuan.app.libbase.utils.TextViewUtils;
import com.dunzhixuan.app.libbase.utils.UIUtils;

//默认的titlebar 左边是返回,右边是一或多个Button，统一样式

public class CtsiHeaderBar extends RelativeLayout {

    // 设置右侧按钮
    private OnClickListener onBackButtonClickListener;
    private OnCtsiMenuItemClickListener onRightClickLisetner;
    private int resId_RigntButton;
    private Toolbar toolbar;
    private TextView txv_title;
    private Context mContext;
    private View mBottomLine;

    public CtsiHeaderBar(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }

    public CtsiHeaderBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        toolbar = new Toolbar(context);
        toolbar.setMinimumHeight(getHeight());

        LayoutParams params_toolbar = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(toolbar, params_toolbar);
        addBottomLine(context);
        init();
    }

    //添加顶部分割线
    private void addBottomLine(Context context) {
        mBottomLine = new View(context);
        TextViewUtils.setBackground(mBottomLine, ContextCompat.getDrawable(context, R.drawable.mts_diver));
        LayoutParams paramsLine = new LayoutParams(
                LayoutParams.MATCH_PARENT, UIUtils.convertDip2Px(context, 1));
        paramsLine.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(mBottomLine, paramsLine);
    }


    private void initTitle(Context context) {
        toolbar.setTitle("");
        txv_title = new TextView(context);
        LayoutParams params_txv = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params_txv.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(txv_title, params_txv);
        TextViewUtils.setTextAppearance(context, txv_title, R.style.TextView_Title_Headerbar);

    }

    //隐藏返回按钮
    public void hideBackButton() {
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(null);
    }

    //隐藏返回按钮
    public void hideBackButtonIcon() {
        toolbar.setNavigationIcon(new ColorDrawable(ContextCompat.getColor(mContext, R.color.mts_background_navigation)));
    }

    //隐藏返回按钮
    public void hideBottomLine() {
        if (mBottomLine != null) {
            mBottomLine.setVisibility(View.GONE);
        }
    }

    public void init() {
        setTitleBarBackground(true, ContextCompat.getColor(mContext, R.color.mts_background_navigation));
        toolbar.setNavigationIcon(R.drawable.icon_title_back);
        toolbar.setNavigationOnClickListener(onBackClickListener);
        initTitle(getContext());

    }

    public void setTitle(CharSequence title) {
        txv_title.setText(title);
    }

    public void setTitle(CharSequence title, int resId) {
        txv_title.setText(title);
        Drawable img = UIUtils.getDrawable(mContext, resId);
        img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
        txv_title.setCompoundDrawables(img, null, null, null);
    }

    public void setTitleColor(int color){
        txv_title.setTextColor(color);
        toolbar.setNavigationIcon(R.drawable.icon_title_white);
    }

    public void setOnBackButtonClickListener(OnClickListener listener) {
        onBackButtonClickListener = listener;
    }

    private OnClickListener onBackClickListener = new OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            if (onBackButtonClickListener != null) {
                onBackButtonClickListener.onClick(arg0);
            }
        }
    };

    public void setRightButton(int imgId, String describe, OnCtsiMenuItemClickListener listener) {
        resId_RigntButton = imgId;
        onRightClickLisetner = listener;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_base_activity);
        MenuItem menu_right = toolbar.getMenu().findItem(R.id.menu_right);
        menu_right.setIcon(resId_RigntButton);
        menu_right.setTitle(describe);
        MenuItemCompat.setShowAsAction(menu_right, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        toolbar.setOnMenuItemClickListener(onDefaultMenuClickListener);
    }

    public void addCompoentView(View view, LayoutParams params) {
        this.addView(view, params);
    }

    private Toolbar.OnMenuItemClickListener onDefaultMenuClickListener = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            // TODO Auto-generated method stub
            int i = item.getItemId();
            if (i == R.id.menu_right) {
                if (onRightClickLisetner != null)
                    onRightClickLisetner.onClick(item);

            }
            return true;
        }

    };

    public interface OnCtsiMenuItemClickListener {
        void onClick(MenuItem item);
    }

    public void setTitleBarBackground(boolean isColor, int color) {
        if (isColor) {
            toolbar.setBackgroundColor(color);
        } else
            toolbar.setBackgroundResource(R.drawable.bg_titlebar);
    }

}
