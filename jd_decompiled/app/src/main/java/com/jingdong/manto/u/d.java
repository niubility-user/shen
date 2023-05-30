package com.jingdong.manto.u;

import android.os.Handler;
import android.os.HandlerThread;
import android.webkit.ValueCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.utils.MemoryManager;
import com.jingdong.manto.m.k0;
import com.jingdong.sdk.jweb.JSContext;
import com.jingdong.sdk.jweb.JSExceptionHandler;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes16.dex */
public class d implements JSContext {
    private V8 a;
    private i b;

    /* renamed from: c  reason: collision with root package name */
    public com.jingdong.manto.u.c f14219c;
    private MemoryManager d;

    /* renamed from: e  reason: collision with root package name */
    private CopyOnWriteArrayList<com.jingdong.manto.u.a> f14220e = new CopyOnWriteArrayList<>();

    /* renamed from: f  reason: collision with root package name */
    private Handler f14221f;

    /* loaded from: classes16.dex */
    class a implements Runnable {
        final /* synthetic */ Object a;
        final /* synthetic */ String b;

        a(Object obj, String str) {
            this.a = obj;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.u.a gVar;
            Object obj = this.a;
            if (obj instanceof k0) {
                com.jingdong.manto.u.e eVar = new com.jingdong.manto.u.e();
                d dVar = d.this;
                eVar.a(dVar, this.a, this.b, dVar.a);
                d.this.f14220e.add(eVar);
                return;
            }
            if (obj instanceof com.jingdong.manto.m.u1.d) {
                gVar = new f();
            } else if (obj instanceof com.jingdong.manto.m.v1.d) {
                gVar = new h();
            } else if (!(obj instanceof com.jingdong.manto.m.v1.c)) {
                return;
            } else {
                gVar = new g();
            }
            d dVar2 = d.this;
            gVar.a(dVar2, obj, this.b, dVar2.a);
            d.this.f14220e.add(gVar);
        }
    }

    /* loaded from: classes16.dex */
    class b implements Runnable {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                d.this.a.executeScript(this.a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* loaded from: classes16.dex */
    class c implements Runnable {
        final /* synthetic */ String a;

        c(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                d.this.a.executeScript(this.a);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: com.jingdong.manto.u.d$d  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class RunnableC0676d implements Runnable {
        RunnableC0676d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.a = V8.createV8Runtime();
            d dVar = d.this;
            dVar.d = new MemoryManager(dVar.a);
            d.this.b = new i();
            i iVar = d.this.b;
            d dVar2 = d.this;
            iVar.a(dVar2, dVar2.b, "nativeBufferCompat", d.this.a);
            d.this.f14220e.add(d.this.b);
            d.this.a.executeScript("function getNativeBufferId(){return nativeBufferCompat?nativeBufferCompat.getNativeBufferId():-1}function setNativeBuffer(a,b){return nativeBufferCompat?nativeBufferCompat.setNativeBuffer(a,b):void 0}function getNativeBuffer(a){return nativeBufferCompat?nativeBufferCompat.getNativeBuffer(a):void 0}");
            d.this.f14219c = new com.jingdong.manto.u.c();
            d dVar3 = d.this;
            com.jingdong.manto.u.c cVar = dVar3.f14219c;
            cVar.a(dVar3, cVar, "JDArrayBuffer", dVar3.a);
            d.this.f14220e.add(d.this.f14219c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = d.this.f14220e.iterator();
            while (it.hasNext()) {
                ((com.jingdong.manto.u.a) it.next()).clear();
            }
            if (d.this.d != null) {
                d.this.d.release();
            }
            if (d.this.a != null) {
                d.this.a.close();
            }
            if (d.this.f14221f != null) {
                d.this.f14221f.getLooper().quit();
                d.this.f14221f = null;
            }
        }
    }

    public d() {
        b();
    }

    private void a() {
        a(new e());
    }

    private void b() {
        if (this.f14221f != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("MantoV8JsBridgeThread");
        handlerThread.start();
        this.f14221f = new Handler(handlerThread.getLooper());
    }

    public void a(Runnable runnable) {
        Handler handler = this.f14221f;
        if (handler == null || runnable == null) {
            return;
        }
        handler.post(runnable);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void addJavascriptInterface(Object obj, String str) {
        a(new a(obj, str));
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canPauseAndResume() {
        return false;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canSetTitle() {
        return true;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canUseNativeBuffer() {
        return true;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void cleanup() {
        a();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        a(new b(str));
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url) {
        a(new c(str));
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public ByteBuffer getNativeBuffer(int i2) {
        byte[] a2 = this.b.a(i2);
        if (a2 == null || a2.length <= 0) {
            return null;
        }
        return ByteBuffer.wrap(a2);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public int getNativeBufferId() {
        return this.b.b();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void init() {
        a(new RunnableC0676d());
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void pause() {
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void resume() {
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setJsExceptionHandler(JSExceptionHandler jSExceptionHandler) {
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setNativeBuffer(int i2, ByteBuffer byteBuffer) {
        byte[] array;
        if (byteBuffer == null) {
            array = new byte[0];
        } else if (byteBuffer.isDirect()) {
            int position = byteBuffer.position();
            byteBuffer.position(0);
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            byteBuffer.position(position);
            array = bArr;
        } else {
            array = byteBuffer.array();
        }
        this.b.a(i2, array);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setTitle(String str) {
    }
}
