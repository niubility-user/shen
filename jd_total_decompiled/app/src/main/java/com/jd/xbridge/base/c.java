package com.jd.xbridge.base;

import android.view.View;
import com.jd.xbridge.XBridge;
import com.jd.xbridge.XBridgeManager;
import com.jd.xbridge.e;
import java.util.Map;
import kotlin.jvm.JvmOverloads;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes18.dex */
public final class c {
    @JvmOverloads
    public static final void callJS(@NotNull IBridgeWebView iBridgeWebView) {
        callJS$default(iBridgeWebView, null, null, null, 7, null);
    }

    @JvmOverloads
    public static final void callJS(@NotNull IBridgeWebView iBridgeWebView, @Nullable String str) {
        callJS$default(iBridgeWebView, str, null, null, 6, null);
    }

    @JvmOverloads
    public static final void callJS(@NotNull IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable Object obj) {
        callJS$default(iBridgeWebView, str, obj, null, 4, null);
    }

    @JvmOverloads
    public static final void callJS(@NotNull IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable Object obj, @Nullable IBridgeCallback iBridgeCallback) {
        XBridge xBridge = getXBridge(iBridgeWebView);
        if (xBridge != null) {
            xBridge.callJS(str, obj, iBridgeCallback);
        }
    }

    public static /* synthetic */ void callJS$default(IBridgeWebView iBridgeWebView, String str, Object obj, IBridgeCallback iBridgeCallback, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            obj = null;
        }
        if ((i2 & 4) != 0) {
            iBridgeCallback = null;
        }
        callJS(iBridgeWebView, str, obj, iBridgeCallback);
    }

    public static final void dispatchEvent(@NotNull IBridgeWebView iBridgeWebView, @NotNull String str, @Nullable Object obj) {
        XBridge xBridge = getXBridge(iBridgeWebView);
        if (xBridge != null) {
            xBridge.dispatchEvent(str, obj);
        }
    }

    public static /* synthetic */ void dispatchEvent$default(IBridgeWebView iBridgeWebView, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            obj = null;
        }
        dispatchEvent(iBridgeWebView, str, obj);
    }

    @Nullable
    public static final d getBridge(@NotNull IBridgeWebView iBridgeWebView, @NotNull String str) {
        Map<String, d> bridgeMap = iBridgeWebView.getBridgeMap();
        if (bridgeMap != null) {
            return bridgeMap.get(str);
        }
        return null;
    }

    @Nullable
    public static final XBridge getXBridge(@NotNull IBridgeWebView iBridgeWebView) {
        d bridge = getBridge(iBridgeWebView, "XWebView");
        if (bridge instanceof XBridge) {
            return (XBridge) bridge;
        }
        XBridgeManager.INSTANCE.getWebDebug();
        return null;
    }

    public static final void registerBridge(@NotNull IBridgeWebView iBridgeWebView, @NotNull d dVar) {
        Map<String, d> bridgeMap = iBridgeWebView.getBridgeMap();
        if (bridgeMap != null) {
            bridgeMap.put(dVar.getName(), dVar);
            iBridgeWebView.addJavascriptInterface(dVar, dVar.getName());
        }
    }

    public static final void registerDefaultPlugin(@NotNull IBridgeWebView iBridgeWebView, @NotNull IBridgePlugin iBridgePlugin) {
        XBridge xBridge = getXBridge(iBridgeWebView);
        if (xBridge != null) {
            xBridge.registerDefaultPlugin(iBridgePlugin);
        }
    }

    public static final void registerPlugin(@NotNull IBridgeWebView iBridgeWebView, @NotNull String str, @NotNull IBridgePlugin iBridgePlugin) {
        XBridge xBridge = getXBridge(iBridgeWebView);
        if (xBridge != null) {
            xBridge.registerPlugin(str, iBridgePlugin);
        }
    }

    public static final void runOnMain(@NotNull IBridgeWebView iBridgeWebView, @NotNull Runnable runnable) {
        e eVar = e.b;
        View view = iBridgeWebView.getView();
        eVar.c(view != null ? view.getHandler() : null, runnable);
    }

    public static final void unregisterPlugin(@NotNull IBridgeWebView iBridgeWebView, @NotNull String str) {
        XBridge xBridge = getXBridge(iBridgeWebView);
        if (xBridge != null) {
            xBridge.unregisterPlugin(str);
        }
    }
}
