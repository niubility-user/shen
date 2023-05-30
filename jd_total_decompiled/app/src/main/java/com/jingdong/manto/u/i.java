package com.jingdong.manto.u;

import android.util.SparseArray;
import com.eclipsesource.v8.JavaCallback;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8ArrayBuffer;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.V8TypedArray;
import com.eclipsesource.v8.utils.TypedArray;
import com.eclipsesource.v8.utils.V8ObjectUtils;
import com.jingdong.manto.utils.MantoLog;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes16.dex */
public class i implements com.jingdong.manto.u.a {
    private static final String b = "com.jingdong.manto.u.i";

    /* renamed from: c */
    private static final AtomicInteger f14224c = new AtomicInteger(1);
    SparseArray<byte[]> a = new SparseArray<>();

    /* loaded from: classes16.dex */
    public class a implements JavaCallback {
        a() {
            i.this = r1;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public final Object invoke(V8Object v8Object, V8Array v8Array) {
            return Integer.valueOf(i.this.b());
        }
    }

    /* loaded from: classes16.dex */
    public class b implements JavaCallback {
        final /* synthetic */ i a;
        final /* synthetic */ V8 b;

        b(i iVar, i iVar2, V8 v8) {
            this.a = iVar2;
            this.b = v8;
        }

        @Override // com.eclipsesource.v8.JavaCallback
        public final Object invoke(V8Object v8Object, V8Array v8Array) {
            String str;
            String str2;
            if (v8Array.length() <= 0 || v8Array.getType(0) != 1) {
                str = i.b;
                str2 = "getNativeBuffer invalid parameters";
            } else {
                MantoLog.d(i.b, "getNativeBuffer, id:%d", Integer.valueOf(v8Array.getInteger(0)));
                byte[] a = this.a.a(v8Array.getInteger(0));
                if (a != null) {
                    V8ArrayBuffer v8ArrayBuffer = new V8ArrayBuffer(this.b, a.length);
                    v8ArrayBuffer.put(a);
                    return v8ArrayBuffer;
                }
                str = i.b;
                str2 = "getNativeBuffer bb null";
            }
            MantoLog.w(str, str2);
            return null;
        }
    }

    /* loaded from: classes16.dex */
    public class c implements JavaVoidCallback {
        final /* synthetic */ i a;

        c(i iVar, i iVar2) {
            this.a = iVar2;
        }

        @Override // com.eclipsesource.v8.JavaVoidCallback
        public final void invoke(V8Object v8Object, V8Array v8Array) {
            if (v8Array.length() >= 2 && v8Array.getType(0) == 1 && v8Array.getType(1) == 10) {
                MantoLog.d(i.b, "setNativeBuffer, id:%d", Integer.valueOf(v8Array.getInteger(0)));
                V8ArrayBuffer v8ArrayBuffer = (V8ArrayBuffer) v8Array.get(1);
                if (v8ArrayBuffer == null) {
                    MantoLog.w(i.b, "setNativeBuffer buffer null");
                    return;
                }
                byte[] bArr = new byte[v8ArrayBuffer.capacity()];
                v8ArrayBuffer.get(bArr);
                this.a.a(v8Array.getInteger(0), bArr);
                v8ArrayBuffer.close();
            } else if (v8Array.length() < 2 || v8Array.getType(0) != 1) {
                MantoLog.w(i.b, "setNativeBuffer invalid parameters");
            } else {
                int integer = v8Array.getInteger(0);
                V8TypedArray v8TypedArray = ((TypedArray) V8ObjectUtils.getValue(v8Array, 1)).getV8TypedArray();
                byte[] bArr2 = new byte[v8TypedArray.length()];
                v8TypedArray.getBytes(0, v8TypedArray.length(), bArr2);
                this.a.a(integer, bArr2);
                MantoLog.w(i.b, "setNativeBuffer unit8");
            }
        }
    }

    public final void a(int i2, byte[] bArr) {
        synchronized (this.a) {
            this.a.put(i2, bArr);
        }
    }

    @Override // com.jingdong.manto.u.a
    public void a(d dVar, Object obj, String str, V8 v8) {
        if (obj instanceof i) {
            i iVar = (i) obj;
            V8Object v8Object = new V8Object(v8);
            v8.add(str, v8Object);
            v8Object.registerJavaMethod(new a(), "getNativeBufferId");
            v8Object.registerJavaMethod(new b(this, iVar, v8), "getNativeBuffer");
            v8Object.registerJavaMethod(new c(this, iVar), "setNativeBuffer");
        }
    }

    public final byte[] a(int i2) {
        byte[] bArr;
        synchronized (this.a) {
            bArr = this.a.get(i2);
            this.a.remove(i2);
        }
        return bArr;
    }

    public final int b() {
        AtomicInteger atomicInteger;
        int i2;
        int i3;
        do {
            atomicInteger = f14224c;
            i2 = atomicInteger.get();
            i3 = i2 + 1;
            if (i3 > 16777216) {
                i3 = 1;
            }
        } while (!atomicInteger.compareAndSet(i2, i3));
        return i2;
    }

    @Override // com.jingdong.manto.u.a
    public void clear() {
        this.a.clear();
    }
}
