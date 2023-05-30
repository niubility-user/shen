package jd.wjlogin_sdk.b;

import android.text.TextUtils;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.w;

/* loaded from: classes.dex */
public class c {
    static volatile String a;

    private static String a() {
        String str = jd.wjlogin_sdk.util.g.a(jd.wjlogin_sdk.common.b.a()) + "#" + System.currentTimeMillis() + "#azje&";
        if (p.b) {
            p.b("Encryptor.originalKey = " + str);
        }
        return b0.d(str);
    }

    public static String b() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String a2 = w.a(jd.wjlogin_sdk.util.e.o, "");
        if (!TextUtils.isEmpty(a2)) {
            a = a2;
        }
        return a2;
    }

    public static String c() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String a2 = w.a(jd.wjlogin_sdk.util.e.o, "");
        if (!TextUtils.isEmpty(a2)) {
            a = a2;
            return a2;
        }
        String a3 = a();
        a = a3;
        w.b(jd.wjlogin_sdk.util.e.o, a3);
        return a3;
    }
}
