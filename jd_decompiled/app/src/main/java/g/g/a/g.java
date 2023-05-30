package g.g.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.jd.ai.auth.basic.JDAIAuthEngine;
import com.jd.dynamic.DYConstants;
import com.jd.jdaisfrontend.ttsengine.NativeTTSEngineListener;
import com.jd.jdaisfrontend.ttsengine.TTSEngineInterface;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes18.dex */
public class g {

    /* renamed from: k  reason: collision with root package name */
    private static String f19603k = "";

    /* renamed from: l  reason: collision with root package name */
    static JDAIAuthEngine f19604l = null;

    /* renamed from: m  reason: collision with root package name */
    private static int f19605m = 24000;

    /* renamed from: n  reason: collision with root package name */
    private static int f19606n = 24000;
    private static float o = 1.0f;
    private static float p = 2.0f;
    private static String q = "";
    protected Context b;
    private TTSEngineInterface d;

    /* renamed from: f  reason: collision with root package name */
    private HandlerThread f19609f;

    /* renamed from: g  reason: collision with root package name */
    private Handler f19610g;
    private i a = null;

    /* renamed from: c  reason: collision with root package name */
    c f19607c = new c(this);

    /* renamed from: e  reason: collision with root package name */
    b f19608e = b.ENGINE_IDLE;

    /* renamed from: h  reason: collision with root package name */
    private boolean f19611h = true;

    /* renamed from: i  reason: collision with root package name */
    private String f19612i = "";

    /* renamed from: j  reason: collision with root package name */
    private g.g.a.a.a f19613j = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0045, code lost:
            r1.printStackTrace();
         */
        /* JADX WARN: Code restructure failed: missing block: B:6:0x0037, code lost:
            if (r6.a.f19608e == g.g.a.g.b.ENGINE_BUSY) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x0039, code lost:
            g.g.a.f.e("OffLineEngine", "ActionSynthesize:wait");
            java.lang.Thread.sleep(1000);
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0044, code lost:
            r1 = move-exception;
         */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r7) {
            /*
                r6 = this;
                super.handleMessage(r7)
                int r0 = r7.what
                r1 = 4
                if (r0 == r1) goto L9
                goto L6f
            L9:
                java.lang.Object r7 = r7.obj
                java.lang.String[] r7 = (java.lang.String[]) r7
                r0 = 0
                r0 = r7[r0]
                r1 = 1
                r7 = r7[r1]
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "ActionSynthesize:utteranceId="
                r1.append(r2)
                r1.append(r7)
                java.lang.String r2 = ", txt="
                r1.append(r2)
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                java.lang.String r3 = "OffLineEngine"
                g.g.a.f.a(r3, r1)
                g.g.a.g r1 = g.g.a.g.this
                g.g.a.g$b r1 = r1.f19608e
                g.g.a.g$b r4 = g.g.a.g.b.ENGINE_BUSY
                if (r1 != r4) goto L50
            L39:
                java.lang.String r1 = "ActionSynthesize:wait"
                g.g.a.f.e(r3, r1)     // Catch: java.lang.InterruptedException -> L44
                r4 = 1000(0x3e8, double:4.94E-321)
                java.lang.Thread.sleep(r4)     // Catch: java.lang.InterruptedException -> L44
                goto L48
            L44:
                r1 = move-exception
                r1.printStackTrace()
            L48:
                g.g.a.g r1 = g.g.a.g.this
                g.g.a.g$b r1 = r1.f19608e
                g.g.a.g$b r4 = g.g.a.g.b.ENGINE_IDLE
                if (r1 != r4) goto L39
            L50:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r4 = "doSynthesize:utteranceId="
                r1.append(r4)
                r1.append(r7)
                r1.append(r2)
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                g.g.a.f.e(r3, r1)
                g.g.a.g r1 = g.g.a.g.this
                g.g.a.g.a(r1, r0, r7)
            L6f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: g.g.a.g.a.handleMessage(android.os.Message):void");
        }
    }

    /* loaded from: classes18.dex */
    public enum b {
        ENGINE_BUSY,
        ENGINE_IDLE
    }

    /* loaded from: classes18.dex */
    public class c implements NativeTTSEngineListener {
        public c(g gVar) {
        }
    }

    public g(Context context) {
        new LinkedBlockingQueue();
        f.c("OffLineEngine", "new OffLineEngine1");
        this.b = context;
        if (f19604l == null) {
            f19604l = new JDAIAuthEngine(this.b);
        }
        g();
    }

    private void c(int i2, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        obtain.obj = obj;
        this.f19610g.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(String str, String str2) {
        this.f19608e = b.ENGINE_BUSY;
        if (this.d != null) {
            this.d = null;
        }
        f.c("OffLineEngine", "new TTSEngineInterface=" + this.f19608e);
        if (this.f19607c == null) {
            f.c("OffLineEngine", "ttsEngineInterfaceListener=null, " + this.f19608e);
        }
        TTSEngineInterface.loadModel(this.b.getApplicationContext().getAssets(), q);
        TTSEngineInterface tTSEngineInterface = new TTSEngineInterface(this.f19607c, f19605m, f19606n, o, p);
        this.d = tTSEngineInterface;
        if (!tTSEngineInterface.writeRequestNoPlay(str, str2)) {
            f.c("OffLineEngine", "writeRequestNoPlay=null, " + this.f19608e);
            this.d = null;
        }
        this.f19608e = b.ENGINE_IDLE;
        f.c("OffLineEngine", "doSynthesize2=" + this.f19608e);
        return 0;
    }

    private void g() {
        f.c("OffLineEngine", "startHandleThread");
        HandlerThread handlerThread = new HandlerThread("OffLineEngine Thread", -1);
        this.f19609f = handlerThread;
        handlerThread.start();
        this.f19610g = new a(this.f19609f.getLooper());
    }

    public int b() {
        f.c("OffLineEngine", "offEngine exit=");
        this.f19609f.quit();
        g.g.a.a.a aVar = this.f19613j;
        if (aVar != null) {
            aVar.m();
            return 0;
        }
        return 0;
    }

    public void e(n nVar) {
        int intValue = Integer.valueOf(nVar.a("sr")).intValue();
        f19606n = intValue;
        f19605m = intValue;
        o = Float.valueOf(nVar.a("sp")).floatValue();
        p = Float.valueOf(nVar.a("vol")).floatValue();
        q = nVar.a("ttsModel");
        f19603k = nVar.a("assetsPath");
        f.c("OffLineEngine", "assetsPath=" + f19603k);
        f.c("OffLineEngine", "response_size=" + f19606n);
        f.c("OffLineEngine", "voice_speed=" + o);
        f.c("OffLineEngine", "voice_volume=" + p);
        f.c("OffLineEngine", "ttsModel=" + q);
        if (f19603k.equals(DYConstants.DY_ASSETS)) {
            TTSEngineInterface.loadResource(this.b.getApplicationContext().getAssets(), "jd_tts_text.dat");
            TTSEngineInterface.loadModel(this.b.getApplicationContext().getAssets(), q);
        } else {
            TTSEngineInterface.loadResource(this.b.getApplicationContext().getAssets(), f19603k + "/jd_tts_text.dat");
            TTSEngineInterface.loadModel(this.b.getApplicationContext().getAssets(), f19603k + "/" + q);
        }
        if (this.f19613j == null) {
            this.f19613j = new g.g.a.a.a(this.b, nVar.a("authID"));
        }
    }

    public void f(i iVar) {
        this.a = iVar;
    }

    public int h() {
        if (!this.f19611h) {
            this.a.onError(this.f19612i, l.ERR_NOT_AUTH);
            return -1;
        }
        b bVar = this.f19608e;
        b bVar2 = b.ENGINE_IDLE;
        if (bVar == bVar2) {
            f.c("OffLineEngine", "stop1=" + this.f19608e);
            return 0;
        }
        this.d.stop();
        this.d.close();
        if (this.d != null) {
            this.d = null;
        }
        this.f19608e = bVar2;
        return 0;
    }

    public String i(String str) {
        String uuid = UUID.randomUUID().toString();
        f.c("OffLineEngine", "OffLine Synthesize textID=" + uuid + ", txt=" + str);
        c(4, new String[]{str, uuid});
        g.g.a.a.a aVar = this.f19613j;
        if (aVar != null) {
            aVar.l();
        }
        return uuid;
    }
}
