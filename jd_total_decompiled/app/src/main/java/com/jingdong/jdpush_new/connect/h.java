package com.jingdong.jdpush_new.connect;

import com.jingdong.jdpush_new.j.m;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/* loaded from: classes12.dex */
public class h implements Closeable {

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> f12813g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f12814h = true;

    /* renamed from: i  reason: collision with root package name */
    private DataInputStream f12815i;

    public h(Socket socket, com.jingdong.jdpush_new.j.d<com.jingdong.jdpush_new.g.b> dVar) throws IOException {
        this.f12813g = dVar;
        this.f12815i = new DataInputStream(socket.getInputStream());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f12814h = false;
        this.f12815i.close();
    }

    public void f() throws Exception {
        short readShort = this.f12815i.readShort();
        short readShort2 = this.f12815i.readShort();
        byte[] bArr = new byte[readShort - 4];
        this.f12815i.readFully(bArr);
        this.f12813g.accept(new com.jingdong.jdpush_new.g.b(readShort2, m.f(new String(bArr, "utf-8"))));
    }

    public void g() {
        while (this.f12814h) {
            try {
                f();
            } catch (Exception e2) {
                if (this.f12814h) {
                    com.jingdong.jdpush_new.j.g.c("long conn read error " + e2.toString());
                    return;
                }
                return;
            }
        }
    }
}
