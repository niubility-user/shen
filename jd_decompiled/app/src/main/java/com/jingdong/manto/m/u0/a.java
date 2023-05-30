package com.jingdong.manto.m.u0;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import com.jingdong.manto.widget.canvas.a;
import java.lang.ref.WeakReference;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a implements com.jingdong.manto.widget.canvas.a {

    /* renamed from: j */
    private static Handler f13719j = new Handler(Looper.getMainLooper());
    private com.jingdong.manto.widget.canvas.a a;

    /* renamed from: c */
    volatile boolean f13720c;
    JSONArray d;

    /* renamed from: e */
    volatile com.jingdong.manto.widget.canvas.b f13721e;

    /* renamed from: f */
    private com.jingdong.manto.m.u0.c f13722f;

    /* renamed from: h */
    public volatile boolean f13724h;
    boolean b = true;

    /* renamed from: i */
    private Runnable f13725i = new RunnableC0624a();

    /* renamed from: g */
    private com.jingdong.manto.m.u0.b f13723g = new com.jingdong.manto.m.u0.b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.u0.a$a */
    /* loaded from: classes15.dex */
    public class RunnableC0624a implements Runnable {
        RunnableC0624a() {
            a.this = r1;
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z = a.this.f13724h;
            a.this.f13724h = false;
            if (z && a.this.f13720c) {
                a.this.a.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ JSONArray a;
        final /* synthetic */ a.InterfaceC0691a b;

        b(JSONArray jSONArray, a.InterfaceC0691a interfaceC0691a) {
            a.this = r1;
            this.a = jSONArray;
            this.b = interfaceC0691a;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.jingdong.manto.widget.canvas.b bVar = a.this.f13721e;
            a aVar = a.this;
            aVar.d = this.a;
            aVar.b = true;
            aVar.f13720c = true;
            a.InterfaceC0691a interfaceC0691a = this.b;
            if (interfaceC0691a != null) {
                interfaceC0691a.a(bVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements Runnable {
        final /* synthetic */ JSONArray a;
        final /* synthetic */ a.InterfaceC0691a b;

        c(JSONArray jSONArray, a.InterfaceC0691a interfaceC0691a) {
            a.this = r1;
            this.a = jSONArray;
            this.b = interfaceC0691a;
        }

        @Override // java.lang.Runnable
        public final void run() {
            a aVar = a.this;
            aVar.b = true;
            if (aVar.d == null) {
                aVar.d = this.a;
            } else {
                for (int i2 = 0; i2 < this.a.length(); i2++) {
                    a.this.d.put(this.a.opt(i2));
                }
            }
            a.this.f13720c = true;
            a.InterfaceC0691a interfaceC0691a = this.b;
            if (interfaceC0691a != null) {
                interfaceC0691a.a(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public static class d implements com.jingdong.manto.m.u0.d {
        d(com.jingdong.manto.widget.canvas.a aVar) {
            new WeakReference(aVar);
        }
    }

    public a(com.jingdong.manto.widget.canvas.a aVar) {
        this.a = aVar;
        this.f13722f = new com.jingdong.manto.m.u0.c(new d(aVar));
    }

    private boolean a(Canvas canvas, JSONArray jSONArray) {
        this.f13720c = false;
        if (jSONArray == null || jSONArray.length() == 0) {
            return false;
        }
        this.f13722f.a();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                try {
                    this.f13723g.a(this.f13722f, canvas, optJSONObject);
                } catch (Exception unused) {
                }
            }
        }
        return true;
    }

    private boolean b(Canvas canvas) {
        this.f13722f.d = true;
        com.jingdong.manto.widget.canvas.b bVar = this.f13721e;
        this.f13720c = false;
        if (bVar == null) {
            return false;
        }
        List<com.jingdong.manto.m.u0.o.k0.c0.d> list = this.f13721e == null ? null : this.f13721e.a;
        if (list == null || list.size() == 0) {
            return false;
        }
        this.f13722f.a();
        for (com.jingdong.manto.m.u0.o.k0.c0.d dVar : list) {
            if (dVar != null) {
                try {
                    int i2 = dVar.f13760c;
                    if (i2 == 1) {
                        this.f13723g.a(this.f13722f, canvas, dVar.b);
                    } else if (i2 != 2) {
                    }
                    com.jingdong.manto.m.u0.b bVar2 = this.f13723g;
                    com.jingdong.manto.m.u0.c cVar = this.f13722f;
                    com.jingdong.manto.m.u0.o.k0.c0.a aVar = dVar.a;
                    com.jingdong.manto.m.u0.o.i iVar = bVar2.a.get(aVar.a);
                    if (iVar != null) {
                        iVar.a(cVar, canvas, aVar);
                    }
                } catch (Exception e2) {
                    try {
                        e2.printStackTrace();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        return true;
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public void a() {
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public void a(Runnable runnable) {
        if (this.a == null) {
            return;
        }
        f13719j.post(runnable);
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public void a(JSONArray jSONArray, a.InterfaceC0691a interfaceC0691a) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        this.a.a(new c(jSONArray, interfaceC0691a));
    }

    @Override // com.jingdong.manto.widget.canvas.a
    public synchronized boolean a(Canvas canvas) {
        if (!this.b) {
            return b(canvas);
        }
        this.f13722f.d = false;
        return a(canvas, this.d);
    }

    public com.jingdong.manto.m.u0.c b() {
        return this.f13722f;
    }

    public void b(JSONArray jSONArray, a.InterfaceC0691a interfaceC0691a) {
        this.a.a(new b(jSONArray, interfaceC0691a));
    }

    public void c() {
        this.a.a(this.f13725i);
    }
}
