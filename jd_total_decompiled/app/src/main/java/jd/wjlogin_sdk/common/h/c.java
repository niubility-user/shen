package jd.wjlogin_sdk.common.h;

import android.text.TextUtils;
import android.util.Pair;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Date;
import java.util.List;
import jd.wjlogin_sdk.common.WJDGuardProxy;
import jd.wjlogin_sdk.common.WJLoginAntiRpIdProxy;
import jd.wjlogin_sdk.common.WJLoginClientInfoProxy;
import jd.wjlogin_sdk.common.WJLoginElderProxy;
import jd.wjlogin_sdk.common.WJLoginExtendProxy;
import jd.wjlogin_sdk.common.WJLoginInternationalExtendProxy;
import jd.wjlogin_sdk.common.WJLoginPrivacyProxy;
import jd.wjlogin_sdk.common.WjLoginHttpDnsProxy;
import jd.wjlogin_sdk.common.g;
import jd.wjlogin_sdk.common.listener.OnCommonCallback;
import jd.wjlogin_sdk.model.ErrorResult;
import jd.wjlogin_sdk.model.FailResult;
import jd.wjlogin_sdk.model.IpModel;
import jd.wjlogin_sdk.model.WUserSigInfo;
import jd.wjlogin_sdk.net.b;
import jd.wjlogin_sdk.tlvtype.n0;
import jd.wjlogin_sdk.tlvtype.s0;
import jd.wjlogin_sdk.util.ByteUtil;
import jd.wjlogin_sdk.util.b0;
import jd.wjlogin_sdk.util.c0;
import jd.wjlogin_sdk.util.l;
import jd.wjlogin_sdk.util.m;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.r;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class c extends g {
    private static final String q = "WJLogin.WJLoginBase";

    /* renamed from: f */
    protected WJLoginPrivacyProxy f19784f;

    /* renamed from: g */
    protected WJLoginClientInfoProxy f19785g;

    /* renamed from: h */
    protected WJDGuardProxy f19786h;

    /* renamed from: l */
    private jd.wjlogin_sdk.common.h.b f19790l;

    /* renamed from: n */
    private WJLoginAntiRpIdProxy f19792n;
    protected String o;
    protected boolean p;
    protected final Object b = new Object();

    /* renamed from: c */
    protected WJLoginExtendProxy f19782c = null;
    protected WjLoginHttpDnsProxy d = null;

    /* renamed from: e */
    protected WJLoginElderProxy f19783e = null;

    /* renamed from: i */
    protected long f19787i = 0;

    /* renamed from: j */
    private String f19788j = "";

    /* renamed from: k */
    private String f19789k = "";

    /* renamed from: m */
    private String f19791m = "";

    /* loaded from: classes.dex */
    public class a implements jd.wjlogin_sdk.c.f {
        a() {
            c.this = r1;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            c.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            c.this.b((byte) -1, (short) 3, (short) 3);
        }
    }

    /* loaded from: classes.dex */
    public class b implements jd.wjlogin_sdk.c.f {
        b() {
            c.this = r1;
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(Pair<Byte, jd.wjlogin_sdk.tlvtype.a> pair) {
            c.this.a(((Byte) pair.first).byteValue(), (jd.wjlogin_sdk.tlvtype.a) pair.second);
        }

        @Override // jd.wjlogin_sdk.c.f
        public void a(ErrorResult errorResult) {
            c.this.b((byte) -1, (short) 3, (short) 3);
        }
    }

    /* renamed from: jd.wjlogin_sdk.common.h.c$c */
    /* loaded from: classes.dex */
    public class RunnableC0846c implements Runnable {
        final /* synthetic */ jd.wjlogin_sdk.d.b a;

        RunnableC0846c(jd.wjlogin_sdk.d.b bVar) {
            c.this = r1;
            this.a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.d()).a(jd.wjlogin_sdk.util.b.a(this.a.a()).getBytes()).a().b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements Runnable {
        final /* synthetic */ String a;

        d(String str) {
            c.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.d()).a(this.a.getBytes()).a().b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements Runnable {
        final /* synthetic */ String a;

        e(String str) {
            c.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.d()).a(this.a.getBytes()).a().b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    class f implements Runnable {
        final /* synthetic */ String a;

        f(String str) {
            c.this = r1;
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                new b.C0849b().a(jd.wjlogin_sdk.util.e0.b.d()).a(this.a.getBytes()).a().b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void e() {
        jd.wjlogin_sdk.common.communion.b.a();
    }

    private jd.wjlogin_sdk.common.h.b t() {
        if (this.f19790l == null) {
            this.f19790l = new jd.wjlogin_sdk.common.h.b(this.b);
        }
        return this.f19790l;
    }

    private void w() {
        String pin;
        c0.a(getUserAccount());
        WUserSigInfo b2 = t().b();
        if (b2 == null || (pin = b2.getPin()) == null) {
            return;
        }
        c0.b(pin);
    }

    public boolean b() {
        synchronized (this.b) {
            try {
                try {
                    WUserSigInfo b2 = t().b();
                    if (b2 != null && !TextUtils.isEmpty(b2.getA2())) {
                        return ((int) ((new Date().getTime() - b2.getA2CreateDate().getTime()) / 1000)) >= b2.getA2RefreshTime();
                    }
                    return false;
                } catch (Exception unused) {
                    return false;
                }
            } finally {
            }
        }
    }

    public boolean c() {
        synchronized (this.b) {
            try {
                try {
                    WUserSigInfo b2 = t().b();
                    if (b2 != null && !TextUtils.isEmpty(b2.getA2())) {
                        return ((int) ((new Date().getTime() - b2.getA2CreateDate().getTime()) / 1000)) >= b2.getA2TimeOut();
                    }
                    return true;
                } catch (Exception unused) {
                    return true;
                }
            } finally {
            }
        }
    }

    public void clearA4() {
        if (p.b) {
            p.b(q, "clearLocalA4");
        }
        t().a();
    }

    public void clearAntiCrawlerToken() {
        synchronized (this.b) {
            this.o = null;
        }
    }

    public void clearLocalOnlineState() {
        synchronized (this.b) {
            p.b(q, "clearLocalOnlineState");
            t().b(t().b());
            clearA4();
            c0.a();
            e();
        }
    }

    public boolean d() {
        try {
            jd.wjlogin_sdk.common.a f2 = f();
            if (f2 != null && !TextUtils.isEmpty(f2.b())) {
                return ((int) ((new Date().getTime() - f2.c().getTime()) / 1000)) >= f2.e();
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public void deleteUserByPin(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            String parseByte2HexStr = ByteUtil.parseByte2HexStr(str.getBytes());
            if (b2 != null && parseByte2HexStr.equals(b2.getPin())) {
                exitLogin();
            } else {
                WUserSigInfo b3 = t().b(parseByte2HexStr);
                if (b3 != null && !TextUtils.isEmpty(b3.getPin())) {
                    t().a(b3.getPin());
                    a(str, b3.getA2());
                }
            }
        }
    }

    public void exitLogin() {
        try {
            try {
                if (p.b) {
                    p.b(q, "exitLogin");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 3, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            String pin = getPin();
            String str = "";
            if (pin == null) {
                pin = "";
            }
            jd.wjlogin_sdk.d.d.a(bVar, pin);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            String a2 = getA2();
            if (a2 != null) {
                str = a2;
            }
            jd.wjlogin_sdk.d.d.y(bVar, str);
            jd.wjlogin_sdk.d.d.w(bVar, jd.wjlogin_sdk.common.communion.b.b());
            clearLocalOnlineState();
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new b());
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("exitLogin");
            gVar.b();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public jd.wjlogin_sdk.common.a f() {
        return t().c();
    }

    public String g() {
        WJLoginExtendProxy wJLoginExtendProxy = this.f19782c;
        if (wJLoginExtendProxy == null) {
            p.b(q, "getArea() null == mProxy ");
            return "";
        }
        String area = wJLoginExtendProxy.getArea();
        p.b(q, "getArea() area =  " + area);
        return TextUtils.isEmpty(area) ? "" : area;
    }

    public String getA2() {
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            if (b2 != null) {
                String a2 = b2.getA2();
                if (TextUtils.isEmpty(a2)) {
                    a2 = "";
                }
                return a2;
            }
            return "";
        }
    }

    public String getAntiCrawlerToken() {
        String str;
        synchronized (this.b) {
            str = this.o;
        }
        return str;
    }

    public String getAntiRpId() {
        try {
            WJLoginAntiRpIdProxy wJLoginAntiRpIdProxy = this.f19792n;
            return wJLoginAntiRpIdProxy != null ? wJLoginAntiRpIdProxy.getAntiCrawlerRpId() : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public WJLoginAntiRpIdProxy getAntiRpIdProxy() {
        return this.f19792n;
    }

    public WJLoginClientInfoProxy getClientInfoProxy() {
        return this.f19785g;
    }

    public JSONObject getConfig() {
        return jd.wjlogin_sdk.config.a.c().a();
    }

    public String getCountryCode() {
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            if (b2 != null) {
                String countryCode = b2.getCountryCode();
                if (TextUtils.isEmpty(countryCode)) {
                    countryCode = "";
                }
                return countryCode;
            }
            return "";
        }
    }

    public String getDebugUrl() {
        return this.f19791m;
    }

    public WJLoginElderProxy getElderProxy() {
        return this.f19783e;
    }

    public String getHashedEmail() {
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            if (b2 != null) {
                String hashedEmail = b2.getHashedEmail();
                if (TextUtils.isEmpty(hashedEmail)) {
                    hashedEmail = "";
                }
                return hashedEmail;
            }
            return "";
        }
    }

    public String getHashedPin() {
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            if (b2 != null) {
                String hashedPin = b2.getHashedPin();
                if (TextUtils.isEmpty(hashedPin)) {
                    hashedPin = "";
                }
                return hashedPin;
            }
            return "";
        }
    }

    public void getLoginConfig() {
        String u = u();
        jd.wjlogin_sdk.config.a.c().b(getPin(), u);
    }

    public String getLoginURL() {
        return this.f19788j;
    }

    public String getPin() {
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            if (b2 != null) {
                String pin = b2.getPin();
                if (!TextUtils.isEmpty(pin)) {
                    String str = new String(ByteUtil.parseHexStr2Byte(pin));
                    if (TextUtils.isEmpty(str)) {
                        str = "";
                    }
                    return str;
                }
            }
            return "";
        }
    }

    public WJLoginPrivacyProxy getPrivacyProxy() {
        return this.f19784f;
    }

    public String getReportURL() {
        return this.f19789k;
    }

    public String getUserAccount() {
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            if (b2 != null) {
                String account = b2.getAccount();
                if (TextUtils.isEmpty(account)) {
                    account = "";
                }
                return account;
            }
            return "";
        }
    }

    public WJDGuardProxy getWJdGuardProxy() {
        return this.f19786h;
    }

    public String h() {
        WJLoginExtendProxy wJLoginExtendProxy = this.f19782c;
        if (wJLoginExtendProxy == null) {
            return "";
        }
        String deviceFinger = wJLoginExtendProxy.getDeviceFinger();
        return TextUtils.isEmpty(deviceFinger) ? "" : deviceFinger;
    }

    public void handleHeaderByteBelow31(byte[] bArr) {
        try {
            int currentTimeMillis = (int) (System.currentTimeMillis() - this.a);
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 256, (short) 256, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            String str = "";
            jd.wjlogin_sdk.d.d.a(bVar, bArr == null ? "" : ByteUtil.parseByte2HexStr(bArr));
            jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, (byte) 0);
            String userAccount = getUserAccount();
            if (TextUtils.isEmpty(userAccount)) {
                String pin = getPin();
                if (!TextUtils.isEmpty(pin)) {
                    userAccount = pin;
                }
            }
            if (userAccount != null) {
                str = userAccount;
            }
            jd.wjlogin_sdk.d.d.d(bVar, str);
            jd.wjlogin_sdk.net.d.a().a(new RunnableC0846c(bVar));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean hasLogin() {
        return (TextUtils.isEmpty(getPin()) || TextUtils.isEmpty(getA2())) ? false : true;
    }

    public int i() {
        WJLoginElderProxy wJLoginElderProxy = this.f19783e;
        if (wJLoginElderProxy == null) {
            return 0;
        }
        String elderUemps = wJLoginElderProxy.getElderUemps();
        p.b(q, "getElderUemps=" + elderUemps);
        return "1".equals(elderUemps) ? 1 : 0;
    }

    public boolean isEnterLogined() {
        return this.p;
    }

    public boolean isExistsA2() {
        boolean z;
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            z = (b2 == null || TextUtils.isEmpty(b2.getA2())) ? false : true;
        }
        return z;
    }

    public String j() {
        WJLoginClientInfoProxy wJLoginClientInfoProxy = this.f19785g;
        if (wJLoginClientInfoProxy == null) {
            p.b(q, "getJDDeviceBrand() null == clientInfoProxy ");
            return "";
        }
        String jDDeviceBrand = wJLoginClientInfoProxy.getJDDeviceBrand();
        p.b(q, "getJDDeviceBrand =  " + jDDeviceBrand);
        return TextUtils.isEmpty(jDDeviceBrand) ? "" : jDDeviceBrand;
    }

    public String k() {
        WJLoginClientInfoProxy wJLoginClientInfoProxy = this.f19785g;
        if (wJLoginClientInfoProxy == null) {
            p.b(q, "getJDDeviceModel() null == clientInfoProxy ");
            return "";
        }
        String jDDeviceModel = wJLoginClientInfoProxy.getJDDeviceModel();
        p.b(q, "getJDDeviceModel =  " + jDDeviceModel);
        return TextUtils.isEmpty(jDDeviceModel) ? "" : jDDeviceModel;
    }

    public String l() {
        WJLoginClientInfoProxy wJLoginClientInfoProxy = this.f19785g;
        if (wJLoginClientInfoProxy == null) {
            p.b(q, "getJDDeviceName() null == clientInfoProxy ");
            return "";
        }
        String jDDeviceName = wJLoginClientInfoProxy.getJDDeviceName();
        p.b(q, "getJDDeviceName =  " + jDDeviceName);
        return TextUtils.isEmpty(jDDeviceName) ? "" : jDDeviceName;
    }

    public String m() {
        WJLoginClientInfoProxy wJLoginClientInfoProxy = this.f19785g;
        if (wJLoginClientInfoProxy == null) {
            p.b(q, "getJDOsVer() null == clientInfoProxy ");
            return "";
        }
        String jDOsVer = wJLoginClientInfoProxy.getJDOsVer();
        p.b(q, "getJDOsVer =  " + jDOsVer);
        return TextUtils.isEmpty(jDOsVer) ? "" : jDOsVer;
    }

    public String n() {
        WJLoginClientInfoProxy wJLoginClientInfoProxy = this.f19785g;
        if (wJLoginClientInfoProxy == null) {
            p.b(q, "getJDScreen() null == clientInfoProxy ");
            return "";
        }
        String jDScreen = wJLoginClientInfoProxy.getJDScreen();
        p.b(q, "getJDScreen =  " + jDScreen);
        return TextUtils.isEmpty(jDScreen) ? "" : jDScreen;
    }

    public String o() {
        WJLoginExtendProxy wJLoginExtendProxy = this.f19782c;
        if (wJLoginExtendProxy == null) {
            p.b(q, "getJMAFinger() null == mProxy ");
            return "";
        }
        String jMAFinger = wJLoginExtendProxy.getJMAFinger();
        p.b(q, "getJMAFinger() jma =  " + jMAFinger);
        return TextUtils.isEmpty(jMAFinger) ? "" : jMAFinger;
    }

    public String p() {
        try {
            WJLoginExtendProxy wJLoginExtendProxy = this.f19782c;
            if (wJLoginExtendProxy != null && (wJLoginExtendProxy instanceof WJLoginInternationalExtendProxy)) {
                String languageCode = ((WJLoginInternationalExtendProxy) wJLoginExtendProxy).getLanguageCode();
                if (!TextUtils.isEmpty(languageCode)) {
                    p.b(q, "ExtendProxy.currentLanguage = " + languageCode);
                    return languageCode;
                }
            }
            return m.b(jd.wjlogin_sdk.common.b.a());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String q() {
        try {
            WJLoginExtendProxy wJLoginExtendProxy = this.f19782c;
            if (wJLoginExtendProxy == null || !(wJLoginExtendProxy instanceof WJLoginInternationalExtendProxy)) {
                return "";
            }
            String languageCode = ((WJLoginInternationalExtendProxy) wJLoginExtendProxy).getLanguageCode();
            if (TextUtils.isEmpty(languageCode)) {
                return "";
            }
            p.b(q, "ExtendProxy.currentLanguage = " + languageCode);
            return languageCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public List<WUserSigInfo> r() {
        return t().f();
    }

    public void refreshLoginStatus() {
        p.b(q, "refreshLoginStatus begin = " + System.currentTimeMillis());
        try {
            synchronized (this.b) {
                t().j();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        p.b(q, "refreshLoginStatus end = " + System.currentTimeMillis());
    }

    public void reportCommand(short s, String str) {
        if (System.currentTimeMillis() - this.f19787i > 60000) {
            c0.a(s, str);
            this.f19787i = System.currentTimeMillis();
        }
    }

    public void reportNewLoginResult(byte b2, short s, short s2) {
        try {
            String userAccount = getUserAccount();
            if (TextUtils.isEmpty(userAccount)) {
                String pin = getPin();
                if (!TextUtils.isEmpty(pin)) {
                    userAccount = pin;
                }
            }
            if (userAccount == null) {
                userAccount = "";
            }
            b(userAccount, b2, s, s2);
        } catch (Exception unused) {
        }
    }

    public JSONObject s() {
        JSONObject jSONObject = new JSONObject();
        try {
            List<WUserSigInfo> f2 = t().f();
            t().a(f2);
            if (f2 != null && !f2.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < f2.size(); i2++) {
                    WUserSigInfo wUserSigInfo = f2.get(i2);
                    if (wUserSigInfo != null && !TextUtils.isEmpty(wUserSigInfo.getA2())) {
                        jSONArray.put(wUserSigInfo.getA2());
                    }
                }
                jSONObject.put(MobileCertConstants.WSKEY, jSONArray);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void setAntiRpIdProxy(WJLoginAntiRpIdProxy wJLoginAntiRpIdProxy) {
        this.f19792n = wJLoginAntiRpIdProxy;
    }

    public void setClientInfoProxy(WJLoginClientInfoProxy wJLoginClientInfoProxy) {
        this.f19785g = wJLoginClientInfoProxy;
    }

    public void setDebugUrl(String str) {
        this.f19791m = str;
    }

    public void setElderProxy(WJLoginElderProxy wJLoginElderProxy) {
        this.f19783e = wJLoginElderProxy;
    }

    public void setEnterLogined(boolean z) {
        this.p = z;
    }

    public void setPrivacyProxy(WJLoginPrivacyProxy wJLoginPrivacyProxy) {
        this.f19784f = wJLoginPrivacyProxy;
    }

    public void setReportURL(String str) {
        this.f19789k = str;
    }

    public void setWJLoginExtendProxy(WJLoginExtendProxy wJLoginExtendProxy) {
        this.f19782c = wJLoginExtendProxy;
    }

    public void setWJLoginHttpDnsProxy(WjLoginHttpDnsProxy wjLoginHttpDnsProxy) {
        this.d = wjLoginHttpDnsProxy;
    }

    public void setWJdGuardProxy(WJDGuardProxy wJDGuardProxy) {
        this.f19786h = wJDGuardProxy;
    }

    public void switchUserByPin(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.b) {
            WUserSigInfo b2 = t().b(ByteUtil.parseByte2HexStr(str.getBytes()));
            if (b2 == null) {
                return;
            }
            t().a(b2, true);
            refreshLoginStatus();
            clearA4();
        }
    }

    public String u() {
        WJLoginExtendProxy wJLoginExtendProxy = this.f19782c;
        if (wJLoginExtendProxy == null) {
            p.b(q, "getUuid() null == mProxy ");
            return "";
        }
        String uuid = wJLoginExtendProxy.getUuid();
        p.b(q, "getUuid() uuid =  " + uuid);
        return TextUtils.isEmpty(uuid) ? "" : uuid;
    }

    public void v() {
        synchronized (this.b) {
            if (p.b && jd.wjlogin_sdk.util.g.d() != null) {
                p.b(q, " init APP getLastUpdateTime()=" + jd.wjlogin_sdk.util.g.d().k());
                p.b(q, " init APP getFirstInstallTime()=" + jd.wjlogin_sdk.util.g.d().j());
            }
            if (!t().i() && jd.wjlogin_sdk.util.g.d() != null && 0 != jd.wjlogin_sdk.util.g.d().k() && 0 != jd.wjlogin_sdk.util.g.d().j() && jd.wjlogin_sdk.util.g.d().k() != jd.wjlogin_sdk.util.g.d().j()) {
                p.b(q, " init copy= ");
                jd.wjlogin_sdk.common.d dVar = new jd.wjlogin_sdk.common.d();
                dVar.a();
                t().c(dVar.a);
                dVar.h();
                dVar.g();
            } else {
                p.b(q, " init getUserManager().init() ");
                t().g();
            }
            w();
        }
    }

    public boolean x() {
        return jd.wjlogin_sdk.config.a.c().g();
    }

    public boolean y() {
        WJLoginPrivacyProxy wJLoginPrivacyProxy = this.f19784f;
        if (wJLoginPrivacyProxy == null) {
            return true;
        }
        return wJLoginPrivacyProxy.isWJAgreePrivacy();
    }

    public void a(jd.wjlogin_sdk.common.a aVar) {
        t().a(aVar);
    }

    private void a(String str, String str2) {
        try {
            try {
                if (p.b) {
                    p.b(q, "exitLogin");
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.seq++;
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a((short) 3, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            if (str == null) {
                str = "";
            }
            jd.wjlogin_sdk.d.d.a(bVar, str);
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            if (str2 == null) {
                str2 = "";
            }
            jd.wjlogin_sdk.d.d.y(bVar, str2);
            this.a = System.currentTimeMillis();
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(new a());
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a("exitLogin");
            gVar.b();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void getLoginConfig(OnCommonCallback onCommonCallback) {
        String u = u();
        jd.wjlogin_sdk.config.a.c().a(getPin(), u, onCommonCallback);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void reportNewLoginResult(byte b2, int i2) {
        String pin;
        String userAccount;
        try {
            int currentTimeMillis = (int) (System.currentTimeMillis() - this.a);
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            short s = 2;
            if (i2 != 1 && i2 == 2) {
                bVar.a(jd.wjlogin_sdk.d.d.a(jd.wjlogin_sdk.util.d.x, s, jd.wjlogin_sdk.util.g.d(), this.seq));
                jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
                jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
                pin = getPin();
                String str = "";
                if (pin == null) {
                    pin = "";
                }
                jd.wjlogin_sdk.d.d.a(bVar, pin);
                jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, b2);
                userAccount = getUserAccount();
                if (userAccount == null) {
                    str = userAccount;
                }
                jd.wjlogin_sdk.d.d.d(bVar, str);
                jd.wjlogin_sdk.net.d.a().a(new f(jd.wjlogin_sdk.util.b.a(bVar.a())));
            }
            s = 1;
            bVar.a(jd.wjlogin_sdk.d.d.a(jd.wjlogin_sdk.util.d.x, s, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            pin = getPin();
            String str2 = "";
            if (pin == null) {
            }
            jd.wjlogin_sdk.d.d.a(bVar, pin);
            jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, b2);
            userAccount = getUserAccount();
            if (userAccount == null) {
            }
            jd.wjlogin_sdk.d.d.d(bVar, str2);
            jd.wjlogin_sdk.net.d.a().a(new f(jd.wjlogin_sdk.util.b.a(bVar.a())));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b(byte b2, short s, short s2) {
        try {
            String userAccount = getUserAccount();
            if (TextUtils.isEmpty(userAccount)) {
                String pin = getPin();
                if (!TextUtils.isEmpty(pin)) {
                    userAccount = pin;
                }
            }
            if (userAccount == null) {
                userAccount = "";
            }
            a(userAccount, b2, s, s2);
        } catch (Exception unused) {
        }
    }

    public void a(byte b2, jd.wjlogin_sdk.tlvtype.a aVar) {
        try {
            if (b2 == 0) {
                b(b2, (short) 3, (short) 3);
                return;
            }
            a(new FailResult(), b2, aVar.p());
            b(b2, (short) 3, (short) 3);
        } catch (Exception unused) {
            b((byte) -2, (short) 3, (short) 3);
        }
    }

    public void b(String str, String str2, byte b2, short s, short s2) {
        a(str, str2, b2, s, s2);
    }

    private void b(jd.wjlogin_sdk.tlvtype.a aVar) {
        jd.wjlogin_sdk.common.communion.b.a(aVar);
    }

    public void b(jd.wjlogin_sdk.d.b bVar, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.c.f fVar, String str) {
        try {
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(fVar);
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(bVar.b()).a(str);
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void a(jd.wjlogin_sdk.tlvtype.a aVar) {
        n0 J = aVar.J();
        s0 N = aVar.N();
        if (J == null || N == null) {
            return;
        }
        synchronized (this.b) {
            WUserSigInfo b2 = t().b();
            if (b2 == null) {
                b2 = new WUserSigInfo();
            }
            b2.setA2(J.a());
            b2.setA2CreateDate(new Date());
            b2.setA2RefreshTime(N.a());
            b2.setA2TimeOut(N.b());
            t().c(b2);
            b(aVar);
        }
    }

    protected void b(String str, byte b2, short s, short s2) {
        try {
            if (p.b) {
                p.b(q, "start reportNewLoginResult strAccount = " + str + " cReplyCode = " + ((int) b2) + " cmd = " + ((int) s) + " subcmd = " + ((int) s2));
            }
            int currentTimeMillis = (int) (System.currentTimeMillis() - this.a);
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a(jd.wjlogin_sdk.util.d.x, (short) 3, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            String pin = getPin();
            if (pin == null) {
                pin = "";
            }
            jd.wjlogin_sdk.d.d.a(bVar, pin);
            jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, b2);
            if (str == null) {
                str = "";
            }
            jd.wjlogin_sdk.d.d.d(bVar, str + "@@@" + ((int) s) + CartConstant.KEY_YB_INFO_LINK + ((int) s2));
            jd.wjlogin_sdk.net.d.a().a(new e(jd.wjlogin_sdk.util.b.a(bVar.a())));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(jd.wjlogin_sdk.tlvtype.a aVar, String str, String str2) {
        synchronized (this.b) {
            WUserSigInfo wUserSigInfo = new WUserSigInfo();
            if (aVar.e() != null) {
                str = aVar.e().a();
            }
            wUserSigInfo.setAccount(str);
            if (str2 == null) {
                str2 = "";
            }
            wUserSigInfo.setCountryCode(str2);
            if (aVar.J() != null) {
                wUserSigInfo.setA2(aVar.J().a());
                wUserSigInfo.setA2CreateDate(new Date());
            }
            if (aVar.N() != null) {
                wUserSigInfo.setA2RefreshTime(aVar.N().a());
                wUserSigInfo.setA2TimeOut(aVar.N().b());
            }
            if (aVar.a() != null) {
                String a2 = aVar.a().a();
                wUserSigInfo.setPin(a2);
                c0.b(a2);
            }
            if (aVar.G() != null) {
                wUserSigInfo.setHashedEmail(aVar.G().a());
            }
            if (aVar.H() != null) {
                wUserSigInfo.setHashedPin(aVar.H().a());
            }
            c0.a(str);
            wUserSigInfo.setCurrentMainUser(true);
            t().c(wUserSigInfo);
            b(aVar);
            if (aVar.Q() != null) {
                this.o = aVar.Q().a();
                if (p.b) {
                    p.b(q, "SaveUserInfo antiCrawlerToken=" + this.o);
                }
            }
        }
    }

    public void b(String str, JSONObject jSONObject) {
        try {
            if (p.b) {
                p.b(str, "request: " + l.a(jSONObject));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public WUserSigInfo b(String str) {
        return t().b(ByteUtil.parseByte2HexStr(str.getBytes()));
    }

    public void a(jd.wjlogin_sdk.tlvtype.a aVar, String str) {
        a(aVar, str, "");
    }

    public void a(String str, byte b2, short s, short s2) {
        a(getPin(), str, b2, s, s2);
    }

    private void a(String str, String str2, byte b2, short s, short s2) {
        try {
            if (p.b) {
                p.b(q, "start reportLoginResult strAccount = " + str2 + " cReplyCode = " + ((int) b2) + " cmd = " + ((int) s) + " subcmd = " + ((int) s2));
            }
            int currentTimeMillis = (int) (System.currentTimeMillis() - this.a);
            jd.wjlogin_sdk.d.b bVar = new jd.wjlogin_sdk.d.b();
            bVar.a(jd.wjlogin_sdk.d.d.a(s, s2, jd.wjlogin_sdk.util.g.d(), this.seq));
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.e());
            jd.wjlogin_sdk.d.d.a(bVar, jd.wjlogin_sdk.util.g.d(), r.b(jd.wjlogin_sdk.common.b.a()));
            if (TextUtils.isEmpty(str)) {
                str = getPin();
            }
            if (str == null) {
                str = "";
            }
            jd.wjlogin_sdk.d.d.a(bVar, str);
            jd.wjlogin_sdk.d.d.a(bVar, currentTimeMillis, b2);
            if (str2 == null) {
                str2 = "";
            }
            jd.wjlogin_sdk.d.d.d(bVar, str2);
            jd.wjlogin_sdk.net.d.a().a(new d(jd.wjlogin_sdk.util.b.a(bVar.a())));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(byte b2, short s, short s2) {
        try {
            if (p.b) {
                p.b(q, "login finish, cmd = " + ((int) s) + " subcmd = " + ((int) s2));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(jd.wjlogin_sdk.d.b bVar, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.c.f fVar, String str) {
        try {
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(fVar);
            gVar.a(bVar.a()).a(x() ? 2 : 1).b(false).b(jd.wjlogin_sdk.util.e0.b.f(jd.wjlogin_sdk.util.e0.c.f19950l)).a(bVar.b()).a(str);
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void a(jd.wjlogin_sdk.d.b bVar, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.c.f fVar, String str, boolean z) {
        try {
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(fVar);
            gVar.a(bVar.a()).a(x() ? 2 : 1).a(z).a(bVar.b()).a(str);
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    protected void a(jd.wjlogin_sdk.d.e.c cVar, OnCommonCallback onCommonCallback, jd.wjlogin_sdk.c.f fVar, String str) {
        try {
            jd.wjlogin_sdk.c.g gVar = new jd.wjlogin_sdk.c.g(fVar);
            gVar.a(cVar.a()).a(x() ? 2 : 1).a(cVar.b()).a(str);
            gVar.b();
        } catch (Exception e2) {
            if (onCommonCallback != null) {
                onCommonCallback.onErrorHandleInner(b0.a(-102, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2));
            }
        }
    }

    public void a(String str, JSONObject jSONObject) {
        try {
            if (p.b) {
                p.b(str, "reponse: " + l.a(jSONObject));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public IpModel a(String str) {
        WjLoginHttpDnsProxy wjLoginHttpDnsProxy = this.d;
        if (wjLoginHttpDnsProxy == null) {
            return null;
        }
        try {
            return wjLoginHttpDnsProxy.getIpModel(str);
        } catch (Exception unused) {
            return null;
        }
    }
}
