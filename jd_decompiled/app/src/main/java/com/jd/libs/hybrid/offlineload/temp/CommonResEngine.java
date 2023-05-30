package com.jd.libs.hybrid.offlineload.temp;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CommonResEngine {
    private static HashMap<String, String> a = new HashMap<>();
    public static boolean available;
    private static HashMap<String, String> b;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        b = hashMap;
        hashMap.put(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ProxyConfig.MATCH_ALL_SCHEMES);
        b.put(HttpHeaders.CACHE_CONTROL, "max-age=315360000");
        b.put(HttpHeaders.TIMING_ALLOW_ORIGIN, ProxyConfig.MATCH_ALL_SCHEMES);
        b.put("X-Cache", "jd");
        available = true;
    }

    @Nullable
    public static String getBuildInGlobalFileName(Uri uri) {
        if (!HybridSettings.isInited() || !available || uri == null || HybridSettings.getAppContext() == null) {
            return null;
        }
        String uri2 = uri.toString();
        if ("http".equals(uri.getScheme())) {
            uri2 = uri.toString().replace("http", "https");
        }
        String str = a.get(uri2.split("!")[0]);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = "offlineload" + File.separator + str;
        if (Log.isDebug()) {
            Log.d("CommonResEngine", "common pic location: " + str2);
        }
        return str2;
    }

    public static HashMap<String, String> getResMap() {
        return a;
    }

    @Nullable
    @RequiresApi(api = 21)
    public static WebResourceResponse getResponse(Uri uri, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            WebResourceResponse webResourceResponse = new WebResourceResponse("image/*", null, HybridSettings.getAppContext().getAssets().open(str));
            webResourceResponse.setResponseHeaders(b);
            if (Log.isDebug()) {
                Log.xLogD("CommonResEngine", "\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u6587\u4ef6\u66ff\u6362\u7ebf\u4e0a\u8d44\u6e90\uff0c\u5c5e\u4e8e\u5185\u7f6e\u516c\u5171\u8d44\u6e90\uff0c\u539f\u7ebf\u4e0aURL\uff1a" + uri);
                Log.d("CommonResEngine", String.format("Assembling local X response for url[%s], file[%s]", uri.toString(), str));
            }
            return webResourceResponse;
        } catch (IOException e2) {
            if (Log.isDebug()) {
                Log.e("CommonResEngine", e2);
                Log.xLogEForDev("x_resource", e2.getMessage());
            }
            return null;
        }
    }

    @RequiresApi(api = 21)
    public static WebResourceResponse getXLocalResource(Uri uri) {
        if (uri == null || HybridSettings.getAppContext() == null || !available) {
            return null;
        }
        try {
            return getResponse(uri, getBuildInGlobalFileName(uri));
        } catch (Exception e2) {
            if (Log.isDebug()) {
                Log.e("CommonResEngine", e2);
                Log.xLogEForDev("x_resource", e2.getMessage());
                return null;
            }
            return null;
        }
    }
}
