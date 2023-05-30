package com.jingdong.sdk.perfmonitor.b;

import android.app.Activity;
import android.os.HandlerThread;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.Reporter;
import com.jingdong.sdk.perfmonitor.d.b;
import java.text.DecimalFormat;
import java.util.HashMap;

/* loaded from: classes12.dex */
public abstract class b<T extends com.jingdong.sdk.perfmonitor.d.b> {

    /* renamed from: f  reason: collision with root package name */
    protected static HandlerThread f15310f;
    private String a;
    T b;

    /* renamed from: c  reason: collision with root package name */
    Reporter f15311c;
    long d;

    /* renamed from: e  reason: collision with root package name */
    long f15312e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Reporter reporter) {
        this.f15311c = reporter;
        if (f15310f == null) {
            HandlerThread handlerThread = new HandlerThread("perfmonitor-launch");
            f15310f = handlerThread;
            handlerThread.start();
        }
    }

    public static String d(@NonNull Activity activity) {
        return activity.getClass().getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String e() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(HashMap<String, String> hashMap) {
        if (this.a == null) {
            return;
        }
        hashMap.put("typeId", "11");
        hashMap.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
        hashMap.put(WebPerfManager.PAGE_NAME, this.a);
        if (OKLog.D) {
            OKLog.d("PerfMonitor", hashMap.toString());
        }
        Reporter reporter = this.f15311c;
        if (reporter != null) {
            reporter.submit(hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(String str) {
        this.a = str;
    }

    public boolean h(String str) {
        T t = this.b;
        if (t == null) {
            return false;
        }
        return t.b(str);
    }

    public void i(String str) {
        this.a = str;
        this.d = SystemClock.elapsedRealtime();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void j() {
        this.f15312e = SystemClock.elapsedRealtime();
    }
}
