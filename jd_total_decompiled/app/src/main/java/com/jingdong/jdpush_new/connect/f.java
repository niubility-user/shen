package com.jingdong.jdpush_new.connect;

import android.content.Context;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* loaded from: classes12.dex */
public class f {

    /* renamed from: g */
    private static final ExecutorService f12801g = Executors.newSingleThreadExecutor();

    /* renamed from: h */
    public static final String f12802h = f.class.getSimpleName();
    private Context a;
    private Socket b;

    /* renamed from: c */
    private h f12803c;
    private i d;

    /* renamed from: e */
    private com.jingdong.jdpush_new.connect.a f12804e;

    /* renamed from: f */
    private boolean f12805f = false;

    /* loaded from: classes12.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.jdpush_new.g.b f12806g;

        a(com.jingdong.jdpush_new.g.b bVar) {
            f.this = r1;
            this.f12806g = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (f.this.e()) {
                return;
            }
            try {
                com.jingdong.jdpush_new.j.g.c("---> " + this.f12806g.toString());
                f.this.d.f(this.f12806g);
            } catch (IOException e2) {
                com.jingdong.jdpush_new.j.g.c("write msg page error : " + e2.toString());
            }
        }
    }

    public f(Context context) {
        this.a = context;
    }

    private com.jingdong.jdpush_new.i.c b() {
        com.jingdong.jdpush_new.i.c cVar = new com.jingdong.jdpush_new.i.c();
        cVar.b((short) 2114, new com.jingdong.jdpush_new.i.a(this.a, this));
        cVar.b((short) 2121, new com.jingdong.jdpush_new.i.a(this.a, this));
        cVar.b((short) 2103, new com.jingdong.jdpush_new.i.b(this.a, this));
        cVar.b((short) 1, this.f12804e);
        return cVar;
    }

    private SocketAddress d() {
        if ("1".equals(com.jingdong.jdpush_new.j.c.f(this.a))) {
            return new InetSocketAddress("primary-bat.jd.com", Integer.parseInt("2002"));
        }
        return new InetSocketAddress("jpushsc.m.jd.com", Integer.parseInt("2002"));
    }

    public com.jingdong.jdpush_new.connect.a c() {
        return this.f12804e;
    }

    public synchronized boolean e() {
        return this.f12805f;
    }

    public synchronized void f() {
        try {
        } catch (Throwable th) {
            com.jingdong.jdpush_new.j.g.g(th);
        }
        if (this.f12805f) {
            com.jingdong.jdpush_new.j.g.a("already closed");
            return;
        }
        com.jingdong.jdpush_new.j.g.c("close socket");
        this.f12805f = true;
        com.jingdong.jdpush_new.connect.a aVar = this.f12804e;
        if (aVar != null) {
            aVar.h();
        }
        i iVar = this.d;
        if (iVar != null) {
            try {
                iVar.close();
            } catch (IOException unused) {
            }
        }
        h hVar = this.f12803c;
        if (hVar != null) {
            try {
                hVar.close();
            } catch (IOException unused2) {
            }
        }
        Socket socket = this.b;
        if (socket != null && !socket.isClosed()) {
            try {
                this.b.close();
            } catch (IOException unused3) {
            }
        }
    }

    public void g(com.jingdong.jdpush_new.g.b bVar) {
        f12801g.execute(new a(bVar));
    }

    public void h() throws IOException, JSONException, b {
        try {
            com.jingdong.jdpush_new.mta.a.b().f();
            String str = f12802h;
            com.jingdong.jdpush_new.j.g.b(str, "create long connection");
            Socket socket = new Socket();
            this.b = socket;
            socket.connect(d(), 30000);
            this.b.setKeepAlive(false);
            com.jingdong.jdpush_new.j.g.b(str, "connectSuccess");
            c.c().f12793i = 0;
            this.d = new i(this.b);
            this.f12804e = new com.jingdong.jdpush_new.connect.a(this.a, this);
            g(e.a(this.a));
            h hVar = new h(this.b, b());
            this.f12803c = hVar;
            hVar.g();
        } finally {
            com.jingdong.jdpush_new.mta.a.b().e();
            f();
        }
    }
}
