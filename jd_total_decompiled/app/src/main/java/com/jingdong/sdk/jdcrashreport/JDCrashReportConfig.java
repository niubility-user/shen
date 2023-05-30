package com.jingdong.sdk.jdcrashreport;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class JDCrashReportConfig implements Serializable {
    private ArrayList<Pattern> a;
    private transient Context b;

    /* renamed from: c  reason: collision with root package name */
    private String f14834c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private int f14835e;

    /* renamed from: f  reason: collision with root package name */
    private String f14836f;

    /* renamed from: g  reason: collision with root package name */
    private String f14837g;

    /* renamed from: h  reason: collision with root package name */
    private String f14838h;

    /* renamed from: i  reason: collision with root package name */
    private String f14839i;

    /* renamed from: j  reason: collision with root package name */
    private long f14840j;

    /* renamed from: k  reason: collision with root package name */
    private long f14841k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f14842l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f14843m;

    /* renamed from: n  reason: collision with root package name */
    private long f14844n;
    private boolean o;
    private transient com.jingdong.sdk.jdcrashreport.recover.b p;
    private transient e q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private ArrayList<String> v;
    private transient d w;
    private transient b x;
    private transient c y;

    /* loaded from: classes7.dex */
    public static class Builder {

        /* renamed from: c  reason: collision with root package name */
        Context f14845c;
        String d;

        /* renamed from: e  reason: collision with root package name */
        String f14846e;

        /* renamed from: k  reason: collision with root package name */
        ArrayList<Pattern> f14852k;

        /* renamed from: n  reason: collision with root package name */
        com.jingdong.sdk.jdcrashreport.recover.b f14855n;
        d r;
        b s;
        boolean a = true;
        boolean b = true;

        /* renamed from: f  reason: collision with root package name */
        int f14847f = -1;

        /* renamed from: g  reason: collision with root package name */
        String f14848g = "";

        /* renamed from: h  reason: collision with root package name */
        String f14849h = "";

        /* renamed from: i  reason: collision with root package name */
        String f14850i = "";

        /* renamed from: j  reason: collision with root package name */
        String f14851j = "";

        /* renamed from: l  reason: collision with root package name */
        long f14853l = 60000;

        /* renamed from: m  reason: collision with root package name */
        boolean f14854m = false;
        e o = new e(null);
        ArrayList<String> p = new ArrayList<>();
        boolean q = false;
        c t = new a(this);
        boolean u = true;

        /* loaded from: classes7.dex */
        class a extends c {
            a(Builder builder) {
            }

            @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportConfig.c
            public String a() {
                return super.a();
            }
        }

        public Builder addFilters(String... strArr) {
            if (this.f14852k == null) {
                this.f14852k = new ArrayList<>();
            }
            for (String str : strArr) {
                this.f14852k.add(Pattern.compile(str, 66));
            }
            return this;
        }

        public JDCrashReportConfig build() {
            return new JDCrashReportConfig(this, null);
        }

        public Builder enableRecover(boolean z) {
            this.f14854m = z;
            return this;
        }

        public Builder setAppId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.f14848g = str;
                return this;
            }
            throw new IllegalArgumentException("appId must not be empty");
        }

        public Builder setContext(Context context) {
            if (context != null) {
                this.f14845c = com.jingdong.sdk.jdcrashreport.b.c.n(context);
                return this;
            }
            throw new IllegalArgumentException("JDCrashReport Given context is null");
        }

        public Builder setCustomCrashReporter(b bVar) {
            this.s = bVar;
            return this;
        }

        public Builder setCustomParamConfig(c cVar) {
            this.t = cVar;
            return this;
        }

        public Builder setCustomRecoverView(com.jingdong.sdk.jdcrashreport.recover.b bVar) {
            this.f14855n = bVar;
            return this;
        }

        public Builder setDeviceUniqueId(String str) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            this.f14851j = str;
            return this;
        }

        public Builder setDowngradeCallback(d dVar) {
            this.r = dVar;
            return this;
        }

        public Builder setEnableAnr(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setEnableFragment(boolean z) {
            this.q = z;
            return this;
        }

        public Builder setEnableNative(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setPartner(String str) {
            this.d = str;
            return this;
        }

        public Builder setRecoverIgnoreExceptions(String[] strArr) {
            this.p.clear();
            if (strArr != null) {
                this.p.addAll(Arrays.asList(strArr));
            }
            return this;
        }

        public Builder setRecoverInfo(e eVar) {
            this.o = eVar;
            return this;
        }

        public Builder setReportDelay(int i2) {
            this.f14853l = i2 * 1000;
            return this;
        }

        public Builder setReportUV(boolean z) {
            this.u = z;
            return this;
        }

        public Builder setUserId(String str) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            this.f14849h = str;
            return this;
        }

        public Builder setUts(String str) {
            this.f14850i = str;
            return this;
        }

        public Builder setVersionCode(int i2) {
            this.f14847f = i2;
            return this;
        }

        public Builder setVersionName(String str) {
            this.f14846e = str;
            return this;
        }
    }

    /* loaded from: classes7.dex */
    class a extends c {
        a(JDCrashReportConfig jDCrashReportConfig) {
        }

        @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportConfig.c
        public String a() {
            return super.a();
        }
    }

    /* loaded from: classes7.dex */
    public interface b {
        void a(String str, String str2);

        f b(String str, String str2);
    }

    /* loaded from: classes7.dex */
    public static abstract class c {
        public String a() {
            return "1";
        }
    }

    /* loaded from: classes7.dex */
    public interface d {
        boolean a();
    }

    /* loaded from: classes7.dex */
    public static class e {
        private Class<? extends Activity> a;
        private a b;

        /* renamed from: c  reason: collision with root package name */
        private List<Class<? extends Activity>> f14856c;

        /* loaded from: classes7.dex */
        public interface a {
            void a(Activity activity);
        }

        /* synthetic */ e(a aVar) {
            this();
        }

        private e() {
            this.f14856c = new ArrayList();
        }

        @SafeVarargs
        public e(Class<? extends Activity> cls, a aVar, Class<? extends Activity>... clsArr) {
            ArrayList arrayList = new ArrayList();
            this.f14856c = arrayList;
            this.a = cls;
            this.b = aVar;
            if (clsArr != null) {
                arrayList.addAll(Arrays.asList(clsArr));
            }
        }
    }

    /* loaded from: classes7.dex */
    public enum f {
        DEFAULT,
        EXCEPTION,
        CUSTOM
    }

    /* synthetic */ JDCrashReportConfig(Builder builder, a aVar) {
        this(builder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context a() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        this.t = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.u;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String e() {
        return this.f14834c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String f() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.f14835e;
    }

    public String getDeviceUniqueId() {
        return this.f14837g;
    }

    public String getUserId() {
        return this.f14838h;
    }

    public String getUts() {
        return this.f14839i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String h() {
        return this.f14836f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<String> i() {
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long j() {
        return this.f14840j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long k() {
        return this.f14841k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l() {
        return this.f14842l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean m() {
        return this.f14843m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long n() {
        return this.f14844n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Pattern> o() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean p() {
        return this.o;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.jingdong.sdk.jdcrashreport.recover.b q() {
        return this.p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class<? extends Activity> r() {
        e eVar = this.q;
        if (eVar != null) {
            return eVar.a;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e.a s() {
        e eVar = this.q;
        if (eVar != null) {
            return eVar.b;
        }
        return null;
    }

    public void setApplicationContext(Context context) {
        this.b = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Class<? extends Activity>> t() {
        e eVar = this.q;
        if (eVar != null) {
            return eVar.f14856c;
        }
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean u() {
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d v() {
        return this.w;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b w() {
        return this.x;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c x() {
        return this.y;
    }

    private JDCrashReportConfig() {
        this.a = null;
        this.f14834c = "";
        this.f14836f = "";
        this.f14837g = "";
        this.f14838h = "";
        this.f14839i = "";
        this.f14842l = true;
        this.f14843m = true;
        this.f14844n = 60000L;
        this.o = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = true;
        this.v = new ArrayList<>();
        this.y = new a(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.s = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        return this.s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(String str) {
        this.f14839i = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.f14838h = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        this.f14837g = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Class<? extends Activity> cls) {
        if (cls == null || this.q.f14856c.contains(cls)) {
            return;
        }
        this.q.f14856c.add(cls);
    }

    private JDCrashReportConfig(Builder builder) {
        this.a = null;
        this.f14834c = "";
        this.f14836f = "";
        this.f14837g = "";
        this.f14838h = "";
        this.f14839i = "";
        this.f14842l = true;
        this.f14843m = true;
        this.f14844n = 60000L;
        this.o = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = true;
        this.v = new ArrayList<>();
        this.y = new a(this);
        this.b = builder.f14845c;
        this.f14834c = TextUtils.isEmpty(builder.d) ? "" : builder.d;
        this.d = TextUtils.isEmpty(builder.f14846e) ? com.jingdong.sdk.jdcrashreport.b.c.p(this.b) : builder.f14846e;
        int i2 = builder.f14847f;
        this.f14835e = i2 == -1 ? com.jingdong.sdk.jdcrashreport.b.c.r(this.b) : i2;
        this.f14840j = SystemClock.elapsedRealtime();
        this.f14841k = SystemClock.uptimeMillis();
        this.f14842l = builder.a;
        this.f14843m = builder.b;
        this.a = new ArrayList<>();
        try {
            Pattern compile = Pattern.compile(this.b.getPackageName() + "\\S+", 66);
            ArrayList<Pattern> arrayList = builder.f14852k;
            if (arrayList != null) {
                this.a.addAll(arrayList);
            }
            this.a.add(compile);
        } catch (Throwable unused) {
        }
        this.f14836f = builder.f14848g;
        this.f14837g = builder.f14851j;
        this.f14838h = builder.f14849h;
        this.f14839i = builder.f14850i;
        this.f14844n = builder.f14853l;
        this.o = builder.f14854m;
        this.p = builder.f14855n;
        this.q = builder.o;
        this.v.addAll(builder.p);
        this.r = builder.q;
        this.w = builder.r;
        this.x = builder.s;
        this.u = builder.u;
        this.y = builder.t;
    }
}
