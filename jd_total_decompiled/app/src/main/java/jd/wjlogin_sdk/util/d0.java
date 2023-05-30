package jd.wjlogin_sdk.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class d0 extends Handler {
    public d0(String str) {
        this(str, 0);
    }

    private static Looper a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            str = "com.jd.stat." + System.currentTimeMillis();
        }
        HandlerThread handlerThread = new HandlerThread(str, i2);
        handlerThread.start();
        return handlerThread.getLooper();
    }

    public d0(String str, int i2) {
        super(a(str, i2));
    }
}
