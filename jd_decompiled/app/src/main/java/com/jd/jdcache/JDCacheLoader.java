package com.jd.jdcache;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.jd.jdcache.JDCacheLoader$messageHandler$2;
import com.jd.jdcache.match.ResourceMatcherManager;
import com.jd.jdcache.match.base.JDCacheResourceMatcher;
import com.jd.jdcache.service.JDCacheMaster;
import com.jd.jdcache.util.JDCacheLog;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\b\u0017\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\u0010\b\u0002\u0010?\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010=\u0012\b\b\u0002\u0010+\u001a\u00020*\u00a2\u0006\u0004\bX\u0010YJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0000H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001aH\u0014\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0004\u00a2\u0006\u0004\b\u001e\u0010\nJ\u000f\u0010\u001f\u001a\u00020\u0004H\u0015\u00a2\u0006\u0004\b\u001f\u0010\nR\u0019\u0010 \u001a\u00020\u000b8\u0006@\u0006\u00a2\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001d\u0010)\u001a\u00020$8D@\u0004X\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010+\u001a\u00020*8\u0006@\u0006\u00a2\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u001c\u00100\u001a\u00020/8\u0004@\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R.\u00106\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u0001048\u0006@FX\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0019\u0010\f\u001a\u00020\u000b8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\f\u0010!\u001a\u0004\b<\u0010#R!\u0010?\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010=8\u0006@\u0006\u00a2\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u001d\u0010G\u001a\u00020C8D@\u0004X\u0084\u0084\u0002\u00a2\u0006\f\n\u0004\bD\u0010&\u001a\u0004\bE\u0010FR\"\u0010H\u001a\u00020*8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010,\u001a\u0004\bI\u0010.\"\u0004\bJ\u0010KR$\u0010M\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u00158\u0006@BX\u0086\u000e\u00a2\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR.\u0010R\u001a\u0004\u0018\u00010Q2\b\u00105\u001a\u0004\u0018\u00010Q8\u0006@FX\u0086\u000e\u00a2\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010W\u00a8\u0006Z"}, d2 = {"Lcom/jd/jdcache/JDCacheLoader;", "", "Landroidx/lifecycle/Lifecycle$Event;", "event", "", "onLifecycleStateChanged", "(Landroidx/lifecycle/Lifecycle$Event;)V", XView2Constants.XVIEW2_ACTION_INIT, "()Lcom/jd/jdcache/JDCacheLoader;", "prepareMatchers", "()V", "", "url", "onPageStarted", "(Ljava/lang/String;)V", "onPageFinished", "Landroid/webkit/WebResourceRequest;", "request", "Landroid/webkit/WebResourceResponse;", "onRequest", "(Landroid/webkit/WebResourceRequest;)Landroid/webkit/WebResourceResponse;", "", "what", "data", "sendMessageData", "(ILjava/lang/Object;)V", "Landroid/os/Message;", "msg", "handleMessageData", "(Landroid/os/Message;)V", "destroy", "onDestroy", "key", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "Landroid/os/Handler;", "messageHandler$delegate", "Lkotlin/Lazy;", "getMessageHandler", "()Landroid/os/Handler;", "messageHandler", "", "enable", "Z", "getEnable", "()Z", "Ljava/util/concurrent/atomic/AtomicBoolean;", "destroyed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getDestroyed", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "Landroidx/lifecycle/LifecycleOwner;", "value", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "setLifecycleOwner", "(Landroidx/lifecycle/LifecycleOwner;)V", "getUrl", "", "Lcom/jd/jdcache/match/base/JDCacheResourceMatcher;", "matcherList", "Ljava/util/List;", "getMatcherList", "()Ljava/util/List;", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycleEventObserver$delegate", "getLifecycleEventObserver", "()Landroidx/lifecycle/LifecycleEventObserver;", "lifecycleEventObserver", "preloadHtml", "getPreloadHtml", "setPreloadHtml", "(Z)V", "<set-?>", "viewId", "I", "getViewId", "()I", "Lcom/jd/jdcache/JDCacheWebView;", "view", "Lcom/jd/jdcache/JDCacheWebView;", "getView", "()Lcom/jd/jdcache/JDCacheWebView;", "setView", "(Lcom/jd/jdcache/JDCacheWebView;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public class JDCacheLoader {
    @NotNull
    private final AtomicBoolean destroyed;
    private final boolean enable;
    @NotNull
    private final String key;
    @NotNull

    /* renamed from: lifecycleEventObserver$delegate  reason: from kotlin metadata */
    private final Lazy lifecycleEventObserver;
    @Nullable
    private LifecycleOwner lifecycleOwner;
    @Nullable
    private final List<JDCacheResourceMatcher> matcherList;
    @NotNull

    /* renamed from: messageHandler$delegate  reason: from kotlin metadata */
    private final Lazy messageHandler;
    private boolean preloadHtml;
    @NotNull
    private final String url;
    @Nullable
    private JDCacheWebView view;
    private int viewId;

    /* JADX WARN: Multi-variable type inference failed */
    public JDCacheLoader(@NotNull String str, @NotNull String str2, @Nullable List<? extends JDCacheResourceMatcher> list, boolean z) {
        Lazy lazy;
        Lazy lazy2;
        this.url = str;
        this.key = str2;
        this.matcherList = list;
        this.enable = z;
        this.preloadHtml = true;
        this.viewId = -1;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<JDCacheLoader$messageHandler$2.AnonymousClass1>() { // from class: com.jd.jdcache.JDCacheLoader$messageHandler$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                JDCacheLoader.this = this;
            }

            /* JADX WARN: Type inference failed for: r0v0, types: [com.jd.jdcache.JDCacheLoader$messageHandler$2$1] */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final AnonymousClass1 invoke() {
                return new Handler(Looper.getMainLooper()) { // from class: com.jd.jdcache.JDCacheLoader$messageHandler$2.1
                    {
                        JDCacheLoader$messageHandler$2.this = this;
                    }

                    @Override // android.os.Handler
                    public void handleMessage(@NotNull Message msg) {
                        JDCacheLoader.this.handleMessageData(msg);
                    }
                };
            }
        });
        this.messageHandler = lazy;
        this.destroyed = new AtomicBoolean(false);
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<LifecycleEventObserver>() { // from class: com.jd.jdcache.JDCacheLoader$lifecycleEventObserver$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                JDCacheLoader.this = this;
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final LifecycleEventObserver invoke() {
                return new LifecycleEventObserver() { // from class: com.jd.jdcache.JDCacheLoader$lifecycleEventObserver$2.1
                    {
                        JDCacheLoader$lifecycleEventObserver$2.this = this;
                    }

                    @Override // androidx.lifecycle.LifecycleEventObserver
                    public final void onStateChanged(@NotNull LifecycleOwner lifecycleOwner, @NotNull Lifecycle.Event event) {
                        JDCacheLoader.this.onLifecycleStateChanged(event);
                    }
                };
            }
        });
        this.lifecycleEventObserver = lazy2;
    }

    public static /* synthetic */ void sendMessageData$default(JDCacheLoader jDCacheLoader, int i2, Object obj, int i3, Object obj2) {
        if (obj2 == null) {
            if ((i3 & 2) != 0) {
                obj = null;
            }
            jDCacheLoader.sendMessageData(i2, obj);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendMessageData");
    }

    public final void destroy() {
        if (this.destroyed.compareAndSet(false, true)) {
            onDestroy();
        }
    }

    @NotNull
    public final AtomicBoolean getDestroyed() {
        return this.destroyed;
    }

    public final boolean getEnable() {
        return this.enable;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    protected final LifecycleEventObserver getLifecycleEventObserver() {
        return (LifecycleEventObserver) this.lifecycleEventObserver.getValue();
    }

    @Nullable
    public final LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    @Nullable
    public final List<JDCacheResourceMatcher> getMatcherList() {
        return this.matcherList;
    }

    @NotNull
    protected final Handler getMessageHandler() {
        return (Handler) this.messageHandler.getValue();
    }

    public final boolean getPreloadHtml() {
        return this.preloadHtml;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @Nullable
    public final JDCacheWebView getView() {
        return this.view;
    }

    public final int getViewId() {
        return this.viewId;
    }

    public void handleMessageData(@NotNull Message msg) {
    }

    @NotNull
    public JDCacheLoader init() {
        if (this.enable) {
            JDCacheMaster.INSTANCE.getInstance().addLoader(this);
            prepareMatchers();
            return this;
        }
        return this;
    }

    @CallSuper
    protected void onDestroy() {
        JDCacheMaster.INSTANCE.getInstance().removeLoader(this.key);
        List<JDCacheResourceMatcher> list = this.matcherList;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((JDCacheResourceMatcher) it.next()).destroy();
            }
        }
        setView(null);
    }

    public void onLifecycleStateChanged(@NotNull Lifecycle.Event event) {
        List<JDCacheResourceMatcher> list = this.matcherList;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ((JDCacheResourceMatcher) it.next()).onLifecycleStateChanged(event);
            }
        }
        if (event == Lifecycle.Event.ON_DESTROY) {
            destroy();
        }
    }

    public void onPageFinished(@NotNull String url) {
        if (!this.enable) {
        }
    }

    public void onPageStarted(@NotNull String url) {
        if (!this.enable) {
        }
    }

    @Nullable
    public WebResourceResponse onRequest(@NotNull WebResourceRequest request) {
        List<JDCacheResourceMatcher> list;
        if (this.enable && (list = this.matcherList) != null) {
            for (JDCacheResourceMatcher jDCacheResourceMatcher : list) {
                WebResourceResponse match = jDCacheResourceMatcher.match(request);
                if (match != null) {
                    JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                    if (jDCacheLog.getCanLog()) {
                        jDCacheLog.d("JDCacheLoader", "Use local file to create response:[" + jDCacheResourceMatcher.getName() + "](" + request.getUrl() + ')');
                    }
                    return match;
                }
            }
        }
        return null;
    }

    protected void prepareMatchers() {
        List<JDCacheResourceMatcher> list;
        if (this.enable && (list = this.matcherList) != null) {
            for (JDCacheResourceMatcher jDCacheResourceMatcher : list) {
                jDCacheResourceMatcher.setLoader(this);
                jDCacheResourceMatcher.prepare(this.url);
            }
        }
    }

    public void sendMessageData(int what, @Nullable Object data) {
        getMessageHandler().sendMessage(getMessageHandler().obtainMessage(what, data));
    }

    public final void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        Lifecycle lifecycle;
        Lifecycle lifecycle2;
        LifecycleOwner lifecycleOwner2 = this.lifecycleOwner;
        if (lifecycleOwner2 != null && (lifecycle2 = lifecycleOwner2.getLifecycle()) != null) {
            lifecycle2.removeObserver(getLifecycleEventObserver());
        }
        this.lifecycleOwner = lifecycleOwner;
        if (lifecycleOwner == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) {
            return;
        }
        lifecycle.addObserver(getLifecycleEventObserver());
    }

    public final void setPreloadHtml(boolean z) {
        this.preloadHtml = z;
    }

    public final void setView(@Nullable JDCacheWebView jDCacheWebView) {
        if ((!Intrinsics.areEqual(jDCacheWebView, this.view)) != false) {
            this.viewId = jDCacheWebView != null ? jDCacheWebView.hashCode() : -1;
        }
        this.view = jDCacheWebView;
    }

    public /* synthetic */ JDCacheLoader(String str, String str2, List list, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? String.valueOf(System.currentTimeMillis()) : str2, (i2 & 4) != 0 ? ResourceMatcherManager.INSTANCE.createDefaultMatcherList() : list, (i2 & 8) != 0 ? JDCacheSetting.INSTANCE.getEnable() : z);
    }
}
