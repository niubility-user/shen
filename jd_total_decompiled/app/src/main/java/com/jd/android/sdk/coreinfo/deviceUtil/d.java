package com.jd.android.sdk.coreinfo.deviceUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class d {
    public static volatile String a;

    static String a(Context context) {
        try {
            WebView webView = new WebView(context);
            webView.getSettings().setAllowFileAccess(false);
            webView.getSettings().setSavePassword(false);
            return webView.getSettings().getUserAgentString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(final Context context, long j2) {
        Looper mainLooper;
        if (a == null) {
            if (context == null || (mainLooper = Looper.getMainLooper()) == null) {
                return "";
            }
            if (Thread.currentThread() == mainLooper.getThread()) {
                a = a(context);
            } else {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                new Handler(mainLooper).post(new Runnable() { // from class: com.jd.android.sdk.coreinfo.deviceUtil.d.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (d.a == null) {
                            d.a = d.a(context);
                        }
                        countDownLatch.countDown();
                    }
                });
                try {
                    countDownLatch.await(j2, TimeUnit.MILLISECONDS);
                } catch (InterruptedException unused) {
                }
            }
            if (a == null) {
                a = "";
            }
        }
        return a;
    }
}
