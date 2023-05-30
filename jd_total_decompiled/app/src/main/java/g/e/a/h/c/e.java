package g.e.a.h.c;

import android.content.Context;
import g.e.a.g;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public class e extends g.e.a.h.a {

    /* renamed from: c */
    private final Context f19460c;
    private final String d;

    /* renamed from: e */
    private g.e.a.h.b f19461e;

    /* renamed from: f */
    private volatile f f19462f;

    /* renamed from: g */
    private final Object f19463g = new Object();

    /* renamed from: h */
    private g.e.a.b f19464h = g.e.a.b.b;

    /* renamed from: i */
    private final Map<String, String> f19465i = new HashMap();

    /* renamed from: j */
    private volatile g f19466j;

    public e(Context context, String str) {
        this.f19460c = context;
        this.d = str;
    }

    private static String d(String str) {
        int i2 = 0;
        if (str.length() > 0) {
            while (str.charAt(i2) == '/') {
                i2++;
            }
        }
        return '/' + str.substring(i2);
    }

    private void e() {
        if (this.f19462f == null) {
            synchronized (this.f19463g) {
                if (this.f19462f == null) {
                    g.e.a.h.b bVar = this.f19461e;
                    if (bVar != null) {
                        this.f19462f = new j(bVar.b(), "UTF-8");
                        this.f19461e.a();
                        throw null;
                    }
                    this.f19462f = new m(this.f19460c, this.d);
                    this.f19466j = new g(this.f19462f);
                }
                g();
            }
        }
    }

    private String f(String str) {
        g.a aVar;
        Map<String, g.a> a = g.e.a.g.a();
        if (a.containsKey(str) && (aVar = a.get(str)) != null) {
            return aVar.a(this);
        }
        return null;
    }

    private void g() {
        if (this.f19464h != g.e.a.b.b || this.f19462f == null) {
            return;
        }
        this.f19464h = b.f(this.f19462f.a("/region", null), this.f19462f.a("/agcgw/url", null));
    }

    @Override // g.e.a.e
    public g.e.a.b a() {
        if (this.f19464h == null) {
            this.f19464h = g.e.a.b.b;
        }
        g.e.a.b bVar = this.f19464h;
        g.e.a.b bVar2 = g.e.a.b.b;
        if (bVar == bVar2 && this.f19462f == null) {
            e();
        }
        g.e.a.b bVar3 = this.f19464h;
        return bVar3 == null ? bVar2 : bVar3;
    }

    @Override // g.e.a.e
    public Context getContext() {
        return this.f19460c;
    }

    @Override // g.e.a.e
    public String getIdentifier() {
        return "DEFAULT_INSTANCE";
    }

    @Override // g.e.a.e
    public String getString(String str) {
        return h(str, null);
    }

    public String h(String str, String str2) {
        if (str != null) {
            if (this.f19462f == null) {
                e();
            }
            String d = d(str);
            String str3 = this.f19465i.get(d);
            if (str3 != null) {
                return str3;
            }
            String f2 = f(d);
            if (f2 != null) {
                return f2;
            }
            String a = this.f19462f.a(d, str2);
            return g.c(a) ? this.f19466j.a(a, str2) : a;
        }
        throw new NullPointerException("path must not be null.");
    }
}
