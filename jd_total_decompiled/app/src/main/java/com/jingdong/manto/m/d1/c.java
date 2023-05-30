package com.jingdong.manto.m.d1;

import android.os.Bundle;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoUtils;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class c extends d0 {
    protected i a;

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

    /* renamed from: com.jingdong.manto.m.d1.c$c  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0557c implements n.f0 {
        final /* synthetic */ MantoLifecycleLisener a;

        C0557c(MantoLifecycleLisener mantoLifecycleLisener) {
            this.a = mantoLifecycleLisener;
        }
    }

    /* loaded from: classes15.dex */
    class d implements n.g0 {
        final /* synthetic */ MantoLifecycleLisener a;

        d(MantoLifecycleLisener mantoLifecycleLisener) {
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
    public c(i iVar) {
        this.a = iVar;
    }

    public static com.jingdong.manto.jsapi.base.e a(n nVar, MantoLifecycleLisener mantoLifecycleLisener) {
        a aVar = new a(mantoLifecycleLisener);
        b bVar = new b(mantoLifecycleLisener);
        C0557c c0557c = new C0557c(mantoLifecycleLisener);
        d dVar = new d(mantoLifecycleLisener);
        e eVar = new e(mantoLifecycleLisener);
        f fVar = new f(mantoLifecycleLisener);
        com.jingdong.manto.jsapi.base.e eVar2 = new com.jingdong.manto.jsapi.base.e(aVar, bVar, c0557c, dVar, eVar, fVar);
        nVar.a(aVar);
        nVar.a(bVar);
        nVar.a(c0557c);
        nVar.a(dVar);
        nVar.a(eVar);
        nVar.a(fVar);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map a(Bundle bundle) {
        return MantoUtils.formatBundle(bundle);
    }

    protected abstract void a(e0 e0Var, JSONObject jSONObject, int i2, int i3, String str);

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        a(hVar, jSONObject, i2, 0, str);
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        a(nVar, jSONObject, i2, this.webAPI ? 2 : 1, str);
    }

    @Override // com.jingdong.manto.m.a
    public final String getJsApiName() {
        i iVar = this.a;
        if (iVar == null) {
            return null;
        }
        return iVar.a();
    }
}
