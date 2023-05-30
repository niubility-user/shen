package com.android.volley;

import android.os.SystemClock;
import android.util.Log;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class VolleyLog {
    public static boolean DEBUG = false;
    public static String TAG = "Volley";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MarkerLog {
        public static final boolean ENABLED = VolleyLog.DEBUG;
        private static final long MIN_DURATION_FOR_LOGGING_MS = 0;
        private final List<Marker> mMarkers = new ArrayList();
        private boolean mFinished = false;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Marker {
            public final String name;
            public final int sequence;
            public final long thread;
            public final long time;

            public Marker(String str, long j2, long j3, int i2) {
                this.name = str;
                this.thread = j2;
                this.time = j3;
                this.sequence = i2;
            }
        }

        private long getTotalDuration() {
            if (this.mMarkers.size() == 0) {
                return 0L;
            }
            return this.mMarkers.get(r2.size() - 1).time - this.mMarkers.get(0).time;
        }

        public synchronized void add(String str, long j2, int i2) {
            if (this.mFinished) {
                return;
            }
            this.mMarkers.add(new Marker(str, j2, SystemClock.elapsedRealtime(), i2));
        }

        protected void finalize() throws Throwable {
            if (this.mFinished) {
                return;
            }
            finish("Request on the loose");
            VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }

        public synchronized void finish(String str) {
            this.mFinished = true;
            long totalDuration = getTotalDuration();
            if (totalDuration <= 0) {
                return;
            }
            long j2 = this.mMarkers.get(0).time;
            VolleyLog.d("[id:%d-] (%-4d ms) %s", Integer.valueOf(this.mMarkers.get(0).sequence), Long.valueOf(totalDuration), str);
            for (Marker marker : this.mMarkers) {
                long j3 = marker.time;
                VolleyLog.d("[id:%d-] (+%-4d) [%2d] %s", Integer.valueOf(marker.sequence), Long.valueOf(j3 - j2), Long.valueOf(marker.thread), marker.name);
                j2 = j3;
            }
        }
    }

    private static String buildMessage(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i2 = 2;
        while (true) {
            if (i2 >= stackTrace.length) {
                str2 = "<unknown>";
                break;
            } else if (!stackTrace[i2].getClass().equals(VolleyLog.class)) {
                String className = stackTrace[i2].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                str2 = substring.substring(substring.lastIndexOf(36) + 1) + OrderISVUtil.MONEY_DECIMAL + stackTrace[i2].getMethodName();
                break;
            } else {
                i2++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str2, str);
    }

    public static void d(String str, Object... objArr) {
        if (DEBUG) {
            buildMessage(str, objArr);
        }
    }

    public static void e(String str, Object... objArr) {
        if (DEBUG) {
            buildMessage(str, objArr);
        }
    }

    public static void setTag(String str) {
        d("Changing log tag to %s", str);
        TAG = str;
        DEBUG = Log.isLoggable(str, 2);
    }

    public static void v(String str, Object... objArr) {
        if (DEBUG) {
            buildMessage(str, objArr);
        }
    }

    public static void wtf(String str, Object... objArr) {
        if (DEBUG) {
            Log.wtf(TAG, buildMessage(str, objArr));
        }
    }

    public static void e(Throwable th, String str, Object... objArr) {
        if (DEBUG) {
            buildMessage(str, objArr);
        }
    }

    public static void wtf(Throwable th, String str, Object... objArr) {
        if (DEBUG) {
            Log.wtf(TAG, buildMessage(str, objArr), th);
        }
    }
}
