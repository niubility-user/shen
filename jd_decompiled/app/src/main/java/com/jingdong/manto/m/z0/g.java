package com.jingdong.manto.m.z0;

import android.view.View;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.input.j;
import com.jingdong.manto.widget.input.k;
import com.jingdong.manto.widget.input.o;
import com.jingdong.manto.widget.input.u;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class g extends com.jingdong.manto.m.z0.b<com.jingdong.manto.widget.input.a0.f> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements com.jingdong.manto.widget.input.z.g {
        final /* synthetic */ j a;
        final /* synthetic */ n b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13836c;

        a(j jVar, String str, n nVar, int i2) {
            this.a = jVar;
            this.b = nVar;
            this.f13836c = i2;
        }

        @Override // com.jingdong.manto.widget.input.z.g
        public void a(String str, int i2) {
            int m2 = this.a.m();
            if (this.a.c() == null) {
                return;
            }
            h hVar = new h();
            HashMap hashMap = new HashMap();
            hashMap.put("value", MantoUtils.replaceChangeLineCharacter(str));
            String b = com.jingdong.manto.m.z0.b.b(m2);
            MantoLog.i("JsApiShowKeyboard", "onValueChange: get passing data:" + b);
            hashMap.put("data", b);
            hashMap.put("cursor", Integer.valueOf(i2));
            hashMap.put("inputId", Integer.valueOf(m2));
            hashMap.put("keyCode", Integer.valueOf(this.a.c().getLastKeyPressed()));
            hVar.a(this.b.h(), this.f13836c).a(hashMap).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ WeakReference a;
        final /* synthetic */ j b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.widget.input.a0.f f13837c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13838e;

        b(WeakReference weakReference, j jVar, com.jingdong.manto.widget.input.a0.f fVar, int i2, int i3) {
            this.a = weakReference;
            this.b = jVar;
            this.f13837c = fVar;
            this.d = i2;
            this.f13838e = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            n nVar = (n) this.a.get();
            if (nVar == null || nVar.s() == null) {
                return;
            }
            u.a().a(nVar.s());
            this.b.a(nVar, this.f13837c, this.d, this.f13838e);
        }
    }

    /* loaded from: classes15.dex */
    class c implements Runnable {
        final /* synthetic */ n a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13839c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ int f13840e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f13841f;

        c(n nVar, int i2, int i3, int i4, int i5, String str) {
            this.a = nVar;
            this.b = i2;
            this.f13839c = i3;
            this.d = i4;
            this.f13840e = i5;
            this.f13841f = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            n nVar;
            int i2;
            g gVar;
            String str;
            String str2;
            if (o.a(this.a, this.b, this.f13839c, this.d)) {
                nVar = this.a;
                i2 = this.f13840e;
                gVar = g.this;
                str = this.f13841f;
                str2 = IMantoBaseModule.SUCCESS;
            } else {
                nVar = this.a;
                i2 = this.f13840e;
                gVar = g.this;
                str = this.f13841f;
                str2 = "fail:invalid data";
            }
            nVar.a(i2, gVar.putErrMsg(str2, null, str));
        }
    }

    /* loaded from: classes15.dex */
    class d implements Runnable {
        final /* synthetic */ n a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13843c;
        final /* synthetic */ JSONObject d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.widget.input.a0.f f13844e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ int f13845f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f13846g;

        d(n nVar, int i2, int i3, JSONObject jSONObject, com.jingdong.manto.widget.input.a0.f fVar, int i4, String str) {
            this.a = nVar;
            this.b = i2;
            this.f13843c = i3;
            this.d = jSONObject;
            this.f13844e = fVar;
            this.f13845f = i4;
            this.f13846g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.a(g.this, this.a, this.b, this.f13843c, this.d.optString("data"), this.f13844e, this.f13845f, this.f13846g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class e implements j.o {
        final /* synthetic */ j a;

        e(g gVar, j jVar) {
            this.a = jVar;
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x0063, code lost:
            if (r6.toLowerCase().contains("redmi") != false) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:28:0x006a  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x006d  */
        @Override // com.jingdong.manto.widget.input.j.o
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(boolean r10) {
            /*
                r9 = this;
                if (r10 == 0) goto La3
                com.jingdong.manto.widget.input.j r10 = r9.a
                int r10 = r10.m()
                com.jingdong.manto.q.n r0 = com.jingdong.manto.m.z0.b.a(r10)
                if (r0 == 0) goto La3
                boolean r1 = r0.f()
                if (r1 == 0) goto La3
                com.jingdong.manto.m.z0.g$f r1 = new com.jingdong.manto.m.z0.g$f
                r2 = 0
                r1.<init>(r2)
                com.jingdong.manto.widget.input.j r2 = r9.a
                android.view.View r2 = r2.a()
                int r2 = r2.getHeight()
                r3 = 1
                r4 = 0
                java.lang.String r5 = com.jingdong.manto.utils.q.b()     // Catch: java.lang.Throwable -> L71
                java.lang.String r6 = com.jingdong.manto.utils.q.d()     // Catch: java.lang.Throwable -> L71
                java.lang.String r7 = "redmi7"
                if (r5 == 0) goto L3c
                java.lang.String r8 = r5.toLowerCase()     // Catch: java.lang.Throwable -> L71
                boolean r8 = r8.contains(r7)     // Catch: java.lang.Throwable -> L71
                if (r8 != 0) goto L48
            L3c:
                if (r6 == 0) goto L4a
                java.lang.String r8 = r6.toLowerCase()     // Catch: java.lang.Throwable -> L71
                boolean r7 = r8.contains(r7)     // Catch: java.lang.Throwable -> L71
                if (r7 == 0) goto L4a
            L48:
                r7 = 1
                goto L4b
            L4a:
                r7 = 0
            L4b:
                java.lang.String r8 = "redmi"
                if (r5 == 0) goto L59
                java.lang.String r5 = r5.toLowerCase()     // Catch: java.lang.Throwable -> L71
                boolean r5 = r5.contains(r8)     // Catch: java.lang.Throwable -> L71
                if (r5 != 0) goto L65
            L59:
                if (r6 == 0) goto L67
                java.lang.String r5 = r6.toLowerCase()     // Catch: java.lang.Throwable -> L71
                boolean r5 = r5.contains(r8)     // Catch: java.lang.Throwable -> L71
                if (r5 == 0) goto L67
            L65:
                r5 = 1
                goto L68
            L67:
                r5 = 0
            L68:
                if (r7 == 0) goto L6d
                int r2 = r2 + 90
                goto L71
            L6d:
                if (r5 == 0) goto L71
                int r2 = r2 + 40
            L71:
                java.util.HashMap r5 = new java.util.HashMap
                r5.<init>()
                java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
                java.lang.String r6 = "inputId"
                r5.put(r6, r10)
                int r10 = com.jingdong.manto.utils.MantoDensityUtils.pixel2dip(r2)
                java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
                java.lang.String r2 = "height"
                r5.put(r2, r10)
                com.jingdong.manto.f r10 = r0.h()
                com.jingdong.manto.m.d r10 = r1.a(r10, r4)
                com.jingdong.manto.m.d r10 = r10.a(r5)
                int[] r1 = new int[r3]
                int r0 = r0.hashCode()
                r1[r4] = r0
                r10.a(r1)
            La3:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.z0.g.e.a(boolean):void");
        }
    }

    /* loaded from: classes15.dex */
    private static final class f extends com.jingdong.manto.m.d {
        private f() {
        }

        /* synthetic */ f(a aVar) {
            this();
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onKeyboardShow";
        }
    }

    /* renamed from: com.jingdong.manto.m.z0.g$g  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public static final class C0640g extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onKeyboardHeightChange";
        }
    }

    /* loaded from: classes15.dex */
    public static final class h extends com.jingdong.manto.m.d {
        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onKeyboardValueChange";
        }
    }

    static void a(g gVar, n nVar, int i2, int i3, String str, com.jingdong.manto.widget.input.a0.f fVar, int i4, String str2) {
        if (b(gVar, nVar, i2, i3, str, fVar, i4, str2)) {
            return;
        }
        String a2 = nVar.a();
        int hashCode = nVar.hashCode();
        WeakReference<n> weakReference = new WeakReference<>(nVar);
        fVar.G = weakReference;
        j a3 = gVar.a(weakReference, str, i4, str2);
        a3.a(new a(a3, a2, nVar, hashCode));
        gVar.a(a3);
        MantoUtils.runOnUiThread(new b(weakReference, a3, fVar, i2, i3));
    }

    private static boolean b(g gVar, n nVar, int i2, int i3, String str, com.jingdong.manto.widget.input.a0.f fVar, int i4, String str2) {
        boolean z;
        Integer num;
        com.jingdong.manto.widget.input.z.d dVar;
        com.jingdong.manto.widget.input.i iVar;
        k kVar = k.b;
        com.jingdong.manto.widget.input.c a2 = com.jingdong.manto.widget.input.c.a(fVar.M, nVar, fVar);
        if (a2 != null) {
            String str3 = fVar.a;
            if (str3 != null) {
                a2.a(str3);
            }
            a2.b(fVar);
            n nVar2 = a2.f14446c.get();
            if (nVar2 == null || nVar2.s() == null || (dVar = (com.jingdong.manto.widget.input.z.d) a2.g()) == null || (iVar = nVar2.v) == null) {
                z = false;
            } else {
                z = iVar.a(nVar2.s(), (View) dVar, fVar.f14402c.intValue(), fVar.d.intValue(), fVar.f14404f.intValue(), fVar.f14403e.intValue(), fVar.b.booleanValue());
                if (z) {
                    dVar.a(a2.d);
                    dVar.addTextChangedListener(a2);
                }
            }
            if (z && a2.a(i2, i3)) {
                MantoLog.e("JsApiShowKeyboard", "initAndShowKeyboard: =======>inputComponent.showKeyboard done");
                WeakReference weakReference = new WeakReference(nVar);
                int h2 = a2.h();
                a2.a = new k.b(kVar, weakReference, a2, h2, str);
                a2.b = new k.c(kVar, weakReference, h2);
                if (nVar != null && a2 != null) {
                    int h3 = a2.h();
                    nVar.a(new k.a(kVar, h3));
                    kVar.a.put(Integer.valueOf(h3), a2);
                }
                num = Integer.valueOf(h2);
            } else {
                num = null;
            }
            if (num != null) {
                HashMap hashMap = new HashMap(1);
                hashMap.put("inputId", num);
                nVar.a(i4, gVar.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, str2));
                return true;
            }
        }
        return false;
    }

    protected j a(WeakReference<n> weakReference, String str, int i2, String str2) {
        return new com.jingdong.manto.widget.input.d(this, weakReference, i2, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(j jVar) {
        jVar.a(new e(this, jVar));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(com.jingdong.manto.widget.input.a0.f fVar, JSONObject jSONObject, n nVar, int i2, String str) {
        if (super.a((g) fVar, jSONObject, nVar, i2)) {
            fVar.L = jSONObject.optInt("parentId");
            fVar.H = jSONObject.optInt("inputId");
            Integer num = fVar.f14402c;
            fVar.f14402c = Integer.valueOf(num == null ? 0 : Math.max(0, num.intValue()));
            Integer num2 = fVar.d;
            int max = num2 == null ? 0 : Math.max(0, num2.intValue());
            fVar.E = true;
            fVar.d = Integer.valueOf(max);
            fVar.M = jSONObject.optString("type", "text");
            if (!o.b.contains(fVar.M)) {
                nVar.a(i2, putErrMsg("fail:unsupported input type", null, str));
                return false;
            }
            fVar.I = jSONObject.optBoolean(DeepLink3DProductHelper.EXTRA_PASSWORD);
            fVar.F = Boolean.valueOf(jSONObject.optBoolean(DeepLink3DProductHelper.EXTRA_PASSWORD));
            return true;
        }
        return false;
    }

    protected boolean b() {
        return true;
    }

    public boolean c() {
        return true;
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        int optInt;
        int optInt2;
        int optInt3 = jSONObject.optInt("cursor", -2);
        if (jSONObject.has("selectionStart") || jSONObject.has("selectionEnd")) {
            optInt = jSONObject.optInt("selectionStart", -2);
            optInt2 = jSONObject.optInt("selectionEnd", -2);
        } else {
            optInt = optInt3;
            optInt2 = optInt;
        }
        try {
            int i3 = jSONObject.getInt("inputId");
            if (b()) {
                MantoUtils.runOnUiThread(new c(nVar, i3, optInt, optInt2, optInt2, str));
                return;
            }
        } catch (JSONException unused) {
        }
        com.jingdong.manto.widget.input.a0.f fVar = new com.jingdong.manto.widget.input.a0.f();
        if (a(fVar, jSONObject, nVar, i2, str)) {
            if (!jSONObject.has("inputId")) {
                fVar.H = (nVar.hashCode() + "#" + System.currentTimeMillis() + "#" + System.nanoTime()).hashCode();
            }
            MantoUtils.runOnUiThread(new d(nVar, optInt, optInt2, jSONObject, fVar, i2, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return JshopConst.JSHOP_SHOW_KEYBOARD;
    }
}
