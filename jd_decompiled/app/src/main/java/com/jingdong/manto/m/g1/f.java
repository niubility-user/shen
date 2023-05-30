package com.jingdong.manto.m.g1;

import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.api.IPickerInterface;
import com.jingdong.manto.widget.input.x;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class f {

    /* renamed from: e  reason: collision with root package name */
    private static Reference<com.jingdong.manto.widget.k.f> f13359e = new WeakReference(null);
    private Reference<MantoResultCallBack> a;
    private Reference<AbstractMantoModule> b;

    /* renamed from: c  reason: collision with root package name */
    private Reference<IPickerInterface> f13360c;
    public final MantoLifecycleLisener d = new a(this);

    /* loaded from: classes15.dex */
    class a implements MantoLifecycleLisener {
        a(f fVar) {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onBackground() {
            com.jingdong.manto.widget.k.f fVar = (com.jingdong.manto.widget.k.f) f.f13359e.get();
            if (fVar != null) {
                fVar.a();
            }
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onDestroy() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onForeground() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onPause() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onReady() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public boolean onRemove() {
            com.jingdong.manto.widget.k.f fVar = (com.jingdong.manto.widget.k.f) f.f13359e.get();
            if (fVar != null) {
                fVar.a();
                return false;
            }
            return false;
        }
    }

    /* loaded from: classes15.dex */
    class b implements x.c {
        final /* synthetic */ com.jingdong.manto.widget.k.f a;

        b(f fVar, com.jingdong.manto.widget.k.f fVar2) {
            this.a = fVar2;
        }

        @Override // com.jingdong.manto.widget.input.x.c
        public void a() {
            this.a.b();
        }
    }

    public static void b() {
        f13359e.clear();
    }

    static com.jingdong.manto.widget.k.a c() {
        com.jingdong.manto.widget.k.f fVar = f13359e.get();
        if (fVar != null) {
            return fVar.getCurPicker();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.jingdong.manto.widget.k.a a(Class cls) {
        com.jingdong.manto.widget.k.a c2 = c();
        if (cls.isInstance(c2)) {
            return c2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.jingdong.manto.widget.k.f a(MantoCore mantoCore) {
        if (f13359e.get() != null) {
            return f13359e.get();
        }
        x a2 = x.a(mantoCore.getActivity());
        if (a2 == null) {
            return null;
        }
        com.jingdong.manto.widget.k.f a3 = com.jingdong.manto.widget.k.f.a(a2);
        if (a3 == null) {
            a3 = new com.jingdong.manto.widget.k.f(com.jingdong.a.g(), this.f13360c.get());
            b bVar = new b(this, a3);
            a2.a(a3, true);
            a2.f14496f.add(bVar);
        }
        f13359e = new WeakReference(a3);
        return a3;
    }

    abstract void a(Bundle bundle, MantoCore mantoCore);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(MantoCore mantoCore, String str, JSONObject jSONObject, int[] iArr) {
        AbstractMantoModule abstractMantoModule = this.b.get();
        if (abstractMantoModule != null) {
            abstractMantoModule.dispatchEventToPage(mantoCore, str, jSONObject, iArr);
        }
    }

    public void a(AbstractMantoModule abstractMantoModule, IPickerInterface iPickerInterface, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        this.a = new SoftReference(mantoResultCallBack);
        this.b = new SoftReference(abstractMantoModule);
        this.f13360c = new SoftReference(iPickerInterface);
        a(bundle, mantoCore);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, Bundle bundle) {
        MantoResultCallBack mantoResultCallBack = this.a.get();
        if (mantoResultCallBack != null) {
            if (IMantoBaseModule.SUCCESS.equals(str)) {
                mantoResultCallBack.onSuccess(bundle);
            } else if (!"fail".equals(str) && "cancel".equals(str)) {
                mantoResultCallBack.onCancel(bundle);
            } else {
                mantoResultCallBack.onFailed(bundle);
            }
        }
    }
}
