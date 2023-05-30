package com.jingdong.manto.u;

import android.util.SparseArray;
import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8ArrayBuffer;
import com.eclipsesource.v8.V8Object;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.manto.utils.MantoLog;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes16.dex */
public class c implements com.jingdong.manto.u.a {
    private static final String b = "com.jingdong.manto.u.c";
    SparseArray<ByteBuffer> a = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements JavaCallback {
        final /* synthetic */ c a;
        final /* synthetic */ V8 b;

        a(c cVar, c cVar2, V8 v8) {
            this.a = cVar2;
            this.b = v8;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public final Object invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() <= 0 || v8Array.getType(0) != 1) {
                MantoLog.w(c.b, "getJDArrayBuffer invalid parameters");
                return null;
            }
            MantoLog.d(c.b, "getJDArrayBuffer, id:%d", Integer.valueOf(v8Array.getInteger(0)));
            return new V8ArrayBuffer(this.b, this.a.a(v8Array.getInteger(0)));
        }
    }

    static {
        new AtomicInteger(1);
    }

    public final ByteBuffer a(int i2) {
        return this.a.get(i2);
    }

    @Override // com.jingdong.manto.u.a
    public void a(d dVar, Object obj, String str, V8 v8) {
        if (obj instanceof c) {
            V8Object v8Object = new V8Object(v8);
            v8.add(str, v8Object);
            v8Object.registerJavaMethod(new a(this, (c) obj, v8), IMantoServerRequester.GET);
        }
    }

    @Override // com.jingdong.manto.u.a
    public void clear() {
    }
}
