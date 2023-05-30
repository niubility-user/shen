package com.jingdong.manto.m.p0.f.f.c;

import java.io.OutputStream;

/* loaded from: classes15.dex */
public interface l {

    /* loaded from: classes15.dex */
    public static final class a implements l {
        @Override // com.jingdong.manto.m.p0.f.f.c.l
        public void a(b bVar, OutputStream outputStream) {
            outputStream.write(bVar.b());
        }
    }

    void a(b bVar, OutputStream outputStream);
}
