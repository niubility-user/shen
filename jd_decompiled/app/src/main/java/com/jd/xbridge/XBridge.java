package com.jd.xbridge;

import com.jd.libs.hybrid.xbehavior.events.PageLifeCycleEvent;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 Z2\u00020\u0001:\u0002>6B\u000f\u0012\u0006\u0010Q\u001a\u00020M\u00a2\u0006\u0004\bX\u0010YJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0004J\r\u0010\u0007\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0004J\r\u0010\b\u001a\u00020\u0002\u00a2\u0006\u0004\b\b\u0010\u0004J\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0004J\u001d\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u0012\u0010\u0013J1\u0010\u0018\u001a\u00020\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\n2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0004\b\u001b\u0010\u001cJA\u0010 \u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0007\u00a2\u0006\u0004\b \u0010!J\u0019\u0010#\u001a\u00020\u00022\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0007\u00a2\u0006\u0004\b#\u0010\u0011J\u0017\u0010&\u001a\u00020\u00022\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00022\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b(\u0010'J\u0017\u0010)\u001a\u00020\u00022\u0006\u0010%\u001a\u00020$H\u0002\u00a2\u0006\u0004\b)\u0010'J\u000f\u0010*\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b*\u0010\u0004J\u000f\u0010+\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b+\u0010\u0004J\u001b\u0010,\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b,\u0010-J\u0019\u0010.\u001a\u00020\u00022\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b.\u0010\u0011JE\u00104\u001a\u00020\u00022\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010/\u001a\u00020\n2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u00101\u001a\u0004\u0018\u00010\n2\b\b\u0002\u00103\u001a\u000202H\u0002\u00a2\u0006\u0004\b4\u00105JG\u00106\u001a\u00020\u00022\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010/\u001a\u00020\n2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u00101\u001a\u0004\u0018\u00010\nH\u0002\u00a2\u0006\u0004\b6\u00107J\u0017\u00108\u001a\u00020\u00022\u0006\u00101\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b8\u0010\u0011J\u001b\u0010<\u001a\u00020\u00022\n\u0010;\u001a\u000609j\u0002`:H\u0002\u00a2\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u00022\u0006\u00101\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b>\u0010\u0011R\u0018\u0010@\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b;\u0010?R)\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\f0A8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bB\u0010DR)\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00160A8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010C\u001a\u0004\b;\u0010DR\u0016\u0010I\u001a\u00020G8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010HR\u0016\u0010L\u001a\u00020\n8V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bJ\u0010KR\u0019\u0010Q\u001a\u00020M8\u0006@\u0006\u00a2\u0006\f\n\u0004\b,\u0010N\u001a\u0004\bO\u0010PR\u0016\u0010T\u001a\u00020R8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010SR\u001e\u0010W\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010U8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u0010V\u00a8\u0006["}, d2 = {"Lcom/jd/xbridge/XBridge;", "Lcom/jd/xbridge/base/d;", "", "onStart", "()V", "onResume", "onPause", "onStop", "destroy", "startQueueRequest", "", "pluginName", "Lcom/jd/xbridge/base/IBridgePlugin;", "plugin", "registerPlugin", "(Ljava/lang/String;Lcom/jd/xbridge/base/IBridgePlugin;)V", "unregisterPlugin", "(Ljava/lang/String;)V", "registerDefaultPlugin", "(Lcom/jd/xbridge/base/IBridgePlugin;)V", "", "params", "Lcom/jd/xbridge/base/IBridgeCallback;", "callback", "callJS", "(Ljava/lang/String;Ljava/lang/Object;Lcom/jd/xbridge/base/IBridgeCallback;)V", "eventName", "dispatchEvent", "(Ljava/lang/String;Ljava/lang/Object;)V", "action", "callbackName", "callbackId", "callNative", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "obj", "_callNative", "Lcom/jd/xbridge/a;", "request", "k", "(Lcom/jd/xbridge/a;)V", "c", NotifyType.LIGHTS, "d", JshopConst.JSHOP_PROMOTIO_H, "g", "(Ljava/lang/String;)Lcom/jd/xbridge/base/IBridgePlugin;", "m", "status", "data", "msg", "", "complete", PersonalConstants.ICON_STYLE_N, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Z)V", "b", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)V", "i", "Ljava/lang/Exception;", "Lkotlin/Exception;", com.jingdong.app.mall.e.a, "j", "(Ljava/lang/Exception;)V", com.jingdong.jdsdk.a.a.a, "Lcom/jd/xbridge/base/IBridgePlugin;", "nativeDefaultPlugin", "", "f", "Lkotlin/Lazy;", "()Ljava/util/Map;", "nativeLocalPluginMap", "nativeCallbackMap", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "firstJsQueue", "getName", "()Ljava/lang/String;", "name", "Lcom/jd/xbridge/base/IBridgeWebView;", "Lcom/jd/xbridge/base/IBridgeWebView;", "getWebView", "()Lcom/jd/xbridge/base/IBridgeWebView;", "webView", "Ljava/util/concurrent/atomic/AtomicInteger;", "Ljava/util/concurrent/atomic/AtomicInteger;", "callbackIdCreator", "Ljava/util/LinkedList;", "Ljava/util/LinkedList;", "callJsQueue", "<init>", "(Lcom/jd/xbridge/base/IBridgeWebView;)V", "Companion", "XBridge_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class XBridge implements com.jd.xbridge.base.d {
    @NotNull
    public static final String JS_NAME = "XWebView";

    /* renamed from: a */
    private AtomicBoolean firstJsQueue = new AtomicBoolean(true);

    /* renamed from: b  reason: from kotlin metadata */
    private LinkedList<a> callJsQueue = new LinkedList<>();

    /* renamed from: c  reason: from kotlin metadata */
    private final AtomicInteger callbackIdCreator = new AtomicInteger(0);

    /* renamed from: d  reason: from kotlin metadata */
    private final Lazy nativeCallbackMap;

    /* renamed from: e */
    private IBridgePlugin nativeDefaultPlugin;

    /* renamed from: f  reason: from kotlin metadata */
    private final Lazy nativeLocalPluginMap;
    @NotNull

    /* renamed from: g  reason: from kotlin metadata */
    private final IBridgeWebView webView;

    /* loaded from: classes18.dex */
    public final class b implements IBridgePlugin {
        public b() {
            XBridge.this = r1;
        }

        @Override // com.jd.xbridge.base.IBridgePlugin
        public boolean execute(@Nullable IBridgeWebView iBridgeWebView, @Nullable String str, @Nullable String str2, @Nullable IBridgeCallback iBridgeCallback) {
            if (str != null) {
                int hashCode = str.hashCode();
                if (hashCode != -404416119) {
                    if (hashCode == 1556682520 && str.equals("_jsInit")) {
                        XBridge.this.h();
                        return true;
                    }
                } else if (str.equals("_respondFromJs")) {
                    XBridge.this.m(str2);
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes18.dex */
    public static final class c implements com.jd.xbridge.base.b {
        final /* synthetic */ String b;

        c(String str) {
            XBridge.this = r1;
            this.b = str;
        }

        @Override // com.jd.xbridge.base.IBridgeCallback
        public void onError(@Nullable String str) {
            XBridge.this.i("_callNative -> onError, msg: " + str);
            XBridge.o(XBridge.this, this.b, "-1", null, str, false, 16, null);
        }

        @Override // com.jd.xbridge.base.b
        public void onProgress(@Nullable Object obj) {
            XBridge.this.i("_callNative -> onProgress, data: " + obj);
            XBridge.this.n(this.b, "0", obj, "onProgress", false);
        }

        @Override // com.jd.xbridge.base.IBridgeCallback
        public void onSuccess(@Nullable Object obj) {
            XBridge.this.i("_callNative -> onSuccess, result: " + obj);
            XBridge.this.n(this.b, "0", obj, null, true);
        }
    }

    /* loaded from: classes18.dex */
    public static final class d implements Runnable {

        /* renamed from: h */
        final /* synthetic */ String f7248h;

        d(String str) {
            XBridge.this = r1;
            this.f7248h = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IBridgeWebView webView = XBridge.this.getWebView();
            String format = String.format("alert('XBridge Debug Msg: %s')", Arrays.copyOf(new Object[]{this.f7248h}, 1));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
            webView.evaluateJavascript(format, null);
        }
    }

    /* loaded from: classes18.dex */
    public static final class e implements com.jd.xbridge.base.b {
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f7249c;

        e(String str, String str2) {
            XBridge.this = r1;
            this.b = str;
            this.f7249c = str2;
        }

        @Override // com.jd.xbridge.base.IBridgeCallback
        public void onError(@Nullable String str) {
            XBridge.this.i("_callNative -> onError, msg: " + str);
            XBridge.this.b(this.b, this.f7249c, "-1", null, str);
        }

        @Override // com.jd.xbridge.base.b
        public void onProgress(@Nullable Object obj) {
        }

        @Override // com.jd.xbridge.base.IBridgeCallback
        public void onSuccess(@Nullable Object obj) {
            XBridge.this.i("_callNative -> onSuccess, result: " + obj);
            XBridge.this.b(this.b, this.f7249c, "0", obj, null);
        }
    }

    /* loaded from: classes18.dex */
    public static final class f implements Runnable {

        /* renamed from: h */
        final /* synthetic */ String f7251h;

        /* renamed from: i */
        final /* synthetic */ String f7252i;

        f(String str, String str2) {
            XBridge.this = r1;
            this.f7251h = str;
            this.f7252i = str2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IBridgeWebView webView = XBridge.this.getWebView();
            String format = String.format(";(function(){var event = new CustomEvent('%s', {'detail': %s}); window.dispatchEvent(event);})();", Arrays.copyOf(new Object[]{this.f7251h, this.f7252i}, 2));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
            webView.evaluateJavascript(format, null);
        }
    }

    /* loaded from: classes18.dex */
    public static final class g implements Runnable {

        /* renamed from: h */
        final /* synthetic */ a f7254h;

        g(a aVar) {
            XBridge.this = r1;
            this.f7254h = aVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IBridgeWebView webView = XBridge.this.getWebView();
            String format = String.format("window.XBridge._handleRequestFromNative(%s)", Arrays.copyOf(new Object[]{this.f7254h.toString()}, 1));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
            webView.evaluateJavascript(format, null);
        }
    }

    /* loaded from: classes18.dex */
    public static final class h implements Runnable {
        h() {
            XBridge.this = r1;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IBridgeWebView webView = XBridge.this.getWebView();
            String format = String.format("window.XBridge.setDebug(%b)", Arrays.copyOf(new Object[]{Boolean.TRUE}, 1));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
            webView.evaluateJavascript(format, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/jd/xbridge/base/IBridgeCallback;", "invoke", "()Ljava/util/concurrent/ConcurrentHashMap;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes18.dex */
    public static final class i extends Lambda implements Function0<ConcurrentHashMap<String, IBridgeCallback>> {
        public static final i INSTANCE = new i();

        i() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final ConcurrentHashMap<String, IBridgeCallback> invoke() {
            return new ConcurrentHashMap<>();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/util/HashMap;", "", "Lcom/jd/xbridge/base/IBridgePlugin;", "invoke", "()Ljava/util/HashMap;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes18.dex */
    public static final class j extends Lambda implements Function0<HashMap<String, IBridgePlugin>> {
        public static final j INSTANCE = new j();

        j() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final HashMap<String, IBridgePlugin> invoke() {
            return new HashMap<>();
        }
    }

    /* loaded from: classes18.dex */
    public static final class k implements Runnable {

        /* renamed from: g */
        final /* synthetic */ IBridgeCallback f7256g;

        /* renamed from: h */
        final /* synthetic */ XBridge f7257h;

        /* renamed from: i */
        final /* synthetic */ Ref.ObjectRef f7258i;

        k(IBridgeCallback iBridgeCallback, XBridge xBridge, Ref.ObjectRef objectRef) {
            this.f7256g = iBridgeCallback;
            this.f7257h = xBridge;
            this.f7258i = objectRef;
        }

        @Override // java.lang.Runnable
        public final void run() {
            com.jd.xbridge.c cVar = (com.jd.xbridge.c) this.f7258i.element;
            if (cVar != null) {
                try {
                    if (!cVar.b()) {
                        IBridgeCallback iBridgeCallback = this.f7256g;
                        if (iBridgeCallback instanceof com.jd.xbridge.base.b) {
                            ((com.jd.xbridge.base.b) iBridgeCallback).onProgress(cVar.c());
                            return;
                        }
                    }
                    Map e2 = this.f7257h.e();
                    String a = cVar.a();
                    if (e2 != null) {
                        TypeIntrinsics.asMutableMap(e2).remove(a);
                        if (Intrinsics.areEqual(cVar.e(), "0")) {
                            this.f7256g.onSuccess(cVar.c());
                            return;
                        } else {
                            this.f7256g.onError(cVar.d());
                            return;
                        }
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, V>");
                } catch (Exception e3) {
                    this.f7257h.j(e3);
                    this.f7257h.a("RespondFromJs Error, err = " + e3.getMessage());
                }
            }
        }
    }

    /* loaded from: classes18.dex */
    public static final class l implements Runnable {

        /* renamed from: h */
        final /* synthetic */ String f7260h;

        /* renamed from: i */
        final /* synthetic */ String f7261i;

        /* renamed from: j */
        final /* synthetic */ Object f7262j;

        /* renamed from: k */
        final /* synthetic */ String f7263k;

        /* renamed from: l */
        final /* synthetic */ boolean f7264l;

        l(String str, String str2, Object obj, String str3, boolean z) {
            XBridge.this = r1;
            this.f7260h = str;
            this.f7261i = str2;
            this.f7262j = obj;
            this.f7263k = str3;
            this.f7264l = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            IBridgeWebView webView = XBridge.this.getWebView();
            StringBuilder sb = new StringBuilder();
            sb.append('\'');
            sb.append(new com.jd.xbridge.c(this.f7260h, this.f7261i, this.f7262j, this.f7263k, this.f7264l));
            sb.append('\'');
            String format = String.format("window.XBridge._handleResponseFromNative(%s)", Arrays.copyOf(new Object[]{sb.toString()}, 1));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
            webView.evaluateJavascript(format, null);
        }
    }

    public XBridge(@NotNull IBridgeWebView iBridgeWebView) {
        Lazy lazy;
        Lazy lazy2;
        this.webView = iBridgeWebView;
        lazy = LazyKt__LazyJVMKt.lazy(i.INSTANCE);
        this.nativeCallbackMap = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(j.INSTANCE);
        this.nativeLocalPluginMap = lazy2;
        registerPlugin("_xbridge", new b());
    }

    public final void a(String str) {
        if (XBridgeManager.INSTANCE.getWebDebug()) {
            if (str.length() > 0) {
                com.jd.xbridge.base.c.runOnMain(this.webView, new d(str));
            }
        }
    }

    public final void b(String callbackName, String callbackId, String status, Object data, String msg) {
        if (callbackName != null) {
            XBridgeManager.INSTANCE.callback2H5$XBridge_release(this.webView, callbackName, callbackId, status, data, msg);
        }
    }

    private final void c(a request) {
        String c2 = request.c();
        if (!(c2 == null || c2.length() == 0) && request.b() != null) {
            Map<String, IBridgeCallback> e2 = e();
            String c3 = request.c();
            IBridgeCallback b2 = request.b();
            if (b2 == null) {
                Intrinsics.throwNpe();
            }
            e2.put(c3, b2);
        }
        com.jd.xbridge.base.c.runOnMain(this.webView, new g(request));
    }

    public static /* synthetic */ void callJS$default(XBridge xBridge, String str, Object obj, IBridgeCallback iBridgeCallback, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            str = null;
        }
        if ((i2 & 2) != 0) {
            obj = null;
        }
        if ((i2 & 4) != 0) {
            iBridgeCallback = null;
        }
        xBridge.callJS(str, obj, iBridgeCallback);
    }

    private final void d() {
        LinkedList<a> linkedList = this.callJsQueue;
        StringBuilder sb = new StringBuilder();
        sb.append("dispatchStartupJsCall, queue size = ");
        sb.append(linkedList != null ? Integer.valueOf(linkedList.size()) : null);
        i(sb.toString());
        this.callJsQueue = null;
        if (linkedList != null) {
            Iterator<T> it = linkedList.iterator();
            while (it.hasNext()) {
                c((a) it.next());
            }
        }
    }

    public static /* synthetic */ void dispatchEvent$default(XBridge xBridge, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            obj = null;
        }
        xBridge.dispatchEvent(str, obj);
    }

    public final Map<String, IBridgeCallback> e() {
        return (Map) this.nativeCallbackMap.getValue();
    }

    private final Map<String, IBridgePlugin> f() {
        return (Map) this.nativeLocalPluginMap.getValue();
    }

    private final IBridgePlugin g(String pluginName) {
        IBridgePlugin iBridgePlugin = this.nativeDefaultPlugin;
        if (!(pluginName == null || pluginName.length() == 0)) {
            synchronized (this) {
                IBridgePlugin iBridgePlugin2 = f().get(pluginName);
                if (iBridgePlugin2 == null) {
                    Class<? extends IBridgePlugin> pluginClass$XBridge_release = XBridgeManager.INSTANCE.getPluginClass$XBridge_release(pluginName);
                    r2 = pluginClass$XBridge_release != null ? pluginClass$XBridge_release.newInstance() : null;
                    if (r2 != null) {
                        f().put(pluginName, r2);
                    }
                } else {
                    r2 = iBridgePlugin2;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        if (r2 == null) {
            if (iBridgePlugin == null) {
                i("_callNative -> Use native default plugin to process this calling of " + pluginName);
                return iBridgePlugin;
            }
            return iBridgePlugin;
        }
        return r2;
    }

    public final void h() {
        if (XBridgeManager.INSTANCE.getWebDebug()) {
            com.jd.xbridge.base.c.runOnMain(this.webView, new h());
        }
        d();
    }

    public final void i(String msg) {
        if (XBridgeManager.INSTANCE.getWebDebug() && msg.length() <= 0) {
        }
    }

    public final void j(Exception r2) {
        if (XBridgeManager.INSTANCE.getWebDebug()) {
            r2.getMessage();
        }
    }

    private final void k(a request) {
        LinkedList<a> linkedList = this.callJsQueue;
        if (linkedList != null) {
            linkedList.addLast(request);
            i("queueJsCall, queue size = " + linkedList.size());
            if (linkedList != null) {
                return;
            }
        }
        i("dispatchJsCall, request: " + request.e());
        c(request);
        Unit unit = Unit.INSTANCE;
    }

    private final void l(a aVar) {
        String c2 = aVar.c();
        if (c2 == null || c2.length() == 0) {
            return;
        }
        e().remove(aVar.c());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void m(String obj) {
        T t;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        if (obj != null) {
            try {
                t = com.jd.xbridge.d.a(new JSONObject(obj));
            } catch (JSONException e2) {
                j(e2);
                a("RespondFromJs, cannot convert " + obj + " to json, e: " + e2.getMessage());
            }
        } else {
            t = 0;
        }
        objectRef.element = t;
        IBridgeCallback iBridgeCallback = ((com.jd.xbridge.c) objectRef.element) != null ? e().get(((com.jd.xbridge.c) objectRef.element).a()) : null;
        StringBuilder sb = new StringBuilder();
        sb.append("_respondFromJs -> callbackId:");
        com.jd.xbridge.c cVar = (com.jd.xbridge.c) objectRef.element;
        sb.append(cVar != null ? cVar.a() : null);
        sb.append(", ");
        sb.append("callback:");
        sb.append(iBridgeCallback);
        sb.append(", data:");
        com.jd.xbridge.c cVar2 = (com.jd.xbridge.c) objectRef.element;
        sb.append(cVar2 != null ? cVar2.c() : null);
        sb.append(", ");
        sb.append("success:");
        com.jd.xbridge.c cVar3 = (com.jd.xbridge.c) objectRef.element;
        sb.append(Intrinsics.areEqual(cVar3 != null ? cVar3.e() : null, "0"));
        sb.append(", ");
        sb.append("complete:");
        com.jd.xbridge.c cVar4 = (com.jd.xbridge.c) objectRef.element;
        sb.append(cVar4 != null ? Boolean.valueOf(cVar4.b()) : null);
        i(sb.toString());
        if (iBridgeCallback != null) {
            com.jd.xbridge.base.c.runOnMain(this.webView, new k(iBridgeCallback, this, objectRef));
        }
    }

    public final void n(String str, String str2, Object obj, String str3, boolean z) {
        com.jd.xbridge.base.c.runOnMain(this.webView, new l(str2, str, obj, str3, z));
    }

    static /* synthetic */ void o(XBridge xBridge, String str, String str2, Object obj, String str3, boolean z, int i2, Object obj2) {
        xBridge.n((i2 & 1) != 0 ? null : str, str2, (i2 & 4) != 0 ? null : obj, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? true : z);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void _callNative(@org.jetbrains.annotations.Nullable java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.xbridge.XBridge._callNative(java.lang.String):void");
    }

    public final void callJS(@Nullable String pluginName, @Nullable Object params, @Nullable IBridgeCallback callback) {
        a aVar = new a(pluginName, com.jd.xbridge.e.b.b(params), String.valueOf(this.callbackIdCreator.incrementAndGet()));
        if (callback != null) {
            aVar.g(callback);
        }
        k(aVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x004c A[Catch: Exception -> 0x0047, TryCatch #0 {Exception -> 0x0047, blocks: (B:30:0x003e, B:38:0x004c, B:39:0x0057, B:41:0x005d, B:43:0x007d, B:45:0x008a), top: B:49:0x003e }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x005d A[Catch: Exception -> 0x0047, TryCatch #0 {Exception -> 0x0047, blocks: (B:30:0x003e, B:38:0x004c, B:39:0x0057, B:41:0x005d, B:43:0x007d, B:45:0x008a), top: B:49:0x003e }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007d A[Catch: Exception -> 0x0047, TryCatch #0 {Exception -> 0x0047, blocks: (B:30:0x003e, B:38:0x004c, B:39:0x0057, B:41:0x005d, B:43:0x007d, B:45:0x008a), top: B:49:0x003e }] */
    @kotlin.Deprecated(message = "Deprecated method, do not use.")
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void callNative(@org.jetbrains.annotations.Nullable java.lang.String r8, @org.jetbrains.annotations.Nullable java.lang.String r9, @org.jetbrains.annotations.Nullable java.lang.String r10, @org.jetbrains.annotations.Nullable java.lang.String r11, @org.jetbrains.annotations.Nullable java.lang.String r12) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "callNative(Old) -> pluginName:"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = ", action:"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r1 = ", "
            r0.append(r1)
            java.lang.String r2 = "callbackName:"
            r0.append(r2)
            r0.append(r11)
            java.lang.String r2 = ", callbackId:"
            r0.append(r2)
            r0.append(r12)
            r0.append(r1)
            java.lang.String r1 = "params:"
            r0.append(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            r7.i(r0)
            if (r9 == 0) goto L49
            int r0 = r9.length()     // Catch: java.lang.Exception -> L47
            if (r0 != 0) goto L45
            goto L49
        L45:
            r0 = 0
            goto L4a
        L47:
            r8 = move-exception
            goto Lab
        L49:
            r0 = 1
        L4a:
            if (r0 == 0) goto L57
            java.lang.String r4 = "-2"
            r5 = 0
            java.lang.String r6 = "Target action not found."
            r1 = r7
            r2 = r11
            r3 = r12
            r1.b(r2, r3, r4, r5, r6)     // Catch: java.lang.Exception -> L47
        L57:
            com.jd.xbridge.base.IBridgePlugin r0 = r7.g(r8)     // Catch: java.lang.Exception -> L47
            if (r0 != 0) goto L7d
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L47
            r9.<init>()     // Catch: java.lang.Exception -> L47
            java.lang.String r10 = "_callNative -> No native plugin found can process this calling of "
            r9.append(r10)     // Catch: java.lang.Exception -> L47
            r9.append(r8)     // Catch: java.lang.Exception -> L47
            java.lang.String r8 = r9.toString()     // Catch: java.lang.Exception -> L47
            r7.i(r8)     // Catch: java.lang.Exception -> L47
            java.lang.String r3 = "-2"
            r4 = 0
            java.lang.String r5 = "Target plugin not found."
            r0 = r7
            r1 = r11
            r2 = r12
            r0.b(r1, r2, r3, r4, r5)     // Catch: java.lang.Exception -> L47
            return
        L7d:
            com.jd.xbridge.base.IBridgeWebView r8 = r7.webView     // Catch: java.lang.Exception -> L47
            com.jd.xbridge.XBridge$e r1 = new com.jd.xbridge.XBridge$e     // Catch: java.lang.Exception -> L47
            r1.<init>(r11, r12)     // Catch: java.lang.Exception -> L47
            boolean r8 = r0.execute(r8, r9, r10, r1)     // Catch: java.lang.Exception -> L47
            if (r8 != 0) goto Le4
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L47
            r8.<init>()     // Catch: java.lang.Exception -> L47
            java.lang.String r10 = "_callNative -> Native plugin returns false for action = "
            r8.append(r10)     // Catch: java.lang.Exception -> L47
            r8.append(r9)     // Catch: java.lang.Exception -> L47
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Exception -> L47
            r7.i(r8)     // Catch: java.lang.Exception -> L47
            java.lang.String r3 = "-2"
            java.lang.String r4 = ""
            java.lang.String r5 = "Target action not found."
            r0 = r7
            r1 = r11
            r2 = r12
            r0.b(r1, r2, r3, r4, r5)     // Catch: java.lang.Exception -> L47
            goto Le4
        Lab:
            r4 = 0
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Execute plugin throws., e: "
            r9.append(r10)
            java.lang.String r10 = r8.getMessage()
            r9.append(r10)
            java.lang.String r5 = r9.toString()
            java.lang.String r3 = "1"
            r0 = r7
            r1 = r11
            r2 = r12
            r0.b(r1, r2, r3, r4, r5)
            r7.j(r8)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "CallNative Error, err = "
            r9.append(r10)
            java.lang.String r8 = r8.getMessage()
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r7.a(r8)
        Le4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.xbridge.XBridge.callNative(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public final void destroy() {
        synchronized (this) {
            for (Map.Entry<String, IBridgePlugin> entry : f().entrySet()) {
                if (entry.getValue() instanceof com.jd.xbridge.base.a) {
                    IBridgePlugin value = entry.getValue();
                    if (value == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.jd.xbridge.base.Destroyable");
                    }
                    ((com.jd.xbridge.base.a) value).destroy();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        IBridgePlugin iBridgePlugin = this.nativeDefaultPlugin;
        if (iBridgePlugin instanceof com.jd.xbridge.base.a) {
            if (iBridgePlugin == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jd.xbridge.base.Destroyable");
            }
            ((com.jd.xbridge.base.a) iBridgePlugin).destroy();
        }
    }

    public final void dispatchEvent(@NotNull String eventName, @Nullable Object params) {
        String sb;
        try {
            if (!(params instanceof JSONObject) && !(params instanceof JSONArray)) {
                if (params instanceof Map) {
                    sb = new JSONObject((Map) params).toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb, "JSONObject(params).toString()");
                } else if (params instanceof Collection) {
                    sb = new JSONArray((Collection) params).toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb, "JSONArray(params).toString()");
                } else if (params instanceof Object[]) {
                    sb = com.jd.xbridge.e.b.a(params).toString();
                    Intrinsics.checkExpressionValueIsNotNull(sb, "arrayToJsonArray(params).toString()");
                } else {
                    if (!(params instanceof Number) && !(params instanceof Boolean)) {
                        if (params == null) {
                            sb = "undefined";
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append('\'');
                            sb2.append(params);
                            sb2.append('\'');
                            sb = sb2.toString();
                        }
                    }
                    sb = String.valueOf(params);
                }
                com.jd.xbridge.base.c.runOnMain(this.webView, new f(eventName, sb));
            }
            sb = params.toString();
            com.jd.xbridge.base.c.runOnMain(this.webView, new f(eventName, sb));
        } catch (Exception e2) {
            j(e2);
            a("DispatchEvent Error, err = " + e2.getMessage());
        }
    }

    @Override // com.jd.xbridge.base.d
    @NotNull
    public String getName() {
        return "XWebView";
    }

    @NotNull
    public final IBridgeWebView getWebView() {
        return this.webView;
    }

    public final void onPause() {
        dispatchEvent$default(this, "ContainerInactive", null, 2, null);
    }

    public final void onResume() {
        dispatchEvent$default(this, PageLifeCycleEvent.STATE_ACTIVE, null, 2, null);
    }

    public final void onStart() {
        dispatchEvent$default(this, PageLifeCycleEvent.STATE_SHOW, null, 2, null);
    }

    public final void onStop() {
        dispatchEvent$default(this, PageLifeCycleEvent.STATE_HIDE, null, 2, null);
    }

    public final void registerDefaultPlugin(@NotNull IBridgePlugin plugin) {
        this.nativeDefaultPlugin = plugin;
    }

    public final void registerPlugin(@NotNull String pluginName, @NotNull IBridgePlugin plugin) {
        synchronized (this) {
            f().put(pluginName, plugin);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void startQueueRequest() {
        if (this.firstJsQueue.compareAndSet(true, false)) {
            return;
        }
        LinkedList<a> linkedList = this.callJsQueue;
        if (linkedList != null) {
            Iterator<T> it = linkedList.iterator();
            while (it.hasNext()) {
                l((a) it.next());
            }
        }
        this.callJsQueue = new LinkedList<>();
    }

    public final void unregisterPlugin(@NotNull String pluginName) {
        synchronized (this) {
            f().remove(pluginName);
        }
    }
}
