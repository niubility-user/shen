package com.jingdong.app.mall.bundle.dolphinlib.common.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;

/* loaded from: classes19.dex */
public class ToastUtil {
    private static int mDefaultGravity;
    private static int mDefaultXOffset;
    private static int mDefaultYOffset;
    private static Handler mHandler;
    private static Toast mToast;

    private static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    private static Toast getToast(Context context) {
        if (mToast == null) {
            Toast toast = new Toast(context);
            mToast = toast;
            mDefaultGravity = toast.getGravity();
            mDefaultXOffset = mToast.getXOffset();
            mDefaultYOffset = mToast.getYOffset();
        }
        return mToast;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Toast makeToast(Context context, String str, int i2) {
        TextView textView = new TextView(context);
        textView.setBackgroundResource(R.drawable.dolphin_toast_bg);
        textView.setText(str);
        textView.setTextColor(-1);
        textView.setTextSize(1, 14.0f);
        int dip2px = DPIUtil.dip2px(context, 10.0f);
        int dip2px2 = DPIUtil.dip2px(context, 20.0f);
        textView.setPadding(dip2px2, dip2px, dip2px2, dip2px);
        Toast toast = getToast(context);
        toast.setGravity(mDefaultGravity, mDefaultXOffset, mDefaultYOffset);
        toast.setDuration(i2);
        toast.setView(textView);
        return toast;
    }

    public static void showToast(final Context context, final String str) {
        getHandler().post(new Runnable() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.util.ToastUtil.1
            @Override // java.lang.Runnable
            public void run() {
                Toast unused = ToastUtil.mToast = ToastUtil.makeToast(context.getApplicationContext(), str, 0);
                ToastUtil.mToast.show();
            }
        });
    }
}
