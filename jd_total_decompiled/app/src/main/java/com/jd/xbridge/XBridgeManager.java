package com.jd.xbridge;

import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b*\u0010+J!\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007J%\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ%\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\u0004\b\u000b\u0010\u0010J\u001d\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0011\u00a2\u0006\u0004\b\u0013\u0010\u0014JM\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00022\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u00022\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001bR1\u0010\"\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00040\u001d8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\"\u0010)\u001a\u00020#8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(\u00a8\u0006,"}, d2 = {"Lcom/jd/xbridge/XBridgeManager;", "", "", "pluginName", "Ljava/lang/Class;", "Lcom/jd/xbridge/base/IBridgePlugin;", "getPluginClass$XBridge_release", "(Ljava/lang/String;)Ljava/lang/Class;", "getPluginClass", "pluginClass", "", "registerPlugin", "(Ljava/lang/String;Ljava/lang/Class;)V", "Lcom/jd/xbridge/base/IBridgeWebView;", "webView", "plugin", "(Lcom/jd/xbridge/base/IBridgeWebView;Ljava/lang/String;Lcom/jd/xbridge/base/IBridgePlugin;)V", "Lcom/jd/xbridge/base/d;", "proxy", "registerBridge", "(Lcom/jd/xbridge/base/IBridgeWebView;Lcom/jd/xbridge/base/d;)V", "functionName", "callbackId", "status", "result", "msg", "callback2H5$XBridge_release", "(Lcom/jd/xbridge/base/IBridgeWebView;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)V", "callback2H5", "", "b", "Lkotlin/Lazy;", com.jingdong.jdsdk.a.a.a, "()Ljava/util/Map;", "mFnClassMap", "", "Z", "getWebDebug", "()Z", "setWebDebug", "(Z)V", "webDebug", "<init>", "()V", "XBridge_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes18.dex */
public final class XBridgeManager {

    /* renamed from: a */
    private static boolean webDebug;
    public static final XBridgeManager INSTANCE = new XBridgeManager();

    /* renamed from: b  reason: from kotlin metadata */
    private static final Lazy mFnClassMap = LazyKt.lazy(b.INSTANCE);

    /* loaded from: classes18.dex */
    public static final class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IBridgeWebView f7265g;

        /* renamed from: h */
        final /* synthetic */ String f7266h;

        a(IBridgeWebView iBridgeWebView, String str) {
            this.f7265g = iBridgeWebView;
            this.f7266h = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f7265g.evaluateJavascript(this.f7266h, null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00020\u0000H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Ljava/util/HashMap;", "", "Ljava/lang/Class;", "Lcom/jd/xbridge/base/IBridgePlugin;", "invoke", "()Ljava/util/HashMap;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes18.dex */
    static final class b extends Lambda implements Function0<HashMap<String, Class<? extends IBridgePlugin>>> {
        public static final b INSTANCE = new b();

        b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final HashMap<String, Class<? extends IBridgePlugin>> invoke() {
            return new HashMap<>();
        }
    }

    private XBridgeManager() {
    }

    private final Map<String, Class<? extends IBridgePlugin>> a() {
        return (Map) mFnClassMap.getValue();
    }

    public final void callback2H5$XBridge_release(@NotNull IBridgeWebView webView, @NotNull String functionName, @Nullable String callbackId, @NotNull String status, @Nullable Object result, @Nullable String msg) {
        if (functionName.length() > 0) {
            com.jd.xbridge.base.c.runOnMain(webView, new a(webView, functionName + " && " + functionName + "('" + new c(status, callbackId, result, msg, false, 16, null) + "');"));
        }
    }

    @Nullable
    public final Class<? extends IBridgePlugin> getPluginClass$XBridge_release(@NotNull String pluginName) {
        return a().get(pluginName);
    }

    public final boolean getWebDebug() {
        return webDebug;
    }

    public final void registerBridge(@NotNull IBridgeWebView webView, @NotNull com.jd.xbridge.base.d proxy) {
        com.jd.xbridge.base.c.registerBridge(webView, proxy);
    }

    public final void registerPlugin(@NotNull String pluginName, @NotNull Class<? extends IBridgePlugin> pluginClass) {
        a().put(pluginName, pluginClass);
    }

    public final void setWebDebug(boolean z) {
        webDebug = z;
    }

    public final void registerPlugin(@NotNull IBridgeWebView webView, @NotNull String pluginName, @NotNull IBridgePlugin plugin) {
        com.jd.xbridge.base.c.registerPlugin(webView, pluginName, plugin);
    }
}
