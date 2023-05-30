package com.jingdong.sdk.perfmonitor.b;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.StringBuilderPrinter;
import android.view.Choreographer;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.perfmonitor.IJankCustomInfo;
import com.jingdong.sdk.perfmonitor.Reporter;
import com.jingdong.sdk.perfmonitor.b.e;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class d extends com.jingdong.sdk.perfmonitor.b.b<com.jingdong.sdk.perfmonitor.d.d> {
    private String A;
    private String B;
    private Map<String, Integer> C;
    private JDJSONArray D;
    private JDJSONArray E;
    private JDJSONArray F;
    private AtomicBoolean G;
    private int H;
    private int I;
    private WeakReference<Activity> J;
    private boolean K;
    private boolean L;
    Handler M;
    private Runnable N;

    /* renamed from: g */
    long f15341g;

    /* renamed from: h */
    long f15342h;

    /* renamed from: i */
    public long f15343i;

    /* renamed from: j */
    public long f15344j;

    /* renamed from: k */
    private long f15345k;

    /* renamed from: l */
    private long f15346l;

    /* renamed from: m */
    private long f15347m;

    /* renamed from: n */
    private long f15348n;
    private long o;
    private Choreographer.FrameCallback p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private String w;
    private String x;
    private String y;
    private String z;

    /* loaded from: classes12.dex */
    public class a implements Choreographer.FrameCallback {
        a() {
            d.this = r1;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j2) {
            d.k(d.this);
            if (d.this.o != 0) {
                long millis = TimeUnit.NANOSECONDS.toMillis(j2 - d.this.o);
                if (millis >= d.this.f15347m) {
                    d.this.v = true;
                } else if (millis < d.this.f15346l) {
                    if (d.this.v) {
                        d.this.F("bigJank");
                    } else {
                        d dVar = d.this;
                        if (d.this.r >= dVar.f15344j) {
                            dVar.F("cStuck");
                        } else {
                            d dVar2 = d.this;
                            if (dVar.q >= dVar2.f15343i) {
                                dVar2.F("lStuck");
                            } else {
                                dVar2.F(null);
                            }
                        }
                    }
                } else {
                    d.x(d.this);
                    if (millis < d.this.f15345k) {
                        d dVar3 = d.this;
                        if (d.this.r < dVar3.f15344j) {
                            dVar3.r = 0;
                        }
                    } else {
                        d.B(d.this);
                    }
                }
            }
            d.this.o = j2;
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    /* loaded from: classes12.dex */
    public class b implements Runnable {
        b() {
            d.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar;
            Handler handler;
            try {
                d.this.C.put(d.this.E(Looper.getMainLooper().getThread().getStackTrace(), d.this.I), 1);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (d.this.G.get() && (handler = (dVar = d.this).M) != null) {
                handler.postDelayed(dVar.N, d.this.f15348n);
            } else {
                d.this.P();
            }
        }
    }

    public d(Context context, Reporter reporter) {
        super(reporter);
        this.z = "0";
        this.C = new ConcurrentHashMap();
        this.D = new JDJSONArray();
        this.E = new JDJSONArray();
        this.F = new JDJSONArray();
        this.G = new AtomicBoolean(false);
        this.K = false;
        this.L = false;
        this.N = new b();
        com.jingdong.sdk.perfmonitor.d.d dVar = new com.jingdong.sdk.perfmonitor.d.d(context);
        this.b = dVar;
        this.I = dVar.d;
        this.f15343i = dVar.f15411e;
        this.f15344j = dVar.f15412f;
        this.f15345k = dVar.f15413g;
        this.f15346l = dVar.f15414h;
        this.f15347m = dVar.f15415i;
        this.f15348n = dVar.f15416j;
        this.K = dVar.f15417k;
        this.M = new Handler(com.jingdong.sdk.perfmonitor.b.b.f15310f.getLooper());
        if (Build.VERSION.SDK_INT >= 16) {
            this.p = new a();
        }
    }

    static /* synthetic */ int B(d dVar) {
        int i2 = dVar.r;
        dVar.r = i2 + 1;
        return i2;
    }

    public String E(StackTraceElement[] stackTraceElementArr, int i2) {
        if (stackTraceElementArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        StringBuilderPrinter stringBuilderPrinter = new StringBuilderPrinter(sb);
        boolean z = this.K;
        int i3 = 0;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            String className = stackTraceElement.getClassName();
            if (z || className.contains(".jd.") || className.contains(".jingdong.")) {
                stringBuilderPrinter.println(stackTraceElement.toString());
                if (!this.K && (i3 = i3 + 1) >= i2) {
                    break;
                }
                z = true;
            }
        }
        return sb.toString();
    }

    public void F(String str) {
        if (!TextUtils.isEmpty(str)) {
            if ("bigJank".equals(str)) {
                this.u++;
            } else if ("cStuck".equals(str)) {
                this.t++;
            } else if ("lStuck".equals(str)) {
                this.s++;
            }
            for (String str2 : this.C.keySet()) {
                if (!TextUtils.isEmpty(str2)) {
                    if ("bigJank".equals(str)) {
                        this.F.add(str2);
                    } else if ("cStuck".equals(str)) {
                        this.E.add(str2);
                    } else if ("lStuck".equals(str)) {
                        this.D.add(str2);
                    }
                }
            }
        }
        this.C.clear();
        this.v = false;
        this.q = 0;
        this.r = 0;
    }

    private void I(long j2) {
        if ("2".equals(this.z)) {
            HashMap<String, String> hashMap = new HashMap<>();
            JSONObject jSONObject = new JSONObject();
            hashMap.put("chId", "2");
            String str = this.A;
            String str2 = this.w;
            String str3 = this.x;
            String str4 = this.y;
            String value = e.p.BUSINESS.getValue();
            g(this.B);
            try {
                jSONObject.put("avg", str);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            hashMap.put("cpuFps", jSONObject.toString());
            hashMap.put("lstuckCount", str2);
            hashMap.put("cstuckCount", str3);
            hashMap.put("bstuckCount", str4);
            hashMap.put(JshopConst.JSKEY_COUPON_PLATFORM, this.z);
            hashMap.put("rtype", value);
            hashMap.put("duration", String.valueOf(j2));
            OKLog.d("JankMonitor", "flutter report lstuckCount is " + str2 + " and cstuckCount is " + str3 + " and bigJankCount is " + str4 + "and platformType is " + this.z);
            f(hashMap);
        }
    }

    private void K() {
        if (Build.VERSION.SDK_INT >= 16 && this.p != null) {
            Choreographer.getInstance().removeFrameCallback(this.p);
        }
        this.H = 0;
        this.o = 0L;
        this.q = 0;
        this.r = 0;
        this.v = false;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.C.clear();
        this.D.clear();
        this.E.clear();
        this.F.clear();
        this.L = false;
        this.z = "0";
        this.A = "";
        this.w = "";
        this.x = "";
        this.y = "";
        this.B = "";
        this.G.set(false);
        WeakReference<Activity> weakReference = this.J;
        if (weakReference != null) {
            weakReference.clear();
            this.J = null;
        }
    }

    static /* synthetic */ int k(d dVar) {
        int i2 = dVar.H;
        dVar.H = i2 + 1;
        return i2;
    }

    static /* synthetic */ int x(d dVar) {
        int i2 = dVar.q;
        dVar.q = i2 + 1;
        return i2;
    }

    public void G(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return;
        }
        this.A = hashMap.get("cpuFps");
        this.z = hashMap.get(JshopConst.JSKEY_COUPON_PLATFORM);
        this.w = hashMap.get("lstuckCount");
        this.x = hashMap.get("cstuckCount");
        this.y = hashMap.get("bstuckCount");
        this.B = hashMap.get(WebPerfManager.PAGE_NAME);
    }

    public void H() {
        long j2 = this.f15341g;
        if (j2 != 0) {
            long j3 = this.f15342h;
            if (j3 == 0) {
                return;
            }
            long j4 = j3 - j2;
            if (j4 < 1000) {
                return;
            }
            int i2 = (int) ((this.H * 1000) / j4);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("chId", "2");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("avg", String.valueOf(i2));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            hashMap.put("cpuFps", jSONObject.toString());
            hashMap.put("lstuckCount", String.valueOf(this.s));
            hashMap.put("cstuckCount", String.valueOf(this.t));
            hashMap.put("bstuckCount", String.valueOf(this.u));
            hashMap.put("duration", String.valueOf(j4));
            if (this.D.size() > 0) {
                if (this.D.size() >= 50) {
                    hashMap.put("lstuckInfo", new JDJSONArray(this.D.subList(0, 50)).toJSONString());
                } else {
                    hashMap.put("lstuckInfo", this.D.toJSONString());
                }
            }
            if (this.E.size() > 0) {
                if (this.E.size() >= 50) {
                    hashMap.put("cstuckInfo", new JDJSONArray(this.E.subList(0, 50)).toJSONString());
                } else {
                    hashMap.put("cstuckInfo", this.E.toJSONString());
                }
            }
            if (this.F.size() > 0) {
                if (this.F.size() >= 50) {
                    hashMap.put("bstuckInfo", new JDJSONArray(this.F.subList(0, 50)).toJSONString());
                } else {
                    hashMap.put("bstuckInfo", this.F.toJSONString());
                }
            }
            J(hashMap);
            OKLog.d("JankMonitor", "report lstuckCount is " + this.s + " and cstuckCount is " + this.t + " and bigJankCount is " + this.u + " \nlstuckInfo size is " + this.D.size() + " cStuckInfo size is " + this.E.size() + " bigJankInfo size is " + this.F.size());
            f(hashMap);
            I(j4);
        }
    }

    public void J(HashMap<String, String> hashMap) {
        WeakReference<Activity> weakReference = this.J;
        if (weakReference == null || weakReference.get() == null || !(this.J.get() instanceof IJankCustomInfo)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        Map<String, String> jankCustomInfo = ((IJankCustomInfo) this.J.get()).getJankCustomInfo();
        if (jankCustomInfo == null || jankCustomInfo.size() == 0) {
            return;
        }
        try {
            for (Map.Entry<String, String> entry : jankCustomInfo.entrySet()) {
                jSONObject.put(entry.getKey(), entry.getValue());
            }
            hashMap.put("extraInfo", jSONObject.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void L(@NonNull Activity activity) {
        if (this.L) {
            N(activity);
            this.L = false;
        }
    }

    public boolean M(@NonNull Activity activity) {
        return h(com.jingdong.sdk.perfmonitor.b.b.d(activity));
    }

    public void N(@NonNull Activity activity) {
        super.i(com.jingdong.sdk.perfmonitor.b.b.d(activity));
        this.f15341g = SystemClock.elapsedRealtime();
        K();
        this.J = new WeakReference<>(activity);
        O();
        if (Build.VERSION.SDK_INT < 16 || this.p == null) {
            return;
        }
        Choreographer.getInstance().postFrameCallback(this.p);
    }

    public void O() {
        if (this.M == null || this.G.get()) {
            return;
        }
        this.G.set(true);
        this.M.removeCallbacks(this.N);
        this.M.postDelayed(this.N, this.f15348n);
    }

    public void P() {
        if (this.M != null && this.G.get()) {
            this.G.set(false);
            this.M.removeCallbacks(this.N);
        }
    }

    @Override // com.jingdong.sdk.perfmonitor.b.b
    public void j() {
        super.j();
        this.f15342h = SystemClock.elapsedRealtime();
        P();
        if (Build.VERSION.SDK_INT >= 16 && this.p != null) {
            Choreographer.getInstance().removeFrameCallback(this.p);
        }
        this.L = true;
    }
}
