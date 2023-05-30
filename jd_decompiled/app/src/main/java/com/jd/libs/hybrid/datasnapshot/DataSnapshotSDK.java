package com.jd.libs.hybrid.datasnapshot;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 &2\u00020\u00012\u00020\u0001:\u0002&'B\u0007\u00a2\u0006\u0004\b%\u0010\bJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0004\u00a2\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\n\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u000b\u0010\bJ-\u0010\u0011\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0013\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J#\u0010\u0015\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0014J\u0019\u0010\u0016\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0017R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$\u00a8\u0006("}, d2 = {"Lcom/jd/libs/hybrid/datasnapshot/DataSnapshotSDK;", "", "Ljava/lang/Runnable;", "runnable", "", "execute", "(Ljava/lang/Runnable;)V", "ensureExecutor", "()V", XView2Constants.XVIEW2_ACTION_INIT, "onCreate", "onDestroy", "", "key", "value", "Lcom/jd/libs/hybrid/datasnapshot/ISnapshotListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "setItem", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/libs/hybrid/datasnapshot/ISnapshotListener;)V", "getItem", "(Ljava/lang/String;Lcom/jd/libs/hybrid/datasnapshot/ISnapshotListener;)V", "removeItem", CartConstant.KEY_LENGTH, "(Lcom/jd/libs/hybrid/datasnapshot/ISnapshotListener;)V", "getAllKeys", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isInit", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/ExecutorService;", "executorService", "Ljava/util/concurrent/ExecutorService;", "Lh/a/a;", "dataProvider", "Lh/a/a;", "Ljava/util/concurrent/atomic/AtomicInteger;", "refCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "<init>", "Companion", com.jingdong.jdsdk.a.a.a, "data-snapshot_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class DataSnapshotSDK {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    public static final String TAG = "DataSnapshotSDK";
    private h.a.a dataProvider;
    private ExecutorService executorService;
    private AtomicBoolean isInit = new AtomicBoolean(false);
    private AtomicInteger refCount = new AtomicInteger(0);

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lcom/jd/libs/hybrid/datasnapshot/DataSnapshotSDK$Companion;", "", "Lcom/jd/libs/hybrid/datasnapshot/DataSnapshotSDK;", "getInstance", "()Lcom/jd/libs/hybrid/datasnapshot/DataSnapshotSDK;", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "data-snapshot_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final DataSnapshotSDK getInstance() {
            a aVar = a.b;
            return a.a;
        }
    }

    /* loaded from: classes16.dex */
    public static final class a {
        public static final a b = new a();
        @NotNull
        public static final DataSnapshotSDK a = new DataSnapshotSDK();
    }

    /* loaded from: classes16.dex */
    public static final class b implements Runnable {
        public final /* synthetic */ Runnable a;

        public b(Runnable runnable) {
            this.a = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                this.a.run();
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes16.dex */
    public static final class c implements Runnable {
        public final /* synthetic */ ISnapshotListener b;

        public c(ISnapshotListener iSnapshotListener) {
            this.b = iSnapshotListener;
        }

        @Override // java.lang.Runnable
        public final void run() {
            List<String> c2 = DataSnapshotSDK.access$getDataProvider$p(DataSnapshotSDK.this).c();
            if (c2 == null) {
                c2 = new ArrayList<>(1);
            }
            HashMap hashMap = new HashMap(4);
            hashMap.put("result", "success");
            hashMap.put("data", c2);
            ISnapshotListener iSnapshotListener = this.b;
            if (iSnapshotListener == null) {
                return;
            }
            iSnapshotListener.onReceived(hashMap);
        }
    }

    /* loaded from: classes16.dex */
    public static final class d implements Runnable {
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ ISnapshotListener f5901c;

        public d(String str, ISnapshotListener iSnapshotListener) {
            this.b = str;
            this.f5901c = iSnapshotListener;
        }

        @Override // java.lang.Runnable
        public final void run() {
            h.a.a access$getDataProvider$p = DataSnapshotSDK.access$getDataProvider$p(DataSnapshotSDK.this);
            String str = this.b;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            String b = access$getDataProvider$p.b(str);
            HashMap hashMap = new HashMap(4);
            hashMap.put("result", b != null ? "success" : JDReactConstant.FAILED);
            if (b == null) {
                b = "undefined";
            }
            hashMap.put("data", b);
            ISnapshotListener iSnapshotListener = this.f5901c;
            if (iSnapshotListener != null) {
                iSnapshotListener.onReceived(hashMap);
            }
        }
    }

    /* loaded from: classes16.dex */
    public static final class e implements Runnable {
        public final /* synthetic */ ISnapshotListener b;

        public e(ISnapshotListener iSnapshotListener) {
            this.b = iSnapshotListener;
        }

        @Override // java.lang.Runnable
        public final void run() {
            long a = DataSnapshotSDK.access$getDataProvider$p(DataSnapshotSDK.this).a();
            HashMap hashMap = new HashMap(4);
            hashMap.put("result", "success");
            hashMap.put("data", Long.valueOf(a));
            ISnapshotListener iSnapshotListener = this.b;
            if (iSnapshotListener == null) {
                return;
            }
            iSnapshotListener.onReceived(hashMap);
        }
    }

    /* loaded from: classes16.dex */
    public static final class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            DataSnapshotSDK.access$getDataProvider$p(DataSnapshotSDK.this).b();
        }
    }

    /* loaded from: classes16.dex */
    public static final class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                DataSnapshotSDK.access$getDataProvider$p(DataSnapshotSDK.this).close();
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes16.dex */
    public static final class h implements Runnable {
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ ISnapshotListener f5902c;

        public h(String str, ISnapshotListener iSnapshotListener) {
            this.b = str;
            this.f5902c = iSnapshotListener;
        }

        @Override // java.lang.Runnable
        public final void run() {
            h.a.a access$getDataProvider$p = DataSnapshotSDK.access$getDataProvider$p(DataSnapshotSDK.this);
            String str = this.b;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            boolean a = access$getDataProvider$p.a(str);
            HashMap hashMap = new HashMap(4);
            hashMap.put("result", a ? "success" : JDReactConstant.FAILED);
            hashMap.put("data", "undefined");
            ISnapshotListener iSnapshotListener = this.f5902c;
            if (iSnapshotListener != null) {
                iSnapshotListener.onReceived(hashMap);
            }
        }
    }

    /* loaded from: classes16.dex */
    public static final class i implements Runnable {
        public final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ String f5903c;
        public final /* synthetic */ ISnapshotListener d;

        public i(String str, String str2, ISnapshotListener iSnapshotListener) {
            this.b = str;
            this.f5903c = str2;
            this.d = iSnapshotListener;
        }

        @Override // java.lang.Runnable
        public final void run() {
            h.a.a access$getDataProvider$p = DataSnapshotSDK.access$getDataProvider$p(DataSnapshotSDK.this);
            String str = this.b;
            if (str == null) {
                Intrinsics.throwNpe();
            }
            boolean a = access$getDataProvider$p.a(str, this.f5903c, false, true);
            HashMap hashMap = new HashMap(4);
            hashMap.put("result", a ? "success" : JDReactConstant.FAILED);
            hashMap.put("data", "undefined");
            ISnapshotListener iSnapshotListener = this.d;
            if (iSnapshotListener != null) {
                iSnapshotListener.onReceived(hashMap);
            }
        }
    }

    public static final /* synthetic */ h.a.a access$getDataProvider$p(DataSnapshotSDK dataSnapshotSDK) {
        h.a.a aVar = dataSnapshotSDK.dataProvider;
        if (aVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
        }
        return aVar;
    }

    private final void ensureExecutor() {
        if (this.executorService == null) {
            this.executorService = Executors.newSingleThreadExecutor();
        }
    }

    private final void execute(Runnable runnable) {
        ensureExecutor();
        if (runnable != null) {
            ExecutorService executorService = this.executorService;
            if (executorService == null) {
                Intrinsics.throwNpe();
            }
            if (executorService.isShutdown()) {
                return;
            }
            ExecutorService executorService2 = this.executorService;
            if (executorService2 == null) {
                Intrinsics.throwNpe();
            }
            if (executorService2.isTerminated()) {
                return;
            }
            try {
                ExecutorService executorService3 = this.executorService;
                if (executorService3 == null) {
                    Intrinsics.throwNpe();
                }
                executorService3.execute(new b(runnable));
            } catch (Throwable unused) {
            }
        }
    }

    public void getAllKeys(@Nullable ISnapshotListener listener) {
        if (this.isInit.get()) {
            execute(new c(listener));
        } else if (listener != null) {
            HashMap hashMap = new HashMap(4);
            hashMap.put("result", JDReactConstant.FAILED);
            hashMap.put("data", "undefined");
            listener.onReceived(hashMap);
        }
    }

    public void getItem(@Nullable String key, @Nullable ISnapshotListener listener) {
        if (!this.isInit.get()) {
            if (listener != null) {
                HashMap hashMap = new HashMap(4);
                hashMap.put("result", JDReactConstant.FAILED);
                hashMap.put("data", "undefined");
                listener.onReceived(hashMap);
            }
        } else if (!TextUtils.isEmpty(key)) {
            execute(new d(key, listener));
        } else if (listener != null) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("result", JDReactConstant.FAILED);
            hashMap2.put("data", "undefined");
            listener.onReceived(hashMap2);
        }
    }

    public final void init() {
        if (HybridSettings.getAppContext() != null) {
            Context appContext = HybridSettings.getAppContext();
            Intrinsics.checkExpressionValueIsNotNull(appContext, "HybridSettings.getAppContext()");
            this.dataProvider = new h.b.a(appContext);
            this.isInit.set(true);
        }
    }

    public void length(@Nullable ISnapshotListener listener) {
        if (this.isInit.get()) {
            execute(new e(listener));
        } else if (listener != null) {
            HashMap hashMap = new HashMap(4);
            hashMap.put("result", JDReactConstant.FAILED);
            hashMap.put("data", "undefined");
            listener.onReceived(hashMap);
        }
    }

    public void onCreate() {
        if (this.isInit.get() && this.refCount.incrementAndGet() == 1) {
            execute(new f());
        }
    }

    public void onDestroy() {
        if (this.isInit.get() && this.refCount.decrementAndGet() == 0) {
            execute(new g());
            ExecutorService executorService = this.executorService;
            if (executorService != null) {
                executorService.shutdown();
            }
            this.executorService = null;
        }
    }

    public void removeItem(@Nullable String key, @Nullable ISnapshotListener listener) {
        if (!this.isInit.get()) {
            if (listener != null) {
                HashMap hashMap = new HashMap(4);
                hashMap.put("result", JDReactConstant.FAILED);
                hashMap.put("data", "undefined");
                listener.onReceived(hashMap);
            }
        } else if (!TextUtils.isEmpty(key)) {
            execute(new h(key, listener));
        } else if (listener != null) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("result", JDReactConstant.FAILED);
            hashMap2.put("data", "undefined");
            listener.onReceived(hashMap2);
        }
    }

    public void setItem(@Nullable String key, @Nullable String value, @Nullable ISnapshotListener listener) {
        if (!this.isInit.get()) {
            if (listener != null) {
                HashMap hashMap = new HashMap(4);
                hashMap.put("result", JDReactConstant.FAILED);
                hashMap.put("data", "undefined");
                listener.onReceived(hashMap);
            }
        } else if (!TextUtils.isEmpty(key) && value != null) {
            execute(new i(key, value, listener));
        } else if (listener != null) {
            HashMap hashMap2 = new HashMap(4);
            hashMap2.put("result", JDReactConstant.FAILED);
            hashMap2.put("data", "undefined");
            listener.onReceived(hashMap2);
        }
    }
}
