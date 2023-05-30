package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;

/* loaded from: classes9.dex */
public class WebSettings {
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;
    private IX5WebSettings a;
    private android.webkit.WebSettings b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f17794c;

    /* loaded from: classes9.dex */
    public enum LayoutAlgorithm {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS
    }

    /* loaded from: classes9.dex */
    public enum PluginState {
        ON,
        ON_DEMAND,
        OFF
    }

    /* loaded from: classes9.dex */
    public enum RenderPriority {
        NORMAL,
        HIGH,
        LOW
    }

    /* loaded from: classes9.dex */
    public enum TextSize {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(125),
        LARGEST(150);
        
        int value;

        TextSize(int i2) {
            this.value = i2;
        }
    }

    /* loaded from: classes9.dex */
    public enum ZoomDensity {
        FAR(150),
        MEDIUM(100),
        CLOSE(75);
        
        int value;

        ZoomDensity(int i2) {
            this.value = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSettings(android.webkit.WebSettings webSettings) {
        this.a = null;
        this.b = null;
        this.f17794c = false;
        this.a = null;
        this.b = webSettings;
        this.f17794c = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSettings(IX5WebSettings iX5WebSettings) {
        this.a = null;
        this.b = null;
        this.f17794c = false;
        this.a = iX5WebSettings;
        this.b = null;
        this.f17794c = true;
    }

    @TargetApi(17)
    public static String getDefaultUserAgent(Context context) {
        Object a;
        if (u.a().b()) {
            return u.a().c().i(context);
        }
        if (Build.VERSION.SDK_INT >= 17 && (a = com.tencent.smtt.utils.j.a((Class<?>) android.webkit.WebSettings.class, "getDefaultUserAgent", (Class<?>[]) new Class[]{Context.class}, context)) != null) {
            return (String) a;
        }
        return null;
    }

    @Deprecated
    public boolean enableSmoothTransition() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 11 || (a = com.tencent.smtt.utils.j.a(webSettings, "enableSmoothTransition")) == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        }
        return iX5WebSettings.enableSmoothTransition();
    }

    @TargetApi(11)
    public boolean getAllowContentAccess() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 11 || (a = com.tencent.smtt.utils.j.a(webSettings, "getAllowContentAccess")) == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        }
        return iX5WebSettings.getAllowContentAccess();
    }

    @TargetApi(3)
    public boolean getAllowFileAccess() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getAllowFileAccess();
        }
        return iX5WebSettings.getAllowFileAccess();
    }

    public synchronized boolean getBlockNetworkImage() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getBlockNetworkImage();
        }
        return iX5WebSettings.getBlockNetworkImage();
    }

    @TargetApi(8)
    public synchronized boolean getBlockNetworkLoads() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 8) {
                return webSettings.getBlockNetworkLoads();
            }
            return false;
        }
        return iX5WebSettings.getBlockNetworkLoads();
    }

    @TargetApi(3)
    public boolean getBuiltInZoomControls() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getBuiltInZoomControls();
        }
        return iX5WebSettings.getBuiltInZoomControls();
    }

    public int getCacheMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return 0;
            }
            return webSettings.getCacheMode();
        }
        return iX5WebSettings.getCacheMode();
    }

    public synchronized String getCursiveFontFamily() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getCursiveFontFamily() : iX5WebSettings.getCursiveFontFamily();
    }

    @TargetApi(5)
    public synchronized boolean getDatabaseEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getDatabaseEnabled();
        }
        return iX5WebSettings.getDatabaseEnabled();
    }

    @TargetApi(5)
    @Deprecated
    public synchronized String getDatabasePath() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getDatabasePath() : iX5WebSettings.getDatabasePath();
    }

    public synchronized int getDefaultFixedFontSize() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return 0;
            }
            return webSettings.getDefaultFixedFontSize();
        }
        return iX5WebSettings.getDefaultFixedFontSize();
    }

    public synchronized int getDefaultFontSize() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return 0;
            }
            return webSettings.getDefaultFontSize();
        }
        return iX5WebSettings.getDefaultFontSize();
    }

    public synchronized String getDefaultTextEncodingName() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getDefaultTextEncodingName() : iX5WebSettings.getDefaultTextEncodingName();
    }

    @TargetApi(7)
    @Deprecated
    public ZoomDensity getDefaultZoom() {
        android.webkit.WebSettings webSettings;
        String name;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            name = iX5WebSettings.getDefaultZoom().name();
        } else if (z || (webSettings = this.b) == null) {
            return null;
        } else {
            name = webSettings.getDefaultZoom().name();
        }
        return ZoomDensity.valueOf(name);
    }

    @TargetApi(11)
    public boolean getDisplayZoomControls() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 11 || (a = com.tencent.smtt.utils.j.a(webSettings, "getDisplayZoomControls")) == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        }
        return iX5WebSettings.getDisplayZoomControls();
    }

    @TargetApi(7)
    public synchronized boolean getDomStorageEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getDomStorageEnabled();
        }
        return iX5WebSettings.getDomStorageEnabled();
    }

    public synchronized String getFantasyFontFamily() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getFantasyFontFamily() : iX5WebSettings.getFantasyFontFamily();
    }

    public synchronized String getFixedFontFamily() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getFixedFontFamily() : iX5WebSettings.getFixedFontFamily();
    }

    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getJavaScriptCanOpenWindowsAutomatically();
        }
        return iX5WebSettings.getJavaScriptCanOpenWindowsAutomatically();
    }

    public synchronized boolean getJavaScriptEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getJavaScriptEnabled();
        }
        return iX5WebSettings.getJavaScriptEnabled();
    }

    public synchronized LayoutAlgorithm getLayoutAlgorithm() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return null;
            }
            return LayoutAlgorithm.valueOf(webSettings.getLayoutAlgorithm().name());
        }
        return LayoutAlgorithm.valueOf(iX5WebSettings.getLayoutAlgorithm().name());
    }

    @Deprecated
    public boolean getLightTouchEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getLightTouchEnabled();
        }
        return iX5WebSettings.getLightTouchEnabled();
    }

    @TargetApi(7)
    public boolean getLoadWithOverviewMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getLoadWithOverviewMode();
        }
        return iX5WebSettings.getLoadWithOverviewMode();
    }

    public synchronized boolean getLoadsImagesAutomatically() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getLoadsImagesAutomatically();
        }
        return iX5WebSettings.getLoadsImagesAutomatically();
    }

    @TargetApi(17)
    public boolean getMediaPlaybackRequiresUserGesture() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 17 || (a = com.tencent.smtt.utils.j.a(webSettings, "getMediaPlaybackRequiresUserGesture")) == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        }
        return iX5WebSettings.getMediaPlaybackRequiresUserGesture();
    }

    public synchronized int getMinimumFontSize() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return 0;
            }
            return webSettings.getMinimumFontSize();
        }
        return iX5WebSettings.getMinimumFontSize();
    }

    public synchronized int getMinimumLogicalFontSize() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return 0;
            }
            return webSettings.getMinimumLogicalFontSize();
        }
        return iX5WebSettings.getMinimumLogicalFontSize();
    }

    public synchronized int getMixedContentMode() {
        IX5WebSettings iX5WebSettings;
        int i2 = -1;
        if (!this.f17794c || (iX5WebSettings = this.a) == null) {
            if (Build.VERSION.SDK_INT < 21) {
                return -1;
            }
            Object a = com.tencent.smtt.utils.j.a(this.b, "getMixedContentMode", new Class[0], new Object[0]);
            if (a != null) {
                i2 = ((Integer) a).intValue();
            }
            return i2;
        }
        return iX5WebSettings.getMixedContentMode();
    }

    @Deprecated
    public boolean getNavDump() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null || (a = com.tencent.smtt.utils.j.a(webSettings, "getNavDump")) == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        }
        return iX5WebSettings.getNavDump();
    }

    @TargetApi(8)
    @Deprecated
    public synchronized PluginState getPluginState() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 8) {
                Object a = com.tencent.smtt.utils.j.a(webSettings, "getPluginState");
                if (a == null) {
                    return null;
                }
                return PluginState.valueOf(((WebSettings.PluginState) a).name());
            }
            return null;
        }
        return PluginState.valueOf(iX5WebSettings.getPluginState().name());
    }

    @TargetApi(8)
    @Deprecated
    public synchronized boolean getPluginsEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 > 17) {
                if (i2 == 18) {
                    return WebSettings.PluginState.ON == webSettings.getPluginState();
                }
                return false;
            }
            Object a = com.tencent.smtt.utils.j.a(webSettings, "getPluginsEnabled");
            if (a != null) {
                r1 = ((Boolean) a).booleanValue();
            }
            return r1;
        }
        return iX5WebSettings.getPluginsEnabled();
    }

    @Deprecated
    public synchronized String getPluginsPath() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return "";
            }
            if (Build.VERSION.SDK_INT <= 17) {
                Object a = com.tencent.smtt.utils.j.a(webSettings, "getPluginsPath");
                return a == null ? null : (String) a;
            }
            return "";
        }
        return iX5WebSettings.getPluginsPath();
    }

    public boolean getSafeBrowsingEnabled() {
        IX5WebSettings iX5WebSettings;
        android.webkit.WebSettings webSettings;
        boolean z = this.f17794c;
        if (!z && (webSettings = this.b) != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                return webSettings.getSafeBrowsingEnabled();
            }
            return false;
        } else if (!z || (iX5WebSettings = this.a) == null) {
            return false;
        } else {
            try {
                return iX5WebSettings.getSafeBrowsingEnabled();
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
    }

    public synchronized String getSansSerifFontFamily() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getSansSerifFontFamily() : iX5WebSettings.getSansSerifFontFamily();
    }

    @Deprecated
    public boolean getSaveFormData() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getSaveFormData();
        }
        return iX5WebSettings.getSaveFormData();
    }

    @Deprecated
    public boolean getSavePassword() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getSavePassword();
        }
        return iX5WebSettings.getSavePassword();
    }

    public synchronized String getSerifFontFamily() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getSerifFontFamily() : iX5WebSettings.getSerifFontFamily();
    }

    public synchronized String getStandardFontFamily() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getStandardFontFamily() : iX5WebSettings.getStandardFontFamily();
    }

    @Deprecated
    public TextSize getTextSize() {
        android.webkit.WebSettings webSettings;
        String name;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            name = iX5WebSettings.getTextSize().name();
        } else if (z || (webSettings = this.b) == null) {
            return null;
        } else {
            name = webSettings.getTextSize().name();
        }
        return TextSize.valueOf(name);
    }

    @TargetApi(14)
    public synchronized int getTextZoom() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return 0;
            }
            if (Build.VERSION.SDK_INT < 14) {
                return 0;
            }
            try {
                return webSettings.getTextZoom();
            } catch (Exception unused) {
                Object a = com.tencent.smtt.utils.j.a(this.b, "getTextZoom");
                if (a == null) {
                    return 0;
                }
                return ((Integer) a).intValue();
            }
        }
        return iX5WebSettings.getTextZoom();
    }

    @Deprecated
    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        android.webkit.WebSettings webSettings;
        Object a;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null || (a = com.tencent.smtt.utils.j.a(webSettings, "getUseWebViewBackgroundForOverscrollBackground")) == null) {
                return false;
            }
            return ((Boolean) a).booleanValue();
        }
        return iX5WebSettings.getUseWebViewBackgroundForOverscrollBackground();
    }

    public synchronized boolean getUseWideViewPort() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.getUseWideViewPort();
        }
        return iX5WebSettings.getUseWideViewPort();
    }

    @TargetApi(3)
    public String getUserAgentString() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        return (!z || (iX5WebSettings = this.a) == null) ? (z || (webSettings = this.b) == null) ? "" : webSettings.getUserAgentString() : iX5WebSettings.getUserAgentString();
    }

    @TargetApi(11)
    public void setAllowContentAccess(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowContentAccess(z);
        } else if (z2 || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 11) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setAllowContentAccess", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(3)
    public void setAllowFileAccess(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowFileAccess(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setAllowFileAccess(z);
        }
    }

    @TargetApi(16)
    public void setAllowFileAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowFileAccessFromFileURLs(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setAllowFileAccessFromFileURLs", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(16)
    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowUniversalAccessFromFileURLs(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setAllowUniversalAccessFromFileURLs", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(7)
    public void setAppCacheEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAppCacheEnabled(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setAppCacheEnabled(z);
        }
    }

    @TargetApi(7)
    @Deprecated
    public void setAppCacheMaxSize(long j2) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAppCacheMaxSize(j2);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setAppCacheMaxSize(j2);
        }
    }

    @TargetApi(7)
    public void setAppCachePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAppCachePath(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setAppCachePath(str);
        }
    }

    public void setBlockNetworkImage(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setBlockNetworkImage(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setBlockNetworkImage(z);
        }
    }

    @TargetApi(8)
    public synchronized void setBlockNetworkLoads(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setBlockNetworkLoads(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            if (Build.VERSION.SDK_INT >= 8) {
                webSettings.setBlockNetworkLoads(z);
            }
        }
    }

    @TargetApi(3)
    public void setBuiltInZoomControls(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setBuiltInZoomControls(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setBuiltInZoomControls(z);
        }
    }

    public void setCacheMode(int i2) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setCacheMode(i2);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setCacheMode(i2);
        }
    }

    public synchronized void setCursiveFontFamily(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setCursiveFontFamily(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setCursiveFontFamily(str);
        }
    }

    @TargetApi(5)
    public void setDatabaseEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDatabaseEnabled(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setDatabaseEnabled(z);
        }
    }

    @TargetApi(5)
    @Deprecated
    public void setDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDatabasePath(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setDatabasePath", new Class[]{String.class}, str);
        }
    }

    public synchronized void setDefaultFixedFontSize(int i2) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDefaultFixedFontSize(i2);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setDefaultFixedFontSize(i2);
        }
    }

    public synchronized void setDefaultFontSize(int i2) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDefaultFontSize(i2);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setDefaultFontSize(i2);
        }
    }

    public synchronized void setDefaultTextEncodingName(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDefaultTextEncodingName(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setDefaultTextEncodingName(str);
        }
    }

    @TargetApi(7)
    @Deprecated
    public void setDefaultZoom(ZoomDensity zoomDensity) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDefaultZoom(IX5WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        }
    }

    @TargetApi(11)
    public void setDisplayZoomControls(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDisplayZoomControls(z);
        } else if (z2 || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 11) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setDisplayZoomControls", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(7)
    public void setDomStorageEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDomStorageEnabled(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setDomStorageEnabled(z);
        }
    }

    @TargetApi(11)
    @Deprecated
    public void setEnableSmoothTransition(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setEnableSmoothTransition(z);
        } else if (z2 || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 11) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setEnableSmoothTransition", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setFantasyFontFamily(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setFantasyFontFamily(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setFantasyFontFamily(str);
        }
    }

    public synchronized void setFixedFontFamily(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setFixedFontFamily(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setFixedFontFamily(str);
        }
    }

    @TargetApi(5)
    @Deprecated
    public void setGeolocationDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setGeolocationDatabasePath(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setGeolocationDatabasePath(str);
        }
    }

    @TargetApi(5)
    public void setGeolocationEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setGeolocationEnabled(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setGeolocationEnabled(z);
        }
    }

    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setJavaScriptCanOpenWindowsAutomatically(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setJavaScriptCanOpenWindowsAutomatically(z);
        }
    }

    @Deprecated
    public void setJavaScriptEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        try {
            boolean z2 = this.f17794c;
            if (z2 && (iX5WebSettings = this.a) != null) {
                iX5WebSettings.setJavaScriptEnabled(z);
            } else if (z2 || (webSettings = this.b) == null) {
            } else {
                webSettings.setJavaScriptEnabled(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        }
    }

    @Deprecated
    public void setLightTouchEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLightTouchEnabled(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setLightTouchEnabled(z);
        }
    }

    @TargetApi(7)
    public void setLoadWithOverviewMode(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLoadWithOverviewMode(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setLoadWithOverviewMode(z);
        }
    }

    public void setLoadsImagesAutomatically(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLoadsImagesAutomatically(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setLoadsImagesAutomatically(z);
        }
    }

    @TargetApi(17)
    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setMediaPlaybackRequiresUserGesture(z);
        } else if (z2 || (webSettings = this.b) == null || Build.VERSION.SDK_INT < 17) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setMinimumFontSize(int i2) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setMinimumFontSize(i2);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setMinimumFontSize(i2);
        }
    }

    public synchronized void setMinimumLogicalFontSize(int i2) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setMinimumLogicalFontSize(i2);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setMinimumLogicalFontSize(i2);
        }
    }

    @TargetApi(21)
    public void setMixedContentMode(int i2) {
        android.webkit.WebSettings webSettings;
        boolean z = this.f17794c;
        if ((!z || this.a == null) && !z && (webSettings = this.b) != null && Build.VERSION.SDK_INT >= 21) {
            com.tencent.smtt.utils.j.a(webSettings, "setMixedContentMode", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        }
    }

    @Deprecated
    public void setNavDump(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setNavDump(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setNavDump", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setNeedInitialFocus(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setNeedInitialFocus(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setNeedInitialFocus(z);
        }
    }

    @TargetApi(8)
    @Deprecated
    public synchronized void setPluginState(PluginState pluginState) {
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setPluginState(IX5WebSettings.PluginState.valueOf(pluginState.name()));
        } else if (z || this.b == null) {
        } else {
            if (Build.VERSION.SDK_INT >= 8) {
                com.tencent.smtt.utils.j.a(this.b, "setPluginState", new Class[]{WebSettings.PluginState.class}, WebSettings.PluginState.valueOf(pluginState.name()));
            }
        }
    }

    @Deprecated
    public void setPluginsEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setPluginsEnabled(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setPluginsEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @Deprecated
    public synchronized void setPluginsPath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setPluginsPath(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setPluginsPath", new Class[]{String.class}, str);
        }
    }

    @Deprecated
    public void setRenderPriority(RenderPriority renderPriority) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setRenderPriority(IX5WebSettings.RenderPriority.valueOf(renderPriority.name()));
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setRenderPriority(WebSettings.RenderPriority.valueOf(renderPriority.name()));
        }
    }

    public void setSafeBrowsingEnabled(boolean z) {
        IX5WebSettings iX5WebSettings;
        android.webkit.WebSettings webSettings;
        boolean z2 = this.f17794c;
        if (!z2 && (webSettings = this.b) != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                webSettings.setSafeBrowsingEnabled(z);
            }
        } else if (!z2 || (iX5WebSettings = this.a) == null) {
        } else {
            try {
                iX5WebSettings.setSafeBrowsingEnabled(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public synchronized void setSansSerifFontFamily(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSansSerifFontFamily(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setSansSerifFontFamily(str);
        }
    }

    @Deprecated
    public void setSaveFormData(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSaveFormData(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setSaveFormData(z);
        }
    }

    @Deprecated
    public void setSavePassword(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSavePassword(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setSavePassword(z);
        }
    }

    public synchronized void setSerifFontFamily(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSerifFontFamily(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setSerifFontFamily(str);
        }
    }

    public synchronized void setStandardFontFamily(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setStandardFontFamily(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setStandardFontFamily(str);
        }
    }

    public void setSupportMultipleWindows(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSupportMultipleWindows(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setSupportMultipleWindows(z);
        }
    }

    public void setSupportZoom(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSupportZoom(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setSupportZoom(z);
        }
    }

    @Deprecated
    public void setTextSize(TextSize textSize) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setTextSize(IX5WebSettings.TextSize.valueOf(textSize.name()));
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setTextSize(WebSettings.TextSize.valueOf(textSize.name()));
        }
    }

    @TargetApi(14)
    public synchronized void setTextZoom(int i2) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setTextZoom(i2);
        } else if (!z && (webSettings = this.b) != null) {
            if (Build.VERSION.SDK_INT < 14) {
                return;
            }
            try {
                webSettings.setTextZoom(i2);
            } catch (Exception unused) {
                com.tencent.smtt.utils.j.a(this.b, "setTextZoom", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
            }
        }
    }

    @Deprecated
    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUseWebViewBackgroundForOverscrollBackground(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            com.tencent.smtt.utils.j.a(webSettings, "setUseWebViewBackgroundForOverscrollBackground", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setUseWideViewPort(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z2 = this.f17794c;
        if (z2 && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUseWideViewPort(z);
        } else if (z2 || (webSettings = this.b) == null) {
        } else {
            webSettings.setUseWideViewPort(z);
        }
    }

    public void setUserAgent(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUserAgent(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setUserAgentString(str);
        }
    }

    @TargetApi(3)
    public void setUserAgentString(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (z && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUserAgentString(str);
        } else if (z || (webSettings = this.b) == null) {
        } else {
            webSettings.setUserAgentString(str);
        }
    }

    public synchronized boolean supportMultipleWindows() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.supportMultipleWindows();
        }
        return iX5WebSettings.supportMultipleWindows();
    }

    public boolean supportZoom() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        boolean z = this.f17794c;
        if (!z || (iX5WebSettings = this.a) == null) {
            if (z || (webSettings = this.b) == null) {
                return false;
            }
            return webSettings.supportZoom();
        }
        return iX5WebSettings.supportZoom();
    }
}
