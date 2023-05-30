package com.jd.jdcache;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import androidx.annotation.Keep;
import com.jd.jdcache.entity.JDCacheDataSource;
import com.jd.jdcache.util.JDCacheLog;
import com.jd.lib.babel.servicekit.iservice.BabelLoginCallback;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\t\u0010\u0005J)\u0010\f\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ#\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00028V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0018"}, d2 = {"Lcom/jd/jdcache/JDCacheParamsProvider;", "", "", "url", "getUserAgent", "(Ljava/lang/String;)Ljava/lang/String;", "", "showLog", "()Z", "getCookie", "", BabelLoginCallback.KEY_COOKIES, "saveCookie", "(Ljava/lang/String;Ljava/util/List;)Z", "Lcom/jd/jdcache/JDCacheLoader;", "loader", "Lcom/jd/jdcache/entity/JDCacheDataSource;", "sourceWithUrl", "(Ljava/lang/String;Lcom/jd/jdcache/JDCacheLoader;)Lcom/jd/jdcache/entity/JDCacheDataSource;", "getCacheDir", "()Ljava/lang/String;", "cacheDir", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class JDCacheParamsProvider {
    @Nullable
    public String getCacheDir() {
        Context appContext = JDCacheSetting.INSTANCE.getAppContext();
        if (appContext == null) {
            Intrinsics.throwNpe();
        }
        File filesDir = appContext.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "JDCacheSetting.appContext!!.filesDir");
        return new File(filesDir.getPath(), "jdcache").getAbsolutePath();
    }

    @NotNull
    public String getCookie(@Nullable String url) {
        String cookie = CookieManager.getInstance().getCookie(url);
        return cookie != null ? cookie : "";
    }

    @Nullable
    public abstract String getUserAgent(@Nullable String url);

    public boolean saveCookie(@Nullable String url, @NotNull List<String> r7) {
        CookieManager cookieManager = CookieManager.getInstance();
        if (cookieManager.acceptCookie()) {
            for (String str : r7) {
                if (!TextUtils.isEmpty(str)) {
                    cookieManager.setCookie(url, str);
                    JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                    if (jDCacheLog.getCanLog()) {
                        jDCacheLog.d("JDCacheParamsProvider", "set cookie: " + str);
                    }
                }
            }
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(JDCacheSetting.INSTANCE.getAppContext());
                CookieSyncManager.getInstance().sync();
                return true;
            }
            CookieManager.getInstance().flush();
            return true;
        }
        return false;
    }

    public boolean showLog() {
        return false;
    }

    @Nullable
    public JDCacheDataSource sourceWithUrl(@NotNull String url, @Nullable JDCacheLoader loader) {
        return null;
    }
}
