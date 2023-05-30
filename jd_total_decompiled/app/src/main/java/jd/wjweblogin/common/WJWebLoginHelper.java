package jd.wjweblogin.common;

import android.app.Application;
import android.content.Context;
import jd.wjweblogin.common.inland.WJWebLoginInland;
import jd.wjweblogin.d.d;
import jd.wjweblogin.d.e;
import jd.wjweblogin.d.g;
import jd.wjweblogin.d.i;
import jd.wjweblogin.model.WJClientParams;

/* loaded from: classes11.dex */
public final class WJWebLoginHelper extends WJWebLoginInland {

    /* renamed from: i  reason: collision with root package name */
    private static final String f20014i = "WJWebLogin.WJWebLoginHelper";

    /* renamed from: j  reason: collision with root package name */
    static WJWebLoginHelper f20015j;

    protected WJWebLoginHelper() {
    }

    public static void clearWJLoginHelperInstance() {
        f20015j = null;
    }

    public static synchronized WJWebLoginHelper createInstance(Context context, WJClientParams wJClientParams) {
        WJWebLoginHelper createInstance;
        synchronized (WJWebLoginHelper.class) {
            createInstance = createInstance(context, wJClientParams, false);
        }
        return createInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WJWebLoginHelper h() {
        return f20015j;
    }

    public static void testLocal(Context context, boolean z) {
        e.a(context, z);
    }

    public static synchronized WJWebLoginHelper createInstance(Context context, WJClientParams wJClientParams, boolean z) {
        WJWebLoginHelper wJWebLoginHelper;
        synchronized (WJWebLoginHelper.class) {
            if (context == null) {
                try {
                    g.a(f20014i, "happened fatal cause,context is null!!!!!!!");
                } catch (Throwable th) {
                    g.a(f20014i, "createInstance happened something wrong!");
                    th.printStackTrace();
                    return f20015j;
                }
            }
            d.a(wJClientParams);
            if (a.a() == null) {
                if (context instanceof Application) {
                    a.a = context;
                    g.b(f20014i, "use Application");
                } else if (context != null) {
                    a.a = context.getApplicationContext();
                    g.b(f20014i, "use getApplicationContext");
                }
                i.a(a.a());
            }
            if (f20015j == null) {
                f20015j = new WJWebLoginHelper();
                g.a(z);
                g.b(f20014i, "createInstance ok");
                f20015j.e();
            }
            wJWebLoginHelper = f20015j;
        }
        return wJWebLoginHelper;
    }
}
