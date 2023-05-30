package com.jingdong.manto.jsapi.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d1.i;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.c;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class a extends d {
    static final ConcurrentHashMap<Integer, e> b = new ConcurrentHashMap<>();

    /* renamed from: com.jingdong.manto.jsapi.base.a$a */
    /* loaded from: classes15.dex */
    public class RunnableC0519a implements Runnable {
        final /* synthetic */ n a;
        final /* synthetic */ e0 b;

        /* renamed from: c */
        final /* synthetic */ int f13116c;
        final /* synthetic */ String d;

        /* renamed from: e */
        final /* synthetic */ JSONObject f13117e;

        /* renamed from: com.jingdong.manto.jsapi.base.a$a$a */
        /* loaded from: classes15.dex */
        class C0520a implements n.d0 {
            final /* synthetic */ MantoLifecycleLisener a;

            C0520a(RunnableC0519a runnableC0519a, MantoLifecycleLisener mantoLifecycleLisener) {
                this.a = mantoLifecycleLisener;
            }

            @Override // com.jingdong.manto.q.n.d0
            public void onBackground() {
                this.a.onBackground();
            }
        }

        /* renamed from: com.jingdong.manto.jsapi.base.a$a$b */
        /* loaded from: classes15.dex */
        class b implements n.c0 {
            final /* synthetic */ MantoLifecycleLisener a;

            b(RunnableC0519a runnableC0519a, MantoLifecycleLisener mantoLifecycleLisener) {
                this.a = mantoLifecycleLisener;
            }

            @Override // com.jingdong.manto.q.n.c0
            public void onDestroy() {
                this.a.onDestroy();
            }
        }

        /* renamed from: com.jingdong.manto.jsapi.base.a$a$c */
        /* loaded from: classes15.dex */
        class c implements n.f0 {
            final /* synthetic */ MantoLifecycleLisener a;

            c(RunnableC0519a runnableC0519a, MantoLifecycleLisener mantoLifecycleLisener) {
                this.a = mantoLifecycleLisener;
            }
        }

        /* renamed from: com.jingdong.manto.jsapi.base.a$a$d */
        /* loaded from: classes15.dex */
        class d implements n.g0 {
            final /* synthetic */ MantoLifecycleLisener a;

            d(RunnableC0519a runnableC0519a, MantoLifecycleLisener mantoLifecycleLisener) {
                this.a = mantoLifecycleLisener;
            }

            @Override // com.jingdong.manto.q.n.g0
            public void onReady() {
                this.a.onReady();
            }
        }

        /* renamed from: com.jingdong.manto.jsapi.base.a$a$e */
        /* loaded from: classes15.dex */
        class e implements n.h0 {
            final /* synthetic */ MantoLifecycleLisener a;

            e(RunnableC0519a runnableC0519a, MantoLifecycleLisener mantoLifecycleLisener) {
                this.a = mantoLifecycleLisener;
            }

            @Override // com.jingdong.manto.q.n.h0
            public boolean a() {
                return this.a.onRemove();
            }
        }

        /* renamed from: com.jingdong.manto.jsapi.base.a$a$f */
        /* loaded from: classes15.dex */
        class f implements n.e0 {
            final /* synthetic */ MantoLifecycleLisener a;

            f(RunnableC0519a runnableC0519a, MantoLifecycleLisener mantoLifecycleLisener) {
                this.a = mantoLifecycleLisener;
            }

            @Override // com.jingdong.manto.q.n.e0
            public void onForeground() {
                this.a.onForeground();
            }
        }

        /* renamed from: com.jingdong.manto.jsapi.base.a$a$g */
        /* loaded from: classes15.dex */
        public class g implements n.c0 {
            final /* synthetic */ int a;
            final /* synthetic */ c.a b;

            /* renamed from: com.jingdong.manto.jsapi.base.a$a$g$a */
            /* loaded from: classes15.dex */
            class RunnableC0521a implements Runnable {
                RunnableC0521a() {
                    g.this = r1;
                }

                @Override // java.lang.Runnable
                public void run() {
                    RunnableC0519a.this.a.n().c(g.this.a);
                }
            }

            g(int i2, c.a aVar) {
                RunnableC0519a.this = r1;
                this.a = i2;
                this.b = aVar;
            }

            @Override // com.jingdong.manto.q.n.c0
            public void onDestroy() {
                RunnableC0519a.this.a.b(this);
                com.jingdong.manto.sdk.thread.a.a(new RunnableC0521a());
                RunnableC0519a.this.a.n().e(this.a);
                this.b.a();
                System.gc();
            }
        }

        RunnableC0519a(n nVar, e0 e0Var, int i2, String str, JSONObject jSONObject) {
            a.this = r1;
            this.a = nVar;
            this.b = e0Var;
            this.f13116c = i2;
            this.d = str;
            this.f13117e = jSONObject;
        }

        /* JADX WARN: Removed duplicated region for block: B:302:0x024d A[Catch: JSONException -> 0x02d7, TRY_LEAVE, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:245:0x00d5, B:247:0x00dd, B:249:0x00e5, B:251:0x00ef, B:254:0x010b, B:257:0x0168, B:259:0x0175, B:261:0x017d, B:263:0x0188, B:292:0x022e, B:296:0x023f, B:302:0x024d, B:305:0x025e, B:307:0x0269, B:306:0x0263, B:308:0x027f, B:310:0x0283, B:312:0x028b, B:313:0x0297, B:293:0x0235, B:314:0x02a0, B:316:0x02b2, B:320:0x02c6, B:256:0x015e), top: B:326:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:310:0x0283 A[Catch: JSONException -> 0x02d7, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:245:0x00d5, B:247:0x00dd, B:249:0x00e5, B:251:0x00ef, B:254:0x010b, B:257:0x0168, B:259:0x0175, B:261:0x017d, B:263:0x0188, B:292:0x022e, B:296:0x023f, B:302:0x024d, B:305:0x025e, B:307:0x0269, B:306:0x0263, B:308:0x027f, B:310:0x0283, B:312:0x028b, B:313:0x0297, B:293:0x0235, B:314:0x02a0, B:316:0x02b2, B:320:0x02c6, B:256:0x015e), top: B:326:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:312:0x028b A[Catch: JSONException -> 0x02d7, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:245:0x00d5, B:247:0x00dd, B:249:0x00e5, B:251:0x00ef, B:254:0x010b, B:257:0x0168, B:259:0x0175, B:261:0x017d, B:263:0x0188, B:292:0x022e, B:296:0x023f, B:302:0x024d, B:305:0x025e, B:307:0x0269, B:306:0x0263, B:308:0x027f, B:310:0x0283, B:312:0x028b, B:313:0x0297, B:293:0x0235, B:314:0x02a0, B:316:0x02b2, B:320:0x02c6, B:256:0x015e), top: B:326:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:313:0x0297 A[Catch: JSONException -> 0x02d7, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:245:0x00d5, B:247:0x00dd, B:249:0x00e5, B:251:0x00ef, B:254:0x010b, B:257:0x0168, B:259:0x0175, B:261:0x017d, B:263:0x0188, B:292:0x022e, B:296:0x023f, B:302:0x024d, B:305:0x025e, B:307:0x0269, B:306:0x0263, B:308:0x027f, B:310:0x0283, B:312:0x028b, B:313:0x0297, B:293:0x0235, B:314:0x02a0, B:316:0x02b2, B:320:0x02c6, B:256:0x015e), top: B:326:0x00d3 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void run() {
            Bundle bundle;
            AbstractMantoViewManager abstractMantoViewManager;
            View a;
            String str;
            int a2;
            String str2;
            boolean z;
            boolean z2;
            boolean z3;
            float[] e2;
            int b2;
            com.jingdong.manto.q.d n2;
            Boolean valueOf;
            String replace;
            e0 e0Var;
            int i2;
            a aVar;
            String str3;
            String str4;
            if (this.a == null) {
                e0Var = this.b;
                i2 = this.f13116c;
                aVar = a.this;
                str3 = this.d;
                str4 = "fail:page is null";
            } else {
                Bundle bundle2 = new Bundle();
                i iVar = a.this.a;
                if (iVar != null) {
                    abstractMantoViewManager = (AbstractMantoViewManager) iVar.c();
                    bundle = abstractMantoViewManager.handleInsertData(a.this.getCore(this.b), this.f13117e);
                    if (bundle != null) {
                        bundle.putString("appId", this.a.a());
                        bundle.putString("appid", this.a.a());
                        bundle.putString("type", this.a.b());
                        bundle.putString("appUniqueId", this.a.c());
                        bundle.putInt("hashCode", this.a.hashCode());
                        bundle.putInt("runtimeHashCode", this.b.h().hashCode());
                        bundle.putString("path", this.a.r());
                    }
                } else {
                    bundle = bundle2;
                    abstractMantoViewManager = null;
                }
                bundle.putBoolean("full_screen", this.a.x);
                a aVar2 = a.this;
                if (abstractMantoViewManager != null) {
                    a = abstractMantoViewManager.getView(aVar2.getCore(this.b));
                    if (a == null) {
                        a = abstractMantoViewManager.getView(a.this.getCore(this.b), bundle);
                    }
                } else {
                    a = aVar2.a(this.a, this.f13117e);
                }
                if (a != null) {
                    try {
                        if (abstractMantoViewManager != null) {
                            a2 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY, 0);
                            if (a2 == 0) {
                                this.b.a(this.f13116c, a.this.putErrMsg("fail:viewID NULL", null, this.d));
                                return;
                            }
                            n pageView = c0.getPageView(this.b);
                            MantoLifecycleLisener addLifecycleLisener = a.this.a.c().addLifecycleLisener(a.this.getJsApiName(), bundle);
                            if (addLifecycleLisener == null || pageView == null) {
                                str = "baseViewDestroyListener";
                            } else {
                                C0520a c0520a = new C0520a(this, addLifecycleLisener);
                                pageView.a(c0520a);
                                b bVar = new b(this, addLifecycleLisener);
                                pageView.a(bVar);
                                c cVar = new c(this, addLifecycleLisener);
                                pageView.a(cVar);
                                d dVar = new d(this, addLifecycleLisener);
                                pageView.a(dVar);
                                e eVar = new e(this, addLifecycleLisener);
                                pageView.a(eVar);
                                str = "baseViewDestroyListener";
                                f fVar = new f(this, addLifecycleLisener);
                                pageView.a(fVar);
                                a.a(Integer.valueOf(a2), new com.jingdong.manto.jsapi.base.e(c0520a, bVar, cVar, dVar, eVar, fVar));
                                dVar.onReady();
                            }
                        } else {
                            str = "baseViewDestroyListener";
                            a2 = a.this.a(this.f13117e);
                        }
                        int i3 = a2;
                        if (this.a.n().a(i3)) {
                            this.b.a(this.f13116c, a.this.putErrMsg("fail:the view has already exist", null, this.d));
                            return;
                        }
                        int optInt = this.f13117e.optInt("parentId", 0);
                        try {
                            e2 = com.jingdong.manto.jsapi.base.d.e(this.f13117e);
                            b2 = com.jingdong.manto.jsapi.base.d.b(this.f13117e);
                            Boolean d2 = com.jingdong.manto.jsapi.base.d.d(this.f13117e);
                            n2 = this.a.n();
                            valueOf = Boolean.valueOf(d2 != null && d2.booleanValue());
                            replace = this.d.replace("insert", "");
                            str2 = "data";
                            try {
                                z = this.f13117e.optBoolean("disableMixRender", true);
                            } catch (Exception unused) {
                            }
                        } catch (Exception unused2) {
                            str2 = "data";
                        }
                        if (z) {
                            z = false;
                            z2 = n2.b(a, i3, optInt, e2, b2, valueOf.booleanValue(), com.jingdong.manto.jsapi.base.d.c(this.f13117e));
                        } else if (this.a.i(replace)) {
                            int i4 = (int) e2[2];
                            int i5 = (int) e2[3];
                            if (i4 != 0 && i5 != 0) {
                                z = false;
                                this.a.a(this.d.replace("insert", ""), i3, a, i4, i5, TextUtils.equals("Canvas", replace));
                                a.this.a(true, this.a, i3, a, this.f13117e);
                                z2 = true;
                            }
                            z = false;
                            z2 = false;
                        } else {
                            z = false;
                            a.this.a(false, this.a, i3, a, this.f13117e);
                            z2 = false;
                        }
                        if (z2) {
                            if (!(abstractMantoViewManager != null ? bundle.getBoolean("abg", z) : a.this.b()) && !this.f13117e.optBoolean("disableScroll", z)) {
                                z3 = false;
                                if (z3) {
                                    c.a a3 = this.a.n().a(i3, true);
                                    a3.b("disableScroll", true);
                                    a3.b("enableLongClick", abstractMantoViewManager != null ? bundle.getBoolean("enableLongClick", z) : a.this.c());
                                    String str5 = str2;
                                    a3.b(str5, this.f13117e.optString(str5));
                                    this.f13117e.optBoolean("gesture", z);
                                }
                                if (a instanceof CoverViewContainer) {
                                    ((CoverViewContainer) a).setDisableScroll(z3);
                                }
                                if (abstractMantoViewManager == null) {
                                    abstractMantoViewManager.onViewInsert(bundle, a, a.this.getCore(this.b));
                                } else {
                                    a.this.a(this.a, i3, a, this.f13117e);
                                }
                            }
                            z3 = true;
                            if (z3) {
                            }
                            if (a instanceof CoverViewContainer) {
                            }
                            if (abstractMantoViewManager == null) {
                            }
                        }
                        c.a a4 = this.a.n().a(i3, true);
                        String str6 = str;
                        if (a4.a(str6) == null) {
                            g gVar = new g(i3, a4);
                            a4.a(str6, gVar);
                            this.a.a(gVar);
                        }
                        this.b.a(this.f13116c, a.this.putErrMsg(z2 ? IMantoBaseModule.SUCCESS : "fail:insert view fail", null, this.d));
                        return;
                    } catch (JSONException unused3) {
                        this.b.a(this.f13116c, a.this.putErrMsg("fail:invalid view id", null, this.d));
                        return;
                    }
                }
                e0Var = this.b;
                i2 = this.f13116c;
                aVar = a.this;
                str3 = this.d;
                str4 = "inflate view failed";
            }
            e0Var.a(i2, aVar.putErrMsg(str4, null, str3));
        }
    }

    public a() {
    }

    public a(i iVar) {
        super(iVar);
    }

    private void a(e0 e0Var, int i2, n nVar, JSONObject jSONObject, String str) {
        com.jingdong.manto.sdk.thread.a.a(new RunnableC0519a(nVar, e0Var, i2, str, jSONObject));
    }

    public static void a(Integer num, e eVar) {
        b.put(num, eVar);
    }

    public static void a(Integer num, n nVar) {
        ConcurrentHashMap<Integer, e> concurrentHashMap = b;
        e eVar = concurrentHashMap.get(num);
        if (eVar != null) {
            nVar.b(eVar.b());
            nVar.b(eVar.a());
            nVar.b(eVar.e());
            nVar.b(eVar.f());
            nVar.b(eVar.d());
            nVar.b(eVar.c());
        }
        concurrentHashMap.remove(num);
    }

    public abstract View a(n nVar, JSONObject jSONObject);

    public void a(n nVar, int i2, View view, JSONObject jSONObject) {
    }

    public void a(boolean z, n nVar, int i2, View view, JSONObject jSONObject) {
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(h hVar, JSONObject jSONObject, int i2, String str) {
        super.exec(hVar, jSONObject, i2, str);
        n pageView = c0.getPageView(hVar);
        if (pageView == null) {
            hVar.a(i2, putErrMsg("fail:page is null", null, str));
        } else {
            a(hVar, i2, pageView, jSONObject, str);
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        super.exec(nVar, jSONObject, i2, str);
        a(nVar, i2, nVar, jSONObject, str);
    }
}
