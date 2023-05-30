package jd.wjlogin_sdk.common;

import android.app.Application;
import android.content.Context;
import jd.wjlogin_sdk.model.ClientInfo;
import jd.wjlogin_sdk.util.c0;
import jd.wjlogin_sdk.util.k;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.v;

/* loaded from: classes.dex */
public final class WJLoginHelper extends WJLoginForThirdParty {
    private static final String w = "WJLogin.WJLoginHelper";
    static WJLoginHelper x;

    protected WJLoginHelper() {
    }

    public static void clearWJLoginHelperInstance() {
        x = null;
    }

    public static synchronized WJLoginHelper createInstance(Context context, ClientInfo clientInfo) {
        WJLoginHelper createInstance;
        synchronized (WJLoginHelper.class) {
            createInstance = createInstance(context, clientInfo, false);
        }
        return createInstance;
    }

    public static void testLocal(Context context, boolean z) {
        k.a(context, z);
    }

    public static WJLoginHelper z() {
        return x;
    }

    public static synchronized WJLoginHelper createInstance(Context context, ClientInfo clientInfo, boolean z) {
        WJLoginHelper wJLoginHelper;
        synchronized (WJLoginHelper.class) {
            if (context == null) {
                try {
                    p.a(w, "happened fatal cause,context is null!!!!!!!");
                } catch (Throwable th) {
                    p.a(w, "createInstance happened something wrong!");
                    th.printStackTrace();
                    c0.a((short) jd.wjlogin_sdk.util.d.i0, "WJLoginFactory.createInstance happened Throwable@@@" + th.getMessage());
                    return x;
                }
            }
            if (b.a() == null) {
                if (context instanceof Application) {
                    b.a = context;
                    p.b(w, "use Application");
                } else if (context != null) {
                    b.a = context.getApplicationContext();
                    p.b(w, "use getApplicationContext");
                }
                v.a(b.a());
            }
            if (x == null) {
                x = new WJLoginHelper();
                p.a(z);
                p.b(w, "createInstance ok");
                try {
                    jd.wjlogin_sdk.util.g.a(clientInfo);
                    jd.wjlogin_sdk.util.g.a();
                    c.h();
                } catch (Throwable unused) {
                }
                x.v();
            }
            wJLoginHelper = x;
        }
        return wJLoginHelper;
    }
}
