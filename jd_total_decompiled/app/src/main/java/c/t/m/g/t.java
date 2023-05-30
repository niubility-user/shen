package c.t.m.g;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes.dex */
public class t {
    public static Message a(Handler handler, int i2, int i3, int i4, Object obj) {
        Message message = handler == null ? new Message() : handler.obtainMessage(i2);
        message.what = i2;
        message.arg1 = i3;
        message.arg2 = i4;
        message.obj = obj;
        return message;
    }

    public static void b(Handler handler, int i2) {
        if (handler != null) {
            handler.removeMessages(i2);
        }
    }

    public static boolean c(Handler handler) {
        return i(handler == null ? null : handler.getLooper());
    }

    public static boolean d(Handler handler, int i2, long j2) {
        return f(handler, handler == null ? null : handler.obtainMessage(i2), j2);
    }

    public static boolean e(Handler handler, Message message) {
        return f(handler, message, 0L);
    }

    public static boolean f(Handler handler, Message message, long j2) {
        if (message == null || !c(handler)) {
            return false;
        }
        return handler.sendMessageDelayed(message, j2);
    }

    public static boolean g(Handler handler, Runnable runnable) {
        return h(handler, runnable, 0L);
    }

    public static boolean h(Handler handler, Runnable runnable, long j2) {
        if (runnable == null || !c(handler)) {
            return false;
        }
        return handler.postDelayed(runnable, j2);
    }

    public static boolean i(Looper looper) {
        return looper != null && looper.getThread().isAlive();
    }

    public static void j(Handler handler) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public static boolean k(Handler handler, int i2) {
        return d(handler, i2, 0L);
    }

    public static boolean l(Handler handler, int i2, int i3, int i4, Object obj) {
        if (handler == null) {
            return false;
        }
        return e(handler, a(handler, i2, i3, i4, obj));
    }
}
