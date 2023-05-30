package com.jingdong.sdk.talos;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.talos.inner.e;

/* loaded from: classes.dex */
public class b {
    private String a;
    private String b;

    /* renamed from: c */
    private byte[] f15460c;
    private byte[] d;

    /* renamed from: e */
    private boolean f15461e;

    /* renamed from: f */
    private boolean f15462f;

    /* renamed from: g */
    private String f15463g;

    /* renamed from: h */
    private String f15464h;

    /* renamed from: i */
    private String f15465i;

    /* renamed from: j */
    private String f15466j;

    /* renamed from: k */
    private Context f15467k;

    /* renamed from: l */
    private com.jingdong.sdk.talos.inner.c.b f15468l;

    /* renamed from: m */
    private boolean f15469m;

    /* renamed from: n */
    private int f15470n;
    private c o;
    private d p;

    /* loaded from: classes.dex */
    public final class a implements c {
        a(b bVar) {
        }

        @Override // com.jingdong.sdk.talos.b.c
        public final boolean a() {
            return false;
        }
    }

    /* renamed from: com.jingdong.sdk.talos.b$b */
    /* loaded from: classes.dex */
    public static final class C0746b {
        Context a;

        /* renamed from: c */
        Boolean f15471c;

        /* renamed from: j */
        c f15477j;

        /* renamed from: k */
        d f15478k;
        boolean b = false;
        String d = "";

        /* renamed from: e */
        String f15472e = "";

        /* renamed from: f */
        String f15473f = "";

        /* renamed from: g */
        String f15474g = "";

        /* renamed from: h */
        int f15475h = 5;

        /* renamed from: i */
        private boolean f15476i = true;

        public C0746b(Context context) {
            this.a = context;
        }

        public final b b() {
            return new b(this, null);
        }

        public final C0746b c(String str) {
            if (str.length() == 32) {
                this.d = str;
                return this;
            }
            throw new IllegalArgumentException("config parameter appkey`s format is not right, appkey must be 32-bit");
        }

        public final C0746b d(String str) {
            this.f15473f = str;
            return this;
        }

        public final C0746b e(c cVar) {
            this.f15477j = cVar;
            return this;
        }

        public final C0746b f(int i2) {
            if (i2 >= 0 && i2 <= 60) {
                this.f15475h = i2;
            }
            return this;
        }

        public final C0746b g(boolean z) {
            this.b = z;
            return this;
        }

        public final C0746b h(String str) {
            this.f15474g = str;
            return this;
        }

        public final C0746b i(Boolean bool) {
            this.f15471c = bool;
            return this;
        }

        public final C0746b j(d dVar) {
            this.f15478k = dVar;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        boolean a();
    }

    /* loaded from: classes.dex */
    public interface d {
        String getUserId();
    }

    public b() {
    }

    private b(C0746b c0746b) {
        this.f15460c = c0746b.d.substring(0, 16).getBytes();
        this.d = c0746b.d.substring(16).getBytes();
        this.f15469m = c0746b.f15476i;
        this.f15470n = c0746b.f15475h;
        boolean z = c0746b.b;
        this.f15461e = z;
        Boolean bool = c0746b.f15471c;
        this.f15462f = bool != null ? bool.booleanValue() : z;
        this.f15463g = c0746b.d;
        this.f15464h = c0746b.f15472e;
        this.f15465i = c0746b.f15473f;
        this.f15466j = c0746b.f15474g;
        this.o = c0746b.f15477j;
        this.p = c0746b.f15478k;
        Context context = c0746b.a;
        this.f15467k = context;
        e.d.a = c0746b.b;
        this.f15468l = new com.jingdong.sdk.talos.inner.c.b(context);
    }

    /* synthetic */ b(C0746b c0746b, a aVar) {
        this(c0746b);
    }

    public static b d() {
        return new b();
    }

    public boolean A() {
        return (TextUtils.isEmpty(this.f15463g) || this.f15460c == null || this.d == null) ? false : true;
    }

    public void B(String str) {
        this.a = str;
    }

    public void C(String str) {
        this.b = str;
    }

    public String a() {
        return this.a;
    }

    public Context b() {
        return this.f15467k;
    }

    public long c() {
        return this.f15468l.a.f15497e * 86400000;
    }

    public String e() {
        return this.f15465i;
    }

    public byte[] f() {
        return this.d;
    }

    public byte[] g() {
        return this.f15460c;
    }

    public c h() {
        if (this.o == null) {
            this.o = new a(this);
        }
        return this.o;
    }

    public String i() {
        return this.b;
    }

    public int j() {
        return this.f15470n;
    }

    public int k() {
        return this.f15468l.a.f15501i;
    }

    public String l() {
        return this.f15468l.a.b;
    }

    public String m() {
        return this.f15468l.a.d;
    }

    public long n() {
        return this.f15468l.a.f15498f * 1024;
    }

    public long o() {
        return this.f15468l.a.f15500h;
    }

    public long p() {
        return this.f15468l.a.f15499g * 1048576;
    }

    public String q() {
        return this.f15466j;
    }

    public String r() {
        return this.f15468l.a.f15502j;
    }

    public com.jingdong.sdk.talos.inner.c.b s() {
        return this.f15468l;
    }

    public String t() {
        d dVar = this.p;
        return dVar != null ? dVar.getUserId() : this.f15464h;
    }

    public String u() {
        return this.f15463g;
    }

    public boolean v() {
        return this.f15461e;
    }

    public boolean w() {
        return this.f15468l.a.a;
    }

    public boolean x() {
        return this.f15469m;
    }

    public boolean y() {
        return this.f15462f;
    }

    public boolean z() {
        com.jingdong.sdk.talos.inner.c.b bVar = this.f15468l;
        if (bVar == null) {
            return false;
        }
        String networkType = BaseInfo.getNetworkType();
        if (!networkType.equalsIgnoreCase("none")) {
            if (networkType.equals("wifi") || networkType.equals(BaseInfo.NETWORK_TYPE_ETHERNET)) {
                return bVar.a.f15496c.contains("wifi");
            }
            if (bVar.a.f15496c.contains("5g") || bVar.a.f15496c.contains("4g") || bVar.a.f15496c.contains("3g") || bVar.a.f15496c.contains("2g") || bVar.a.f15496c.contains("mobile")) {
                return true;
            }
        }
        return false;
    }
}
