package g.f.a.a;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes12.dex */
public class h {
    private static Handler a;

    /* loaded from: classes12.dex */
    static class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ g.f.a.c.d f19508g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f19509h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f19510i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ byte[] f19511j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ int f19512k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ int f19513l;

        a(g.f.a.c.d dVar, String str, String str2, byte[] bArr, int i2, int i3) {
            this.f19508g = dVar;
            this.f19509h = str;
            this.f19510i = str2;
            this.f19511j = bArr;
            this.f19512k = i2;
            this.f19513l = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f19508g.b(this.f19509h, this.f19510i, this.f19511j, this.f19512k, this.f19513l);
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread("message");
        handlerThread.start();
        a = new Handler(handlerThread.getLooper());
    }

    public static void a(g.f.a.c.d dVar, String str, String str2, byte[] bArr, int i2, int i3) {
        if (dVar == null) {
            return;
        }
        g.f.a.b.b.b("messagePost", "name: " + str + " params: " + str2);
        a.post(new a(dVar, str, str2, bArr, i2, i3));
    }
}
