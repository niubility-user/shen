package com.jingdong.app.mall.aura;

import android.content.SharedPreferences;
import android.os.Build;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes19.dex */
public class h extends com.jingdong.app.mall.aura.internal.d {
    private static int a = 726;
    private static h b;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 == 21 || i2 == 22) {
            a = R2.attr.curveFit;
        }
    }

    private h() {
    }

    private int b() {
        String trim = c().trim();
        int i2 = a;
        if (trim != null && !"".equals(trim)) {
            try {
                if (trim.length() > 3) {
                    trim = trim.substring(trim.length() - 3);
                }
                i2 = Integer.parseInt(trim, 16);
            } catch (Exception e2) {
                e2.printStackTrace();
                i2 = a;
            }
        }
        return (i2 < 0 || i2 > 65535) ? a : i2;
    }

    public static synchronized h d() {
        h hVar;
        synchronized (h.class) {
            if (b == null) {
                b = new h();
            }
            hVar = b;
        }
        return hVar;
    }

    public void a() {
        SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
        edit.remove("auraServerConfig");
        try {
            edit.apply();
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    public String c() {
        return ConfigUtil.getStringFromPreference("auraServerConfig");
    }

    public boolean e(int i2) {
        return (i2 & b()) > 0;
    }

    public void f() {
        AuraConfig.setArtUseNativeOdex(e(1));
        AuraConfig.setIsCheckDexMd5(e(2));
        AuraConfig.setIsSetExtraClassLoaderForIntent(e(4));
        AuraConfig.setIsUseAuraDexOpt(e(8));
        AuraConfig.setIsUseOptDexCache(e(16));
        AuraConfig.setIsHangWait(e(32));
        AuraConfig.setIsCheckDexOnlyRootPhone(e(64));
        AuraConfig.setIsUseNewResourcesStrategy(e(128));
        AuraConfig.setIsUseMainThreadDealResource(e(256));
        AuraConfig.setIsUseAuraUtils(e(512));
    }
}
