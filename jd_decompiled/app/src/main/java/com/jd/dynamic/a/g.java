package com.jd.dynamic.a;

import android.view.View;
import com.jd.dynamic.a.a.a.k;
import com.jd.dynamic.a.a.a.l;
import com.jd.dynamic.a.a.a.o;
import com.jd.dynamic.a.a.a.p;
import com.jd.dynamic.a.a.a.q;
import com.jd.dynamic.a.a.a.r;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.engine.jni.JSCBridge;
import com.jd.dynamic.engine.jni.JSCException;
import com.jd.dynamic.engine.jni.JavaScriptRuntime;
import com.jd.dynamic.lib.utils.m;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class g implements JSCBridge.a {
    private j a;
    private DynamicTemplateEngine b;

    /* renamed from: c  reason: collision with root package name */
    private JSCBridge f1711c;
    public Object d;

    /* renamed from: e  reason: collision with root package name */
    public View f1712e;

    /* renamed from: f  reason: collision with root package name */
    protected HashMap<String, com.jd.dynamic.a.a.a.h> f1713f = new HashMap<>();

    /* loaded from: classes13.dex */
    class a implements com.jd.dynamic.a.a.b {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        a(DynamicTemplateEngine dynamicTemplateEngine, String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // com.jd.dynamic.a.a.b
        public void a(String str, Exception exc) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_JS, "js event exception -> " + str, this.a, this.b, "preprocess".equals(str) ? R2.attr.miaoShaPriceTextColor : R2.attr.miaoShaPriceTextSize, exc, g.this.b == null ? null : m.q(g.this.b.getZipVersion(), null));
        }
    }

    public g(DynamicTemplateEngine dynamicTemplateEngine, String str, String str2, boolean z) {
        this.b = dynamicTemplateEngine;
        if (!com.jd.dynamic.b.a.b.o().n() || dynamicTemplateEngine == null || dynamicTemplateEngine.getActivity() == null) {
            this.a = j.t();
        } else {
            this.a = j.r(DynamicSdk.getDriver().getContextGroup(dynamicTemplateEngine.getActivity()));
            DynamicSdk.getDriver().cacheContextGroup(dynamicTemplateEngine.getActivity(), this.a.b);
            DynamicSdk.getDriver().cacheContext(dynamicTemplateEngine.getActivity(), this.a.a);
        }
        this.f1711c = new JSCBridge(this.a.u(), this, z);
        JSCException.addJSContextExceptionCallback(this.a, new a(dynamicTemplateEngine, str, str2));
        f(dynamicTemplateEngine);
    }

    private void f(DynamicTemplateEngine dynamicTemplateEngine) {
        g(new com.jd.dynamic.a.a.a.e(dynamicTemplateEngine.getActivity()));
        g(new com.jd.dynamic.a.a.a.f(dynamicTemplateEngine));
        g(new k());
        g(new com.jd.dynamic.a.a.a.j());
        g(new l());
        g(new q());
        g(new r());
        g(new p());
        g(new com.jd.dynamic.a.a.a.m());
        g(new com.jd.dynamic.a.a.a.d(dynamicTemplateEngine));
        g(new com.jd.dynamic.a.a.a.c(dynamicTemplateEngine));
        g(new com.jd.dynamic.a.a.a.b(dynamicTemplateEngine));
        g(new com.jd.dynamic.a.a.a.a(dynamicTemplateEngine));
        g(new o(dynamicTemplateEngine, this));
        g(new com.jd.dynamic.a.a.a.g(dynamicTemplateEngine, this));
    }

    @Override // com.jd.dynamic.engine.jni.JSCBridge.a
    public Object a(String str, String str2, Object... objArr) {
        com.jd.dynamic.a.a.a.h hVar = this.f1713f.get(str);
        if (hVar != null) {
            return hVar.a(this, str2, objArr);
        }
        return null;
    }

    public DynamicTemplateEngine b() {
        return this.b;
    }

    public i d(String str) {
        return this.a.b(str);
    }

    public Object e(String str, String str2, View view) {
        if (m.T(h())) {
            return null;
        }
        return h.b(h(), JSCBridge.evalEvents(h(), str.trim(), str2, view == null ? -1 : view.getId(), com.jd.dynamic.b.a.b.o().h()));
    }

    public void g(com.jd.dynamic.a.a.a.h hVar) {
        if (hVar == null) {
            return;
        }
        this.f1713f.put(hVar.a(), hVar);
    }

    public long h() {
        return this.a.u();
    }

    public void i() {
        this.f1713f.clear();
        this.f1711c.release();
        JSCException.release(h());
        JavaScriptRuntime.b(h());
        j jVar = this.a;
        if (jVar != null) {
            jVar.q();
        }
    }

    public void j() {
        this.f1711c.resetData();
    }
}
