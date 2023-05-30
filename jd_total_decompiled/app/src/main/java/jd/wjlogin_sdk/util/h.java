package jd.wjlogin_sdk.util;

import android.content.Context;
import java.util.UUID;

/* loaded from: classes.dex */
public class h {
    protected static UUID a;

    public h(Context context) {
        if (a == null) {
            synchronized (h.class) {
                if (a == null) {
                    try {
                        a = UUID.randomUUID();
                    } catch (Exception e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        }
    }

    public UUID a() {
        return a;
    }
}
