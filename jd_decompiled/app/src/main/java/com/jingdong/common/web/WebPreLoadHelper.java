package com.jingdong.common.web;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.BaseGraySwitch;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.preload.CustomParamProvider;
import com.jd.libs.hybrid.preload.DataProvider;
import com.jd.libs.hybrid.preload.ExtraParamsGetter;
import com.jd.libs.hybrid.preload.IPreloadParamGetter;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.net.URLDecoder;

@Keep
/* loaded from: classes6.dex */
public class WebPreLoadHelper {
    public static void clearPreLoadData(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        destroy(str);
    }

    private static void destroy(String str) {
        if (!HybridSDK.isInited()) {
            Log.e("WebPreLoadHelper", "Hybrid SDK is not initialized!");
        } else if (TextUtils.isEmpty(str)) {
        } else {
            if (!GraySwitch.oldPreloadOffline) {
                DataProvider.getInstance().destroy(str);
            }
            if (BaseGraySwitch.isHybridNewPreloadOn) {
                RequestPreloadSDK.INSTANCE.getInstance().destroy(str);
            }
        }
    }

    public static String preLoad(String str) {
        return preLoad(str, null);
    }

    private static void preload(String str, IPreloadParamGetter.PreloadParamGetter preloadParamGetter, String str2) {
        if (!HybridSDK.isInited()) {
            Log.e("WebPreLoadHelper", "Hybrid SDK is not initialized!");
        } else if (Build.VERSION.SDK_INT < 19) {
            Log.e("WebPreLoadHelper", "Function Pre Load does NOT support SDK less than 19(API < KITKAT).");
        } else if (WebHybridUtils.degradeOfflineFromQuery(str)) {
        } else {
            Context appContext = HybridSettings.getAppContext();
            if (appContext == null) {
                Log.e("WebPreLoadHelper", "Internal error, app context is null.");
            } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            } else {
                if (!GraySwitch.oldPreloadOffline) {
                    DataProvider.getInstance().preLoad(appContext, str, preloadParamGetter, str2);
                }
                if (BaseGraySwitch.isHybridNewPreloadOn) {
                    RequestPreloadSDK.INSTANCE.getInstance().preload(str, str2);
                }
            }
        }
    }

    public static void setPreloadCustomParamsGetter(CustomParamProvider.ParamGetter paramGetter) {
        CustomParamProvider.setParamGetter(paramGetter);
    }

    @Deprecated
    public static void setPreloadExtraParamsGetter(ExtraParamsGetter extraParamsGetter) {
        DataProvider.setExtraParamsGetter(extraParamsGetter);
    }

    public static Bundle startPreLoadForBundle(String str, Bundle bundle) {
        return startPreLoadForBundle(str, bundle, null);
    }

    public static String preLoad(String str, IPreloadParamGetter.PreloadParamGetter preloadParamGetter) {
        if (TextUtils.isEmpty(str) || !WebHybridUtils.hybridEnable) {
            return null;
        }
        if (OKLog.D) {
            OKLog.d("JDHybrid", "PreLoad for url: " + str);
        }
        String valueOf = String.valueOf(SystemClock.elapsedRealtime());
        preload(str, preloadParamGetter, valueOf);
        return valueOf;
    }

    public static Bundle startPreLoadForBundle(String str, Bundle bundle, IPreloadParamGetter.PreloadParamGetter preloadParamGetter) {
        String preLoad;
        if (!TextUtils.isEmpty(str) && WebHybridUtils.hybridEnable && (preLoad = preLoad(str, preloadParamGetter)) != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean(MBaseKeyNames.KEY_USE_HYBRID, true);
            bundle.putString(MBaseKeyNames.KEY_PRELOAD_ID, preLoad);
        }
        return bundle;
    }

    public static Bundle startPreLoadForBundle(Bundle bundle) {
        return startPreLoadForBundle(bundle, (IPreloadParamGetter.PreloadParamGetter) null);
    }

    public static Bundle startPreLoadForBundle(Bundle bundle, IPreloadParamGetter.PreloadParamGetter preloadParamGetter) {
        URLParamMap map;
        if (bundle != null && WebHybridUtils.hybridEnable) {
            String string = bundle.getString("url");
            if (TextUtils.isEmpty(string)) {
                Serializable serializable = bundle.getSerializable("urlParamMap");
                if ((serializable instanceof SerializableContainer) && (map = ((SerializableContainer) serializable).getMap()) != null) {
                    try {
                        string = URLDecoder.decode(map.get((Object) RemoteMessageConst.TO), "utf-8");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        string = null;
                    }
                }
            }
            String preLoad = preLoad(string, preloadParamGetter);
            if (preLoad != null) {
                bundle.putBoolean(MBaseKeyNames.KEY_USE_HYBRID, true);
                bundle.putString(MBaseKeyNames.KEY_PRELOAD_ID, preLoad);
            }
        }
        return bundle;
    }
}
