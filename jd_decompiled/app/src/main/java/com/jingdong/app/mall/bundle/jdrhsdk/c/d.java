package com.jingdong.app.mall.bundle.jdrhsdk.c;

import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;

/* loaded from: classes2.dex */
public class d {
    private static d a;

    /* loaded from: classes2.dex */
    class a implements com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> {
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c a;

        a(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar) {
            this.a = cVar;
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        public void a(JDRiskHandleError jDRiskHandleError) {
            d.this.c(this.a, jDRiskHandleError);
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void a(com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
            d.this.d(this.a, bVar);
        }
    }

    /* loaded from: classes2.dex */
    class b implements com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> {
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c a;

        b(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar) {
            this.a = cVar;
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        public void a(JDRiskHandleError jDRiskHandleError) {
            d.this.c(this.a, jDRiskHandleError);
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void a(com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
            d.this.d(this.a, bVar);
        }
    }

    /* loaded from: classes2.dex */
    class c implements com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> {
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c a;

        c(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar) {
            this.a = cVar;
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        public void a(JDRiskHandleError jDRiskHandleError) {
            d.this.c(this.a, jDRiskHandleError);
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.c.c
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public void a(com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
            d.this.d(this.a, bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.bundle.jdrhsdk.c.d$d  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class RunnableC0251d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c f8160g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.b f8161h;

        RunnableC0251d(d dVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar, com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
            this.f8160g = cVar;
            this.f8161h = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar = this.f8160g;
            if (cVar != null) {
                cVar.a((com.jingdong.app.mall.bundle.jdrhsdk.c.c) this.f8161h);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.bundle.jdrhsdk.c.c f8162g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ JDRiskHandleError f8163h;

        e(d dVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar, JDRiskHandleError jDRiskHandleError) {
            this.f8162g = cVar;
            this.f8163h = jDRiskHandleError;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar = this.f8162g;
            if (cVar != null) {
                cVar.a(this.f8163h);
            }
        }
    }

    public static d a() {
        d dVar;
        d dVar2 = a;
        if (dVar2 != null) {
            return dVar2;
        }
        synchronized (d.class) {
            if (a == null) {
                a = new d();
            }
            dVar = a;
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar, JDRiskHandleError jDRiskHandleError) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.c.c().post(new e(this, cVar, jDRiskHandleError));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar, com.jingdong.app.mall.bundle.jdrhsdk.c.b bVar) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.c.c().post(new RunnableC0251d(this, cVar, bVar));
    }

    public void b(int i2, String str, String str2, String str3, String str4) {
        com.jingdong.app.mall.bundle.jdrhsdk.c.a.h().e(i2, str, str2, str3, str4);
    }

    public void g(com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar) {
        try {
            com.jingdong.app.mall.bundle.jdrhsdk.c.a.h().f(eVar, new b(cVar));
        } catch (Exception unused) {
            if (cVar != null) {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(-1001);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_JAVA_EXCEPTION);
                c(cVar, jDRiskHandleError);
            }
        }
    }

    public void h(com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c<com.jingdong.app.mall.bundle.jdrhsdk.c.b> cVar) {
        try {
            com.jingdong.app.mall.bundle.jdrhsdk.c.a.h().i(eVar, new a(cVar));
        } catch (Exception unused) {
            if (cVar != null) {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(-1001);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_JAVA_EXCEPTION);
                c(cVar, jDRiskHandleError);
            }
        }
    }

    public void i(com.jingdong.app.mall.bundle.jdrhsdk.c.e eVar, com.jingdong.app.mall.bundle.jdrhsdk.c.c cVar) {
        try {
            com.jingdong.app.mall.bundle.jdrhsdk.c.a.h().j(eVar, new c(cVar));
        } catch (Exception unused) {
            if (cVar != null) {
                JDRiskHandleError jDRiskHandleError = new JDRiskHandleError();
                jDRiskHandleError.setCode(-1001);
                jDRiskHandleError.setMsg(JDRiskHandleError.MSG_JAVA_EXCEPTION);
                c(cVar, jDRiskHandleError);
            }
        }
    }
}
