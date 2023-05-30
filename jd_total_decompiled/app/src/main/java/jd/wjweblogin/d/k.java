package jd.wjweblogin.d;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import java.util.List;

/* loaded from: classes11.dex */
public class k {
    private static final String a = "WJWebLogin.CookieEngine";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 21) {
                g.a(k.a, "CookieSyncManager.createInstance");
                CookieSyncManager.createInstance(jd.wjweblogin.common.a.a());
            }
            CookieManager cookieManager = CookieManager.getInstance();
            if (!cookieManager.acceptCookie()) {
                g.b(k.a, "clearWebViewCookie cookieManager.acceptCookie()=" + cookieManager.acceptCookie());
                return;
            }
            List<String> g2 = jd.wjweblogin.common.c.a.g();
            if (g2 != null && !g2.isEmpty()) {
                g.b(k.a, "clearWebViewCookie \u767d\u540d\u5355 list");
                k.b(g2, cookieManager);
            }
            List<String> list = d.d;
            if (list != null && !list.isEmpty()) {
                g.b(k.a, "clearWebViewCookie \u904d\u5386\u8bb0\u5f55\u5f3a\u5236\u6253\u901a\u7684url list");
                k.b(list, cookieManager);
            }
            if (i2 < 21) {
                g.b(k.a, "CookieSyncManager.getInstance().sync()");
                CookieSyncManager.getInstance().sync();
                return;
            }
            g.b(k.a, "CookieSyncManager.getInstance().flush()");
            CookieManager.getInstance().flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<String> list, CookieManager cookieManager) {
        try {
            for (String str : list) {
                String cookie = cookieManager.getCookie(OrderISVUtil.MONEY_DECIMAL + str);
                g.b(a, "clearWebViewCookie domain= " + str + "   allcookies=" + cookie);
                if (!TextUtils.isEmpty(cookie) && !TextUtils.isEmpty(cookie) && cookie.contains("pt_pin=") && cookie.contains("pt_key=")) {
                    b(str, cookie, cookieManager);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean a(List<String> list, List<String> list2) {
        g.a(a, "saveCookieString");
        if (list2 == null || list2.isEmpty()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            g.a(a, "CookieSyncManager.createInstance");
            CookieSyncManager.createInstance(jd.wjweblogin.common.a.a());
        }
        CookieManager cookieManager = CookieManager.getInstance();
        if (cookieManager.acceptCookie()) {
            for (String str : list) {
                g.a(a, "saveCookieString domain=" + str);
                if (!TextUtils.isEmpty(str)) {
                    for (String str2 : list2) {
                        if (!TextUtils.isEmpty(str2)) {
                            String str3 = str2 + ";DOMAIN=." + str + ";HTTPONLY";
                            g.a(a, "saveCookieString set cookie: " + str3);
                            cookieManager.setCookie(OrderISVUtil.MONEY_DECIMAL + str, str3, true);
                        }
                    }
                }
            }
            if (Build.VERSION.SDK_INT < 21) {
                g.a(a, "CookieSyncManager.getInstance().sync()");
                CookieSyncManager.getInstance().sync();
            } else {
                g.a(a, "CookieSyncManager.getInstance().flush()");
                CookieManager.getInstance().flush();
            }
            return true;
        }
        return false;
    }

    private static void b(String str, String str2, CookieManager cookieManager) {
        String[] split;
        if (cookieManager == null || (split = str2.split(";")) == null || split.length <= 0) {
            return;
        }
        for (String str3 : split) {
            g.b(a, "setExpiredCookie str=" + str3);
            if ((!TextUtils.isEmpty(str3) && str3.contains("pt_pin=")) || str3.contains("pt_key=")) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str3);
                stringBuffer.append(";EXPIRES=");
                stringBuffer.append(l.a());
                stringBuffer.append(";PATH=/");
                g.b(a, "setExpiredCookie buffer=" + stringBuffer.toString());
                a(str, stringBuffer.toString(), cookieManager);
            }
        }
    }

    public static String b(String str) {
        g.b(a, "getWebViewCookie");
        try {
            if (Build.VERSION.SDK_INT < 21) {
                g.a(a, "CookieSyncManager.createInstance");
                CookieSyncManager.createInstance(jd.wjweblogin.common.a.a());
            }
            return CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            g.b(a, "getWebViewCookie e=" + th.getMessage());
            return "";
        }
    }

    public static void a() {
        g.b(a, "clearWebViewCookie");
        try {
            new Thread(new a()).start();
        } catch (Throwable th) {
            g.b(a, "clearWebViewCookie e=" + th.getMessage());
        }
    }

    private static boolean a(String str, String str2, CookieManager cookieManager) {
        g.b(a, "saveCookieString \u4fdd\u5b58\u5355\u4e2a");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || cookieManager == null) {
            return false;
        }
        String str3 = str2 + ";DOMAIN=" + str + ";HTTPONLY";
        g.b(a, "saveCookieString  domain " + str + "   set cookie: " + str3);
        StringBuilder sb = new StringBuilder();
        sb.append(OrderISVUtil.MONEY_DECIMAL);
        sb.append(str);
        cookieManager.setCookie(sb.toString(), str3, true);
        return true;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str) || !l.b(str)) {
            return true;
        }
        String host = Uri.parse(str).getHost();
        String b = b(str);
        g.b(a, "checkCookie \u83b7\u53d6\u5bf9\u5e94url =" + str + " \u4ee5\u53cacookie=" + b);
        g.b(a, "checkCookie \u83b7\u53d6\u5bf9\u5e94url\u7684host =" + host + " \u4ee5\u53cacookie=" + b(host));
        String a2 = j.a(host);
        g.b(a, "checkCookie \u83b7\u53d6\u5bf9\u5e94url\u7684\u4e00\u7ea7host =" + a2 + " \u4ee5\u53cacookie=" + b(a2));
        return !TextUtils.isEmpty(b) && b.contains("pt_pin=") && b.contains("pt_key=");
    }
}
