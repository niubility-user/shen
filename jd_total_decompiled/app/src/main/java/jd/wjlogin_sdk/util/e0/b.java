package jd.wjlogin_sdk.util.e0;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.net.URLEncoder;
import jd.wjlogin_sdk.common.DevelopType;
import jd.wjlogin_sdk.config.ConfigHostMode;
import jd.wjlogin_sdk.util.g;
import jd.wjlogin_sdk.util.m;
import jd.wjlogin_sdk.util.r;

/* loaded from: classes.dex */
public class b {
    public static String a() {
        return ConfigHostMode.getConfigMode() == 0 ? c.f19944f : c.f19945g;
    }

    public static String b() {
        return "http://11" + OrderISVUtil.MONEY_DECIMAL + "158" + OrderISVUtil.MONEY_DECIMAL + "78" + OrderISVUtil.MONEY_DECIMAL + "144/";
    }

    public static String c() {
        int debugModel = DevelopType.getDebugModel();
        if (debugModel != 1) {
            return debugModel == 2 ? "https://beta-wlmonitr.m.jd.com/online_report" : "https://wlmonitor.m.jd.com/online_report";
        }
        return b() + c.f19949k;
    }

    public static String d(String str) {
        String loginURL = jd.wjlogin_sdk.common.f.a().getLoginURL();
        if (TextUtils.isEmpty(loginURL)) {
            int debugModel = DevelopType.getDebugModel();
            if (debugModel == 1) {
                return b() + str;
            } else if (debugModel == 2) {
                return c.f19952n + str;
            } else {
                return c.f19951m + str;
            }
        }
        return loginURL;
    }

    public static String e(String str) {
        int debugModel = DevelopType.getDebugModel();
        if (debugModel == 1) {
            return b(b() + str);
        } else if (debugModel == 2) {
            return b(c.A + str);
        } else {
            return b("https://wlogin.m.jd.com/cgi-bin/wlogin/" + str);
        }
    }

    public static String f(String str) {
        int debugModel = DevelopType.getDebugModel();
        if (debugModel != 1) {
            if (debugModel == 2) {
                return c.f19942c + str;
            }
            return c.b + str;
        }
        String debugUrl = jd.wjlogin_sdk.common.f.a().getDebugUrl();
        if (!TextUtils.isEmpty(debugUrl)) {
            return debugUrl + str;
        }
        return b() + str;
    }

    public static String a(String str) {
        try {
            String encode = URLEncoder.encode(str, "utf-8");
            return encode == null ? "" : encode;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(String str) {
        if (DevelopType.getDebugModel() == 0) {
            return b("https://wlogin.m.jd.com/cgi-bin/wlogin/" + str);
        }
        return b(c.w + str);
    }

    public static String d() {
        String reportURL = jd.wjlogin_sdk.common.f.a().getReportURL();
        if (TextUtils.isEmpty(reportURL)) {
            if (DevelopType.getDebugModel() != 1) {
                return g.b() != 1 ? "https://wlmonitor.m.jd.com/login_report" : "https://wlmonitor.jd.co.th/login_report";
            }
            return b() + c.f19948j;
        }
        return reportURL;
    }

    public static String b(String str) {
        return jd.wjlogin_sdk.config.a.c().h() ? String.format("%1$s?client=%2$s&clientVersion=%3$s&uuid=%4$s&osVersion=%5$s&networkType=%6$s&lang=%7$s&sdkVersion=%8$s&dbrand=%9$s&dmodel=%10$s&dname=%11$s", str, "android", a(g.d().c()), a(jd.wjlogin_sdk.common.h.a.k()), a(jd.wjlogin_sdk.common.h.a.g()), a(r.b(jd.wjlogin_sdk.common.b.a())), a(m.b(jd.wjlogin_sdk.common.b.a())), jd.wjlogin_sdk.util.e.a, a(jd.wjlogin_sdk.common.h.a.d()), a(jd.wjlogin_sdk.common.h.a.e()), a(jd.wjlogin_sdk.common.h.a.f())) : str;
    }
}
