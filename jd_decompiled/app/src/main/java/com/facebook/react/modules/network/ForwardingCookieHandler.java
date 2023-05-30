package com.facebook.react.modules.network;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.GuardedResultAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ForwardingCookieHandler extends CookieHandler {
    private static final String COOKIE_HEADER = "Cookie";
    private static final boolean USES_LEGACY_STORE;
    private static final String VERSION_ONE_HEADER = "Set-cookie2";
    private static final String VERSION_ZERO_HEADER = "Set-cookie";
    private final ReactContext mContext;
    @Nullable
    private CookieManager mCookieManager;
    private final CookieSaver mCookieSaver = new CookieSaver();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class CookieSaver {
        private static final int MSG_PERSIST_COOKIES = 1;
        private static final int TIMEOUT = 30000;
        private final Handler mHandler;

        public CookieSaver() {
            this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.CookieSaver.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    if (message.what == 1) {
                        CookieSaver.this.persistCookies();
                        return true;
                    }
                    return false;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        @TargetApi(21)
        public void flush() {
            CookieManager cookieManager = ForwardingCookieHandler.this.getCookieManager();
            if (cookieManager != null) {
                cookieManager.flush();
            }
        }

        public void onCookiesModified() {
            if (ForwardingCookieHandler.USES_LEGACY_STORE) {
                this.mHandler.sendEmptyMessageDelayed(1, Final.HALF_MINUTE);
            }
        }

        public void persistCookies() {
            this.mHandler.removeMessages(1);
            ForwardingCookieHandler.this.runInBackground(new Runnable() { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.CookieSaver.2
                @Override // java.lang.Runnable
                public void run() {
                    if (!ForwardingCookieHandler.USES_LEGACY_STORE) {
                        CookieSaver.this.flush();
                    } else {
                        CookieSyncManager.getInstance().sync();
                    }
                }
            });
        }
    }

    static {
        USES_LEGACY_STORE = Build.VERSION.SDK_INT < 21;
    }

    public ForwardingCookieHandler(ReactContext reactContext) {
        this.mContext = reactContext;
    }

    @TargetApi(21)
    private void addCookieAsync(String str, String str2) {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager != null) {
            cookieManager.setCookie(str, str2, null);
        }
    }

    private void addCookies(final String str, final List<String> list) {
        final CookieManager cookieManager = getCookieManager();
        if (cookieManager == null) {
            return;
        }
        if (USES_LEGACY_STORE) {
            runInBackground(new Runnable() { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.3
                @Override // java.lang.Runnable
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        cookieManager.setCookie(str, (String) it.next());
                    }
                    ForwardingCookieHandler.this.mCookieSaver.onCookiesModified();
                }
            });
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addCookieAsync(str, it.next());
        }
        cookieManager.flush();
        this.mCookieSaver.onCookiesModified();
    }

    private void clearCookiesAsync(final Callback callback) {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager != null) {
            cookieManager.removeAllCookies(new ValueCallback<Boolean>() { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.2
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Boolean bool) {
                    ForwardingCookieHandler.this.mCookieSaver.onCookiesModified();
                    callback.invoke(bool);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public CookieManager getCookieManager() {
        if (this.mCookieManager == null) {
            possiblyWorkaroundSyncManager(this.mContext);
            try {
                CookieManager cookieManager = CookieManager.getInstance();
                this.mCookieManager = cookieManager;
                if (USES_LEGACY_STORE) {
                    cookieManager.removeExpiredCookie();
                }
            } catch (IllegalArgumentException unused) {
                return null;
            } catch (Exception e2) {
                String message = e2.getMessage();
                if (message == null || !message.contains("No WebView installed")) {
                    throw e2;
                }
                return null;
            }
        }
        return this.mCookieManager;
    }

    private static boolean isCookieHeader(String str) {
        return str.equalsIgnoreCase(VERSION_ZERO_HEADER) || str.equalsIgnoreCase(VERSION_ONE_HEADER);
    }

    private static void possiblyWorkaroundSyncManager(Context context) {
        if (USES_LEGACY_STORE) {
            CookieSyncManager.createInstance(context).sync();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runInBackground(final Runnable runnable) {
        new GuardedAsyncTask<Void, Void>(this.mContext) { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... voidArr) {
                runnable.run();
            }
        }.execute(new Void[0]);
    }

    public void clearCookies(final Callback callback) {
        if (USES_LEGACY_STORE) {
            new GuardedResultAsyncTask<Boolean>(this.mContext) { // from class: com.facebook.react.modules.network.ForwardingCookieHandler.1
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.facebook.react.bridge.GuardedResultAsyncTask
                public Boolean doInBackgroundGuarded() {
                    CookieManager cookieManager = ForwardingCookieHandler.this.getCookieManager();
                    if (cookieManager != null) {
                        cookieManager.removeAllCookie();
                    }
                    ForwardingCookieHandler.this.mCookieSaver.onCookiesModified();
                    return Boolean.TRUE;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.facebook.react.bridge.GuardedResultAsyncTask
                public void onPostExecuteGuarded(Boolean bool) {
                    callback.invoke(bool);
                }
            }.execute(new Void[0]);
        } else {
            clearCookiesAsync(callback);
        }
    }

    public void destroy() {
        if (USES_LEGACY_STORE) {
            CookieManager cookieManager = getCookieManager();
            if (cookieManager != null) {
                cookieManager.removeExpiredCookie();
            }
            this.mCookieSaver.persistCookies();
        }
    }

    @Override // java.net.CookieHandler
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
        CookieManager cookieManager = getCookieManager();
        if (cookieManager == null) {
            return Collections.emptyMap();
        }
        String cookie = cookieManager.getCookie(uri.toString());
        if (TextUtils.isEmpty(cookie)) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("Cookie", Collections.singletonList(cookie));
    }

    @Override // java.net.CookieHandler
    public void put(URI uri, Map<String, List<String>> map) throws IOException {
        String uri2 = uri.toString();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null && isCookieHeader(key)) {
                addCookies(uri2, entry.getValue());
            }
        }
    }
}
