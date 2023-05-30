package com.jingdong.manto.jsapi.base;

import android.view.View;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d1.i;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.c;
import java.util.concurrent.ConcurrentHashMap;
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

        /* JADX INFO: Access modifiers changed from: package-private */
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

        /* JADX WARN: Removed duplicated region for block: B:191:0x024d A[Catch: JSONException -> 0x02d7, TRY_LEAVE, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:134:0x00d5, B:136:0x00dd, B:138:0x00e5, B:140:0x00ef, B:143:0x010b, B:146:0x0168, B:148:0x0175, B:150:0x017d, B:152:0x0188, B:181:0x022e, B:185:0x023f, B:191:0x024d, B:194:0x025e, B:196:0x0269, B:195:0x0263, B:197:0x027f, B:199:0x0283, B:201:0x028b, B:202:0x0297, B:182:0x0235, B:203:0x02a0, B:205:0x02b2, B:209:0x02c6, B:145:0x015e), top: B:215:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:199:0x0283 A[Catch: JSONException -> 0x02d7, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:134:0x00d5, B:136:0x00dd, B:138:0x00e5, B:140:0x00ef, B:143:0x010b, B:146:0x0168, B:148:0x0175, B:150:0x017d, B:152:0x0188, B:181:0x022e, B:185:0x023f, B:191:0x024d, B:194:0x025e, B:196:0x0269, B:195:0x0263, B:197:0x027f, B:199:0x0283, B:201:0x028b, B:202:0x0297, B:182:0x0235, B:203:0x02a0, B:205:0x02b2, B:209:0x02c6, B:145:0x015e), top: B:215:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:201:0x028b A[Catch: JSONException -> 0x02d7, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:134:0x00d5, B:136:0x00dd, B:138:0x00e5, B:140:0x00ef, B:143:0x010b, B:146:0x0168, B:148:0x0175, B:150:0x017d, B:152:0x0188, B:181:0x022e, B:185:0x023f, B:191:0x024d, B:194:0x025e, B:196:0x0269, B:195:0x0263, B:197:0x027f, B:199:0x0283, B:201:0x028b, B:202:0x0297, B:182:0x0235, B:203:0x02a0, B:205:0x02b2, B:209:0x02c6, B:145:0x015e), top: B:215:0x00d3 }] */
        /* JADX WARN: Removed duplicated region for block: B:202:0x0297 A[Catch: JSONException -> 0x02d7, TryCatch #0 {JSONException -> 0x02d7, blocks: (B:134:0x00d5, B:136:0x00dd, B:138:0x00e5, B:140:0x00ef, B:143:0x010b, B:146:0x0168, B:148:0x0175, B:150:0x017d, B:152:0x0188, B:181:0x022e, B:185:0x023f, B:191:0x024d, B:194:0x025e, B:196:0x0269, B:195:0x0263, B:197:0x027f, B:199:0x0283, B:201:0x028b, B:202:0x0297, B:182:0x0235, B:203:0x02a0, B:205:0x02b2, B:209:0x02c6, B:145:0x015e), top: B:215:0x00d3 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 746
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.base.a.RunnableC0519a.run():void");
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
