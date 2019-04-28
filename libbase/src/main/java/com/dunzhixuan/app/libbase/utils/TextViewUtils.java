package com.dunzhixuan.app.libbase.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by doulala on 16/2/24.
 */
public class TextViewUtils {

    public static void setTextAppearance(Context context, TextView textView, int res) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextAppearance(res);
        } else {
            textView.setTextAppearance(context, res);
        }
    }


    public static void setBackground(View view, Drawable drawable) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }


    /**
     * 设置文字下划线
     *
     * @param tv
     * @param message
     */
    public static void setTextViewUnderLine(TextView tv, String message) {
        int blue = Color.parseColor("#0097ff");
        setTextViewUnderLine(tv, message, blue);
    }

    /**
     * 设置文字下划线
     *
     * @param tv
     * @param message
     * @param color
     */
    public static void setTextViewUnderLine(TextView tv, String message, int color) {
        SpannableString spannableString = new SpannableString(message);
        spannableString.setSpan(new URLSpan(""), 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(color), 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * @param content       内容
     * @param pattern       正则表达式
     * @param color         字体颜色
     * @param textSizeTimes 为默认字体大小的多少倍
     * @return
     */
    public static SpannableString getTextSpannableString(String content, String pattern, int color, float textSizeTimes) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(content);
        SpannableString msg = new SpannableString(content);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            msg.setSpan(new ForegroundColorSpan(color), start, end,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            msg.setSpan(new RelativeSizeSpan(textSizeTimes), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return msg;
    }

}
