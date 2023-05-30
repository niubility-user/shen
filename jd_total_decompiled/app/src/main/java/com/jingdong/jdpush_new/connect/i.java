package com.jingdong.jdpush_new.connect;

import android.text.TextUtils;
import com.jingdong.jdpush_new.j.m;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/* loaded from: classes12.dex */
public class i implements Closeable {

    /* renamed from: g  reason: collision with root package name */
    private DataOutputStream f12816g;

    public i(Socket socket) throws IOException {
        this.f12816g = new DataOutputStream(socket.getOutputStream());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f12816g.close();
    }

    public boolean f(com.jingdong.jdpush_new.g.b bVar) throws IOException {
        if (!com.jingdong.jdpush_new.d.b.b(bVar.getCommand())) {
            com.jingdong.jdpush_new.j.g.c("\u9519\u8bef\u7684command\uff1a" + ((int) bVar.getCommand()));
            return false;
        }
        String d = bVar.getMsgBody() == null ? null : m.d(bVar.getMsgBody());
        this.f12816g.writeShort((TextUtils.isEmpty(d) ? 0 : d.getBytes().length) + 4);
        this.f12816g.writeShort(bVar.getCommand());
        if (!TextUtils.isEmpty(d)) {
            this.f12816g.write(d.getBytes());
        }
        this.f12816g.flush();
        return true;
    }
}
