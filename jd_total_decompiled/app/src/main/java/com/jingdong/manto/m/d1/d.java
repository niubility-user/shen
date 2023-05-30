package com.jingdong.manto.m.d1;

import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.m.l0;
import com.jingdong.manto.q.n;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class d extends l0 {
    protected i b;

    /* loaded from: classes15.dex */
    class a implements n.d0 {
        final /* synthetic */ MantoLifecycleLisener a;

        a(MantoLifecycleLisener mantoLifecycleLisener) {
            this.a = mantoLifecycleLisener;
        }

        @Override // com.jingdong.manto.q.n.d0
        public void onBackground() {
            this.a.onBackground();
        }
    }

    /* loaded from: classes15.dex */
    class b implements n.c0 {
        final /* synthetic */ MantoLifecycleLisener a;

        b(MantoLifecycleLisener mantoLifecycleLisener) {
            this.a = mantoLifecycleLisener;
        }

        @Override // com.jingdong.manto.q.n.c0
        public void onDestroy() {
            this.a.onDestroy();
        }
    }

    /* loaded from: classes15.dex */
    class c implements n.f0 {
        final /* synthetic */ MantoLifecycleLisener a;

        c(MantoLifecycleLisener mantoLifecycleLisener) {
            this.a = mantoLifecycleLisener;
        }
    }

    /* renamed from: com.jingdong.manto.m.d1.d$d  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0558d implements n.g0 {
        final /* synthetic */ MantoLifecycleLisener a;

        C0558d(MantoLifecycleLisener mantoLifecycleLisener) {
            this.a = mantoLifecycleLisener;
        }

        @Override // com.jingdong.manto.q.n.g0
        public void onReady() {
            this.a.onReady();
        }
    }

    /* loaded from: classes15.dex */
    class e implements n.h0 {
        final /* synthetic */ MantoLifecycleLisener a;

        e(MantoLifecycleLisener mantoLifecycleLisener) {
            this.a = mantoLifecycleLisener;
        }

        @Override // com.jingdong.manto.q.n.h0
        public boolean a() {
            return this.a.onRemove();
        }
    }

    /* loaded from: classes15.dex */
    class f implements n.e0 {
        final /* synthetic */ MantoLifecycleLisener a;

        f(MantoLifecycleLisener mantoLifecycleLisener) {
            this.a = mantoLifecycleLisener;
        }

        @Override // com.jingdong.manto.q.n.e0
        public void onForeground() {
            this.a.onForeground();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(i iVar) {
        this.b = iVar;
    }

    public static com.jingdong.manto.jsapi.base.e a(n nVar, MantoLifecycleLisener mantoLifecycleLisener) {
        a aVar = new a(mantoLifecycleLisener);
        b bVar = new b(mantoLifecycleLisener);
        c cVar = new c(mantoLifecycleLisener);
        C0558d c0558d = new C0558d(mantoLifecycleLisener);
        e eVar = new e(mantoLifecycleLisener);
        com.jingdong.manto.jsapi.base.e eVar2 = new com.jingdong.manto.jsapi.base.e(aVar, bVar, cVar, c0558d, eVar, new f(mantoLifecycleLisener));
        nVar.a(aVar);
        nVar.a(bVar);
        nVar.a(cVar);
        nVar.a(c0558d);
        nVar.a(eVar);
        return eVar2;
    }

    public static void a(n nVar, com.jingdong.manto.jsapi.base.e eVar) {
        if (nVar == null || eVar == null) {
            return;
        }
        nVar.b(eVar.f());
        nVar.b(eVar.d());
        nVar.b(eVar.e());
        nVar.b(eVar.a());
        nVar.b(eVar.b());
        nVar.b(eVar.c());
    }

    @Override // com.jingdong.manto.m.l0
    public final String a(com.jingdong.manto.h hVar, JSONObject jSONObject) {
        return a(hVar, jSONObject, 0);
    }

    protected abstract String a(e0 e0Var, JSONObject jSONObject, int i2);

    @Override // com.jingdong.manto.m.l0
    public final String a(n nVar, JSONObject jSONObject) {
        return getCore(nVar).getActivity() == null ? putErrMsg("fail", null) : this.webAPI ? a(nVar, jSONObject, 2) : a(nVar, jSONObject, 1);
    }

    @Override // com.jingdong.manto.m.a
    public final String getJsApiName() {
        i iVar = this.b;
        if (iVar == null) {
            return null;
        }
        return iVar.a();
    }
}
