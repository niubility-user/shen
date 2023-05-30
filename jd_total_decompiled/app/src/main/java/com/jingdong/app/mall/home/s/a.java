package com.jingdong.app.mall.home.s;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jingdong.app.mall.c;
import com.jingdong.app.mall.performance.PerformanceReporter;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;
import performance.jd.jdreportperformance.entity.StategyEntity;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: g  reason: collision with root package name */
    private static String f10794g = "a";

    /* renamed from: h  reason: collision with root package name */
    private static SharedPreferences f10795h;

    /* renamed from: i  reason: collision with root package name */
    private static long f10796i;

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicBoolean f10797j = new AtomicBoolean(true);

    /* renamed from: k  reason: collision with root package name */
    private static final AtomicBoolean f10798k = new AtomicBoolean(true);

    /* renamed from: l  reason: collision with root package name */
    private static final AtomicBoolean f10799l = new AtomicBoolean(false);

    /* renamed from: m  reason: collision with root package name */
    private static final Handler f10800m = new Handler(Looper.getMainLooper());

    /* renamed from: n  reason: collision with root package name */
    private static int f10801n;
    private Map<String, com.jingdong.app.mall.home.s.b> a;
    private final HashMap<String, String> b;

    /* renamed from: c  reason: collision with root package name */
    private final AtomicBoolean f10802c;
    private long d;

    /* renamed from: e  reason: collision with root package name */
    private long f10803e;

    /* renamed from: f  reason: collision with root package name */
    private long f10804f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.s.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public class RunnableC0321a implements Runnable {
        RunnableC0321a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.g("");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class b {
        static a a = new a(null);
    }

    /* synthetic */ a(RunnableC0321a runnableC0321a) {
        this();
    }

    public static a b() {
        return b.a;
    }

    private boolean d(SharedPreferences.Editor editor) {
        SharedPreferences sharedPreferences = f10795h;
        if (sharedPreferences == null) {
            return false;
        }
        long j2 = sharedPreferences.getLong("LtOpenPhone", 0L);
        int versionCode = PackageInfoUtil.getVersionCode();
        if (versionCode != f10795h.getInt("LtAppCode", 0)) {
            j2 = 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        editor.putLong("LtLaunchEnd", currentTimeMillis);
        editor.putInt("LtAppCode", versionCode);
        long elapsedRealtime = currentTimeMillis - SystemClock.elapsedRealtime();
        if (j2 <= 0 || Math.abs(elapsedRealtime - j2) > 5000) {
            editor.putLong("LtOpenPhone", elapsedRealtime);
            return true;
        }
        return false;
    }

    private boolean e() {
        return (this.a != null && this.f10802c.get() && Looper.myLooper() == Looper.getMainLooper()) ? false : true;
    }

    private void n() {
        if (e()) {
            return;
        }
        a();
        Iterator<Map.Entry<String, com.jingdong.app.mall.home.s.b>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().c(this.b);
        }
        StategyEntity stategyEntitiy = PerformanceReporter.getStategyEntitiy(JdSdk.getInstance().getApplicationContext(), "11", "4");
        if (stategyEntitiy == null || !"1".equals(stategyEntitiy.ret)) {
            return;
        }
        this.b.put("occurTime", new DecimalFormat("0.000000").format(System.currentTimeMillis() / 1000));
        this.b.put("typeId", "11");
        this.b.put("chId", "4");
        PerformanceReporter.reportData(this.b);
    }

    public void a() {
        this.f10802c.set(false);
    }

    public String c() {
        JsonObject jsonObject = new JsonObject();
        Iterator<Map.Entry<String, com.jingdong.app.mall.home.s.b>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().d(jsonObject);
        }
        jsonObject.addProperty("LaunchUsed", String.valueOf(this.d));
        jsonObject.addProperty("BannerUsed", String.valueOf(this.f10803e));
        Gson create = new GsonBuilder().setPrettyPrinting().create();
        if (OKLog.D) {
            OKLog.d(f10794g, create.toJson((JsonElement) jsonObject));
        }
        return create.toJson((JsonElement) jsonObject);
    }

    public void f() {
        f10797j.set(SystemClock.elapsedRealtime() - f10796i > 10000);
    }

    public void g(String str) {
        if (e()) {
            return;
        }
        this.f10803e = SystemClock.elapsedRealtime() - f10796i;
        this.b.put("homeAppear", String.valueOf(System.currentTimeMillis()));
        this.b.put("bannerUrl", str);
        o();
    }

    public void h() {
        if (!e() && TextUtils.isEmpty(this.b.get("leaveHome"))) {
            this.b.put("leaveHome", String.valueOf(System.currentTimeMillis()));
        }
    }

    public void i() {
        if (e()) {
            return;
        }
        long j2 = this.f10804f;
        if (j2 <= 0) {
            j2 = SystemClock.elapsedRealtime() - f10796i;
        }
        this.f10804f = j2;
        if (j2 - this.d > Final.FIVE_SECOND) {
            a();
        }
    }

    public void j() {
        SharedPreferences sharedPreferences = f10795h;
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        boolean d = d(edit);
        if (!e() && !f10797j.get()) {
            if (f10796i - c.b() > 1000) {
                a();
                edit.apply();
                return;
            }
            long j2 = d ? 0L : f10795h.getLong("LtLaunchEnd", 0L);
            edit.apply();
            this.d = SystemClock.elapsedRealtime() - f10796i;
            long currentTimeMillis = System.currentTimeMillis();
            this.b.put("lastLaunchTime", String.valueOf(j2));
            this.b.put("launchStart", String.valueOf(currentTimeMillis - this.d));
            this.b.put("launchEnd", String.valueOf(currentTimeMillis));
            return;
        }
        a();
        edit.apply();
    }

    public void k() {
        if (f10799l.getAndSet(true)) {
            return;
        }
        f10800m.post(new RunnableC0321a());
    }

    public void l(@NotNull String str, @NotNull String str2) {
        com.jingdong.app.mall.home.s.b bVar;
        if (e() || (bVar = this.a.get(str)) == null) {
            return;
        }
        bVar.a(str2);
    }

    public void m(@NotNull String str, @NotNull String str2) {
        if (e()) {
            return;
        }
        com.jingdong.app.mall.home.s.b bVar = this.a.get(str);
        if (bVar == null) {
            bVar = new com.jingdong.app.mall.home.s.b(str);
            this.a.put(str, bVar);
        }
        bVar.b(str2);
    }

    public void o() {
        try {
            int i2 = f10801n + 1;
            f10801n = i2;
            if (i2 >= 2) {
                long j2 = this.d;
                if (j2 <= 0 || j2 >= 20000 || !f10798k.getAndSet(false)) {
                    return;
                }
                n();
            }
        } catch (Exception e2) {
            e2.getMessage();
        }
    }

    private a() {
        this.b = new HashMap<>(64);
        this.f10802c = new AtomicBoolean(SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.LAUNCH_START_ENABLE, false));
        if (ProcessUtil.isMainProcess()) {
            f10796i = SystemClock.elapsedRealtime();
            this.a = new HashMap(64);
            f10795h = JdSdk.getInstance().getApplicationContext().getSharedPreferences("JDLaunchTimeSP", 0);
        }
    }
}
