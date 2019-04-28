package com.dunzhixuan.app.libbase.utils;

import android.view.View;

import com.dunzhixuan.app.libbase.adapter.ViewClickOnSubscribe;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by doulala on 16/2/25.
 */
public class ViewUtils {

    private static long lastClickTime;

    public static Subscription clicks(final View view, final View.OnClickListener onClickListener) {

        return Observable.create(new ViewClickOnSubscribe(view)).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {

                    @Override
                    public void call(Void arg0) {
                        // TODO Auto-generated method stub
                        if (onClickListener != null)
                            onClickListener.onClick(view);
                    }
                });
    }

    /**
     * 防止快速点击
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
