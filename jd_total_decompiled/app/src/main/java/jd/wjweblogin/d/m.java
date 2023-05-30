package jd.wjweblogin.d;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;

/* loaded from: classes11.dex */
public class m extends Handler {
    public m(String str) {
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

    public m(String str, int i2) {
        super(a(str, i2));
    }
}
