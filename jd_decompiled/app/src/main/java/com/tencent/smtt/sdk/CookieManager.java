package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class CookieManager {
    public static String LOGTAG = "CookieManager";

    /* renamed from: c  reason: collision with root package name */
    private static CookieManager f17715c;
    CopyOnWriteArrayList<b> a;
    a b = a.MODE_NONE;
    private boolean d = false;

    /* loaded from: classes9.dex */
    public enum a {
        MODE_NONE,
        MODE_KEYS,
        MODE_ALL
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class b {
        int a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        String f17717c;
        ValueCallback<Boolean> d;

        b() {
        }
    }

    private CookieManager() {
    }

    public static boolean checkSysCoreNotExist() {
        SystemCoreProtector e2 = QbSdk.e();
        if (e2 != null) {
            try {
                android.webkit.CookieManager.getInstance();
                return false;
            } catch (Exception e3) {
                e2.onCookieManagerException(e3);
                return true;
            }
        }
        return false;
    }

    public static CookieManager getInstance() {
        if (f17715c == null) {
            synchronized (CookieManager.class) {
                if (f17715c == null) {
                    f17715c = new CookieManager();
                }
            }
        }
        return f17715c;
    }

    public static int getROMCookieDBVersion(Context context) {
        return context.getSharedPreferences("cookiedb_info", Build.VERSION.SDK_INT >= 11 ? 4 : 0).getInt("db_version", -1);
    }

    public static void setROMCookieDBVersion(Context context, int i2) {
        SharedPreferences.Editor edit = context.getSharedPreferences("cookiedb_info", Build.VERSION.SDK_INT >= 11 ? 4 : 0).edit();
        edit.putInt("db_version", i2);
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a() {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = this.a;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.size() != 0) {
            u a2 = u.a();
            if (a2 == null || !a2.b()) {
                Iterator<b> it = this.a.iterator();
                while (it.hasNext()) {
                    b next = it.next();
                    int i2 = next.a;
                    if (i2 != 1) {
                        if (i2 == 2) {
                            if (checkSysCoreNotExist()) {
                                return;
                            }
                            android.webkit.CookieManager.getInstance().setCookie(next.b, next.f17717c);
                        }
                    } else if (Build.VERSION.SDK_INT < 21) {
                        continue;
                    } else if (checkSysCoreNotExist()) {
                        return;
                    } else {
                        com.tencent.smtt.utils.j.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, next.b, next.f17717c, next.d);
                    }
                }
            } else {
                Iterator<b> it2 = this.a.iterator();
                while (it2.hasNext()) {
                    b next2 = it2.next();
                    int i3 = next2.a;
                    if (i3 == 1) {
                        setCookie(next2.b, next2.f17717c, next2.d);
                    } else if (i3 == 2) {
                        setCookie(next2.b, next2.f17717c);
                    }
                }
            }
            this.a.clear();
        }
    }

    public boolean acceptCookie() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            if (checkSysCoreNotExist()) {
                return false;
            }
            return android.webkit.CookieManager.getInstance().acceptCookie();
        }
        return a2.c().d();
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
            if (invokeStaticMethod != null) {
                return ((Boolean) invokeStaticMethod).booleanValue();
            }
            return true;
        } else if (Build.VERSION.SDK_INT < 21) {
            return true;
        } else {
            if (checkSysCoreNotExist()) {
                return false;
            }
            Object a3 = com.tencent.smtt.utils.j.a(android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{android.webkit.WebView.class}, webView.getView());
            if (a3 != null) {
                return ((Boolean) a3).booleanValue();
            }
            return false;
        }
    }

    public void flush() {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (Build.VERSION.SDK_INT >= 21 && !checkSysCoreNotExist()) {
            com.tencent.smtt.utils.j.a(android.webkit.CookieManager.getInstance(), "flush", new Class[0], new Object[0]);
        }
    }

    public String getCookie(String str) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            if (checkSysCoreNotExist()) {
                return null;
            }
            try {
                return android.webkit.CookieManager.getInstance().getCookie(str);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return a2.c().a(str);
    }

    public boolean hasCookies() {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            if (checkSysCoreNotExist()) {
                return false;
            }
            return android.webkit.CookieManager.getInstance().hasCookies();
        }
        return a2.c().h();
    }

    @Deprecated
    public void removeAllCookie() {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = this.a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().e();
        } else if (checkSysCoreNotExist()) {
        } else {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = this.a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{android.webkit.ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21 && !checkSysCoreNotExist()) {
            com.tencent.smtt.utils.j.a(android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{android.webkit.ValueCallback.class}, valueCallback);
        }
    }

    @Deprecated
    public void removeExpiredCookie() {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        } else if (checkSysCoreNotExist()) {
        } else {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        }
    }

    @Deprecated
    public void removeSessionCookie() {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        } else if (checkSysCoreNotExist()) {
        } else {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{android.webkit.ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21 && !checkSysCoreNotExist()) {
            com.tencent.smtt.utils.j.a(android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{android.webkit.ValueCallback.class}, valueCallback);
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        } else if (checkSysCoreNotExist()) {
        } else {
            android.webkit.CookieManager.getInstance().setAcceptCookie(z);
        }
    }

    public synchronized void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        } else if (Build.VERSION.SDK_INT < 21) {
        } else {
            if (checkSysCoreNotExist()) {
                return;
            }
            com.tencent.smtt.utils.j.a(android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{android.webkit.WebView.class, Boolean.TYPE}, webView.getView(), Boolean.valueOf(z));
        }
    }

    public synchronized void setCookie(String str, String str2) {
        setCookie(str, str2, false);
    }

    public synchronized void setCookie(String str, String str2, ValueCallback<Boolean> valueCallback) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            if (!a2.d()) {
                b bVar = new b();
                bVar.a = 1;
                bVar.b = str;
                bVar.f17717c = str2;
                bVar.d = valueCallback;
                if (this.a == null) {
                    this.a = new CopyOnWriteArrayList<>();
                }
                this.a.add(bVar);
            }
            if (Build.VERSION.SDK_INT < 21) {
                return;
            }
            if (checkSysCoreNotExist()) {
                return;
            }
            com.tencent.smtt.utils.j.a(android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, str, str2, valueCallback);
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, str, str2, valueCallback);
        }
    }

    public synchronized void setCookie(String str, String str2, boolean z) {
        android.webkit.CookieManager cookieManager;
        u a2 = u.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
            if (WebView.hasCreatedSysWebViewInstance()) {
                if (checkSysCoreNotExist()) {
                    return;
                }
                cookieManager = android.webkit.CookieManager.getInstance();
            }
        }
        if (!u.a().d()) {
            b bVar = new b();
            bVar.a = 2;
            bVar.b = str;
            bVar.f17717c = str2;
            bVar.d = null;
            if (this.a == null) {
                this.a = new CopyOnWriteArrayList<>();
            }
            this.a.add(bVar);
        }
        if (checkSysCoreNotExist()) {
            return;
        }
        cookieManager = android.webkit.CookieManager.getInstance();
        cookieManager.setCookie(str, str2);
    }

    public void setCookies(Map<String, String[]> map) {
        u a2 = u.a();
        if ((a2 == null || !a2.b()) ? false : a2.c().a(map)) {
            return;
        }
        for (String str : map.keySet()) {
            for (String str2 : map.get(str)) {
                setCookie(str, str2);
            }
        }
    }
}
