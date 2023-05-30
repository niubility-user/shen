package jd.wjweblogin.c;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.net.URLEncoder;
import jd.wjweblogin.common.DevelopType;

/* loaded from: classes11.dex */
public class c {
    public static String a() {
        return "http://10" + OrderISVUtil.MONEY_DECIMAL + "170" + OrderISVUtil.MONEY_DECIMAL + "179" + OrderISVUtil.MONEY_DECIMAL + "11/";
    }

    public static String b() {
        int debugModel = DevelopType.getDebugModel();
        if (debugModel == 1) {
            return a();
        }
        return debugModel == 2 ? "api.m.jd.care" : "api.m.jd.com";
    }

    public static String a(String str) {
        try {
            String encode = URLEncoder.encode(str, "utf-8");
            return encode == null ? "" : encode;
        } catch (Exception unused) {
            return "";
        }
    }
}
