package g.g.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.UUID;

/* loaded from: classes18.dex */
public class h {

    /* renamed from: c */
    private HandlerThread f19615c;
    private Handler d;

    /* renamed from: g */
    private g.g.a.e.a f19618g;
    private i a = null;
    int b = 1;

    /* renamed from: e */
    private int f19616e = -1;

    /* renamed from: f */
    private b f19617f = new b();

    /* loaded from: classes18.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Looper looper) {
            super(looper);
            h.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String[] strArr = (String[]) message.obj;
            String str = strArr[0];
            String str2 = strArr[1];
            if (message.what != 4) {
                return;
            }
            h hVar = h.this;
            if (hVar.b == 1) {
                hVar.f19618g.a(str, str2, Integer.valueOf(h.this.f19616e), h.this.f19617f);
            } else {
                hVar.f19618g.b(str, str2, Integer.valueOf(h.this.f19616e), h.this.f19617f);
            }
        }
    }

    /* loaded from: classes18.dex */
    public class b implements g.g.a.e.b {
        b() {
            h.this = r1;
        }

        @Override // g.g.a.e.b
        public void a(String str, byte[] bArr, int i2, int i3, int i4, double d, String str2, l lVar) {
            h.this.a.a(str, bArr, i3, i4, d, str2, lVar);
        }

        @Override // g.g.a.e.b
        public void onEnd(String str) {
            h.this.a.onEnd(str);
        }

        @Override // g.g.a.e.b
        public void onStart(String str) {
            h.this.a.onStart(str);
        }

        @Override // g.g.a.e.b
        public void onTry(String str, l lVar) {
            h.this.a.onTry(str, lVar);
        }
    }

    public h(Context context) {
        this.f19618g = null;
        this.f19618g = new g.g.a.e.a(context, "httpClientA");
        j();
    }

    private void f(int i2, Object obj) {
        Message obtain = Message.obtain();
        obtain.what = i2;
        obtain.obj = obj;
        this.d.sendMessage(obtain);
    }

    private void j() {
        HandlerThread handlerThread = new HandlerThread("OnLineEngine Thread", -1);
        this.f19615c = handlerThread;
        handlerThread.start();
        this.d = new a(this.f19615c.getLooper());
    }

    public int e() {
        this.f19615c.quit();
        f.c("OnLineEngine", "onEngine exit=");
        return 0;
    }

    public void g(Long l2) {
        g.g.a.e.a aVar = this.f19618g;
        if (aVar != null) {
            aVar.c(l2);
        }
    }

    public void h(n nVar) {
        this.f19618g.d(nVar);
        this.f19616e = 1;
        if (nVar.a("httpProtocols").equals("http1")) {
            this.b = 1;
        } else {
            this.b = 2;
        }
    }

    public void i(i iVar) {
        this.a = iVar;
    }

    public int k() {
        f.e("OnLineEngine", "stop");
        return this.f19618g.e();
    }

    public String l(String str) {
        String uuid = UUID.randomUUID().toString();
        f.c("OnLineEngine", "synthesize textID=" + uuid + ", txt=" + str);
        f(4, new String[]{str, uuid});
        StringBuilder sb = new StringBuilder();
        sb.append("synthesize end: txtID=");
        sb.append(uuid);
        f.c("OnLineEngine", sb.toString());
        return uuid;
    }
}
