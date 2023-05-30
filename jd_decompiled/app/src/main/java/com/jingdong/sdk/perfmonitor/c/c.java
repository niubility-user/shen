package com.jingdong.sdk.perfmonitor.c;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class c extends com.jingdong.sdk.perfmonitor.c.a {

    /* renamed from: g  reason: collision with root package name */
    private a f15408g;

    /* loaded from: classes12.dex */
    public interface a {
        void b(long j2, long j3, long j4);
    }

    public c(Context context, long j2, long j3, a aVar) {
        super(context, j2, j3);
        this.f15408g = aVar;
    }

    @Override // com.jingdong.sdk.perfmonitor.c.a
    void f() {
        Context context = this.a;
        if (context == null) {
            return;
        }
        Object systemService = context.getSystemService("activity");
        if (systemService instanceof ActivityManager) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
            try {
                long j2 = memoryInfo.availMem;
                long j3 = Build.VERSION.SDK_INT >= 16 ? memoryInfo.totalMem : 0L;
                Debug.MemoryInfo memoryInfo2 = new Debug.MemoryInfo();
                Debug.getMemoryInfo(memoryInfo2);
                int totalPss = memoryInfo2.getTotalPss();
                if (totalPss >= 0) {
                    long j4 = totalPss * 1024;
                    a aVar = this.f15408g;
                    if (aVar != null) {
                        aVar.b(j4, j2, j3);
                    }
                }
            } catch (Exception e2) {
                OKLog.e("PerfMonitor", "getMemoryData fail: " + e2.toString());
            }
        }
    }
}
