package jd.wjlogin_sdk.util.f0;

import android.text.TextUtils;
import java.io.Serializable;
import jd.wjlogin_sdk.util.c0;
import jd.wjlogin_sdk.util.d;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.w;

/* loaded from: classes.dex */
public class a {
    private static final String a = "&*!@#$";
    private static final String b = "WJLogin.Store";

    public static <T extends Serializable> T a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            String a2 = w.a(str, "");
            if (!TextUtils.isEmpty(a2)) {
                String a3 = b.a(a, a2);
                if (TextUtils.isEmpty(a3)) {
                    return null;
                }
                T t = (T) c.a(a3);
                if (p.b) {
                    p.b(b, "get cost = " + (System.currentTimeMillis() - currentTimeMillis));
                }
                return t;
            }
        } catch (Throwable th) {
            c0.a((short) d.g0, "SerializableUtil exception2@@@" + th.getMessage());
        }
        return null;
    }
}
