package com.jingdong.app.mall.log;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.base.CachePool;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.reporter.AbsLogReporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes4.dex */
public class f extends AbsLogReporter {
    private final String a;
    private LogStrategyParam b;

    /* renamed from: c */
    private ExecutorService f11192c = Executors.newFixedThreadPool(5, new a(this));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements ThreadFactory {
        a(f fVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(1);
            return thread;
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ HashMap f11193g;

        b(f fVar, HashMap hashMap) {
            this.f11193g = hashMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.f11193g);
            PerformanceReporter.reportData(arrayList);
        }
    }

    public f(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogStrategyParam logStrategyParam = (LogStrategyParam) JDJSON.parseObject(str, LogStrategyParam.class);
            this.b = logStrategyParam;
            if (logStrategyParam != null) {
                logStrategyParam.parseParams();
                d b2 = d.b();
                b2.c(this.b);
                JdSdk.getInstance().getApplication().registerActivityLifecycleCallbacks(b2);
            }
        }
        this.a = ProcessUtil.getProcessName(JdSdk.getInstance().getApplication());
    }

    private String a() {
        return d.b().a();
    }

    private String b() {
        return String.valueOf(System.currentTimeMillis() - com.jingdong.app.mall.k.a.a);
    }

    public void c(String str) {
        HashMap<String, String> additionalData = getAdditionalData();
        additionalData.put("exceptionType", "");
        additionalData.put("className", "");
        additionalData.put("msg", str);
        additionalData.put("methodStack", "");
        additionalData.put("occurTime", String.format("%.6f", Float.valueOf(((float) System.currentTimeMillis()) / 1000.0f)));
        additionalData.put("logLevel", "INNER");
        additionalData.put("logTag", "ALC");
        report(additionalData);
    }

    @Override // com.jingdong.sdk.oklog.reporter.AbsLogReporter
    public HashMap<String, String> getAdditionalData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("runtime", b());
        hashMap.put(CachePool.K_TAG_CURRENT_PAGE, a());
        hashMap.put("process", this.a);
        hashMap.put("typeId", "2");
        hashMap.put("chId", "1");
        return hashMap;
    }

    @Override // com.jingdong.sdk.oklog.reporter.AbsLogReporter
    public boolean isReportable(int i2) {
        LogStrategyParam logStrategyParam = this.b;
        if (logStrategyParam != null) {
            return logStrategyParam.isReportable(i2);
        }
        return super.isReportable(i2);
    }

    @Override // com.jingdong.sdk.oklog.reporter.AbsLogReporter
    public void report(HashMap<String, String> hashMap) {
        this.f11192c.execute(new b(this, hashMap));
    }
}
