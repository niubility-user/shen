package c.t.m.g;

import java.io.Closeable;

/* loaded from: classes.dex */
public class j0 {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
