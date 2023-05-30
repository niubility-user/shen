package com.jd.lib.cashier.sdk.complete.jsbridge;

import android.view.View;
import android.webkit.JavascriptInterface;
import com.jd.lib.cashier.sdk.core.utils.a0;

/* loaded from: classes14.dex */
public class PayReminderScript {
    public static String JDAPPUNITE = "JDAppUnite";
    private View mWebView;

    public PayReminderScript(View view) {
        this.mWebView = view;
    }

    @JavascriptInterface
    public void addReminder(String str) {
        try {
            a0.a(this.mWebView, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void checkNotificationEnable(String str) {
        try {
            a0.d(this.mWebView, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void checkReminder(String str) {
        try {
            a0.b(this.mWebView, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void getAllRemindersWithTimeSpanAndBusinessType(String str) {
        try {
            a0.c(this.mWebView, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onDestroy() {
        if (this.mWebView != null) {
            this.mWebView = null;
        }
    }

    @JavascriptInterface
    public void removeReminder(String str) {
        try {
            a0.e(this.mWebView, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void showPushOpenGuide(String str) {
        try {
            a0.f(this.mWebView, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
