package com.jingdong.sdk.perfmonitor;

import android.annotation.SuppressLint;
import android.content.Context;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class Reporter {
    private int a;
    private List<HashMap<String, String>> b;

    /* renamed from: c  reason: collision with root package name */
    private Context f15276c;

    public Reporter(Context context) {
        this(context, 1);
    }

    private void a() {
        PerformanceReporter.reportData(new ArrayList(this.b));
        this.b.clear();
    }

    private boolean b() {
        return this.a > 1;
    }

    @SuppressLint({"DefaultLocale"})
    private void c(int i2) {
        OKLog.d("PerfMonitor", String.format("\u4e0a\u62a5%d\u6761\u6570\u636e", Integer.valueOf(i2)));
    }

    public void flush() {
        int size;
        if (!b() || (size = this.b.size()) <= 0) {
            return;
        }
        a();
        c(size);
    }

    public void submit(HashMap<String, String> hashMap) {
        submit(hashMap, false);
    }

    public Reporter(Context context, int i2) {
        this.f15276c = context.getApplicationContext();
        this.a = i2;
        if (b()) {
            this.b = new ArrayList(i2);
        }
    }

    public void submit(HashMap<String, String> hashMap, boolean z) {
        if (b()) {
            this.b.add(hashMap);
            int size = this.b.size();
            if (z || size >= this.a) {
                a();
                c(size);
                return;
            }
            return;
        }
        if (PerformanceReporter.getIsNeedReport(this.f15276c, hashMap.get("typeId"), hashMap.get("chId"))) {
            PerformanceReporter.reportData(hashMap);
            c(1);
        }
    }
}
