package com.jingdong.manto.jsapi.openmodule;

import android.text.TextUtils;
import com.jingdong.manto.utils.MantoUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public final class OpenJsApiManager {
    public static final int PAGE_API = 1;
    public static final int SERVICE_API = 0;
    public static final int WEB_API = 2;
    private static final Map<String, IMantoBaseModule> sApiMap = new HashMap();
    private static final Map<String, IMantoBaseModule> pApiMap = new HashMap();
    private static final Map<String, IMantoBaseModule> wApiMap = new HashMap();

    public static final void addPageJsApi(IMantoBaseModule iMantoBaseModule) {
        putToMap(iMantoBaseModule, pApiMap);
    }

    public static final void addServiceJsApi(IMantoBaseModule iMantoBaseModule) {
        putToMap(iMantoBaseModule, sApiMap);
    }

    public static final void addWebViewJsApi(IMantoBaseModule iMantoBaseModule) {
        putToMap(iMantoBaseModule, wApiMap);
    }

    public static Map<String, IMantoBaseModule> getPApiMap() {
        return pApiMap;
    }

    public static Map<String, IMantoBaseModule> getSApiMap() {
        return sApiMap;
    }

    public static Map<String, IMantoBaseModule> getWApiMap() {
        return wApiMap;
    }

    private static final void putToMap(IMantoBaseModule iMantoBaseModule, Map<String, IMantoBaseModule> map) {
        if (iMantoBaseModule != null) {
            String nonNull = MantoUtils.getNonNull(iMantoBaseModule.getModuleName());
            if (TextUtils.isEmpty(nonNull)) {
                return;
            }
            map.put(nonNull, iMantoBaseModule);
        }
    }
}
