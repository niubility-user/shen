package com.jingdong.manto.r;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.SparseArray;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.R;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.r.c;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.a;
import com.jingdong.manto.utils.p;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class d {
    protected static final SparseArray<b> a = new SparseArray<>();
    public static final int[] b;

    /* renamed from: c  reason: collision with root package name */
    public static final SparseArray<Integer> f14133c;
    public static long d;

    /* renamed from: e  reason: collision with root package name */
    public static long f14134e;

    /* renamed from: f  reason: collision with root package name */
    public static long f14135f;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f14136g;

    /* renamed from: h  reason: collision with root package name */
    private static boolean f14137h;

    /* renamed from: i  reason: collision with root package name */
    public static long f14138i;

    /* renamed from: j  reason: collision with root package name */
    public static long f14139j;

    /* renamed from: k  reason: collision with root package name */
    public static long f14140k;

    /* renamed from: l  reason: collision with root package name */
    public static long f14141l;

    /* renamed from: m  reason: collision with root package name */
    public static long f14142m;

    /* renamed from: n  reason: collision with root package name */
    public static long f14143n;
    static a.InterfaceC0688a o;

    /* loaded from: classes16.dex */
    class a implements a.InterfaceC0688a {
        a() {
        }

        @Override // com.jingdong.manto.utils.a.InterfaceC0688a
        public void a(Context context) {
            boolean unused = d.f14137h = true;
        }

        @Override // com.jingdong.manto.utils.a.InterfaceC0688a
        public void b(Context context) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static class b implements Runnable {
        protected final com.jingdong.manto.f b;

        /* renamed from: c  reason: collision with root package name */
        protected final String f14144c;

        /* renamed from: g  reason: collision with root package name */
        c f14147g;
        volatile boolean a = false;
        private volatile boolean d = true;

        /* renamed from: e  reason: collision with root package name */
        public volatile double f14145e = 0.0d;

        /* renamed from: f  reason: collision with root package name */
        private volatile int f14146f = 4;

        /* renamed from: h  reason: collision with root package name */
        c.a f14148h = new a();

        /* renamed from: i  reason: collision with root package name */
        AppLifeCycle.Listener f14149i = new C0667b();

        /* loaded from: classes16.dex */
        class a implements c.a {
            a() {
            }

            @Override // com.jingdong.manto.r.c.a
            public final void a(double d) {
                if (Math.round(b.this.f14145e) != Math.round(d)) {
                    b.this.f14145e = d;
                    d.a(b.this.b, 33, Math.round(b.this.f14145e) + " fps");
                }
            }
        }

        /* renamed from: com.jingdong.manto.r.d$b$b  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class C0667b extends AppLifeCycle.Listener {
            C0667b() {
            }

            @Override // com.jingdong.manto.AppLifeCycle.Listener
            public void onAppDestroy() {
                b.this.e();
            }

            @Override // com.jingdong.manto.AppLifeCycle.Listener
            public void onAppPause() {
                c cVar;
                b.this.a = true;
                if (!b.f() || (cVar = b.this.f14147g) == null) {
                    return;
                }
                cVar.b();
            }

            @Override // com.jingdong.manto.AppLifeCycle.Listener
            public void onAppResume() {
                c cVar;
                b.this.a = false;
                if (!b.f() || (cVar = b.this.f14147g) == null) {
                    return;
                }
                cVar.a();
            }
        }

        public b(com.jingdong.manto.f fVar) {
            this.b = fVar;
            this.f14144c = fVar.f13017i;
            if (f()) {
                c cVar = new c();
                this.f14147g = cVar;
                cVar.f14130c = 100L;
                cVar.f14132f = this.f14148h;
            }
        }

        static boolean f() {
            return Build.VERSION.SDK_INT >= 16;
        }

        void a() {
            d.a(this.b, 11, ((int) com.jingdong.manto.r.a.b()) + "%");
        }

        void b() {
            d.a(this.b, 12, MantoUtils.getProcessMemory(com.jingdong.a.g()) + "m");
        }

        void c() {
            com.jingdong.manto.i.c cVar;
            com.jingdong.manto.f fVar = this.b;
            if (fVar == null || (cVar = fVar.r) == null) {
                return;
            }
            int b = com.jingdong.manto.t.b.b(this.f14144c, cVar.f13082e);
            d.a(this.b, 41, b + "B");
        }

        public final void d() {
            c cVar;
            this.d = true;
            com.jingdong.manto.sdk.thread.a.b(this);
            AppLifeCycle.add(this.f14144c, this.f14149i);
            if (!f() || (cVar = this.f14147g) == null) {
                return;
            }
            cVar.a();
        }

        public final void e() {
            c cVar;
            this.d = false;
            AppLifeCycle.remove(this.f14144c, this.f14149i);
            if (!f() || (cVar = this.f14147g) == null) {
                return;
            }
            cVar.b();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.b.B) {
                return;
            }
            if (this.d && !this.a) {
                a();
                b();
                this.f14146f++;
                if (this.f14146f >= 4) {
                    this.f14146f = 0;
                    c();
                }
            }
            if (this.d) {
                com.jingdong.manto.sdk.thread.a.a(this, 1000L);
            }
        }
    }

    static {
        b = r0;
        SparseArray<Integer> sparseArray = new SparseArray<>();
        f14133c = sparseArray;
        d = 0L;
        f14134e = 0L;
        f14135f = 0L;
        f14136g = false;
        f14137h = false;
        f14138i = 0L;
        f14139j = 0L;
        f14140k = 0L;
        f14141l = 0L;
        f14142m = 0L;
        f14143n = 0L;
        int[] iArr = {R.string.manto_perfomance_title_base, R.string.manto_perfomance_title_launch, R.string.manto_perfomance_title_render, R.string.manto_perfomance_title_other};
        sparseArray.put(11, Integer.valueOf(R.string.manto_perfomance_detail_cpu));
        sparseArray.put(12, Integer.valueOf(R.string.manto_perfomance_detail_memory));
        sparseArray.put(21, Integer.valueOf(R.string.manto_perfomance_detail_download));
        sparseArray.put(22, Integer.valueOf(R.string.manto_perfomance_detail_page_change));
        sparseArray.put(23, Integer.valueOf(R.string.manto_perfomance_detail_launch));
        sparseArray.put(31, Integer.valueOf(R.string.manto_perfomance_detail_first_render));
        sparseArray.put(32, Integer.valueOf(R.string.manto_perfomance_detail_re_render));
        sparseArray.put(33, Integer.valueOf(R.string.manto_perfomance_detail_fps));
        sparseArray.put(41, Integer.valueOf(R.string.manto_perfomance_detail_dataSize));
        o = new a();
    }

    public static void a() {
        f14134e = System.currentTimeMillis();
        f14136g = false;
        f14137h = false;
        com.jingdong.manto.utils.a.a().a(o);
    }

    public static void a(com.jingdong.manto.f fVar) {
        if (f14136g) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = f14134e;
        if (!fVar.u()) {
            long j3 = d;
            if (j3 > 0) {
                j2 = j3;
            }
        }
        long j4 = currentTimeMillis - j2;
        if (fVar != null && !f14137h) {
            f.a(fVar, "loadRender", currentTimeMillis - f14142m);
            f.a(fVar, "onReadyTime", currentTimeMillis - f14140k);
            f.a(fVar, "launchTime", j4);
            PkgDetailEntity pkgDetailEntity = fVar.f13016h;
            com.jingdong.manto.i.c cVar = fVar.r;
            f.a(pkgDetailEntity, cVar != null ? cVar.f13091n : "0", "", "firstOnReady");
        }
        a(fVar, 23, j4);
        f14136g = true;
        com.jingdong.manto.utils.a.a().b(o);
        f14137h = false;
    }

    public static void a(com.jingdong.manto.f fVar, int i2, long j2) {
        a(fVar, i2, String.format("%d ms", Long.valueOf(j2)));
    }

    public static void a(com.jingdong.manto.f fVar, int i2, String str) {
        com.jingdong.manto.i.e eVar;
        if (fVar == null || (eVar = fVar.s) == null || !eVar.r) {
            return;
        }
        String str2 = fVar.f13017i;
        e eVar2 = fVar.F;
        if (eVar2 != null) {
            eVar2.a(i2, str);
            return;
        }
        p.a().a(str2.hashCode() + "perf_data", true).a(String.valueOf(i2), str);
    }

    public static void a(com.jingdong.manto.f fVar, boolean z) {
        if (z) {
            f.a(fVar, "jdaInfoTime", f14138i);
            f.a(fVar, "prepareLoadingTime", f14143n);
        } else if (fVar.u()) {
            f.a(fVar, "jdaInfoTime", f14138i);
        } else {
            long j2 = f14134e - f14139j;
            if (j2 < Final.FIVE_SECOND) {
                f.a(fVar, "jdaInfoTime", f14138i);
                f.a(fVar, "prepareLoadingTime", j2);
            }
        }
    }

    public static void a(com.jingdong.manto.i.c cVar) {
        if (f14136g || cVar == null) {
            return;
        }
        String str = cVar.a;
        String str2 = !TextUtils.isEmpty(cVar.f13082e) ? cVar.f13082e : "1";
        String str3 = !TextUtils.isEmpty(cVar.f13091n) ? cVar.f13091n : "0";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", str2);
            jSONObject.put(BaseEvent.SCENE, str3);
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(com.jingdong.manto.c.a(), "", "applets_userchurn", str, "", "", jSONObject.toString(), "", null);
    }

    public static void a(String str) {
        int hashCode = str.hashCode();
        SparseArray<b> sparseArray = a;
        b bVar = sparseArray.get(hashCode);
        if (bVar != null) {
            sparseArray.remove(hashCode);
            bVar.e();
        }
    }

    public static void b(com.jingdong.manto.f fVar) {
        long j2 = f14134e;
        if (!fVar.u()) {
            long j3 = d;
            if (j3 > 0) {
                j2 = j3;
            }
        }
        f.a(fVar, "resourcePrepare", f14142m - j2);
    }

    private static void c(com.jingdong.manto.f fVar) {
        String str = fVar.f13017i;
        e eVar = fVar.F;
        p.b a2 = p.a().a(str.hashCode() + "perf_custom_data");
        if (eVar == null) {
            MantoLog.e("PerformanceManager", "insertCachedPerformanceData panel is not ready.");
        } else if (a2 == null) {
            MantoLog.d("PerformanceManager", "insertCachedPerformanceData cache is empty.");
        } else {
            for (String str2 : a2.a()) {
                String str3 = (String) a2.a(str2);
                if (str3 != null) {
                    eVar.a(str2, str3);
                }
            }
        }
    }

    public static void d(com.jingdong.manto.f fVar) {
        String str = fVar.f13017i;
        e eVar = fVar.F;
        p.b a2 = p.a().a(str.hashCode() + "perf_data");
        if (eVar == null) {
            MantoLog.e("PerformanceManager", "insertCachedPerfData panel is not ready.");
        } else if (a2 == null) {
            MantoLog.d("PerformanceManager", "insertCachedPerfData cache is empty.");
        } else {
            for (String str2 : a2.a()) {
                String str3 = (String) a2.a(str2);
                if (str3 != null) {
                    eVar.a(MantoUtils.getInt(str2, 0), str3);
                }
            }
        }
        c(fVar);
    }

    public static void e(com.jingdong.manto.f fVar) {
        String str = fVar.f13017i;
        SparseArray<b> sparseArray = a;
        b bVar = sparseArray.get(str.hashCode());
        if (bVar == null) {
            bVar = new b(fVar);
            sparseArray.put(str.hashCode(), bVar);
        }
        bVar.d();
    }
}
