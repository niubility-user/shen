package com.jingdong.manto.u;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

/* loaded from: classes16.dex */
public class f implements com.jingdong.manto.u.a {

    /* loaded from: classes16.dex */
    class a implements JavaCallback {
        final /* synthetic */ com.jingdong.manto.m.u1.d a;

        a(f fVar, com.jingdong.manto.m.u1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public Object invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 2 && v8Array.getType(0) == 1 && v8Array.getType(1) == 1) {
                return Integer.valueOf(this.a.setContextType(v8Array.getInteger(0), v8Array.getInteger(1)));
            }
            return null;
        }
    }

    /* loaded from: classes16.dex */
    class b implements JavaCallback {
        final /* synthetic */ com.jingdong.manto.m.u1.d a;

        b(f fVar, com.jingdong.manto.m.u1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public Object invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 3 && v8Array.getType(0) == 1 && v8Array.getType(1) == 1 && v8Array.getType(2) == 4) {
                return this.a.invokeMethod(v8Array.getInteger(0), v8Array.getInteger(1), v8Array.getString(2));
            }
            return null;
        }
    }

    /* loaded from: classes16.dex */
    class c implements JavaVoidCallback {
        final /* synthetic */ com.jingdong.manto.m.u1.d a;

        c(f fVar, com.jingdong.manto.m.u1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 3 && v8Array.getType(0) == 1 && v8Array.getType(1) == 4 && v8Array.getType(2) == 1) {
                this.a.bindImageTexture(v8Array.getInteger(0), v8Array.getString(1), v8Array.getInteger(2));
            }
        }
    }

    /* loaded from: classes16.dex */
    class d implements JavaCallback {
        final /* synthetic */ com.jingdong.manto.m.u1.d a;

        d(f fVar, com.jingdong.manto.m.u1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public Object invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 3 && v8Array.getType(0) == 1 && v8Array.getType(1) == 4 && v8Array.getType(2) == 1) {
                return this.a.preLoadImage(v8Array.getInteger(0), v8Array.getString(1), v8Array.getInteger(2));
            }
            return null;
        }
    }

    /* loaded from: classes16.dex */
    class e implements JavaVoidCallback {
        final /* synthetic */ com.jingdong.manto.m.u1.d a;

        e(f fVar, com.jingdong.manto.m.u1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() < 3 || v8Array.getType(0) != 1) {
                return;
            }
            this.a.updateCanvasScale(v8Array.getInteger(0), v8Array.getInteger(1), v8Array.getInteger(2));
        }
    }

    @Override // com.jingdong.manto.u.a
    public void a(com.jingdong.manto.u.d dVar, Object obj, String str, V8 v8) {
        if (obj instanceof com.jingdong.manto.m.u1.d) {
            com.jingdong.manto.m.u1.d dVar2 = (com.jingdong.manto.m.u1.d) obj;
            V8Object v8Object = new V8Object(v8);
            v8.add(str, v8Object);
            v8Object.registerJavaMethod(new a(this, dVar2), "setContextType");
            v8Object.registerJavaMethod(new b(this, dVar2), "invokeMethod");
            v8Object.registerJavaMethod(new c(this, dVar2), "bindImageTexture");
            v8Object.registerJavaMethod(new d(this, dVar2), "preLoadImage");
            v8Object.registerJavaMethod(new e(this, dVar2), "updateCanvasScale");
        }
    }

    @Override // com.jingdong.manto.u.a
    public void clear() {
    }
}
