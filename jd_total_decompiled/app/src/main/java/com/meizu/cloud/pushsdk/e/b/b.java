package com.meizu.cloud.pushsdk.e.b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.google.common.net.HttpHeaders;
import com.meizu.cloud.pushsdk.e.b.b;
import com.meizu.cloud.pushsdk.e.d.b;
import com.meizu.cloud.pushsdk.e.d.c;
import com.meizu.cloud.pushsdk.e.d.f;
import com.meizu.cloud.pushsdk.e.d.g;
import com.meizu.cloud.pushsdk.e.d.h;
import com.meizu.cloud.pushsdk.e.d.j;
import com.meizu.cloud.pushsdk.e.d.k;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class b<T extends b> {
    private static final g G = g.a("application/json; charset=utf-8");
    private static final g H = g.a("text/x-markdown; charset=utf-8");
    private static final Object I = new Object();
    private Bitmap.Config A;
    private int B;
    private int C;
    private ImageView.ScaleType D;
    private final Executor E;
    private String F;
    private final int a;
    private final com.meizu.cloud.pushsdk.e.b.d b;

    /* renamed from: c */
    private final int f15740c;
    private final String d;

    /* renamed from: e */
    private int f15741e;

    /* renamed from: f */
    private final Object f15742f;

    /* renamed from: g */
    private com.meizu.cloud.pushsdk.e.b.e f15743g;

    /* renamed from: h */
    private final HashMap<String, String> f15744h;

    /* renamed from: i */
    private HashMap<String, String> f15745i;

    /* renamed from: j */
    private HashMap<String, String> f15746j;

    /* renamed from: k */
    private HashMap<String, String> f15747k;

    /* renamed from: l */
    private final HashMap<String, String> f15748l;

    /* renamed from: m */
    private final HashMap<String, String> f15749m;

    /* renamed from: n */
    private HashMap<String, File> f15750n;
    private String o;
    private String p;
    private JSONObject q;
    private JSONArray r;
    private String s;
    private byte[] t;
    private File u;
    private g v;
    private com.meizu.cloud.pushsdk.e.d.a w;
    private int x;
    private boolean y;
    private com.meizu.cloud.pushsdk.e.e.a z;

    /* loaded from: classes14.dex */
    public class a implements com.meizu.cloud.pushsdk.e.e.a {
        a() {
            b.this = r1;
        }

        @Override // com.meizu.cloud.pushsdk.e.e.a
        public void a(long j2, long j3) {
            b.this.x = (int) ((100 * j2) / j3);
            if (b.this.z == null || b.this.y) {
                return;
            }
            b.this.z.a(j2, j3);
        }
    }

    /* renamed from: com.meizu.cloud.pushsdk.e.b.b$b */
    /* loaded from: classes14.dex */
    public static /* synthetic */ class C0758b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.meizu.cloud.pushsdk.e.b.e.values().length];
            a = iArr;
            try {
                iArr[com.meizu.cloud.pushsdk.e.b.e.JSON_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.meizu.cloud.pushsdk.e.b.e.JSON_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[com.meizu.cloud.pushsdk.e.b.e.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[com.meizu.cloud.pushsdk.e.b.e.BITMAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[com.meizu.cloud.pushsdk.e.b.e.PREFETCH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes14.dex */
    public static class c<T extends c> {
        private final String b;

        /* renamed from: c */
        private Object f15751c;

        /* renamed from: g */
        private final String f15754g;

        /* renamed from: h */
        private final String f15755h;

        /* renamed from: j */
        private Executor f15757j;

        /* renamed from: k */
        private String f15758k;
        private com.meizu.cloud.pushsdk.e.b.d a = com.meizu.cloud.pushsdk.e.b.d.MEDIUM;
        private final HashMap<String, String> d = new HashMap<>();

        /* renamed from: e */
        private final HashMap<String, String> f15752e = new HashMap<>();

        /* renamed from: f */
        private final HashMap<String, String> f15753f = new HashMap<>();

        /* renamed from: i */
        private int f15756i = 0;

        public c(String str, String str2, String str3) {
            this.b = str;
            this.f15754g = str2;
            this.f15755h = str3;
        }

        public b a() {
            return new b(this);
        }
    }

    /* loaded from: classes14.dex */
    public static class d<T extends d> {

        /* renamed from: c */
        private final String f15759c;
        private Object d;

        /* renamed from: e */
        private Bitmap.Config f15760e;

        /* renamed from: f */
        private int f15761f;

        /* renamed from: g */
        private int f15762g;

        /* renamed from: h */
        private ImageView.ScaleType f15763h;

        /* renamed from: l */
        private Executor f15767l;

        /* renamed from: m */
        private String f15768m;
        private com.meizu.cloud.pushsdk.e.b.d a = com.meizu.cloud.pushsdk.e.b.d.MEDIUM;

        /* renamed from: i */
        private final HashMap<String, String> f15764i = new HashMap<>();

        /* renamed from: j */
        private final HashMap<String, String> f15765j = new HashMap<>();

        /* renamed from: k */
        private final HashMap<String, String> f15766k = new HashMap<>();
        private final int b = 0;

        public d(String str) {
            this.f15759c = str;
        }

        public T b(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    this.f15765j.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public b c() {
            return new b(this);
        }
    }

    /* loaded from: classes14.dex */
    public static class e<T extends e> {
        private final String b;

        /* renamed from: c */
        private Object f15769c;

        /* renamed from: j */
        private Executor f15775j;

        /* renamed from: k */
        private String f15776k;

        /* renamed from: l */
        private String f15777l;
        private com.meizu.cloud.pushsdk.e.b.d a = com.meizu.cloud.pushsdk.e.b.d.MEDIUM;
        private final HashMap<String, String> d = new HashMap<>();

        /* renamed from: e */
        private final HashMap<String, String> f15770e = new HashMap<>();

        /* renamed from: f */
        private final HashMap<String, String> f15771f = new HashMap<>();

        /* renamed from: g */
        private final HashMap<String, String> f15772g = new HashMap<>();

        /* renamed from: h */
        private final HashMap<String, File> f15773h = new HashMap<>();

        /* renamed from: i */
        private int f15774i = 0;

        public e(String str) {
            this.b = str;
        }

        public T a(String str, File file) {
            this.f15773h.put(str, file);
            return this;
        }

        public T b(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    this.f15770e.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public b c() {
            return new b(this);
        }
    }

    /* loaded from: classes14.dex */
    public static class f<T extends f> {

        /* renamed from: c */
        private final String f15778c;
        private Object d;
        private Executor o;
        private String p;
        private String q;
        private com.meizu.cloud.pushsdk.e.b.d a = com.meizu.cloud.pushsdk.e.b.d.MEDIUM;

        /* renamed from: e */
        private JSONObject f15779e = null;

        /* renamed from: f */
        private JSONArray f15780f = null;

        /* renamed from: g */
        private String f15781g = null;

        /* renamed from: h */
        private byte[] f15782h = null;

        /* renamed from: i */
        private File f15783i = null;

        /* renamed from: j */
        private final HashMap<String, String> f15784j = new HashMap<>();

        /* renamed from: k */
        private final HashMap<String, String> f15785k = new HashMap<>();

        /* renamed from: l */
        private final HashMap<String, String> f15786l = new HashMap<>();

        /* renamed from: m */
        private final HashMap<String, String> f15787m = new HashMap<>();

        /* renamed from: n */
        private final HashMap<String, String> f15788n = new HashMap<>();
        private final int b = 1;

        public f(String str) {
            this.f15778c = str;
        }

        public T b(HashMap<String, String> hashMap) {
            if (hashMap != null) {
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    this.f15785k.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public b c() {
            return new b(this);
        }
    }

    public b(c cVar) {
        this.f15745i = new HashMap<>();
        this.f15746j = new HashMap<>();
        this.f15747k = new HashMap<>();
        this.f15750n = new HashMap<>();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.f15740c = 1;
        this.a = 0;
        this.b = cVar.a;
        this.d = cVar.b;
        this.f15742f = cVar.f15751c;
        this.o = cVar.f15754g;
        this.p = cVar.f15755h;
        this.f15744h = cVar.d;
        this.f15748l = cVar.f15752e;
        this.f15749m = cVar.f15753f;
        int unused = cVar.f15756i;
        this.E = cVar.f15757j;
        this.F = cVar.f15758k;
    }

    public b(d dVar) {
        this.f15745i = new HashMap<>();
        this.f15746j = new HashMap<>();
        this.f15747k = new HashMap<>();
        this.f15750n = new HashMap<>();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.f15740c = 0;
        this.a = dVar.b;
        this.b = dVar.a;
        this.d = dVar.f15759c;
        this.f15742f = dVar.d;
        this.f15744h = dVar.f15764i;
        this.A = dVar.f15760e;
        this.C = dVar.f15762g;
        this.B = dVar.f15761f;
        this.D = dVar.f15763h;
        this.f15748l = dVar.f15765j;
        this.f15749m = dVar.f15766k;
        this.E = dVar.f15767l;
        this.F = dVar.f15768m;
    }

    public b(e eVar) {
        this.f15745i = new HashMap<>();
        this.f15746j = new HashMap<>();
        this.f15747k = new HashMap<>();
        this.f15750n = new HashMap<>();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.f15740c = 2;
        this.a = 1;
        this.b = eVar.a;
        this.d = eVar.b;
        this.f15742f = eVar.f15769c;
        this.f15744h = eVar.d;
        this.f15748l = eVar.f15771f;
        this.f15749m = eVar.f15772g;
        this.f15747k = eVar.f15770e;
        this.f15750n = eVar.f15773h;
        int unused = eVar.f15774i;
        this.E = eVar.f15775j;
        this.F = eVar.f15776k;
        if (eVar.f15777l != null) {
            this.v = g.a(eVar.f15777l);
        }
    }

    public b(f fVar) {
        this.f15745i = new HashMap<>();
        this.f15746j = new HashMap<>();
        this.f15747k = new HashMap<>();
        this.f15750n = new HashMap<>();
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.f15740c = 0;
        this.a = fVar.b;
        this.b = fVar.a;
        this.d = fVar.f15778c;
        this.f15742f = fVar.d;
        this.f15744h = fVar.f15784j;
        this.f15745i = fVar.f15785k;
        this.f15746j = fVar.f15786l;
        this.f15748l = fVar.f15787m;
        this.f15749m = fVar.f15788n;
        this.q = fVar.f15779e;
        this.r = fVar.f15780f;
        this.s = fVar.f15781g;
        this.u = fVar.f15783i;
        this.t = fVar.f15782h;
        this.E = fVar.o;
        this.F = fVar.p;
        if (fVar.q != null) {
            this.v = g.a(fVar.q);
        }
    }

    public com.meizu.cloud.pushsdk.e.b.c b() {
        this.f15743g = com.meizu.cloud.pushsdk.e.b.e.BITMAP;
        return com.meizu.cloud.pushsdk.e.f.c.a(this);
    }

    public com.meizu.cloud.pushsdk.e.b.c c(k kVar) {
        com.meizu.cloud.pushsdk.e.b.c<Bitmap> c2;
        int i2 = C0758b.a[this.f15743g.ordinal()];
        if (i2 == 1) {
            try {
                return com.meizu.cloud.pushsdk.e.b.c.b(new JSONArray(com.meizu.cloud.pushsdk.e.h.g.b(kVar.a().g()).a()));
            } catch (Exception e2) {
                com.meizu.cloud.pushsdk.e.c.a aVar = new com.meizu.cloud.pushsdk.e.c.a(e2);
                com.meizu.cloud.pushsdk.e.i.b.j(aVar);
                return com.meizu.cloud.pushsdk.e.b.c.a(aVar);
            }
        } else if (i2 == 2) {
            try {
                return com.meizu.cloud.pushsdk.e.b.c.b(new JSONObject(com.meizu.cloud.pushsdk.e.h.g.b(kVar.a().g()).a()));
            } catch (Exception e3) {
                com.meizu.cloud.pushsdk.e.c.a aVar2 = new com.meizu.cloud.pushsdk.e.c.a(e3);
                com.meizu.cloud.pushsdk.e.i.b.j(aVar2);
                return com.meizu.cloud.pushsdk.e.b.c.a(aVar2);
            }
        } else if (i2 == 3) {
            try {
                return com.meizu.cloud.pushsdk.e.b.c.b(com.meizu.cloud.pushsdk.e.h.g.b(kVar.a().g()).a());
            } catch (Exception e4) {
                com.meizu.cloud.pushsdk.e.c.a aVar3 = new com.meizu.cloud.pushsdk.e.c.a(e4);
                com.meizu.cloud.pushsdk.e.i.b.j(aVar3);
                return com.meizu.cloud.pushsdk.e.b.c.a(aVar3);
            }
        } else if (i2 != 4) {
            if (i2 != 5) {
                return null;
            }
            return com.meizu.cloud.pushsdk.e.b.c.b("prefetch");
        } else {
            synchronized (I) {
                try {
                    try {
                        c2 = com.meizu.cloud.pushsdk.e.i.b.c(kVar, this.B, this.C, this.A, this.D);
                    } catch (Exception e5) {
                        com.meizu.cloud.pushsdk.e.c.a aVar4 = new com.meizu.cloud.pushsdk.e.c.a(e5);
                        com.meizu.cloud.pushsdk.e.i.b.j(aVar4);
                        return com.meizu.cloud.pushsdk.e.b.c.a(aVar4);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return c2;
        }
    }

    public com.meizu.cloud.pushsdk.e.c.a d(com.meizu.cloud.pushsdk.e.c.a aVar) {
        try {
            if (aVar.c() != null && aVar.c().a() != null && aVar.c().a().g() != null) {
                aVar.a(com.meizu.cloud.pushsdk.e.h.g.b(aVar.c().a().g()).a());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return aVar;
    }

    public void e(com.meizu.cloud.pushsdk.e.d.a aVar) {
        this.w = aVar;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void f(String str) {
        this.F = str;
    }

    public com.meizu.cloud.pushsdk.e.b.c h() {
        return com.meizu.cloud.pushsdk.e.f.c.a(this);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public com.meizu.cloud.pushsdk.e.b.c j() {
        this.f15743g = com.meizu.cloud.pushsdk.e.b.e.JSON_OBJECT;
        return com.meizu.cloud.pushsdk.e.f.c.a(this);
    }

    public com.meizu.cloud.pushsdk.e.b.c k() {
        this.f15743g = com.meizu.cloud.pushsdk.e.b.e.STRING;
        return com.meizu.cloud.pushsdk.e.f.c.a(this);
    }

    public com.meizu.cloud.pushsdk.e.d.a l() {
        return this.w;
    }

    public String m() {
        return this.o;
    }

    public String n() {
        return this.p;
    }

    public com.meizu.cloud.pushsdk.e.d.c o() {
        c.b bVar = new c.b();
        try {
            for (Map.Entry<String, String> entry : this.f15744h.entrySet()) {
                bVar.a(entry.getKey(), entry.getValue());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bVar.b();
    }

    public int p() {
        return this.a;
    }

    public j q() {
        h.a aVar = new h.a();
        aVar.b(h.f15817f);
        try {
            for (Map.Entry<String, String> entry : this.f15747k.entrySet()) {
                aVar.a(com.meizu.cloud.pushsdk.e.d.c.a(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + entry.getKey() + "\""), j.c(null, entry.getValue()));
            }
            for (Map.Entry<String, File> entry2 : this.f15750n.entrySet()) {
                if (entry2.getValue() != null) {
                    String name = entry2.getValue().getName();
                    aVar.a(com.meizu.cloud.pushsdk.e.d.c.a(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + entry2.getKey() + "\"; filename=\"" + name + "\""), j.b(g.a(com.meizu.cloud.pushsdk.e.i.b.g(name)), entry2.getValue()));
                    g gVar = this.v;
                    if (gVar != null) {
                        aVar.b(gVar);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return aVar.d();
    }

    public j r() {
        JSONObject jSONObject = this.q;
        if (jSONObject != null) {
            g gVar = this.v;
            return gVar != null ? j.c(gVar, jSONObject.toString()) : j.c(G, jSONObject.toString());
        }
        JSONArray jSONArray = this.r;
        if (jSONArray != null) {
            g gVar2 = this.v;
            return gVar2 != null ? j.c(gVar2, jSONArray.toString()) : j.c(G, jSONArray.toString());
        }
        String str = this.s;
        if (str != null) {
            g gVar3 = this.v;
            return gVar3 != null ? j.c(gVar3, str) : j.c(H, str);
        }
        File file = this.u;
        if (file != null) {
            g gVar4 = this.v;
            return gVar4 != null ? j.b(gVar4, file) : j.b(H, file);
        }
        byte[] bArr = this.t;
        if (bArr != null) {
            g gVar5 = this.v;
            return gVar5 != null ? j.d(gVar5, bArr) : j.d(H, bArr);
        }
        b.C0759b c0759b = new b.C0759b();
        try {
            for (Map.Entry<String, String> entry : this.f15745i.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    c0759b.a(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<String, String> entry2 : this.f15746j.entrySet()) {
                if (!TextUtils.isEmpty(entry2.getKey()) && !TextUtils.isEmpty(entry2.getValue())) {
                    c0759b.c(entry2.getKey(), entry2.getValue());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return c0759b.b();
    }

    public int s() {
        return this.f15740c;
    }

    public com.meizu.cloud.pushsdk.e.b.e t() {
        return this.f15743g;
    }

    public String toString() {
        return "ANRequest{sequenceNumber='" + this.f15741e + ", mMethod=" + this.a + ", mPriority=" + this.b + ", mRequestType=" + this.f15740c + ", mUrl=" + this.d + '}';
    }

    public com.meizu.cloud.pushsdk.e.e.a u() {
        return new a();
    }

    public String v() {
        String str = this.d;
        for (Map.Entry<String, String> entry : this.f15749m.entrySet()) {
            str = str.replace("{" + entry.getKey() + "}", String.valueOf(entry.getValue()));
        }
        f.b A = com.meizu.cloud.pushsdk.e.d.f.p(str).A();
        for (Map.Entry<String, String> entry2 : this.f15748l.entrySet()) {
            A.c(entry2.getKey(), entry2.getValue());
        }
        return A.d().toString();
    }

    public String w() {
        return this.F;
    }
}
