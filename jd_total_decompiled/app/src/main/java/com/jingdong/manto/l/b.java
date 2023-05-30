package com.jingdong.manto.l;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes15.dex */
final class b implements e {
    private static final String a;

    static {
        String str = com.jingdong.manto.utils.n.f14314c;
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        String str2 = str + "cache/";
        a = str2;
        com.jingdong.manto.sdk.e.a.a(str2);
    }

    @Override // com.jingdong.manto.l.e
    public final OutputStream a(String str) {
        try {
            return new FileOutputStream(a + str);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.jingdong.manto.l.e
    public final InputStream b(String str) {
        try {
            return new FileInputStream(a + str);
        } catch (IOException unused) {
            return null;
        }
    }
}
