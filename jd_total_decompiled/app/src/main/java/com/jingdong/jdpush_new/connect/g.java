package com.jingdong.jdpush_new.connect;

import android.content.Context;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes12.dex */
public class g {
    private static final String b = "g";

    /* renamed from: c  reason: collision with root package name */
    private static g f12808c;
    ExecutorService a = Executors.newSingleThreadExecutor();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private Context f12809g;

        /* renamed from: h  reason: collision with root package name */
        private com.jingdong.jdpush_new.g.b f12810h;

        /* renamed from: i  reason: collision with root package name */
        private int f12811i;

        /* renamed from: j  reason: collision with root package name */
        private String f12812j;

        public a(g gVar, Context context, com.jingdong.jdpush_new.g.b bVar, int i2, String str) {
            this.f12809g = context;
            this.f12810h = bVar;
            this.f12811i = i2;
            this.f12812j = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar;
            i iVar;
            InetSocketAddress inetSocketAddress;
            Socket socket = null;
            try {
                try {
                    if (this.f12810h.getCommand() == 2104) {
                        com.jingdong.jdpush_new.mta.b.b().l((this.f12811i * 1000) + 320);
                    }
                    Socket socket2 = new Socket();
                    try {
                        if ("1".equals(com.jingdong.jdpush_new.j.c.f(this.f12809g))) {
                            inetSocketAddress = new InetSocketAddress("primary-bat.jd.com", Integer.parseInt("2002"));
                        } else {
                            inetSocketAddress = new InetSocketAddress("jpushsc.m.jd.com", Integer.parseInt("2002"));
                        }
                        socket2.connect(inetSocketAddress, 10000);
                        if (this.f12810h.getCommand() == 2104) {
                            com.jingdong.jdpush_new.mta.b.b().l((this.f12811i * 1000) + 330);
                        }
                        com.jingdong.jdpush_new.j.g.c("-> " + this.f12810h);
                        iVar = new i(socket2);
                        try {
                            iVar.f(this.f12810h);
                            if (this.f12810h.getCommand() == 2104) {
                                com.jingdong.jdpush_new.mta.b.b().l((this.f12811i * 1000) + R2.attr.actionModeShareDrawable);
                            }
                            hVar = new h(socket2, new com.jingdong.jdpush_new.i.d(this.f12809g, this.f12812j, this.f12811i));
                        } catch (Throwable th) {
                            hVar = null;
                            socket = socket2;
                            th = th;
                        }
                    } catch (Throwable th2) {
                        iVar = null;
                        socket = socket2;
                        th = th2;
                        hVar = null;
                    }
                    try {
                        hVar.f();
                        try {
                            iVar.close();
                        } catch (IOException unused) {
                        }
                        try {
                            hVar.close();
                        } catch (IOException unused2) {
                        }
                        socket2.close();
                    } catch (Throwable th3) {
                        th = th3;
                        socket = socket2;
                        try {
                            com.jingdong.jdpush_new.j.g.e(g.b, "\u77ed\u8fde\u63a5\u5f02\u5e38", th);
                            com.jingdong.jdpush_new.mta.b.b().i(this.f12811i, th);
                            if (socket != null) {
                                socket.close();
                            }
                        } finally {
                            if (iVar != null) {
                                try {
                                    iVar.close();
                                } catch (IOException unused3) {
                                }
                            }
                            if (hVar != null) {
                                try {
                                    hVar.close();
                                } catch (IOException unused4) {
                                }
                            }
                            if (socket != null) {
                                try {
                                    socket.close();
                                } catch (IOException unused5) {
                                }
                            }
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    hVar = null;
                    iVar = null;
                }
            } catch (IOException unused6) {
            }
        }
    }

    public static g c() {
        if (f12808c == null) {
            f12808c = new g();
        }
        return f12808c;
    }

    public void b(Context context, com.jingdong.jdpush_new.g.b bVar, int i2, String str) {
        this.a.execute(new a(this, context, bVar, i2, str));
    }
}
