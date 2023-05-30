package com.jingdong.union.common.config;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.union.a.f;
import com.jingdong.union.dependency.IAdvertUtils;
import com.jingdong.union.dependency.IAndroidId;
import com.jingdong.union.dependency.IDensity;
import com.jingdong.union.dependency.IJdAdvertUtils;
import com.jingdong.union.dependency.IJumpDispatchCallBack;
import com.jingdong.union.dependency.IJumpSubCallBack;
import com.jingdong.union.dependency.ILoadingView;
import com.jingdong.union.dependency.ILoginUser;
import com.jingdong.union.dependency.IMtaUtils;
import com.jingdong.union.dependency.IOaid;
import com.jingdong.union.dependency.IUnionExceptionReport;
import com.jingdong.union.dependency.IUuid;
import com.jingdong.union.dependency.IWebUa;
import com.jingdong.union.dependency.a;
import com.jingdong.union.dependency.b;
import com.jingdong.union.dependency.base.IBaseAdvertUtils;

/* loaded from: classes12.dex */
public class JdUnionBase {
    public static final float DEFAULT_DENSITY = 2.0f;
    private static JdUnionConfig a;
    private static String b;

    /* renamed from: c */
    private static String f15587c;
    private static boolean d;
    public static View tempBgView;
    public static IJumpDispatchCallBack tempIJumpDispatchCallBack;
    public static IJumpSubCallBack tempIJumpSubCallBack;

    private static IAdvertUtils a() {
        if (!e() && getJdUnionConfig().getiAdvertUtils() != null) {
            return getJdUnionConfig().getiAdvertUtils();
        }
        return a.d().f();
    }

    private static void b(boolean z) {
        if (z) {
            b = "https://beta-u.jd.com/api";
        } else {
            b = "https://union-click.jd.com/api";
        }
    }

    private static IJdAdvertUtils c() {
        if (!e() && getJdUnionConfig().getiJdAdvertUtils() != null) {
            return getJdUnionConfig().getiJdAdvertUtils();
        }
        return a.d().c();
    }

    private static void d(boolean z) {
        if (z) {
            f15587c = "https://log.jd.com/vice.gif";
        } else {
            f15587c = "https://log.jd.com/vice.gif";
        }
    }

    private static boolean e() {
        return getJdUnionConfig() == null;
    }

    public static IAndroidId getAndroidId() {
        if (!e() && getJdUnionConfig().getAndroidId() != null) {
            return getJdUnionConfig().getAndroidId();
        }
        return a.d().a();
    }

    public static IBaseAdvertUtils getBaseAdvertUtils() {
        if (c() instanceof b) {
            return a();
        }
        return c();
    }

    public static Context getContext() {
        if (e()) {
            return null;
        }
        return getJdUnionConfig().getContext();
    }

    public static IDensity getDensity() {
        if (!e() && getJdUnionConfig().getDensity() != null) {
            return getJdUnionConfig().getDensity();
        }
        return a.d().b();
    }

    public static String getFeachUrl() {
        return b;
    }

    public static JdUnionConfig getJdUnionConfig() {
        return a;
    }

    public static IJumpDispatchCallBack getJumpDispatchCallBack() {
        if (!e() && getJdUnionConfig().getiJumpDispatchCallBack() != null) {
            return getJdUnionConfig().getiJumpDispatchCallBack();
        }
        return a.d().g();
    }

    public static ILoadingView getLoadingView() {
        if (!e() && getJdUnionConfig().getiLoadingView() != null) {
            return getJdUnionConfig().getiLoadingView();
        }
        return a.d().h();
    }

    public static ILoginUser getLoginUser() {
        if (!e() && getJdUnionConfig().getiLoginUser() != null) {
            return getJdUnionConfig().getiLoginUser();
        }
        return a.d().i();
    }

    public static IMtaUtils getMtaUtils() {
        if (!e() && getJdUnionConfig().getiMtaUtils() != null) {
            return getJdUnionConfig().getiMtaUtils();
        }
        return a.d().j();
    }

    public static IOaid getOaid() {
        if (!e() && getJdUnionConfig().getiOaid() != null) {
            return getJdUnionConfig().getiOaid();
        }
        return a.d().k();
    }

    public static String getSecoundUrl() {
        return f15587c;
    }

    public static String getToken() {
        return e() ? "" : getJdUnionConfig().getToken();
    }

    public static IUnionExceptionReport getUnionExceptionReport() {
        if (!e() && getJdUnionConfig().getiUnionExceptionReport() != null) {
            return getJdUnionConfig().getiUnionExceptionReport();
        }
        return a.d().l();
    }

    public static IUuid getUuid() {
        if (!e() && getJdUnionConfig().getUuid() != null) {
            return getJdUnionConfig().getUuid();
        }
        return a.d().e();
    }

    public static IWebUa getWebUa() {
        if (!e() && getJdUnionConfig().getiWebUa() != null) {
            return getJdUnionConfig().getiWebUa();
        }
        return a.d().m();
    }

    public static boolean hasInited() {
        return d && com.jingdong.union.common.helper.b.a(a).booleanValue() && !TextUtils.isEmpty(b);
    }

    public static void init(JdUnionConfig jdUnionConfig) {
        init(jdUnionConfig, false);
    }

    public static void releaseTemp() {
        tempIJumpDispatchCallBack = null;
        tempIJumpSubCallBack = null;
        tempBgView = null;
    }

    public static boolean init(JdUnionConfig jdUnionConfig, boolean z) {
        if (d || jdUnionConfig == null) {
            return false;
        }
        b(z);
        d(z);
        f.c(jdUnionConfig.isLog());
        a = jdUnionConfig;
        boolean booleanValue = com.jingdong.union.common.helper.b.a(jdUnionConfig).booleanValue();
        d = booleanValue;
        return booleanValue;
    }
}
