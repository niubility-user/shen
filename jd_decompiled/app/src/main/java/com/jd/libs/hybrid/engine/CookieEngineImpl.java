package com.jd.libs.hybrid.engine;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.CookieAdapter;
import com.jd.libs.hybrid.base.GlobalParamProvider;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.engine.CookieEngine;
import com.jd.libs.hybrid.base.util.Log;
import java.util.List;

/* loaded from: classes16.dex */
public class CookieEngineImpl implements CookieEngine {
    @Override // com.jd.libs.hybrid.base.engine.CookieEngine
    public String getCookieString(String str) {
        GlobalParamProvider.IGlobalParamProvider iGlobalParamProvider = GlobalParamProvider.sGlobalParamProvider;
        if (iGlobalParamProvider != null) {
            return iGlobalParamProvider.getCookieString(str);
        }
        CookieAdapter cookieAdapter = (CookieAdapter) HybridSDK.getAdapter(CookieAdapter.NAME);
        if (cookieAdapter != null) {
            return cookieAdapter.getCookieString(str);
        }
        String cookie = CookieManager.getInstance().getCookie(str);
        return cookie == null ? "" : cookie;
    }

    @Override // com.jd.libs.hybrid.base.engine.CookieEngine
    public boolean saveCookieString(String str, List<String> list) {
        GlobalParamProvider.IGlobalParamProvider iGlobalParamProvider = GlobalParamProvider.sGlobalParamProvider;
        if (iGlobalParamProvider != null) {
            iGlobalParamProvider.saveCookieString(str, list);
            return true;
        }
        CookieAdapter cookieAdapter = (CookieAdapter) HybridSDK.getAdapter(CookieAdapter.NAME);
        if (cookieAdapter != null) {
            return cookieAdapter.saveCookieString(str, list);
        }
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
            } else {
                CookieManager.getInstance().flush();
            }
            return true;
        }
        return false;
    }
}
