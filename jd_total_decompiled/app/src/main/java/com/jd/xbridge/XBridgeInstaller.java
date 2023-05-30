package com.jd.xbridge;

import com.jd.xbridge.base.IBridgeWebView;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001d\u0010\fJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0004\u00a2\u0006\u0004\b\r\u0010\fJ\r\u0010\u000e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000e\u0010\fJ\r\u0010\u000f\u001a\u00020\u0004\u00a2\u0006\u0004\b\u000f\u0010\fJ\r\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0010\u0010\fJ\r\u0010\u0011\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0011\u0010\fR%\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00130\u00128\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u001c\u001a\u00020\u00198\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001e"}, d2 = {"Lcom/jd/xbridge/XBridgeInstaller;", "", "Lcom/jd/xbridge/base/IBridgeWebView;", "actualView", "", "install", "(Lcom/jd/xbridge/base/IBridgeWebView;)V", "", "url", "loadUrl", "(Ljava/lang/String;)V", "reload", "()V", "onStart", "onResume", "onPause", "onStop", "destroy", "", "Lcom/jd/xbridge/base/d;", com.jingdong.jdsdk.a.a.a, "Ljava/util/Map;", "getBridgeMap", "()Ljava/util/Map;", "bridgeMap", "Lcom/jd/xbridge/XBridge;", "b", "Lcom/jd/xbridge/XBridge;", "jsBridge", "<init>", "XBridge_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes18.dex */
public final class XBridgeInstaller {
    @NotNull

    /* renamed from: a */
    private final Map<String, com.jd.xbridge.base.d> bridgeMap = new HashMap(1);

    /* renamed from: b  reason: from kotlin metadata */
    private XBridge jsBridge;

    public final void destroy() {
        XBridge xBridge = this.jsBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        xBridge.destroy();
    }

    @NotNull
    public final Map<String, com.jd.xbridge.base.d> getBridgeMap() {
        return this.bridgeMap;
    }

    public final void install(@NotNull IBridgeWebView actualView) {
        XBridge xBridge = new XBridge(actualView);
        this.jsBridge = xBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        com.jd.xbridge.base.c.registerBridge(actualView, xBridge);
    }

    public final void loadUrl(@NotNull String url) {
        boolean startsWith$default;
        boolean startsWith$default2;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(url, "http", false, 2, null);
        if (!startsWith$default) {
            startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(url, "file", false, 2, null);
            if (!startsWith$default2) {
                return;
            }
        }
        XBridge xBridge = this.jsBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        xBridge.startQueueRequest();
    }

    public final void onPause() {
        XBridge xBridge = this.jsBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        xBridge.onPause();
    }

    public final void onResume() {
        XBridge xBridge = this.jsBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        xBridge.onResume();
    }

    public final void onStart() {
        XBridge xBridge = this.jsBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        xBridge.onStart();
    }

    public final void onStop() {
        XBridge xBridge = this.jsBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        xBridge.onStop();
    }

    public final void reload() {
        XBridge xBridge = this.jsBridge;
        if (xBridge == null) {
            Intrinsics.throwUninitializedPropertyAccessException("jsBridge");
        }
        xBridge.startQueueRequest();
    }
}
