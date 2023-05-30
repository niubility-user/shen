package com.jingdong.sdk.oklog.core;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LoggerPrinter implements c {
    private static final int JSON_INDENT = 2;
    private final List<a> logAdapters = new ArrayList();

    private void log(String str, int i2, Throwable th, Object... objArr) {
        String a = d.a(objArr);
        if (TextUtils.isEmpty(a) && th == null) {
            return;
        }
        log(i2, str, a, th);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void addAdapter(a aVar) {
        this.logAdapters.add(aVar);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void d(String str, Object... objArr) {
        log(str, 3, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void d(Throwable th, String str, Object... objArr) {
        log(str, 3, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void e(String str, Object... objArr) {
        log(str, 6, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void e(Throwable th, String str, Object... objArr) {
        log(str, 6, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void i(String str, Object... objArr) {
        log(str, 4, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void i(Throwable th, String str, Object... objArr) {
        log(str, 4, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void json(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            d(str, "Empty/Null json content");
            return;
        }
        try {
            String trim = str2.trim();
            if (trim.startsWith("{")) {
                d(str, new JSONObject(trim).toString(2));
            } else if (trim.startsWith("[")) {
                d(str, new JSONArray(trim).toString(2));
            } else {
                e("Invalid Json", new Object[0]);
            }
        } catch (JSONException unused) {
            e("Invalid Json", new Object[0]);
        }
    }

    public void log(int i2, String str, String str2, Throwable th) {
        Iterator<a> it = this.logAdapters.iterator();
        while (it.hasNext()) {
            it.next().a(i2, str, str2, th);
        }
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void v(String str, Object... objArr) {
        log(str, 2, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void v(Throwable th, String str, Object... objArr) {
        log(str, 2, th, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void w(String str, Object... objArr) {
        log(str, 5, (Throwable) null, objArr);
    }

    @Override // com.jingdong.sdk.oklog.core.c
    public void w(Throwable th, String str, Object... objArr) {
        log(str, 5, th, objArr);
    }
}
