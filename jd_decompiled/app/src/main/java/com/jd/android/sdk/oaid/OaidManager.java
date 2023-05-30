package com.jd.android.sdk.oaid;

import android.app.Application;
import android.content.Context;
import com.jd.android.sdk.oaid.a.d;
import com.jd.android.sdk.oaid.a.e;
import com.jd.android.sdk.oaid.a.f;
import com.jd.android.sdk.oaid.a.g;
import com.jd.android.sdk.oaid.a.i;
import com.jd.android.sdk.oaid.a.k;
import com.jd.android.sdk.oaid.a.l;
import com.jd.android.sdk.oaid.a.m;
import com.jd.android.sdk.oaid.a.n;
import com.jd.android.sdk.oaid.a.o;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class OaidManager {
    private static final String TAG = "OaidManager";
    private static OaidManager instance;
    private OaidInfo mOaidInfo = new OaidInfo();
    private static AtomicBoolean hasRequested = new AtomicBoolean(false);
    private static boolean isSupport = false;

    private OaidManager() {
    }

    public static OaidManager getInstance() {
        if (instance == null) {
            synchronized (OaidManager.class) {
                if (instance == null) {
                    instance = new OaidManager();
                }
            }
        }
        return instance;
    }

    public static String getLastOAID(Context context) {
        if (context == null) {
            return "";
        }
        c.a(context);
        return c.b("sp-last-oaid", "");
    }

    public static boolean getOAIDStatus() {
        return isSupport;
    }

    public static void setLogPrinterEnable(boolean z) {
        b.a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void storeOaid(Context context, OaidInfo oaidInfo) {
        this.mOaidInfo = oaidInfo;
        if (oaidInfo.isOAIDValid()) {
            c.a(context);
            c.a("sp-last-oaid", oaidInfo.getOAID());
        }
    }

    public OaidInfo getOaidInfo() {
        return this.mOaidInfo;
    }

    public void startRequestOaidInfo(final Context context, final OaidInfoRequestListener oaidInfoRequestListener) {
        a aVar;
        a dVar;
        if (oaidInfoRequestListener == null) {
            return;
        }
        OaidInfo oaidInfo = this.mOaidInfo;
        if (oaidInfo != null && oaidInfo.isOAIDValid()) {
            oaidInfoRequestListener.onResult(this.mOaidInfo);
        } else if (hasRequested.get()) {
            oaidInfoRequestListener.onResult(this.mOaidInfo);
        } else {
            try {
                if (context == null) {
                    aVar = new com.jd.android.sdk.oaid.a.c();
                } else {
                    Context applicationContext = !(context instanceof Application) ? context.getApplicationContext() : context;
                    if (i.a == null) {
                        if (!l.a() && !l.b()) {
                            if (!l.e() && !l.f()) {
                                if (l.d()) {
                                    dVar = new n(applicationContext);
                                } else {
                                    if (!l.c() && !l.g()) {
                                        if (l.i()) {
                                            dVar = new f(applicationContext);
                                        } else if (l.a(applicationContext)) {
                                            dVar = new com.jd.android.sdk.oaid.a.b(applicationContext);
                                        } else if (l.h()) {
                                            dVar = new m(applicationContext);
                                        } else if (l.j()) {
                                            dVar = new e(applicationContext);
                                        } else if (l.k()) {
                                            dVar = new g(applicationContext);
                                        } else if (l.l()) {
                                            dVar = new com.jd.android.sdk.oaid.a.a(applicationContext);
                                        } else if (i.a == null) {
                                            i.a = new com.jd.android.sdk.oaid.a.c();
                                        }
                                    }
                                    dVar = new k(applicationContext);
                                }
                                i.a = dVar;
                            }
                            dVar = new o(applicationContext);
                            i.a = dVar;
                        }
                        dVar = new d(applicationContext);
                        i.a = dVar;
                    }
                    aVar = i.a;
                }
                String str = TAG;
                b.a(str, "ioaid instance : " + aVar.getClass().getName());
                isSupport = aVar.a();
                b.a(str, "isSupport : " + isSupport);
                b.a(str, "getOaid()");
                aVar.a(new OaidInfoRequestListener() { // from class: com.jd.android.sdk.oaid.OaidManager.1
                    @Override // com.jd.android.sdk.oaid.OaidInfoRequestListener
                    public final void onResult(OaidInfo oaidInfo2) {
                        b.a(OaidManager.TAG, "getOaid() onResult oaid : " + oaidInfo2.getOAID());
                        OaidManager.this.storeOaid(context, oaidInfo2);
                        if (oaidInfo2.isOAIDValid() || !OaidManager.isSupport) {
                            OaidManager.hasRequested.set(true);
                        }
                        oaidInfoRequestListener.onResult(OaidManager.this.mOaidInfo);
                    }
                });
            } catch (Throwable th) {
                b.a("BaseInfo SDK", "startRequestOaidInfo Exception: ", th);
            }
        }
    }
}
