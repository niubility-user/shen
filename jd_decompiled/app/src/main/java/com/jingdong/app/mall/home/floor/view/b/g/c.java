package com.jingdong.app.mall.home.floor.view.b.g;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.LadySecKillTitle;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes4.dex */
public class c {
    private com.jingdong.app.mall.home.floor.view.b.g.b b;

    /* renamed from: c  reason: collision with root package name */
    private String f9772c;
    private com.jingdong.app.mall.home.x.b d;

    /* renamed from: e  reason: collision with root package name */
    private com.jingdong.app.mall.home.x.c f9773e;

    /* renamed from: g  reason: collision with root package name */
    private int f9775g;

    /* renamed from: h  reason: collision with root package name */
    private int f9776h;
    private String a = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));

    /* renamed from: f  reason: collision with root package name */
    private boolean f9774f = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.CustomOnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f9777g;

        a(boolean z) {
            this.f9777g = z;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            c.this.f9774f = false;
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                return;
            }
            if (this.f9777g) {
                c.this.b.E0(fastJsonObject);
            } else {
                c.this.c(fastJsonObject, true);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            c.this.f9774f = false;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.x.c {
        final /* synthetic */ LadySecKillTitle b;

        b(LadySecKillTitle ladySecKillTitle) {
            this.b = ladySecKillTitle;
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void a() {
            this.b.w("00", "00", "00");
            c.this.g();
        }

        @Override // com.jingdong.app.mall.home.x.c
        public void c(long j2, com.jingdong.app.mall.home.x.e eVar) {
            if (eVar == null) {
                return;
            }
            try {
                String a = eVar.a();
                String b = eVar.b();
                String c2 = eVar.c();
                LadySecKillTitle ladySecKillTitle = this.b;
                if (a.length() <= 1) {
                    a = "0" + a;
                }
                if (b.length() <= 1) {
                    b = "0" + b;
                }
                if (c2.length() <= 1) {
                    c2 = "0" + c2;
                }
                ladySecKillTitle.w(a, b, c2);
                c.this.f(eVar);
            } catch (Exception e2) {
                if (Log.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public c(com.jingdong.app.mall.home.floor.view.b.g.b bVar) {
        this.b = bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0149 A[Catch: Exception -> 0x01a2, TryCatch #2 {Exception -> 0x01a2, blocks: (B:8:0x000e, B:12:0x003f, B:14:0x0071, B:15:0x00a1, B:17:0x00ab, B:22:0x00b7, B:24:0x00d2, B:26:0x00d8, B:49:0x013f, B:53:0x014b, B:54:0x0158, B:56:0x015e, B:61:0x016e, B:62:0x0172, B:52:0x0149, B:27:0x00e3, B:29:0x00e9, B:31:0x00ed, B:38:0x0113, B:40:0x0119, B:43:0x0122, B:45:0x0128, B:46:0x0131, B:47:0x0134, B:37:0x0110, B:48:0x013d, B:32:0x00f1, B:34:0x0102), top: B:82:0x000e, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x015e A[Catch: Exception -> 0x01a2, TryCatch #2 {Exception -> 0x01a2, blocks: (B:8:0x000e, B:12:0x003f, B:14:0x0071, B:15:0x00a1, B:17:0x00ab, B:22:0x00b7, B:24:0x00d2, B:26:0x00d8, B:49:0x013f, B:53:0x014b, B:54:0x0158, B:56:0x015e, B:61:0x016e, B:62:0x0172, B:52:0x0149, B:27:0x00e3, B:29:0x00e9, B:31:0x00ed, B:38:0x0113, B:40:0x0119, B:43:0x0122, B:45:0x0128, B:46:0x0131, B:47:0x0134, B:37:0x0110, B:48:0x013d, B:32:0x00f1, B:34:0x0102), top: B:82:0x000e, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x018b A[Catch: Exception -> 0x019f, TRY_LEAVE, TryCatch #1 {Exception -> 0x019f, blocks: (B:64:0x0179, B:65:0x017e, B:67:0x018b), top: B:80:0x0179 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0179 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(com.jd.framework.json.JDJSONObject r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.b.g.c.c(com.jd.framework.json.JDJSONObject, boolean):boolean");
    }

    public void d(boolean z) {
        if (this.f9774f) {
            return;
        }
        this.f9774f = true;
        com.jingdong.app.mall.home.n.h.h.a(this.f9772c, new a(z), z);
    }

    public void e(JDJSONObject jDJSONObject) {
        if (c(jDJSONObject, false)) {
            return;
        }
        d(false);
    }

    public void f(com.jingdong.app.mall.home.x.e eVar) {
        int W = this.b.W();
        int i2 = W / 60;
        int i3 = W % 60;
        JDJSONObject Y = this.b.Y();
        if (eVar.d == 0 && eVar.f11088e == i2 && eVar.f11089f == i3 && Y == null) {
            d(true);
        }
    }

    public void g() {
        JDJSONObject Y = this.b.Y();
        if (Y != null) {
            if (!c(Y, true)) {
                d(false);
            }
            this.b.N();
            return;
        }
        d(false);
    }

    public void h(String str, int i2) {
        com.jingdong.app.mall.home.o.a.f.x0("OperateWordData", str + "|" + this.a + "|" + (i2 + this.f9776h));
    }

    public void i(LadySecKillTitle ladySecKillTitle) {
        if (com.jingdong.app.mall.home.floor.common.i.g.k()) {
            com.jingdong.app.mall.home.x.b bVar = this.d;
            if (bVar != null) {
                bVar.g(this.f9773e);
            }
            ladySecKillTitle.x(true);
            return;
        }
        ladySecKillTitle.x(false);
        b.a S = this.b.S();
        long abs = (Math.abs(S.c()) * 1000) - S.b();
        if (abs <= 0) {
            ladySecKillTitle.w("00", "00", "00");
            g();
            return;
        }
        try {
            this.d = com.jingdong.app.mall.home.x.g.b().f(S.a(), abs);
            if (this.f9773e == null) {
                this.f9773e = new b(ladySecKillTitle);
            }
            com.jingdong.app.mall.home.x.b bVar2 = this.d;
            if (bVar2 != null) {
                bVar2.a(this.f9773e);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
