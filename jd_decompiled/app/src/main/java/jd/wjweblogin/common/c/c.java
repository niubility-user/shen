package jd.wjweblogin.common.c;

import android.text.TextUtils;
import java.util.Date;
import jd.wjweblogin.common.DevelopType;
import jd.wjweblogin.common.WJNativeLoginProxy;
import jd.wjweblogin.common.WJNetworkParamsProxy;
import jd.wjweblogin.common.WJWebLoginExtendProxy;
import jd.wjweblogin.d.f;
import jd.wjweblogin.d.g;
import jd.wjweblogin.d.k;
import jd.wjweblogin.model.WJWebLoginSigInfo;

/* loaded from: classes11.dex */
public class c {

    /* renamed from: f */
    private static final String f20018f = "WJWebLogin.WJLoginBase";
    protected final Object a = new Object();
    private WJWebLoginExtendProxy b;

    /* renamed from: c */
    private WJNetworkParamsProxy f20019c;
    private WJNativeLoginProxy d;

    /* renamed from: e */
    private b f20020e;

    public void a(WJWebLoginSigInfo wJWebLoginSigInfo) {
        d().a(wJWebLoginSigInfo);
    }

    public boolean b() {
        synchronized (this.a) {
            try {
                try {
                    WJWebLoginSigInfo b = d().b();
                    if (b != null && !TextUtils.isEmpty(b.getPtKey())) {
                        return ((int) ((new Date().getTime() - b.getPtKeyCreateDate().getTime()) / 1000)) >= b.getPtKeyRefreshTime();
                    }
                    return true;
                } catch (Exception unused) {
                    return true;
                }
            } finally {
            }
        }
    }

    public boolean c() {
        synchronized (this.a) {
            try {
                try {
                    WJWebLoginSigInfo b = d().b();
                    if (b != null && !TextUtils.isEmpty(b.getPtKey())) {
                        return ((int) ((new Date().getTime() - b.getPtKeyCreateDate().getTime()) / 1000)) >= b.getPtKeyTimeOut();
                    }
                    return true;
                } catch (Exception unused) {
                    return true;
                }
            } finally {
            }
        }
    }

    public void clearLocalWebLoginStatus() {
        synchronized (this.a) {
            g.b(f20018f, "clearLocalWebLoginStatus");
            d().a();
            k.a();
        }
    }

    public void clearLocalWebLoginUser() {
        synchronized (this.a) {
            g.b(f20018f, "clearLocalWebLoginStatus");
            d().a();
        }
    }

    public b d() {
        if (this.f20020e == null) {
            this.f20020e = new b(this.a);
        }
        return this.f20020e;
    }

    public void e() {
        synchronized (this.a) {
            g.b(f20018f, " init getWebUserManager().init() ");
            d().e();
        }
    }

    public void enableLog(boolean z) {
        g.a(z);
    }

    protected boolean f() {
        boolean z;
        synchronized (this.a) {
            WJWebLoginSigInfo b = d().b();
            z = (b == null || TextUtils.isEmpty(b.getPtKey())) ? false : true;
        }
        return z;
    }

    public String getMid() {
        synchronized (this.a) {
            WJWebLoginSigInfo b = d().b();
            if (b != null) {
                String mid = b.getMid();
                if (TextUtils.isEmpty(mid)) {
                    mid = "";
                }
                return mid;
            }
            return "";
        }
    }

    public WJNativeLoginProxy getNativeLoginProxy() {
        return this.d;
    }

    public WJNetworkParamsProxy getNetworkParamsProxy() {
        return this.f20019c;
    }

    public WJWebLoginExtendProxy getProxy() {
        return this.b;
    }

    public String getPtKey() {
        synchronized (this.a) {
            WJWebLoginSigInfo b = d().b();
            if (b != null) {
                String ptKey = b.getPtKey();
                if (TextUtils.isEmpty(ptKey)) {
                    ptKey = "";
                }
                return ptKey;
            }
            return "";
        }
    }

    public String getPtPin() {
        synchronized (this.a) {
            WJWebLoginSigInfo b = d().b();
            if (b != null && !TextUtils.isEmpty(b.getPtPin())) {
                String ptPin = b.getPtPin();
                g.b(f20018f, "getPtPin encodePin=" + ptPin);
                if (!TextUtils.isEmpty(ptPin)) {
                    g.b(f20018f, "getPtPin pin=" + new String(jd.wjweblogin.d.b.a(ptPin)));
                    String a = jd.wjweblogin.b.b.a(ptPin);
                    g.b(f20018f, "getPtPin ptPin=" + a);
                    if (TextUtils.isEmpty(a)) {
                        a = "";
                    }
                    return a;
                }
            }
            return "";
        }
    }

    public void refreshWebLoginStatus() {
        g.b(f20018f, "refreshCookieStatus begin = " + System.currentTimeMillis());
        try {
            synchronized (this.a) {
                d().g();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        g.b(f20018f, "refreshCookieStatus end = " + System.currentTimeMillis());
    }

    public void setDevelop(int i2) {
        DevelopType.setDebugModel(i2);
    }

    public void setExtendProxy(WJWebLoginExtendProxy wJWebLoginExtendProxy) {
        this.b = wJWebLoginExtendProxy;
    }

    public void setNativeLoginProxy(WJNativeLoginProxy wJNativeLoginProxy) {
        this.d = wJNativeLoginProxy;
    }

    public void setNetworkParamsProxy(WJNetworkParamsProxy wJNetworkParamsProxy) {
        this.f20019c = wJNetworkParamsProxy;
    }

    public boolean a() {
        synchronized (this.a) {
            try {
                try {
                    WJWebLoginSigInfo b = d().b();
                    if (b == null) {
                        return true;
                    }
                    return ((int) ((new Date().getTime() - b.getSetCookieDate().getTime()) / 1000)) >= b.gettCookie();
                } catch (Exception unused) {
                    return true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void clearLocalWebLoginStatus(int i2) {
        f.a(String.valueOf(i2));
        synchronized (this.a) {
            g.b(f20018f, "clearLocalWebLoginStatus");
            d().a();
            k.a();
        }
    }
}
