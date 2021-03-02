package com.dunzhixuan.app.libbase.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

public class UIUtils {
    public static int GetHeightOfClient(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int GetWidthOfClient(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int GetStatusHeight(Context context) {
        View view = new View(context);
        Rect r = new Rect();
        view.getWindowVisibleDisplayFrame(r);
        return r.top;
    }

    public static int convertPx2Dip(Context context, float pxValue) {
        float scale;
        scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int convertDip2Px(Context context, float dipValue) {
        float scale;
        scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static float getScreenDensity(Context context) {
        return context.getApplicationContext().getResources().getDisplayMetrics().density;
    }

    public static int getColor(Context context, int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getApplicationContext().getResources().getColor(res, context.getApplicationContext().getTheme());
        } else {
            return context.getApplicationContext().getResources().getColor(res);
        }

    }

    public static Drawable getDrawable(Context context, int res) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(res, context.getTheme());
        } else {
            return context.getResources().getDrawable(res);
        }

    }

    public static void setEditTextCursorColor(EditText editText, int color) {
        try {
            final Field drawableResField = TextView.class.getDeclaredField("mCursorDrawableRes");
            drawableResField.setAccessible(true);
            final Drawable drawable = UIUtils.getDrawable(editText.getContext(), drawableResField.getInt(editText));
            if (drawable == null) {
                return;
            }
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            final Object drawableFieldOwner;
            final Class<?> drawableFieldClass;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                drawableFieldOwner = editText;
                drawableFieldClass = TextView.class;
            } else {
                final Field editorField = TextView.class.getDeclaredField("mEditor");
                editorField.setAccessible(true);
                drawableFieldOwner = editorField.get(editText);
                drawableFieldClass = drawableFieldOwner.getClass();
            }
            final Field drawableField = drawableFieldClass.getDeclaredField("mCursorDrawable");
            drawableField.setAccessible(true);
            drawableField.set(drawableFieldOwner, new Drawable[]{drawable, drawable});
        } catch (Exception ignored) {
        }
    }

    public static void setCompoundDrawables(Context context, TextView tv, int resId) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        tv.setCompoundDrawables(drawable, null, null, null);
    }
}
