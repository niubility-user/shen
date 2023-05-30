package com.huawei.secure.android.common.encrypt.d;

import android.annotation.SuppressLint;
import android.os.Build;

/* loaded from: classes12.dex */
public class d {
    private static final String b = "RootKeyUtil";
    private byte[] a = null;

    private d() {
    }

    private void a(String str, String str2, String str3, String str4) {
        b(str, str2, str3, c.b(str4));
    }

    @SuppressLint({"NewApi"})
    private void b(String str, String str2, String str3, byte[] bArr) {
        if (Build.VERSION.SDK_INT < 26) {
            f.d(b, "initRootKey: sha1");
            this.a = a.h(str, str2, str3, bArr, false);
            return;
        }
        f.d(b, "initRootKey: sha256");
        this.a = a.h(str, str2, str3, bArr, true);
    }

    public static d d(String str, String str2, String str3, String str4) {
        d dVar = new d();
        dVar.a(str, str2, str3, str4);
        return dVar;
    }

    public byte[] c() {
        return (byte[]) this.a.clone();
    }
}
