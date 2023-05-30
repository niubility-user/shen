package com.jd.libs.hybrid.offlineload;

import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.StringUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.xwin.http.b;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes16.dex */
public class UrlRedirectLoader {

    @Keep
    /* loaded from: classes16.dex */
    public interface LoadRedirectListener {
        void onError();

        void onSuccess(@NonNull String str);
    }

    private UrlRedirectLoader() {
    }

    @Keep
    public static void urlConnect(final String str, final String str2, final String str3, final LoadRedirectListener loadRedirectListener) {
        if (loadRedirectListener == null) {
            Log.e("UrlRedirectLoader", "listener cannot be null");
        } else if (TextUtils.isEmpty(str)) {
            Log.e("UrlRedirectLoader", "url cannot be null");
            loadRedirectListener.onError();
        } else if (TextUtils.isEmpty(str2)) {
            Log.e("UrlRedirectLoader", "userAgent cannot be null");
            loadRedirectListener.onError();
        } else {
            b.a aVar = new b.a() { // from class: com.jd.libs.hybrid.offlineload.UrlRedirectLoader.1
                @Override // com.jd.libs.xwin.http.b.a
                public void onError(int i2, Map<String, List<String>> map, String str4) {
                    String str5 = "code: " + i2 + ", msg: " + str4;
                    if (i2 == 400 || i2 == 431) {
                        try {
                            str5 = str5 + String.format(Locale.getDefault(), ", UA Size=%d, Cookie Size=%d, UA=%s, Cookie=%s", Integer.valueOf(StringUtils.getByteSize(str2)), Integer.valueOf(StringUtils.getByteSize(str3)), str2, str3);
                        } catch (Throwable unused) {
                        }
                    }
                    OfflineExceptionUtils.reportGentokenError("gentoken_http_error", str, str5);
                    loadRedirectListener.onError();
                }

                @Override // com.jd.libs.xwin.http.b.a
                public void onRedirect(int i2, Map<String, List<String>> map, String str4) {
                    if (TextUtils.isEmpty(str4)) {
                        Log.d("UrlRedirectLoader", "redirect location is null!");
                        OfflineExceptionUtils.reportGentokenError("gentoken_http_redirect", str, "location is null");
                        loadRedirectListener.onError();
                        return;
                    }
                    boolean z = false;
                    boolean z2 = true;
                    if (map != null) {
                        try {
                            List<String> list = map.get(HttpHeaders.SET_COOKIE);
                            if (list != null && !list.isEmpty()) {
                                Log.d("UrlRedirectLoader", "Http connect response Set-Cookie value: " + list.toString());
                                z = !HybridBase.getInstance().saveCookieString(str, list);
                            }
                        } catch (Exception e2) {
                            Log.e("UrlRedirectLoader", e2);
                            OfflineExceptionUtils.reportGentokenError("gentoken_http_redirect", str, e2.getMessage());
                        }
                    }
                    z2 = z;
                    if (!z2) {
                        loadRedirectListener.onSuccess(str4);
                    } else {
                        loadRedirectListener.onError();
                    }
                }

                @Override // com.jd.libs.xwin.http.b.a
                public void onStart() {
                }

                @Override // com.jd.libs.xwin.http.b.a
                public void onSusses(int i2, Map<String, List<String>> map, String str4) {
                    OfflineExceptionUtils.reportGentokenError("gentoken_http_success", str, str4);
                    loadRedirectListener.onError();
                }
            };
            com.jd.libs.xwin.http.b bVar = new com.jd.libs.xwin.http.b(str);
            bVar.setAllowRedirect(false);
            bVar.a(aVar);
            if (!TextUtils.isEmpty(str3)) {
                bVar.setCookies(str3);
            }
            bVar.setUserAgent(str2);
            HashMap hashMap = new HashMap(1);
            hashMap.put("Hybrid-Sync-Cookie", "jd");
            bVar.setHeader(hashMap);
            Log.d("UrlRedirectLoader", String.format("Use native HttpConnect to load url = %s, ua = %s, cookie = %s", str, str2, str3));
            com.jd.libs.xwin.http.c.a(bVar);
        }
    }
}
