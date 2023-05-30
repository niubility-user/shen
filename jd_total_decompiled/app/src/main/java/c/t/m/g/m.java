package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import com.jingdong.common.jdmiaosha.utils.cache.Final;

/* loaded from: classes.dex */
public abstract class m {
    public static PowerManager.WakeLock a;

    public static void a() {
        PowerManager.WakeLock wakeLock = a;
        if (wakeLock != null && wakeLock.isHeld()) {
            a.release();
        }
        a = null;
    }

    @SuppressLint({"Wakelock"})
    public static void b(Context context) {
        PowerManager.WakeLock wakeLock = a;
        if (wakeLock != null && wakeLock.isHeld()) {
            a.release();
        }
        PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "WakeLocker");
        a = newWakeLock;
        newWakeLock.acquire(Final.FIVE_SECOND);
    }
}
