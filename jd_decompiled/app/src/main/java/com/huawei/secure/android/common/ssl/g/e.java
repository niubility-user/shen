package com.huawei.secure.android.common.ssl.g;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes12.dex */
public abstract class e {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                f.d("IOUtil", "closeSecure IOException");
            }
        }
    }

    public static void b(InputStream inputStream) {
        a(inputStream);
    }

    public static void c(OutputStream outputStream) {
        a(outputStream);
    }
}
