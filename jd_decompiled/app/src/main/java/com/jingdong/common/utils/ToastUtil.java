package com.jingdong.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.common.R;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes6.dex */
public class ToastUtil {
    private static boolean isShow;
    private static Toast mToastInstance;

    public static void cancelToast() {
        Toast toast;
        if (!isShow || (toast = mToastInstance) == null) {
            return;
        }
        isShow = false;
        toast.cancel();
    }

    private static synchronized Toast createToast2(Context context) {
        Toast toast;
        synchronized (ToastUtil.class) {
            if (mToastInstance == null) {
                mToastInstance = new Toast(context);
            }
            toast = mToastInstance;
        }
        return toast;
    }

    public static void showToast(String str) {
        if (str == null || str.length() <= 100) {
            toastBuild(JdSdk.getInstance().getApplication(), str, 16, 0);
        }
    }

    public static void toastBuild(Context context, String str, int i2, int i3) {
        toastBuild(context, str, i2, 0, i3, R.layout.view_err_toast);
    }

    public static void toastBuild(Context context, String str, int i2, int i3, int i4, int i5) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService("layout_inflater");
        View inflate = layoutInflater.inflate(i5, (ViewGroup) null);
        int i6 = R.id.message;
        if (inflate.findViewById(i6) == null) {
            inflate = layoutInflater.inflate(R.layout.view_err_toast, (ViewGroup) null);
        }
        ((TextView) inflate.findViewById(i6)).setText(str);
        createToast2(context.getApplicationContext());
        mToastInstance.setDuration(0);
        mToastInstance.setView(inflate);
        mToastInstance.setGravity(i2, i3, i4);
        mToastInstance.show();
        isShow = true;
    }

    public static void showToast(Context context, String str) {
        if (str == null || str.length() <= 100) {
            toastBuild(context, str, 16, 0);
        }
    }
}
