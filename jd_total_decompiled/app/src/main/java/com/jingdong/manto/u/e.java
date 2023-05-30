package com.jingdong.manto.u;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Function;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.utils.V8ObjectUtils;
import com.jingdong.manto.m.k0;
import java.util.List;

/* loaded from: classes16.dex */
public class e implements com.jingdong.manto.u.a {
    private j a = new j();

    /* loaded from: classes16.dex */
    class a implements JavaCallback {
        final /* synthetic */ k0 a;

        a(e eVar, k0 k0Var) {
            this.a = k0Var;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public Object invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 3 && v8Array.getType(0) == 4 && v8Array.getType(1) == 4 && v8Array.getType(2) == 1) {
                return this.a.invokeHandler(v8Array.getString(0), v8Array.getString(1), v8Array.getInteger(2));
            }
            return null;
        }
    }

    /* loaded from: classes16.dex */
    class b implements JavaVoidCallback {
        final /* synthetic */ k0 a;

        b(e eVar, k0 k0Var) {
            this.a = k0Var;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 3 && v8Array.getType(0) == 4 && v8Array.getType(1) == 4 && v8Array.getType(2) == 4) {
                this.a.publishHandler(v8Array.getString(0), v8Array.getString(1), v8Array.getString(2));
            }
        }
    }

    /* loaded from: classes16.dex */
    class c implements JavaCallback {
        final /* synthetic */ k0 a;

        c(e eVar, k0 k0Var) {
            this.a = k0Var;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public Object invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() < 1 || v8Array.getType(0) != 1) {
                return null;
            }
            return this.a.retrieveEvent(v8Array.getInteger(0));
        }
    }

    /* loaded from: classes16.dex */
    class d implements JavaCallback {
        final /* synthetic */ k0 a;

        d(e eVar, k0 k0Var) {
            this.a = k0Var;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public Object invoke(V8Object v8Object, V8Array v8Array) {
            return Boolean.valueOf(this.a.isDebugPackage());
        }
    }

    /* renamed from: com.jingdong.manto.u.e$e */
    /* loaded from: classes16.dex */
    class C0677e implements JavaVoidCallback {
        final /* synthetic */ V8 a;
        final /* synthetic */ com.jingdong.manto.u.d b;

        /* renamed from: c */
        final /* synthetic */ V8Object f14223c;

        C0677e(V8 v8, com.jingdong.manto.u.d dVar, V8Object v8Object) {
            e.this = r1;
            this.a = v8;
            this.b = dVar;
            this.f14223c = v8Object;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 4 && v8Array.getType(0) == 7 && v8Array.getType(1) == 1 && v8Array.getType(3) == 3) {
                V8Function v8Function = (V8Function) v8Array.getObject(0);
                int integer = v8Array.getInteger(1);
                int intValue = Double.valueOf(v8Array.getDouble(2)).intValue();
                boolean z = v8Array.getBoolean(3);
                List<? super Object> list = V8ObjectUtils.toList(v8Array);
                e.this.a.a(this.b, this.f14223c, V8ObjectUtils.toV8Array(this.a, list.subList(1, list.size())), v8Function, integer, intValue, z).b();
            }
        }
    }

    /* loaded from: classes16.dex */
    class f implements JavaVoidCallback {
        f() {
            e.this = r1;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 1) {
                int i2 = 0;
                try {
                    if (v8Array.getType(0) == 4) {
                        i2 = Integer.valueOf(v8Array.getString(0)).intValue();
                    } else if (v8Array.getType(0) == 1) {
                        i2 = v8Array.getInteger(0);
                    }
                    e.this.a.a(i2);
                } catch (Throwable unused) {
                }
            }
        }
    }

    @Override // com.jingdong.manto.u.a
    public void a(com.jingdong.manto.u.d dVar, Object obj, String str, V8 v8) {
        if (obj instanceof k0) {
            k0 k0Var = (k0) obj;
            V8Object v8Object = new V8Object(v8);
            v8.add(str, v8Object);
            v8Object.registerJavaMethod(new a(this, k0Var), "invokeHandler");
            v8Object.registerJavaMethod(new b(this, k0Var), "publishHandler");
            v8Object.registerJavaMethod(new c(this, k0Var), "retrieveEvent");
            v8Object.registerJavaMethod(new d(this, k0Var), "isDebugPackage");
            v8Object.registerJavaMethod(new C0677e(v8, dVar, v8Object), "setTimerHandler");
            v8Object.registerJavaMethod(new f(), "clearTimerHandler");
        }
    }

    @Override // com.jingdong.manto.u.a
    public void clear() {
        j jVar = this.a;
        if (jVar != null) {
            jVar.a();
        }
    }
}
