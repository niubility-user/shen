package com.jdpay.util;

import android.content.Context;
import androidx.annotation.NonNull;
import com.jdpay.bury.JPBury;
import com.jdpay.bury.SessionPack;
import com.jdpay.lib.util.JPMonitor;

/* loaded from: classes18.dex */
public class JPMCMonitor {
    public static final int INVOKER_STACK_INDEX = 5;
    private static final JPBury bury = JPBury.createBuilder().sdkName("JPMC").sdkVersion("1.15.04").build();
    private static final JPMonitor instance = new JPMonitor(new JPMonitor.Handler() { // from class: com.jdpay.util.JPMCMonitor.1
        @Override // com.jdpay.lib.util.JPMonitor.Handler
        public void onEvent(@NonNull JPMonitor.Event event, @NonNull String str, @NonNull String str2) {
            JPMCMonitor.bury.onEvent(str, str2);
        }
    });

    private JPMCMonitor() {
    }

    public static void d(String str) {
        instance.d(str, false, 5);
    }

    public static void d(String str, boolean z) {
        instance.d(str, z, 5);
    }

    public static void e(String str) {
        instance.e(str, false, 5);
    }

    public static void e(String str, boolean z) {
        instance.e(str, z, 5);
    }

    public static void e(Throwable th) {
        if (th != null) {
            th.printStackTrace();
            instance.e(th.getLocalizedMessage(), false, 5);
        }
    }

    public static JPMonitor getInstance() {
        return instance;
    }

    public static void i(String str) {
        instance.i(str, false, 5);
    }

    public static void i(String str, boolean z) {
        instance.i(str, z, 5);
    }

    public static void init(@NonNull Context context) {
        JPBury.init(context);
    }

    public static SessionPack initSession() {
        return bury.createSessionPack();
    }

    public static void onEvent(String str) {
        bury.onEvent(str, true);
    }

    public static void onEvent(String str, String str2) {
        bury.onEvent(str, str2, true);
    }

    public static void startSession(SessionPack sessionPack) {
        bury.startSession(sessionPack);
    }

    public static void updateSession(@NonNull String str, @NonNull String str2) {
        bury.updateSession(str, str2);
    }

    public static void v(String str) {
        instance.v(str, false, 5);
    }

    public static void v(String str, boolean z) {
        instance.v(str, z, 5);
    }

    public static void w(String str) {
        instance.w(str, false, 5);
    }

    public static void w(String str, boolean z) {
        instance.w(str, z, 5);
    }
}
