package com.jingdong.manto.u;

import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;

/* loaded from: classes16.dex */
public class g implements com.jingdong.manto.u.a {

    /* loaded from: classes16.dex */
    class a implements JavaVoidCallback {
        final /* synthetic */ com.jingdong.manto.m.v1.c a;

        a(g gVar, com.jingdong.manto.m.v1.c cVar) {
            this.a = cVar;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() < 1 || v8Array.getType(0) != 4) {
                return;
            }
            this.a.postMsgToAppService(v8Array.getString(0));
        }
    }

    @Override // com.jingdong.manto.u.a
    public void a(d dVar, Object obj, String str, V8 v8) {
        if (obj instanceof com.jingdong.manto.m.v1.c) {
            V8Object v8Object = new V8Object(v8);
            v8.add(str, v8Object);
            v8Object.registerJavaMethod(new a(this, (com.jingdong.manto.m.v1.c) obj), "postMsgToAppService");
        }
    }

    @Override // com.jingdong.manto.u.a
    public void clear() {
    }
}
