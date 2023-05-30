package com.vivo.push.util;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes11.dex */
public final class y implements d {
    private static final HashMap<String, Integer> a = new HashMap<>();
    private static final HashMap<String, Long> b = new HashMap<>();

    /* renamed from: c */
    private static final HashMap<String, String> f18313c = new HashMap<>();
    private static y d;

    /* renamed from: e */
    private Context f18314e;

    /* renamed from: f */
    private d f18315f;

    /* renamed from: g */
    private boolean f18316g;

    private y(Context context) {
        this.f18316g = false;
        this.f18314e = context;
        this.f18316g = a(context);
        p.d("SystemCache", "init status is " + this.f18316g + ";  curCache is " + this.f18315f);
    }

    public static synchronized y b(Context context) {
        y yVar;
        synchronized (y.class) {
            if (d == null) {
                d = new y(context.getApplicationContext());
            }
            yVar = d;
        }
        return yVar;
    }

    public final void a() {
        x xVar = new x();
        if (xVar.a(this.f18314e)) {
            xVar.a();
            p.d("SystemCache", "sp cache is cleared");
        }
    }

    @Override // com.vivo.push.util.d
    public final void b(String str, String str2) {
        d dVar;
        f18313c.put(str, str2);
        if (!this.f18316g || (dVar = this.f18315f) == null) {
            return;
        }
        dVar.b(str, str2);
    }

    @Override // com.vivo.push.util.d
    public final boolean a(Context context) {
        v vVar = new v();
        this.f18315f = vVar;
        boolean a2 = vVar.a(context);
        if (!a2) {
            x xVar = new x();
            this.f18315f = xVar;
            a2 = xVar.a(context);
        }
        if (!a2) {
            this.f18315f = null;
        }
        return a2;
    }

    @Override // com.vivo.push.util.d
    public final String a(String str, String str2) {
        d dVar;
        String str3 = f18313c.get(str);
        return (str3 != null || (dVar = this.f18315f) == null) ? str3 : dVar.a(str, str2);
    }
}
