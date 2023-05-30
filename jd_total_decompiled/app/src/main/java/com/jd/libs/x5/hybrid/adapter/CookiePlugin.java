package com.jd.libs.x5.hybrid.adapter;

import android.os.Build;
import android.text.TextUtils;
import com.jd.libs.hybrid.adapter.CookieAdapter;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import java.util.List;

/* loaded from: classes16.dex */
public class CookiePlugin extends CookieAdapter {
    @Override // com.jd.libs.hybrid.adapter.CookieAdapter
    public String getCookieString(String str) {
        String cookie = CookieManager.getInstance().getCookie(str);
        return cookie == null ? "" : cookie;
    }

    @Override // com.jd.libs.hybrid.adapter.CookieAdapter
    public boolean saveCookieString(String str, List<String> list) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (cookieManager.acceptCookie()) {
            for (String str2 : list) {
                if (!TextUtils.isEmpty(str2)) {
                    cookieManager.setCookie(str, str2);
                    Log.d("GlobalParamProvider", "set cookie: " + str2);
                }
            }
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(HybridSettings.getAppContext());
                CookieSyncManager.getInstance().sync();
                return true;
            }
            CookieManager.getInstance().flush();
            return true;
        }
        return false;
    }
}
