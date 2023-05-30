package com.jingdong.manto.u;

import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

/* loaded from: classes16.dex */
public class h implements com.jingdong.manto.u.a {

    /* loaded from: classes16.dex */
    class a implements JavaCallback {
        final /* synthetic */ com.jingdong.manto.m.v1.d a;

        a(h hVar, com.jingdong.manto.m.v1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public Object invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() < 1 || v8Array.getType(0) != 4) {
                return null;
            }
            return Integer.valueOf(this.a.create(v8Array.getString(0)));
        }
    }

    /* loaded from: classes16.dex */
    class b implements JavaVoidCallback {
        final /* synthetic */ com.jingdong.manto.m.v1.d a;

        b(h hVar, com.jingdong.manto.m.v1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 2 && v8Array.getType(0) == 1 && v8Array.getType(1) == 4) {
                this.a.postMsgToWorker(v8Array.getInteger(0), v8Array.getString(1));
            }
        }
    }

    /* loaded from: classes16.dex */
    class c implements JavaVoidCallback {
        final /* synthetic */ com.jingdong.manto.m.v1.d a;

        c(h hVar, com.jingdong.manto.m.v1.d dVar) {
            this.a = dVar;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() < 1 || v8Array.getType(0) != 1) {
                return;
            }
            this.a.terminate(v8Array.getInteger(0));
        }
    }

    @Override // com.jingdong.manto.u.a
    public void a(d dVar, Object obj, String str, V8 v8) {
        if (obj instanceof com.jingdong.manto.m.v1.d) {
            com.jingdong.manto.m.v1.d dVar2 = (com.jingdong.manto.m.v1.d) obj;
            V8Object v8Object = new V8Object(v8);
            v8.add(str, v8Object);
            v8Object.registerJavaMethod(new a(this, dVar2), "create");
            v8Object.registerJavaMethod(new b(this, dVar2), "postMsgToWorker");
            v8Object.registerJavaMethod(new c(this, dVar2), "terminate");
        }
    }

    @Override // com.jingdong.manto.u.a
    public void clear() {
    }
}
