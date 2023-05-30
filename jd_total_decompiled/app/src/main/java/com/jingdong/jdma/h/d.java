package com.jingdong.jdma.h;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.jdma.bean.e.f;
import com.jingdong.jdma.common.utils.i;
import com.jingdong.jdma.common.utils.l;
import com.jingdong.jdma.common.utils.n;
import com.jingdong.jdma.common.utils.o;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class d {

    /* renamed from: e */
    private static d f12763e;
    private c a;

    /* renamed from: c */
    private String f12764c;
    private AtomicBoolean d = new AtomicBoolean(true);
    private e b = new e();

    /* loaded from: classes12.dex */
    public class a implements Runnable {
        final /* synthetic */ Context a;

        a(Context context) {
            d.this = r1;
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.a(this.a);
            d.this.b(this.a);
        }
    }

    /* loaded from: classes12.dex */
    public class b extends com.jingdong.jdma.h.b {

        /* renamed from: e */
        final /* synthetic */ Context f12765e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, String str2, String str3, String str4, Context context) {
            super(str, str2, str3, str4);
            d.this = r1;
            this.f12765e = context;
        }

        @Override // com.jingdong.jdma.h.b
        public void a(String str) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("code") && jSONObject.optInt("code") == 0) {
                    if (this.f12765e != null && !TextUtils.isEmpty(str)) {
                        l.a(this.f12765e).b("statisticstrategy", str);
                    }
                    d.this.b.k(str);
                    if (d.this.a != null) {
                        d.this.a.a();
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.jingdong.jdma.h.b
        public void b() {
        }
    }

    /* loaded from: classes12.dex */
    public interface c {
        void a();

        void b();
    }

    private d() {
    }

    public static d e() {
        if (f12763e == null) {
            synchronized (d.class) {
                if (f12763e == null) {
                    f12763e = new d();
                }
            }
        }
        return f12763e;
    }

    public int c(String str) {
        return this.b.c(str);
    }

    public String d() {
        return this.b.d();
    }

    public void f(String str) {
        this.b.m(str);
    }

    public void g(String str) {
        this.b.n(str);
    }

    public synchronized void h(String str) {
        this.b.o(str);
    }

    public void i(String str) {
        this.b.p(str);
    }

    public void j(String str) {
        this.b.q(str);
    }

    public void k(String str) {
        this.b.r(str);
    }

    public void b(Context context) {
        if (i.a(context)) {
            c cVar = this.a;
            if (cVar != null) {
                cVar.b();
            }
            if ((this.d.getAndSet(false) && n.a().c()) || com.jingdong.jdma.common.utils.c.f12678h) {
                return;
            }
            String y = this.b.h().y();
            o.a().a(new b(this.b.i(), this.f12764c, y, com.jingdong.jdma.common.utils.c.w, context));
        }
    }

    public com.jingdong.jdma.bean.e.b c() {
        return this.b.c();
    }

    public boolean d(String str) {
        return this.b.d(str);
    }

    public com.jingdong.jdma.bean.e.d f() {
        return this.b.e();
    }

    public int g() {
        return this.b.f();
    }

    public String h() {
        return this.b.g();
    }

    public f i() {
        return this.b.h();
    }

    public void j() {
        this.b.j();
    }

    public void a(Context context, String str, c cVar) {
        this.f12764c = str;
        this.a = cVar;
        com.jingdong.jdma.common.utils.c.o = this.b.h().x() * 1000;
        o.a().a(new a(context));
    }

    public void a(Context context) {
        String a2 = l.a(context).a("statisticstrategy", "");
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        this.b.k(a2);
    }

    public void e(String str) {
        this.b.l(str);
    }

    public int a(String str) {
        return this.b.a(str);
    }

    public com.jingdong.jdma.bean.e.a b() {
        return this.b.b();
    }

    public boolean a() {
        return this.b.b().c();
    }

    public int b(String str) {
        return this.b.b(str);
    }
}
