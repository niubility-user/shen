package com.jingdong.manto.jsapi.base;

import android.os.Bundle;
import android.view.View;
import com.jingdong.manto.h;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.m.d1.i;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.q.n;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class b extends d {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ e0 a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ n f13120c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f13121e;

        a(e0 e0Var, JSONObject jSONObject, n nVar, int i2, String str) {
            this.a = e0Var;
            this.b = jSONObject;
            this.f13120c = nVar;
            this.d = i2;
            this.f13121e = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bundle bundle;
            AbstractMantoViewManager abstractMantoViewManager;
            int a;
            boolean z;
            try {
                Bundle bundle2 = new Bundle();
                i iVar = b.this.a;
                if (iVar != null) {
                    abstractMantoViewManager = (AbstractMantoViewManager) iVar.c();
                    bundle = abstractMantoViewManager.handleRemoveData(b.this.getCore(this.a), this.b);
                    if (bundle != null) {
                        bundle.putString("appId", this.f13120c.a());
                        bundle.putString("appUniqueId", this.f13120c.c());
                        bundle.putString("appid", this.f13120c.a());
                        bundle.putString("type", this.f13120c.b());
                        bundle.putInt("hashCode", this.f13120c.hashCode());
                        bundle.putInt("runtimeHashCode", this.a.h().hashCode());
                    }
                } else {
                    bundle = bundle2;
                    abstractMantoViewManager = null;
                }
                boolean z2 = false;
                if (abstractMantoViewManager != null) {
                    a = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY, 0);
                    if (a == 0) {
                        this.a.a(this.d, b.this.putErrMsg("fail:viewID NULL", null, this.f13121e));
                        return;
                    }
                } else {
                    a = b.this.a(this.b);
                }
                String replace = this.f13121e.replace("remove", "");
                if (!this.f13120c.i(replace) || this.f13120c.a(replace, a) == null) {
                    View c2 = this.f13120c.n().c(a);
                    if (this.f13120c.n().a(a) && (z2 = this.f13120c.n().f(a))) {
                        if (abstractMantoViewManager != null) {
                            z2 = abstractMantoViewManager.onViewRemove(bundle, c2, b.this.getCore(this.a));
                            com.jingdong.manto.jsapi.base.a.a(Integer.valueOf(a), this.f13120c);
                        } else {
                            z2 = b.this.a(this.f13120c, a, c2, this.b);
                        }
                    }
                    if (z2) {
                        this.f13120c.n().e(a);
                    }
                    z = z2;
                } else {
                    View a2 = this.f13120c.a(replace, a);
                    this.f13120c.b(replace, a);
                    if (abstractMantoViewManager != null) {
                        z = abstractMantoViewManager.onViewRemove(bundle, a2, b.this.getCore(this.a));
                        com.jingdong.manto.jsapi.base.a.a(Integer.valueOf(a), this.f13120c);
                    } else {
                        z = b.this.a(this.f13120c, a, a2, this.b);
                    }
                }
                this.a.a(this.d, b.this.putErrMsg(z ? IMantoBaseModule.SUCCESS : "fail", null, this.f13121e));
            } catch (JSONException unused) {
                this.a.a(this.d, b.this.putErrMsg("fail:view id do not exist", null, this.f13121e));
            }
        }
    }

    public b() {
    }

    public b(i iVar) {
        super(iVar);
    }

    private void a(e0 e0Var, int i2, n nVar, JSONObject jSONObject, String str) {
        com.jingdong.manto.sdk.thread.a.a(new a(e0Var, jSONObject, nVar, i2, str));
    }

    public boolean a(n nVar, int i2, View view, JSONObject jSONObject) {
        return true;
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
    public final void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        super.exec(nVar, jSONObject, i2, str);
        a(nVar, i2, nVar, jSONObject, str);
    }
}
