package com.jingdong.common.utils;

import android.app.Application;
import android.content.Context;
import com.getkeepsafe.relinker.c;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes.dex */
public class BitmapkitUtils {

    public static final String API_KEY = "XJgK2J9rXdmAH37ilm";
    private static final int RETRY_TIMES = 3;
    private static final String TAG = "BitmapkitUtils";
    public static Application a;
    private static boolean b;
    public static boolean isBMPLoad;

    public static native String a(String... strArr);

    public static native byte[] encodeJni(byte[] bArr, boolean z);

    // shen 真正的计算Sign的函数
    public static native String getSignFromJni(Context context, String functionId, String body, String deviceUUID,
            String platform, String versionName);

    public static native String getstring(String str);

    public static boolean isFuncAvailable() {
        loadBMP();
        return b;
    }

    public static synchronized void loadBMP() {
        synchronized (BitmapkitUtils.class) {
            if (b || isBMPLoad) {
                return;
            }
            c.a(JdSdk.getInstance().getApplication(), "jdbitmapkit");
            b = true;
            isBMPLoad = true;
        }
    }
}
