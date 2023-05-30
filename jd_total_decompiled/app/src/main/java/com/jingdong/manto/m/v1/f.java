package com.jingdong.manto.m.v1;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.jingdong.manto.jsengine.IMantoWebViewJS;
import com.jingdong.manto.pkg.b.g;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class f implements com.jingdong.manto.m.v1.a {
    private static final String d = "com.jingdong.manto.m.v1.f";
    private IMantoWebViewJS a = b();
    private Handler b;

    /* renamed from: c */
    private com.jingdong.manto.f f13811c;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            f.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.a;
            if (str.startsWith("/")) {
                str = this.a.replaceFirst("/", "");
            }
            String format = String.format("require(\"%s\")", str);
            if (f.this.a != null) {
                f.this.a.evaluateJavascript(format, null);
            }
        }
    }

    /* loaded from: classes15.dex */
    class b implements Runnable {
        final /* synthetic */ String a;

        b(String str) {
            f.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String format = String.format("JDWorker.appServiceMsgHandler(%s)", this.a);
            if (f.this.a != null) {
                f.this.a.evaluateJavascript(format, null);
            }
        }
    }

    public f(com.jingdong.manto.f fVar) {
        this.f13811c = fVar;
    }

    private void a(JSONObject jSONObject) {
        try {
            jSONObject.put("nativeBufferEnabled", false);
        } catch (Throwable th) {
            MantoLog.e(d, th.getMessage());
        }
        String format = String.format("var __jdConfig = %s;", jSONObject.toString());
        IMantoWebViewJS iMantoWebViewJS = this.a;
        if (iMantoWebViewJS != null) {
            iMantoWebViewJS.evaluateJavascript(format, null);
        }
    }

    private IMantoWebViewJS b() {
        return com.jingdong.manto.jsengine.d.a(com.jingdong.manto.c.a());
    }

    private void c() {
        if (this.b != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("MantoAsyncWorkerThread");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
    }

    private void c(String str) {
        String str2 = d;
        MantoLog.d(str2, "injectWorker");
        String c2 = com.jingdong.manto.pkg.b.f.c("NAWorker.js");
        String b2 = g.b(this.f13811c, str);
        StringBuilder sb = new StringBuilder(c2);
        sb.append(b2);
        if (MantoStringUtils.isEmpty(sb.toString())) {
            MantoLog.e(str2, "injectNAWorker, worker is null");
            return;
        }
        IMantoWebViewJS iMantoWebViewJS = this.a;
        if (iMantoWebViewJS != null) {
            iMantoWebViewJS.evaluateJavascript(sb.toString(), null);
        }
    }

    @Override // com.jingdong.manto.m.v1.a
    public int a(c cVar, String str, JSONObject jSONObject) {
        IMantoWebViewJS iMantoWebViewJS = this.a;
        if (iMantoWebViewJS == null || (iMantoWebViewJS instanceof com.jingdong.manto.jsengine.c)) {
            return -1;
        }
        if (this.b == null) {
            return -2;
        }
        if (TextUtils.isEmpty(str)) {
            return -3;
        }
        this.a.addJavascriptInterface(cVar, "JDWorker");
        a(jSONObject);
        c(str);
        return 0;
    }

    @Override // com.jingdong.manto.m.v1.a
    public void a() {
        Handler handler = this.b;
        if (handler == null) {
            return;
        }
        handler.getLooper().quit();
        this.b = null;
    }

    @Override // com.jingdong.manto.m.v1.a
    public void a(String str) {
        this.b.post(new b(str));
    }

    @Override // com.jingdong.manto.m.v1.a
    public void b(String str) {
        MantoLog.d(d, "requireScript");
        Handler handler = this.b;
        if (handler == null) {
            return;
        }
        handler.post(new a(str));
    }

    @Override // com.jingdong.manto.m.v1.a
    public void start() {
        c();
    }
}
