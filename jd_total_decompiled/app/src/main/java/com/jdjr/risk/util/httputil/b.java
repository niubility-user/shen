package com.jdjr.risk.util.httputil;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b {
    private static b a = new b();
    private boolean b = false;

    private b() {
    }

    public static b a() {
        return a;
    }

    public static void a(LorasHttpCallback lorasHttpCallback, String str) {
        if (str == null || str.length() <= 0) {
            lorasHttpCallback.onFailInNetThread(901, HttpInfoConstants.FAIL_NULL_RESULT_STR);
            return;
        }
        if (str.contains(HttpInfoConstants.FAIL_ERROR_PARAM_STR)) {
            lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
        }
        if (str.contains(HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR)) {
            lorasHttpCallback.onFailInNetThread(903, HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR);
        }
        if (str.contains(HttpInfoConstants.FAIL_NULL_RESULT_STR)) {
            lorasHttpCallback.onFailInNetThread(901, HttpInfoConstants.FAIL_NULL_RESULT_STR);
        }
        if (str.startsWith("LOCAL_TOKEN***")) {
            str = str.replace("LOCAL_TOKEN***", "");
        }
        lorasHttpCallback.onSuccess(str);
    }

    public void a(String str, String str2, int i2, int i3, LorasHttpCallback lorasHttpCallback) {
        if (str2 == null || str2.length() < 1) {
            lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
        } else if (str == null || str.length() < 1) {
            lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
        } else {
            try {
                if (Build.VERSION.SDK_INT < 16 && Looper.myLooper() != Looper.getMainLooper()) {
                    this.b = true;
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jdjr.risk.util.httputil.b.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Class.forName("android.os.AsyncTask");
                                b.this.b = false;
                            } catch (Exception unused) {
                                b.this.b = false;
                            }
                        }
                    });
                    while (this.b) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(50L);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    return;
                }
                a(lorasHttpCallback, c.a(str2, str, i2, i3));
            } catch (Throwable unused) {
                lorasHttpCallback.onFailInCurentThread(903, HttpInfoConstants.FAIL_HTTP_EXCEPTION_STR);
            }
        }
    }

    public void a(String str, String str2, LorasHttpCallback lorasHttpCallback) {
        a(str, str2, 3000, 5000, lorasHttpCallback);
    }

    public void a(JSONObject jSONObject, String str, LorasHttpCallback lorasHttpCallback) {
        if (jSONObject == null) {
            lorasHttpCallback.onFailInCurentThread(902, HttpInfoConstants.FAIL_ERROR_PARAM_STR);
        } else {
            a(jSONObject.toString(), str, lorasHttpCallback);
        }
    }
}
