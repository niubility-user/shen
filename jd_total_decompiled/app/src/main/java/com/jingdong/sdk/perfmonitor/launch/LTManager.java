package com.jingdong.sdk.perfmonitor.launch;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes12.dex */
public class LTManager {

    /* renamed from: i  reason: collision with root package name */
    private static String f15420i = "LTManager";

    /* renamed from: j  reason: collision with root package name */
    private static SharedPreferences f15421j;

    /* renamed from: k  reason: collision with root package name */
    private static long f15422k;

    /* renamed from: l  reason: collision with root package name */
    private static AtomicBoolean f15423l = new AtomicBoolean(true);

    /* renamed from: m  reason: collision with root package name */
    private static AtomicBoolean f15424m = new AtomicBoolean(true);

    /* renamed from: n  reason: collision with root package name */
    private static boolean f15425n = false;
    private static Application o;
    private Map<String, com.jingdong.sdk.perfmonitor.launch.b> a;
    private HashMap<String, String> b;

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f15426c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private long f15427e;

    /* renamed from: f  reason: collision with root package name */
    private long f15428f;

    /* renamed from: g  reason: collision with root package name */
    private long f15429g;

    /* renamed from: h  reason: collision with root package name */
    private Map<String, String> f15430h;

    /* loaded from: classes12.dex */
    static class b {
        static LTManager a = new LTManager();
    }

    private boolean a(SharedPreferences.Editor editor) {
        long j2 = f15421j.getLong("LtOpenPhone", 0L);
        int b2 = com.jingdong.sdk.perfmonitor.e.a.b(o);
        if (b2 != f15421j.getInt("LtAppCode", 0)) {
            j2 = 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        editor.putLong("LtLaunchEnd", currentTimeMillis);
        editor.putInt("LtAppCode", b2);
        long elapsedRealtime = currentTimeMillis - SystemClock.elapsedRealtime();
        if (j2 <= 0 || Math.abs(elapsedRealtime - j2) > 5000) {
            editor.putLong("LtOpenPhone", elapsedRealtime);
            return true;
        }
        return false;
    }

    private boolean b() {
        return (this.a != null && this.f15426c.get() && Looper.myLooper() == Looper.getMainLooper()) ? false : true;
    }

    private void c() {
        if (b()) {
            return;
        }
        cancel();
        Iterator<Map.Entry<String, com.jingdong.sdk.perfmonitor.launch.b>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().c(this.b);
        }
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(o.getApplicationContext(), "11", "4");
        if (stategyEntitiy == null || !"1".equals(stategyEntitiy.ret)) {
            return;
        }
        this.b.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
        this.b.put("typeId", "11");
        this.b.put("chId", "4");
        Map<String, String> map = this.f15430h;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : this.f15430h.entrySet()) {
                this.b.put(entry.getKey(), entry.getValue());
            }
        }
        OKLog.e(f15420i, "mReportMap==" + this.b.toString());
        PerformanceReporter.reportData(this.b);
    }

    public static LTManager getInstance() {
        return b.a;
    }

    public static void init(boolean z, Application application) {
        f15425n = z;
        if (application != null) {
            o = application;
            return;
        }
        throw new IllegalArgumentException("application must not be null");
    }

    public void cancel() {
        this.f15426c.set(false);
    }

    public Map<String, String> getExtras() {
        return this.f15430h;
    }

    public String getLaunchString() {
        JsonObject jsonObject = new JsonObject();
        Iterator<Map.Entry<String, com.jingdong.sdk.perfmonitor.launch.b>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().d(jsonObject);
        }
        jsonObject.addProperty("LaunchUsed", String.valueOf(this.d));
        jsonObject.addProperty("BannerUsed", String.valueOf(this.f15427e));
        Gson create = new GsonBuilder().setPrettyPrinting().create();
        if (OKLog.D) {
            OKLog.d(f15420i, create.toJson((JsonElement) jsonObject));
        }
        return create.toJson((JsonElement) jsonObject);
    }

    public void launchToHome() {
        long elapsedRealtime = (SystemClock.elapsedRealtime() - f15422k) - this.f15429g;
        OKLog.e(f15420i, "goneHomeTime==" + elapsedRealtime);
        f15423l.set(elapsedRealtime > 10000);
        OKLog.e(f15420i, "sErrorTime==" + f15423l.get());
    }

    public void onBannerEnd(String str) {
        if (b()) {
            return;
        }
        this.f15427e = (SystemClock.elapsedRealtime() - f15422k) - this.f15429g;
        OKLog.e(f15420i, "mBannerUsed==" + this.f15427e);
        this.b.put("homeAppear", String.valueOf(System.currentTimeMillis()));
        this.b.put("bannerUrl", str);
        reportTimeData();
    }

    public void onColdEnd() {
        if (b()) {
            return;
        }
        SharedPreferences.Editor edit = f15421j.edit();
        if (a(edit)) {
            this.b.put("coldEnd", String.valueOf(System.currentTimeMillis()));
            c();
        }
        edit.apply();
    }

    public void onHomePause() {
        if (b() || !TextUtils.isEmpty(this.b.get("leaveHome"))) {
            return;
        }
        this.b.put("leaveHome", String.valueOf(System.currentTimeMillis()));
    }

    public void onHomeResume() {
        if (b()) {
            return;
        }
        long j2 = this.f15428f;
        if (j2 <= 0) {
            j2 = SystemClock.elapsedRealtime() - f15422k;
        }
        this.f15428f = j2;
        long j3 = this.d;
        if (j3 == 0) {
            j3 = this.f15429g;
        }
        if (j2 - j3 > Final.FIVE_SECOND) {
            cancel();
        }
    }

    public void onLaunchEnd() {
        SharedPreferences.Editor edit = f15421j.edit();
        boolean a2 = a(edit);
        if (!b() && !f15423l.get()) {
            long a3 = f15422k - com.jingdong.sdk.perfmonitor.launch.a.a();
            OKLog.e(f15420i, "processTime==" + a3);
            if (a3 > 1000) {
                cancel();
                edit.apply();
                return;
            }
            long j2 = a2 ? 0L : f15421j.getLong("LtLaunchEnd", 0L);
            edit.apply();
            this.d = (SystemClock.elapsedRealtime() - f15422k) - this.f15429g;
            long currentTimeMillis = System.currentTimeMillis();
            this.b.put("lastLaunchTime", String.valueOf(j2));
            this.b.put("launchStart", String.valueOf(currentTimeMillis - this.d));
            this.b.put("launchEnd", String.valueOf(currentTimeMillis));
            OKLog.e(f15420i, "mLaunchUsed==" + this.d);
            return;
        }
        cancel();
        edit.apply();
    }

    public void onTimeEnd(@NotNull String str, @NotNull String str2) {
        com.jingdong.sdk.perfmonitor.launch.b bVar;
        if (b() || (bVar = this.a.get(str)) == null) {
            return;
        }
        bVar.a(str2);
    }

    public void onTimeStart(@NotNull String str, @NotNull String str2) {
        if (b()) {
            return;
        }
        com.jingdong.sdk.perfmonitor.launch.b bVar = this.a.get(str);
        if (bVar == null) {
            bVar = new com.jingdong.sdk.perfmonitor.launch.b(str);
            this.a.put(str, bVar);
        }
        bVar.b(str2);
    }

    public void reportTimeData() {
        try {
            long j2 = this.d;
            if (j2 <= 0 || j2 >= 20000 || !f15424m.getAndSet(false)) {
                return;
            }
            c();
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    public void setExtras(Map<String, String> map) {
        this.f15430h = map;
    }

    public LTManager setRedundancyTime(long j2) {
        this.f15429g = j2;
        OKLog.e(f15420i, "setRedundancyTime==" + j2);
        return this;
    }

    private LTManager() {
        this.b = new HashMap<>(64);
        this.f15426c = new AtomicBoolean(f15425n);
        OKLog.e(f15420i, "isEnable \u5f00\u5173\u662f\u5426\u5f00\u542f==" + this.f15426c);
        if (com.jingdong.sdk.perfmonitor.e.b.h(o)) {
            f15422k = SystemClock.elapsedRealtime();
            this.a = new HashMap(64);
            f15421j = o.getSharedPreferences("JDLaunchTimeSP", 0);
        }
    }
}
