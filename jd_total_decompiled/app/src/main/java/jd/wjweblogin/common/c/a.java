package jd.wjweblogin.common.c;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jd.wjweblogin.common.WJNativeLoginProxy;
import jd.wjweblogin.common.WJNetworkParamsProxy;
import jd.wjweblogin.common.WJWebLoginExtendProxy;
import jd.wjweblogin.d.d;
import jd.wjweblogin.d.g;

/* loaded from: classes11.dex */
public class a {
    private static final String a = "WJWebLogin.ExtraInfoHelper";

    public static List<String> a() {
        String[] split;
        try {
            if (f() != null) {
                String koWhiteList = f().getKoWhiteList();
                g.b(a, "getKoWhiteList=" + koWhiteList);
                if (!TextUtils.isEmpty(koWhiteList) && (split = koWhiteList.split(DYConstants.DY_REGEX_COMMA)) != null) {
                    return new ArrayList(Arrays.asList(split));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(d.a);
        arrayList.add(d.b);
        return arrayList;
    }

    public static String b() {
        try {
            if (d() != null) {
                String nativeCookie = d().getNativeCookie();
                g.b(a, "getNativeCookie=" + nativeCookie);
                return nativeCookie;
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static WJNativeLoginProxy c() {
        try {
            return jd.wjweblogin.common.b.a().getNativeLoginProxy();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static WJNetworkParamsProxy d() {
        try {
            return jd.wjweblogin.common.b.a().getNetworkParamsProxy();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String e() {
        if (f() != null) {
            String pin = f().getPin();
            g.b(a, "getPin=" + pin);
            return !TextUtils.isEmpty(pin) ? pin : "";
        }
        return "";
    }

    public static WJWebLoginExtendProxy f() {
        try {
            return jd.wjweblogin.common.b.a().getProxy();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static List<String> g() {
        String[] split;
        try {
            if (f() != null) {
                String whiteList = f().getWhiteList();
                g.b(a, "getWhiteList=" + whiteList);
                if (!TextUtils.isEmpty(whiteList) && (split = whiteList.split(DYConstants.DY_REGEX_COMMA)) != null) {
                    return new ArrayList(Arrays.asList(split));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(d.f20060c);
        return arrayList;
    }

    public static String h() {
        if (f() != null) {
            String wsKey = f().getWsKey();
            g.b(a, "getA2=" + wsKey);
            return !TextUtils.isEmpty(wsKey) ? wsKey : "";
        }
        return "";
    }

    public static boolean i() {
        try {
            if (f() != null) {
                return f().isOpenNewReqWebCookie();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int j() {
        try {
            if (f() != null) {
                return f().requestTimeout();
            }
            return 5;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 5;
        }
    }
}
