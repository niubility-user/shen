package com.jd.libs.hybrid;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.base.GlobalParamProvider;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.UrlRedirectLoader;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Keep
/* loaded from: classes16.dex */
public class HybridGenTokenSupporter {
    private static final int MAX_LOAD_TIMES = 5;
    private static final String TAG = "HybridGenTokenSupporter";
    private static boolean enableGenTokenSupport = true;
    private static Matcher genTokenMatcher;
    private static Pattern genTokenPattern;

    @Keep
    /* loaded from: classes16.dex */
    public interface GenTokenCallback {
        void onCancel(boolean z, String str);

        void onSuccess(String str);
    }

    @Keep
    @Deprecated
    /* loaded from: classes16.dex */
    public interface GenTokenListener {
        void onError();

        void onSuccess(String str);
    }

    private HybridGenTokenSupporter() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callbackError(Handler handler, final GenTokenCallback genTokenCallback, final boolean z, final String str) {
        if (z) {
            Log.xLogE(TAG, "[genToken]\u4f7f\u7528hybrid\u52a0\u8f7dgentokenUrl\u5931\u8d25, " + str);
        } else {
            Log.xLogD(TAG, "[genToken]\u4e0d\u4f7f\u7528hybrid\u52a0\u8f7dgentokenUrl, " + str);
        }
        handler.post(new Runnable() { // from class: com.jd.libs.hybrid.HybridGenTokenSupporter.6
            @Override // java.lang.Runnable
            public void run() {
                GenTokenCallback.this.onCancel(z, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void callbackSuccess(Handler handler, final GenTokenCallback genTokenCallback, final String str) {
        Log.d(TAG, "http connect gentoken url successfully");
        Log.xLogD(TAG, "[genToken]\u6210\u529f\u4f7f\u7528hybrid\u52a0\u8f7dgentokenUrl\u5e76\u540c\u6b65cookie");
        handler.post(new Runnable() { // from class: com.jd.libs.hybrid.HybridGenTokenSupporter.5
            @Override // java.lang.Runnable
            public void run() {
                GenTokenCallback.this.onSuccess(str);
            }
        });
    }

    public static void checkAndLoadUrl(String str, String str2, HybridOfflineLoader hybridOfflineLoader, GenTokenCallback genTokenCallback) {
        Uri uri;
        if (TextUtils.isEmpty(str) || genTokenCallback == null) {
            return;
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            myLooper = Looper.getMainLooper();
        }
        try {
            uri = Uri.parse(str);
        } catch (Exception e2) {
            Log.e(TAG, e2);
            uri = null;
        }
        boolean isGenTokenUrl = isGenTokenUrl(str);
        if (enableGenTokenSupport && isGenTokenUrl && needHybridGenToken(hybridOfflineLoader) && uri != null) {
            String queryParameter = TextUtils.isEmpty(str2) ? null : uri.getQueryParameter(str2);
            Log.d(TAG, "checkGenToken: Load gentoken url(" + str + "), targetUrl(" + queryParameter + ")");
            loadUrlSetCookies(str, queryParameter, myLooper, genTokenCallback);
            return;
        }
        Log.xLogD(TAG, "[genToken]\u4e0d\u4f7f\u7528hybrid\u52a0\u8f7dgentokenUrl\uff0c\u539f\u56e0\uff1aUrl\u4e0d\u662f\u4e00\u4e2agentoken\u7684url\uff0c\u6216\u8005\u672a\u6ee1\u8db3\u9700\u8981\u52a0\u8f7d\u7684\u6761\u4ef6\u3002\u529f\u80fd\u5f00\u5173:" + enableGenTokenSupport + "\uff0c \u662f\u5426GenTokenUrl:" + isGenTokenUrl);
        Log.d(TAG, "checkGenToken: Load url by webview directly. Enbale:" + enableGenTokenSupport + ", isGenTokenUrl:" + isGenTokenUrl);
        genTokenCallback.onCancel(false, "Url is not a genToken url or loader instructs it not to load.");
    }

    private static boolean isGenTokenUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (genTokenPattern == null) {
                genTokenPattern = Pattern.compile("un.m.jd.com/cgi-bin/app/appjmp");
            }
            Matcher matcher = genTokenMatcher;
            if (matcher == null) {
                genTokenMatcher = genTokenPattern.matcher(str);
            } else {
                matcher.reset(str);
            }
            return genTokenMatcher.find();
        } catch (Exception e2) {
            Log.e(TAG, e2);
            return false;
        }
    }

    public static void loadGenTokenUrl(String str, @Nullable String str2, HybridOfflineLoader hybridOfflineLoader, Looper looper, GenTokenCallback genTokenCallback) {
        if (genTokenCallback == null) {
            return;
        }
        if (enableGenTokenSupport && needHybridGenToken(hybridOfflineLoader)) {
            loadUrlSetCookies(str, str2, looper, genTokenCallback);
            return;
        }
        Log.xLogD(TAG, "[genToken]\u4e0d\u4f7f\u7528hybrid\u52a0\u8f7dgentokenUrl\uff0c\u539f\u56e0\uff1a\u672a\u6ee1\u8db3\u9700\u8981\u52a0\u8f7d\u7684\u6761\u4ef6\u3002\u529f\u80fd\u5f00\u5173:" + enableGenTokenSupport);
        Log.d(TAG, "checkGenToken: Load url by webview directly. Enbale:" + enableGenTokenSupport);
        genTokenCallback.onCancel(false, "loader instructs it not to load.");
    }

    private static void loadUrlSetCookies(String str, @Nullable String str2, Looper looper, GenTokenCallback genTokenCallback) {
        if (genTokenCallback == null) {
            Log.e(TAG, "listener must be not null.");
            return;
        }
        Log.xLogD(TAG, "[genToken]\u5c1d\u8bd5\u4f7f\u7528hybrid\u6765\u52a0\u8f7dgentokenUrl");
        if (looper == null) {
            looper = Looper.myLooper();
        }
        Handler handler = new Handler(looper);
        if (!HybridSDK.isInited()) {
            Log.e(TAG, "Hybrid SDK is not initialized.");
            callbackError(handler, genTokenCallback, false, "Hybrid SDK is not initialized.");
        } else if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "Url to load must not be empty.");
            callbackError(handler, genTokenCallback, true, "Url to load must not be empty.");
        } else {
            String setting = HybridSDK.getSetting("userAgent");
            if (TextUtils.isEmpty(setting)) {
                GlobalParamProvider.IGlobalParamProvider iGlobalParamProvider = GlobalParamProvider.sGlobalParamProvider;
                setting = iGlobalParamProvider != null ? iGlobalParamProvider.getUserAgent(str) : null;
            }
            if (TextUtils.isEmpty(setting)) {
                Log.e(TAG, "UserAgent must not be empty.");
                OfflineExceptionUtils.reportGentokenError("gentoken_http_no_ua", str, null);
                callbackError(handler, genTokenCallback, true, "UserAgent must not be empty.");
                return;
            }
            UrlRedirectLoader.urlConnect(str, setting, HybridBase.getInstance().getCookieString(str), new UrlRedirectLoader.LoadRedirectListener(str, str2, handler, genTokenCallback, setting) { // from class: com.jd.libs.hybrid.HybridGenTokenSupporter.4
                int a = 1;
                String b;

                /* renamed from: c  reason: collision with root package name */
                final /* synthetic */ String f5880c;
                final /* synthetic */ String d;

                /* renamed from: e  reason: collision with root package name */
                final /* synthetic */ Handler f5881e;

                /* renamed from: f  reason: collision with root package name */
                final /* synthetic */ GenTokenCallback f5882f;

                /* renamed from: g  reason: collision with root package name */
                final /* synthetic */ String f5883g;

                {
                    this.f5880c = str;
                    this.d = str2;
                    this.f5881e = handler;
                    this.f5882f = genTokenCallback;
                    this.f5883g = setting;
                    this.b = str;
                }

                @Override // com.jd.libs.hybrid.offlineload.UrlRedirectLoader.LoadRedirectListener
                public void onError() {
                    Log.e(HybridGenTokenSupporter.TAG, "http connect gentoken url fail");
                    HybridGenTokenSupporter.callbackError(this.f5881e, this.f5882f, true, "http connect gentoken url fail");
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x006a  */
                /* JADX WARN: Removed duplicated region for block: B:17:0x006f  */
                /* JADX WARN: Removed duplicated region for block: B:19:0x0072  */
                @Override // com.jd.libs.hybrid.offlineload.UrlRedirectLoader.LoadRedirectListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onSuccess(@androidx.annotation.NonNull java.lang.String r11) {
                    /*
                        Method dump skipped, instructions count: 363
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.HybridGenTokenSupporter.AnonymousClass4.onSuccess(java.lang.String):void");
                }
            });
        }
    }

    public static boolean needHybridGenToken(HybridOfflineLoader hybridOfflineLoader) {
        OfflineFiles files;
        if (hybridOfflineLoader == null || (files = hybridOfflineLoader.getFiles()) == null) {
            return false;
        }
        String type = files.getType();
        if (!"1".equals(type) && !"3".equals(type)) {
            if ("2".equals(type)) {
                boolean isCanPreloadHtml = files.isCanPreloadHtml();
                if (Log.isDebug()) {
                    Log.xLogDForDev(TAG, "[genToken]\u662f\u5426Hybrid GenToken\u5224\u65ad\uff1a\u7c7b\u578b = " + type + ", \u5f00\u542fhtml\u9884\u4e0b\u8f7d = " + isCanPreloadHtml);
                    Log.d(TAG, "checkGenToken: type = " + type + ", canPreloadHtml = " + isCanPreloadHtml);
                }
                return isCanPreloadHtml;
            }
            boolean isAvailable = files.isAvailable();
            boolean binarySwitch = CommonUtils.getBinarySwitch(files.getBConfig(), ModuleHelper.BCONFIG_CONTAIN_HTML);
            boolean isCanPreloadHtml2 = files.isCanPreloadHtml();
            if (Log.isDebug()) {
                Log.xLogDForDev(TAG, "[genToken]\u662f\u5426Hybrid GenToken\u5224\u65ad\uff1a\u6587\u4ef6\u53ef\u7528 = " + isAvailable + ", zip\u6709html = " + binarySwitch + ", \u5f00\u542fhtml\u9884\u4e0b\u8f7d = " + isCanPreloadHtml2);
                Log.d(TAG, "checkGenToken: available = " + isAvailable + ", containHtml = " + binarySwitch + ", canPreloadHtml = " + isCanPreloadHtml2);
            }
            return isCanPreloadHtml2 || (isAvailable && binarySwitch);
        }
        boolean isAvailable2 = files.isAvailable();
        if (Log.isDebug()) {
            Log.xLogDForDev(TAG, "[genToken]\u662f\u5426Hybrid GenToken\u5224\u65ad\uff1a\u7c7b\u578b = " + type + ", \u6587\u4ef6\u53ef\u7528 = " + isAvailable2);
            Log.d(TAG, "checkGenToken: type = " + type + ", available = " + isAvailable2);
        }
        return isAvailable2;
    }

    public static void setEnableGenTokenSupport(boolean z) {
        enableGenTokenSupport = z;
    }

    @Deprecated
    public static void loadGenTokenUrl(String str, Looper looper, final GenTokenListener genTokenListener) {
        if (genTokenListener == null) {
            return;
        }
        if (!enableGenTokenSupport) {
            Log.xLogD(TAG, "[genToken]\u4e0d\u4f7f\u7528hybrid\u52a0\u8f7dgentokenUrl\uff0c\u529f\u80fd\u5f00\u5173\u5df2\u5173\u95ed");
            Log.d(TAG, "checkGenToken: Load url by webview directly. Enable = false");
            genTokenListener.onError();
            return;
        }
        loadUrlSetCookies(str, null, looper, new GenTokenCallback() { // from class: com.jd.libs.hybrid.HybridGenTokenSupporter.2
            @Override // com.jd.libs.hybrid.HybridGenTokenSupporter.GenTokenCallback
            public void onCancel(boolean z, String str2) {
                GenTokenListener.this.onError();
            }

            @Override // com.jd.libs.hybrid.HybridGenTokenSupporter.GenTokenCallback
            public void onSuccess(String str2) {
                GenTokenListener.this.onSuccess(str2);
            }
        });
    }

    @Deprecated
    public static void loadGenTokenUrl(String str, @Nullable String str2, Looper looper, final GenTokenListener genTokenListener) {
        if (genTokenListener == null) {
            return;
        }
        if (!enableGenTokenSupport) {
            Log.xLogD(TAG, "[genToken]\u4e0d\u4f7f\u7528hybrid\u52a0\u8f7dgentokenUrl\uff0c\u529f\u80fd\u5f00\u5173\u5df2\u5173\u95ed");
            Log.d(TAG, "checkGenToken: Load url by webview directly. Enable = false");
            genTokenListener.onError();
            return;
        }
        loadUrlSetCookies(str, str2, looper, new GenTokenCallback() { // from class: com.jd.libs.hybrid.HybridGenTokenSupporter.3
            @Override // com.jd.libs.hybrid.HybridGenTokenSupporter.GenTokenCallback
            public void onCancel(boolean z, String str3) {
                GenTokenListener.this.onError();
            }

            @Override // com.jd.libs.hybrid.HybridGenTokenSupporter.GenTokenCallback
            public void onSuccess(String str3) {
                GenTokenListener.this.onSuccess(str3);
            }
        });
    }

    @Deprecated
    public static void checkAndLoadUrl(String str, String str2, HybridOfflineLoader hybridOfflineLoader, final GenTokenListener genTokenListener) {
        if (genTokenListener == null) {
            return;
        }
        checkAndLoadUrl(str, str2, hybridOfflineLoader, new GenTokenCallback() { // from class: com.jd.libs.hybrid.HybridGenTokenSupporter.1
            @Override // com.jd.libs.hybrid.HybridGenTokenSupporter.GenTokenCallback
            public void onCancel(boolean z, String str3) {
                GenTokenListener.this.onError();
            }

            @Override // com.jd.libs.hybrid.HybridGenTokenSupporter.GenTokenCallback
            public void onSuccess(String str3) {
                GenTokenListener.this.onSuccess(str3);
            }
        });
    }
}
