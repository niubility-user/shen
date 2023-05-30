package com.jingdong.manto.m.v1;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.utils.MemoryManager;
import com.jingdong.manto.pkg.b.g;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b implements com.jingdong.manto.m.v1.a {

    /* renamed from: i */
    private static final String f13803i = "com.jingdong.manto.m.v1.b";
    private com.jingdong.manto.f a;
    private V8Object b;

    /* renamed from: c */
    private com.jingdong.manto.m.v1.c f13804c;
    private String d;

    /* renamed from: e */
    private JSONObject f13805e;

    /* renamed from: f */
    private Handler f13806f;

    /* renamed from: g */
    private V8 f13807g;

    /* renamed from: h */
    private MemoryManager f13808h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {

        /* renamed from: com.jingdong.manto.m.v1.b$a$a */
        /* loaded from: classes15.dex */
        class C0629a implements JavaVoidCallback {
            C0629a() {
                a.this = r1;
            }

            @Override // com.eclipsesource.v8.JavaVoidCallback
            public void invoke(V8Object v8Object, V8Array v8Array) {
                if (v8Array.length() < 1 || v8Array.getType(0) != 4) {
                    return;
                }
                b.this.f13804c.postMsgToAppService(v8Array.getString(0));
            }
        }

        a() {
            b.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f13807g = V8.createV8Runtime();
            b bVar = b.this;
            bVar.f13808h = new MemoryManager(bVar.f13807g);
            b bVar2 = b.this;
            bVar2.b = new V8Object(bVar2.f13807g);
            b.this.f13807g.add("JDWorker", b.this.b);
            b.this.b.registerJavaMethod(new C0629a(), "postMsgToAppService");
            b bVar3 = b.this;
            bVar3.a(bVar3.f13807g, b.this.f13805e);
            b bVar4 = b.this;
            bVar4.a(bVar4.f13807g, b.this.d);
        }
    }

    /* renamed from: com.jingdong.manto.m.v1.b$b */
    /* loaded from: classes15.dex */
    class RunnableC0630b implements Runnable {
        RunnableC0630b() {
            b.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.f13808h != null) {
                b.this.f13808h.release();
            }
            if (b.this.f13807g != null) {
                b.this.f13807g.close();
            }
            b.this.f13806f.getLooper().quit();
            b.this.f13806f = null;
        }
    }

    /* loaded from: classes15.dex */
    class c implements Runnable {
        final /* synthetic */ String a;

        c(String str) {
            b.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.a;
            if (str.startsWith("/")) {
                str = this.a.replaceFirst("/", "");
            }
            String format = String.format("require(\"%s\")", str);
            if (b.this.a != null) {
                b.this.f13807g.executeScript(format);
            }
        }
    }

    /* loaded from: classes15.dex */
    class d implements Runnable {
        final /* synthetic */ String a;

        d(String str) {
            b.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String format = String.format("JDWorker.appServiceMsgHandler(%s)", this.a);
            if (b.this.a != null) {
                b.this.f13807g.executeScript(format);
            }
        }
    }

    public b(com.jingdong.manto.f fVar) {
        this.a = fVar;
    }

    public void a(V8 v8, String str) {
        String str2 = f13803i;
        MantoLog.d(str2, "injectWorker");
        String c2 = com.jingdong.manto.pkg.b.f.c("NAWorker.js");
        String b = g.b(this.a, str);
        StringBuilder sb = new StringBuilder(c2);
        sb.append(b);
        if (MantoStringUtils.isEmpty(sb.toString())) {
            MantoLog.e(str2, "injectNAWorker, worker is null");
        } else if (v8 != null) {
            v8.executeScript(sb.toString());
        }
    }

    public void a(V8 v8, JSONObject jSONObject) {
        try {
            jSONObject.put("nativeBufferEnabled", false);
        } catch (Throwable th) {
            MantoLog.e(f13803i, th.getMessage());
        }
        String format = String.format("var __jdConfig = %s;", jSONObject.toString());
        if (v8 != null) {
            v8.executeScript(format);
        }
    }

    private void b() {
        if (this.f13806f != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("MantoV8AsyncWorkerThread");
        handlerThread.start();
        this.f13806f = new Handler(handlerThread.getLooper());
    }

    @Override // com.jingdong.manto.m.v1.a
    public int a(com.jingdong.manto.m.v1.c cVar, String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str)) {
            return -3;
        }
        this.f13804c = cVar;
        this.d = str;
        this.f13805e = jSONObject;
        return 0;
    }

    @Override // com.jingdong.manto.m.v1.a
    public void a() {
        Handler handler = this.f13806f;
        if (handler == null) {
            return;
        }
        handler.post(new RunnableC0630b());
    }

    @Override // com.jingdong.manto.m.v1.a
    public void a(String str) {
        Handler handler = this.f13806f;
        if (handler == null) {
            return;
        }
        handler.post(new d(str));
    }

    @Override // com.jingdong.manto.m.v1.a
    public void b(String str) {
        MantoLog.d(f13803i, "requireScript");
        Handler handler = this.f13806f;
        if (handler == null) {
            return;
        }
        handler.post(new c(str));
    }

    @Override // com.jingdong.manto.m.v1.a
    public void start() {
        b();
        this.f13806f.post(new a());
    }
}
