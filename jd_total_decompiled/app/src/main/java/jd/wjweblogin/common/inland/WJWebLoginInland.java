package jd.wjweblogin.common.inland;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jd.wjweblogin.common.listener.WJReqWebCookieCallBack;
import jd.wjweblogin.d.g;
import jd.wjweblogin.d.j;
import jd.wjweblogin.d.k;
import jd.wjweblogin.d.l;
import jd.wjweblogin.model.WJErrorResult;
import jd.wjweblogin.model.WJFailResult;
import jd.wjweblogin.model.WJWebLoginSigInfo;

/* loaded from: classes11.dex */
public class WJWebLoginInland extends jd.wjweblogin.common.c.c {

    /* renamed from: h */
    private static final String f20021h = "WJWebLogin.WJLoginInland";

    /* renamed from: g */
    private Handler f20022g = new Handler(Looper.getMainLooper());

    /* loaded from: classes11.dex */
    public class a implements HttpGroup.OnCommonListener {
        final /* synthetic */ WJReqWebCookieCallBack a;
        final /* synthetic */ boolean b;

        /* renamed from: c */
        final /* synthetic */ String f20023c;

        a(WJReqWebCookieCallBack wJReqWebCookieCallBack, boolean z, String str) {
            WJWebLoginInland.this = r1;
            this.a = wJReqWebCookieCallBack;
            this.b = z;
            this.f20023c = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            g.b(WJWebLoginInland.f20021h, "sendRequest request onEnd code=" + httpResponse.getCode());
            if (httpResponse.getCode() == 0 && fastJsonObject != null) {
                g.b(WJWebLoginInland.f20021h, "sendRequest request onEnd jsonObject=" + fastJsonObject.toString());
                WJWebLoginInland.this.a(this.b, this.f20023c, fastJsonObject, this.a);
                return;
            }
            jd.wjweblogin.d.f.b("1", "9");
            jd.wjweblogin.d.f.a("2", "null_null_200_" + httpResponse.getCode() + CartConstant.KEY_YB_INFO_LINK + "null_\u8fd4\u56de\u9519\u8bef\u7801\u6216\u4e0b\u53d1\u7684\u6570\u636e\u662fnull");
            WJWebLoginInland.this.a(j.a(httpResponse.getCode(), 0, "\u63a5\u53e3\u8fd4\u56de\u7684\u6570\u636e\u662f\u7a7a\u7684"), this.a);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            g.b(WJWebLoginInland.f20021h, "sendRequest request onError =" + httpError.toString());
            jd.wjweblogin.d.f.a("2", "null_null_" + httpError.getResponseCode() + CartConstant.KEY_YB_INFO_LINK + httpError.getJsonCode() + CartConstant.KEY_YB_INFO_LINK + httpError.getErrorCode() + CartConstant.KEY_YB_INFO_LINK + httpError.getMessage());
            if (33 == httpError.getErrorCode()) {
                g.b(WJWebLoginInland.f20021h, "sendRequest request onError \u767b\u5f55\u6001\u5931\u6548\u4e86\uff0c\u6e05\u9664cookie");
                WJWebLoginInland.this.clearLocalWebLoginStatus();
                if (jd.wjweblogin.common.c.a.c() != null) {
                    jd.wjweblogin.common.c.a.c().jumpNativeLogin();
                }
            }
            jd.wjweblogin.d.f.b("1", "10");
            WJWebLoginInland.this.a(httpError, this.a);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            g.b(WJWebLoginInland.f20021h, "sendRequest request onReady");
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ WJReqWebCookieCallBack f20024c;

        b(String str, String str2, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
            WJWebLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f20024c = wJReqWebCookieCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<String> a;
            g.b(WJWebLoginInland.f20021h, "postOnSuccess");
            String str = this.a;
            if (!TextUtils.isEmpty(str) && (a = jd.wjweblogin.common.c.a.a()) != null) {
                Iterator<String> it = a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (!TextUtils.isEmpty(next) && this.a.contains(next)) {
                        str = WJWebLoginInland.this.a(this.a, this.b);
                        break;
                    }
                }
            }
            g.b(WJWebLoginInland.f20021h, "postOnSuccess url=" + str);
            if (this.f20024c != null) {
                jd.wjweblogin.d.f.b("0", "1");
                this.f20024c.onSuccess(str);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ WJReqWebCookieCallBack f20025c;

        c(String str, String str2, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
            WJWebLoginInland.this = r1;
            this.a = str;
            this.b = str2;
            this.f20025c = wJReqWebCookieCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<String> a;
            g.b(WJWebLoginInland.f20021h, "postOnWithinTheValidity");
            String str = this.a;
            if (!TextUtils.isEmpty(str) && (a = jd.wjweblogin.common.c.a.a()) != null) {
                Iterator<String> it = a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    if (!TextUtils.isEmpty(next) && this.a.contains(next)) {
                        str = WJWebLoginInland.this.a(this.a, this.b);
                        break;
                    }
                }
            }
            g.b(WJWebLoginInland.f20021h, "postOnWithinTheValidity url=" + str);
            if (this.f20025c != null) {
                jd.wjweblogin.d.f.b("0", "2");
                this.f20025c.onWithinTheValidity(str);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements Runnable {
        final /* synthetic */ WJReqWebCookieCallBack a;
        final /* synthetic */ WJFailResult b;

        d(WJReqWebCookieCallBack wJReqWebCookieCallBack, WJFailResult wJFailResult) {
            WJWebLoginInland.this = r1;
            this.a = wJReqWebCookieCallBack;
            this.b = wJFailResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.b(WJWebLoginInland.f20021h, "postOnFail");
            WJReqWebCookieCallBack wJReqWebCookieCallBack = this.a;
            if (wJReqWebCookieCallBack != null) {
                wJReqWebCookieCallBack.onFail(this.b);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements Runnable {
        final /* synthetic */ WJReqWebCookieCallBack a;
        final /* synthetic */ WJErrorResult b;

        e(WJReqWebCookieCallBack wJReqWebCookieCallBack, WJErrorResult wJErrorResult) {
            WJWebLoginInland.this = r1;
            this.a = wJReqWebCookieCallBack;
            this.b = wJErrorResult;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.b(WJWebLoginInland.f20021h, "postOnLocalError");
            WJReqWebCookieCallBack wJReqWebCookieCallBack = this.a;
            if (wJReqWebCookieCallBack != null) {
                wJReqWebCookieCallBack.onLocalError(this.b);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements Runnable {
        final /* synthetic */ WJReqWebCookieCallBack a;
        final /* synthetic */ HttpError b;

        f(WJReqWebCookieCallBack wJReqWebCookieCallBack, HttpError httpError) {
            WJWebLoginInland.this = r1;
            this.a = wJReqWebCookieCallBack;
            this.b = httpError;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.b(WJWebLoginInland.f20021h, "postOnHttpTaskError");
            WJReqWebCookieCallBack wJReqWebCookieCallBack = this.a;
            if (wJReqWebCookieCallBack != null) {
                wJReqWebCookieCallBack.onHttpTaskError(this.b);
            }
        }
    }

    private void b(String str, String str2, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        try {
            this.f20022g.post(new c(str, str2, wJReqWebCookieCallBack));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private int g() {
        if (jd.wjweblogin.d.d.a() != null) {
            return jd.wjweblogin.d.d.a().getAppId();
        }
        return 0;
    }

    public int refreshWebCookie(int i2, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        g.b(f20021h, "refreshWebCookie start sceneid=" + i2);
        jd.wjweblogin.d.f.b(String.valueOf(i2));
        int a2 = a(wJReqWebCookieCallBack);
        return 1 != a2 ? a2 : a(true, "", i2, wJReqWebCookieCallBack);
    }

    public int reqWebCookie(boolean z, String str, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        boolean z2;
        g.b(f20021h, "reqWebCookie start  url=" + str);
        jd.wjweblogin.d.f.b("3");
        int a2 = a(wJReqWebCookieCallBack);
        if (1 != a2) {
            return a2;
        }
        if (!l.b(str)) {
            jd.wjweblogin.d.f.b("1", "6");
            a(j.a((int) jd.wjweblogin.d.c.f20056k, "\u4f20\u5165\u53c2\u6570\u9519\u8bef", (Exception) null), wJReqWebCookieCallBack);
            return jd.wjweblogin.d.c.f20056k;
        } else if (z) {
            a(z, 3, str, wJReqWebCookieCallBack);
            return 1;
        } else {
            List<String> g2 = jd.wjweblogin.common.c.a.g();
            g.b(f20021h, "reqWebCookie whiteList =" + g2);
            if (g2 != null && !g2.isEmpty()) {
                g.b(f20021h, "reqWebCookie whiteList=" + g2.toString());
                String host = Uri.parse(str).getHost();
                Iterator<String> it = g2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        break;
                    }
                    String next = it.next();
                    g.b(f20021h, "reqWebCookie \u904d\u5386 whiteList =" + next);
                    if (!TextUtils.isEmpty(host) && host.contains(next)) {
                        z2 = true;
                        break;
                    }
                }
                g.b(f20021h, "reqWebCookie isWithinWhite= " + z2);
                if (!z2) {
                    jd.wjweblogin.d.f.b("1", "2");
                    a(j.a(-102, jd.wjweblogin.d.c.f20051f, (Exception) null), wJReqWebCookieCallBack);
                    return -102;
                }
                return a(z, str, 3, wJReqWebCookieCallBack);
            }
            a(j.a(-102, jd.wjweblogin.d.c.f20051f, (Exception) null), wJReqWebCookieCallBack);
            return -102;
        }
    }

    private int a(WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        int g2 = g();
        g.b(f20021h, "checkRequest appid=" + g2);
        if (g2 == 0) {
            jd.wjweblogin.d.f.b("1", "7");
            a(j.a((int) jd.wjweblogin.d.c.o, jd.wjweblogin.d.c.p, (Exception) null), wJReqWebCookieCallBack);
            return jd.wjweblogin.d.c.o;
        } else if (!jd.wjweblogin.common.c.a.i()) {
            jd.wjweblogin.d.f.b("1", "5");
            a(j.a((int) jd.wjweblogin.d.c.f20058m, jd.wjweblogin.d.c.f20059n, (Exception) null), wJReqWebCookieCallBack);
            return jd.wjweblogin.d.c.f20058m;
        } else {
            String e2 = jd.wjweblogin.common.c.a.e();
            String h2 = jd.wjweblogin.common.c.a.h();
            if (TextUtils.isEmpty(e2) || TextUtils.isEmpty(h2)) {
                jd.wjweblogin.d.f.b("1", "3");
                a(j.a((int) jd.wjweblogin.d.c.f20052g, jd.wjweblogin.d.c.f20053h, (Exception) null), wJReqWebCookieCallBack);
                return jd.wjweblogin.d.c.f20052g;
            }
            return 1;
        }
    }

    private int a(boolean z, String str, int i2, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        try {
            String e2 = jd.wjweblogin.common.c.a.e();
            String h2 = jd.wjweblogin.common.c.a.h();
            g.b(f20021h, "reqWebCookie fourceRequest =" + z + " pin=" + e2 + " wskey=" + h2 + " url=" + str);
            if (!TextUtils.isEmpty(e2) && !TextUtils.isEmpty(h2)) {
                String ptPin = getPtPin();
                String ptKey = getPtKey();
                g.b(f20021h, "reqWebCookie pin= " + e2);
                g.b(f20021h, "reqWebCookie ptPin= " + ptPin + " ptKey=" + ptKey);
                if (!TextUtils.isEmpty(ptPin)) {
                    g.b(f20021h, "reqWebCookie !TextUtils.isEmpty(ptPin) ");
                    if (TextUtils.equals(e2, ptPin)) {
                        g.b(f20021h, "reqWebCookie checkPtKeyIsNeedRefresh()= " + b() + " checkPtKeyTimeOut()=" + c() + "checkCookieTimeout()=" + a());
                        if (c()) {
                            g.b(f20021h, "reqWebCookie checkPtKeyIsNeedRefresh() || checkPtKeyTimeOut()");
                            jd.wjweblogin.d.f.a("3");
                            clearLocalWebLoginStatus();
                            a(z, i2, str, wJReqWebCookieCallBack);
                            return 1;
                        } else if (b()) {
                            a(z, i2, str, wJReqWebCookieCallBack);
                            return 1;
                        } else {
                            g.b(f20021h, "reqWebCookie onWithinTheValidity");
                            getMid();
                            a(z, str, d().b(), wJReqWebCookieCallBack, true);
                            return 1;
                        }
                    }
                    g.b(f20021h, "reqWebCookie !pin.equals(ptPin) clearLocalWebLoginUser and reSendRequest");
                    jd.wjweblogin.d.f.a("4");
                    clearLocalWebLoginUser();
                    a(z, i2, str, wJReqWebCookieCallBack);
                    return 1;
                }
                a(z, i2, str, wJReqWebCookieCallBack);
                return 1;
            }
            jd.wjweblogin.d.f.b("1", "3");
            a(j.a((int) jd.wjweblogin.d.c.f20052g, jd.wjweblogin.d.c.f20053h, (Exception) null), wJReqWebCookieCallBack);
            return jd.wjweblogin.d.c.f20052g;
        } catch (Exception e3) {
            e3.printStackTrace();
            g.b(f20021h, "reqWebCookie Exception e=" + e3.getMessage());
            jd.wjweblogin.d.f.b("1", "1");
            a(j.a(-101, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e3), wJReqWebCookieCallBack);
            return -101;
        }
    }

    private void a(boolean z, int i2, String str, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        try {
            g.b(f20021h, "sendRequest start sceneid=" + i2);
            String ptPin = getPtPin();
            String ptKey = getPtKey();
            g.b(f20021h, "sendRequest HttpRequestUtils.getInstance() begin1111");
            jd.wjweblogin.c.b a2 = jd.wjweblogin.c.b.a();
            g.b(f20021h, "sendRequest HttpRequestUtils.getInstance()");
            a2.a(jd.wjweblogin.common.c.a.j());
            g.b(f20021h, "sendRequest HttpRequestUtils.getInstance() setTimeout");
            a2.a(ptPin, ptKey, g(), i2, new a(wJReqWebCookieCallBack, z, str));
        } catch (Exception e2) {
            e2.printStackTrace();
            g.b(f20021h, "sendRequest request Exception e=" + e2.getMessage());
            jd.wjweblogin.d.f.b("1", "1");
            a(j.a(-101, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e2), wJReqWebCookieCallBack);
        }
    }

    public void a(boolean z, String str, JDJSONObject jDJSONObject, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        String str2;
        try {
            g.a(f20021h, "saveData = " + jDJSONObject.toString());
            int optInt = jDJSONObject.optInt("code", -1);
            int optInt2 = jDJSONObject.optInt("sub_code", -1);
            String optString = jDJSONObject.optString("err_msg", "");
            String optString2 = jDJSONObject.optString("pt_key", "");
            String optString3 = jDJSONObject.optString("pt_pin", "");
            String optString4 = jDJSONObject.optString("ept_pin", "");
            String optString5 = jDJSONObject.optString("mid", "");
            int optInt3 = jDJSONObject.optInt("t_expire", 0);
            try {
                int optInt4 = jDJSONObject.optInt("t_refresh", 0);
                int optInt5 = jDJSONObject.optInt("t_cookie", 0);
                g.b(f20021h, "\u89e3\u6790json\u540e saveData ptpin=" + optString3 + "ept_pin=" + optString4 + "ptkey=" + optString2 + " tCookie=" + optInt5 + " tExpire=" + optInt3 + " tRefresh=" + optInt4);
                if (optInt == 0 && optInt2 == 0) {
                    jd.wjweblogin.d.f.a("1", "");
                } else {
                    jd.wjweblogin.d.f.a("2", optInt + CartConstant.KEY_YB_INFO_LINK + optInt2 + "_200_0_null_null" + optString);
                }
                if (268 == optInt2) {
                    g.b(f20021h, "saveData  268==subStatus \u767b\u5f55\u6001\u5931\u6548\u4e86");
                    jd.wjweblogin.d.f.a("5");
                    clearLocalWebLoginStatus();
                    a(j.a(0, (int) jd.wjweblogin.d.c.s, TextUtils.isEmpty(jDJSONObject.toString()) ? jd.wjweblogin.d.c.t : jDJSONObject.toString()), wJReqWebCookieCallBack);
                } else if (optInt == 0 && optInt2 == 0 && !TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString4) && optInt3 > 0 && optInt4 > 0 && optInt5 > 0) {
                    WJWebLoginSigInfo wJWebLoginSigInfo = new WJWebLoginSigInfo();
                    wJWebLoginSigInfo.setPtKey(optString2);
                    wJWebLoginSigInfo.setPtPin(optString3);
                    wJWebLoginSigInfo.setPtKeyRefreshTime(optInt4);
                    wJWebLoginSigInfo.setPtKeyTimeOut(optInt3);
                    wJWebLoginSigInfo.settCookie(optInt5);
                    wJWebLoginSigInfo.setMid(optString5);
                    wJWebLoginSigInfo.setPtKeyCreateDate(new Date());
                    wJWebLoginSigInfo.setEptPin(optString4);
                    a(z, str, wJWebLoginSigInfo, wJReqWebCookieCallBack, false);
                } else {
                    str2 = "9";
                    try {
                        jd.wjweblogin.d.f.b("1", str2);
                        a(j.a(0, (int) jd.wjweblogin.d.c.q, TextUtils.isEmpty(jDJSONObject.toString()) ? jd.wjweblogin.d.c.r : jDJSONObject.toString()), wJReqWebCookieCallBack);
                    } catch (Exception e2) {
                        e = e2;
                        g.b(f20021h, "saveData \u89e3\u6790\u6570\u636e Exception e=" + e.getMessage());
                        jd.wjweblogin.d.f.b("1", str2);
                        a(j.a(-101, "\u77ee\u6cb9\uff0c\u7a0b\u5e8f\u51fa\u9519\u4e86", e), wJReqWebCookieCallBack);
                    }
                }
            } catch (Exception e3) {
                e = e3;
                str2 = "9";
            }
        } catch (Exception e4) {
            e = e4;
            str2 = "9";
        }
    }

    private void a(boolean z, String str, WJWebLoginSigInfo wJWebLoginSigInfo, WJReqWebCookieCallBack wJReqWebCookieCallBack, boolean z2) {
        g.b(f20021h, "setCookie start");
        if (wJWebLoginSigInfo == null) {
            jd.wjweblogin.d.f.b("1", "3");
            a(j.a((int) jd.wjweblogin.d.c.f20052g, jd.wjweblogin.d.c.f20053h, (Exception) null), wJReqWebCookieCallBack);
            return;
        }
        g.b(f20021h, "setCookie wjWebLoginSigInfo=" + wJWebLoginSigInfo.toJSONString());
        String e2 = jd.wjweblogin.common.c.a.e();
        String h2 = jd.wjweblogin.common.c.a.h();
        g.b(f20021h, "saveData pin=" + e2 + " wskey=" + h2 + " url=" + str);
        String a2 = jd.wjweblogin.b.b.a(wJWebLoginSigInfo.getPtPin());
        if (!TextUtils.isEmpty(e2) && !TextUtils.isEmpty(h2) && TextUtils.equals(e2, a2)) {
            String mid = wJWebLoginSigInfo != null ? wJWebLoginSigInfo.getMid() : "";
            boolean b2 = l.b(str);
            String host = b2 ? Uri.parse(str).getHost() : "";
            g.b(f20021h, "setCookie isUriFormat=" + b2);
            List<String> g2 = jd.wjweblogin.common.c.a.g();
            g.b(f20021h, "setCookie whiteList=" + g2);
            if (!z) {
                if (!b2) {
                    jd.wjweblogin.d.f.b("1", "6");
                    a(j.a((int) jd.wjweblogin.d.c.f20056k, "\u4f20\u5165\u53c2\u6570\u9519\u8bef", (Exception) null), wJReqWebCookieCallBack);
                    return;
                }
                List<String> arrayList = new ArrayList<>();
                if (g2 != null && !g2.isEmpty()) {
                    Iterator<String> it = g2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String next = it.next();
                        g.b(f20021h, "setCookie \u904d\u5386 whiteList =" + next);
                        if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(next) && host.contains(next)) {
                            arrayList.add(next);
                            break;
                        }
                    }
                }
                boolean a3 = arrayList.size() > 0 ? a(arrayList, wJWebLoginSigInfo) : false;
                g.b(f20021h, "setCookie isCookieSeted=" + a3);
                if (a3) {
                    if (z2) {
                        b(str, mid, wJReqWebCookieCallBack);
                        return;
                    } else {
                        a(str, mid, wJReqWebCookieCallBack);
                        return;
                    }
                }
                jd.wjweblogin.d.f.b("1", "8");
                jd.wjweblogin.d.f.a("6");
                clearLocalWebLoginUser();
                a(j.a(-104, jd.wjweblogin.d.c.f20055j, (Exception) null), wJReqWebCookieCallBack);
                return;
            }
            ArrayList arrayList2 = new ArrayList();
            if (g2 != null && !g2.isEmpty()) {
                Iterator<String> it2 = g2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    String next2 = it2.next();
                    if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(next2) && !next2.equals(host) && !host.contains(next2)) {
                        g.b(f20021h, "setCookie fourceRequest true host=" + host + "whiteList.domain=" + next2);
                        String a4 = j.a(host);
                        arrayList2.add(a4);
                        jd.wjweblogin.d.d.d.add(a4);
                        break;
                    }
                }
                if (arrayList2.size() > 0) {
                    g2.addAll(arrayList2);
                }
            }
            boolean a5 = a(g2, wJWebLoginSigInfo);
            g.b(f20021h, "setCookie fourceRequest isCookieSeted=" + a5);
            if (a5) {
                if (z2) {
                    b(str, mid, wJReqWebCookieCallBack);
                    return;
                } else {
                    a(str, mid, wJReqWebCookieCallBack);
                    return;
                }
            }
            jd.wjweblogin.d.f.b("1", "8");
            jd.wjweblogin.d.f.a("6");
            clearLocalWebLoginUser();
            a(j.a(-104, jd.wjweblogin.d.c.f20055j, (Exception) null), wJReqWebCookieCallBack);
            return;
        }
        jd.wjweblogin.d.f.b("1", "3");
        a(j.a((int) jd.wjweblogin.d.c.f20052g, jd.wjweblogin.d.c.f20053h, (Exception) null), wJReqWebCookieCallBack);
    }

    private boolean a(List<String> list, WJWebLoginSigInfo wJWebLoginSigInfo) {
        if (wJWebLoginSigInfo == null) {
            return false;
        }
        String a2 = jd.wjweblogin.b.b.a(wJWebLoginSigInfo.getEptPin());
        String ptKey = wJWebLoginSigInfo.getPtKey();
        int i2 = wJWebLoginSigInfo.gettCookie();
        Date date = new Date();
        wJWebLoginSigInfo.setSetCookieDate(date);
        g.b(f20021h, "saveCookieStr eptPin=" + a2 + " ptkey=" + ptKey + " tCookie=" + i2 + " Expires=" + l.a(date, i2));
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("pt_pin=");
        stringBuffer.append(a2);
        stringBuffer.append(";EXPIRES=");
        stringBuffer.append(l.a(date, i2));
        stringBuffer.append(";PATH=/");
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("pt_key=");
        stringBuffer2.append(ptKey);
        stringBuffer2.append(";EXPIRES=");
        stringBuffer2.append(l.a(date, i2));
        stringBuffer2.append(";PATH=/");
        arrayList.add(stringBuffer.toString());
        arrayList.add(stringBuffer2.toString());
        boolean a3 = k.a(list, arrayList);
        if (a3) {
            a(wJWebLoginSigInfo);
        }
        g.b(f20021h, "saveCookieStr setCookie isCookieSeted=" + a3);
        return a3;
    }

    public String a(String str, String str2) {
        g.b(f20021h, "addMidParam url" + str + "  mid=" + str2);
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("mid", str2);
        return buildUpon.build().toString();
    }

    private void a(String str, String str2, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        try {
            this.f20022g.post(new b(str, str2, wJReqWebCookieCallBack));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(WJFailResult wJFailResult, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        try {
            this.f20022g.post(new d(wJReqWebCookieCallBack, wJFailResult));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(WJErrorResult wJErrorResult, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        try {
            this.f20022g.post(new e(wJReqWebCookieCallBack, wJErrorResult));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(HttpError httpError, WJReqWebCookieCallBack wJReqWebCookieCallBack) {
        try {
            this.f20022g.post(new f(wJReqWebCookieCallBack, httpError));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
