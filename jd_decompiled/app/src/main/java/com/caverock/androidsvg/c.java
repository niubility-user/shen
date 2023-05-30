package com.caverock.androidsvg;

import android.graphics.Canvas;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class c {
    static final int a;
    private static final Method b;

    static {
        try {
            a = ((Integer) Canvas.class.getField("MATRIX_SAVE_FLAG").get(null)).intValue();
            b = Canvas.class.getMethod("save", Integer.TYPE);
        } catch (Throwable th) {
            b(th);
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Canvas canvas, int i2) {
        try {
            b.invoke(canvas, Integer.valueOf(i2));
        } catch (Throwable th) {
            b(th);
            throw null;
        }
    }

    private static RuntimeException b(Throwable th) {
        if (th == null) {
            throw new NullPointerException("t");
        }
        c(th);
        throw null;
    }

    private static <T extends Throwable> T c(Throwable th) throws Throwable {
        throw th;
    }
}
