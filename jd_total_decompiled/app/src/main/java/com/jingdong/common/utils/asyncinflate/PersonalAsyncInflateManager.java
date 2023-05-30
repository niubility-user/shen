package com.jingdong.common.utils.asyncinflate;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.unification.uniconfig.UnIconConfigController;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.PersonalSwitchManager;
import com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager;
import com.jingdong.jdsdk.JDSoftReference;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 ?2\u00020\u0001:\u0003@?AB\u0007\u00a2\u0006\u0004\b=\u0010>J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\fJ\u0017\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0017\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ'\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010!\u001a\u0004\u0018\u00010 \u00a2\u0006\u0004\b\u001e\u0010\"J%\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\r\u00a2\u0006\u0004\b\u001e\u0010$J1\u0010'\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010!\u001a\u0004\u0018\u00010 2\b\u0010&\u001a\u0004\u0018\u00010%\u00a2\u0006\u0004\b'\u0010(J1\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\rH\u0007\u00a2\u0006\u0004\b\u001e\u0010)J;\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010#\u001a\u00020\r2\b\u0010*\u001a\u0004\u0018\u00010%H\u0007\u00a2\u0006\u0004\b\u001e\u0010+J\u0019\u0010,\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007\u00a2\u0006\u0004\b,\u0010\fJ\u0015\u0010-\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0015\u00a2\u0006\u0004\b-\u0010\u0018R\u0016\u0010.\u001a\u00020\r8B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b.\u0010/R\u001d\u00105\u001a\u0002008B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R5\u0010<\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010807068B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b9\u00102\u001a\u0004\b:\u0010;\u00a8\u0006B"}, d2 = {"Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateManager;", "", "", "layoutResId", "activityHashCode", "", "getInflateKey", "(II)Ljava/lang/String;", "Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateItem;", CartConstant.KEY_VENDOR_ITEM, "", "onAsyncInflateStart", "(Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateItem;)V", "", "success", "onAsyncInflateEnd", "(Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateItem;Z)V", "inflateWithThreadPool", "strBoolean", "parseStringToBoolean", "(Ljava/lang/String;)Z", "Landroid/content/Context;", "mContext", "handleFontSize", "(Landroid/content/Context;)V", "log", "printLog", "(Ljava/lang/String;)V", AnnoConst.Constructor_Context, "Landroid/view/View;", "getInflatedView", "(Landroid/content/Context;I)Landroid/view/View;", "Landroid/view/ViewGroup;", "parent", "(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;", "isPreloadNextView", "(Landroid/content/Context;IZ)Landroid/view/View;", "Landroid/view/ViewGroup$LayoutParams;", "layoutParams", "getNewInflatedView", "(Landroid/content/Context;ILandroid/view/ViewGroup;Landroid/view/ViewGroup$LayoutParams;)Landroid/view/View;", "(Landroid/content/Context;ILandroid/view/ViewGroup;Z)Landroid/view/View;", "mLayoutParams", "(Landroid/content/Context;ILandroid/view/ViewGroup;ZLandroid/view/ViewGroup$LayoutParams;)Landroid/view/View;", PersonalSwitchManager.ASYNC_INFLATE_KEY, "clearData", "isAsyncSwitcherOpen", "()Z", "Landroid/os/Handler;", "mHandler$delegate", "Lkotlin/Lazy;", "getMHandler", "()Landroid/os/Handler;", "mHandler", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/jingdong/jdsdk/JDSoftReference;", "Ljava/util/concurrent/LinkedBlockingQueue;", "mInflateMap$delegate", "getMInflateMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "mInflateMap", "<init>", "()V", "Companion", "BasicInflater", "ContextThemeFixWrapper", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalAsyncInflateManager {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String IDENTIFIES = "personal";
    private static final String TAG = "PersonalAsyncInflateManager";
    @NotNull
    private static final Lazy instance$delegate;

    /* renamed from: mHandler$delegate  reason: from kotlin metadata */
    private final Lazy mHandler;

    /* renamed from: mInflateMap$delegate  reason: from kotlin metadata */
    private final Lazy mInflateMap;

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0011\b\u0000\u0012\u0006\u0010\r\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateManager$BasicInflater;", "Landroid/view/LayoutInflater;", "Landroid/content/Context;", "newContext", "cloneInContext", "(Landroid/content/Context;)Landroid/view/LayoutInflater;", "", "name", "Landroid/util/AttributeSet;", "attrs", "Landroid/view/View;", "onCreateView", "(Ljava/lang/String;Landroid/util/AttributeSet;)Landroid/view/View;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class BasicInflater extends LayoutInflater {
        private static final String[] S_CLASS_PREFIX_LIST = {"android.widget.", "android.webkit.", "android.app."};

        public BasicInflater(@NotNull Context context) {
            super(context);
            if (context instanceof AppCompatActivity) {
                if (OKLog.D) {
                    OKLog.d(PersonalAsyncInflateManager.TAG, "AppCompatActivity\u5f00\u59cb\u52a0\u8f7dview");
                }
                AppCompatDelegate delegate = ((AppCompatActivity) context).getDelegate();
                Intrinsics.checkExpressionValueIsNotNull(delegate, "context.delegate");
                if (delegate instanceof LayoutInflater.Factory2) {
                    LayoutInflaterCompat.setFactory2(this, (LayoutInflater.Factory2) delegate);
                }
                if (OKLog.D) {
                    OKLog.d(PersonalAsyncInflateManager.TAG, "AppCompatActivity\u7c7b\u578bview \u52a0\u8f7d\u6210\u529f");
                }
            }
        }

        @Override // android.view.LayoutInflater
        @NotNull
        public LayoutInflater cloneInContext(@NotNull Context newContext) {
            return new BasicInflater(newContext);
        }

        @Override // android.view.LayoutInflater
        @NotNull
        protected View onCreateView(@NotNull String name, @NotNull AttributeSet attrs) throws ClassNotFoundException {
            View createView;
            for (String str : S_CLASS_PREFIX_LIST) {
                try {
                    createView = createView(name, str, attrs);
                } catch (ClassNotFoundException e2) {
                    if (OKLog.D) {
                        OKLog.d(PersonalAsyncInflateManager.TAG, "PersonalAsyncInflateManager onCreateView ClassNotFoundException", e2);
                    }
                }
                if (createView != null) {
                    return createView;
                }
            }
            View onCreateView = super.onCreateView(name, attrs);
            Intrinsics.checkExpressionValueIsNotNull(onCreateView, "super.onCreateView(name, attrs)");
            return onCreateView;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u000e\u001a\u00020\t8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u000f8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0011\u00a8\u0006\u0015"}, d2 = {"Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateManager$Companion;", "", "Landroid/view/View;", "inflatedView", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "replaceContextForView", "(Landroid/view/View;Landroid/content/Context;)V", "Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateManager;", "instance$delegate", "Lkotlin/Lazy;", "getInstance", "()Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateManager;", "instance", "", "IDENTIFIES", "Ljava/lang/String;", "TAG", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final PersonalAsyncInflateManager getInstance() {
            Lazy lazy = PersonalAsyncInflateManager.instance$delegate;
            Companion companion = PersonalAsyncInflateManager.INSTANCE;
            return (PersonalAsyncInflateManager) lazy.getValue();
        }

        public final void replaceContextForView(@Nullable View inflatedView, @Nullable Context context) {
            if (inflatedView == null || context == null) {
                return;
            }
            Context context2 = inflatedView.getContext();
            if (context2 instanceof MutableContextWrapper) {
                ((MutableContextWrapper) context2).setBaseContext(context);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0004\b\t\u0010\nJ\u0013\u0010\u0004\u001a\u00060\u0002R\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/common/utils/asyncinflate/PersonalAsyncInflateManager$ContextThemeFixWrapper;", "Landroid/view/ContextThemeWrapper;", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "getTheme", "()Landroid/content/res/Resources$Theme;", "Landroid/content/Context;", UnIconConfigController.A_B_SWITCH_A, "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class ContextThemeFixWrapper extends ContextThemeWrapper {
        private final Context base;

        public ContextThemeFixWrapper(@NotNull Context context) {
            super(context, 0);
            this.base = context;
        }

        @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
        @NotNull
        public Resources.Theme getTheme() {
            Resources.Theme theme = this.base.getTheme();
            if (theme != null) {
                return theme;
            }
            Resources.Theme theme2 = this.base.getTheme();
            if (theme2 != null) {
                return theme2;
            }
            Resources.Theme theme3 = super.getTheme();
            if (theme3 != null) {
                return theme3;
            }
            Resources.Theme theme4 = super.getTheme();
            Intrinsics.checkExpressionValueIsNotNull(theme4, "theme");
            return theme4;
        }
    }

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<PersonalAsyncInflateManager>() { // from class: com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager$Companion$instance$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PersonalAsyncInflateManager invoke() {
                return new PersonalAsyncInflateManager();
            }
        });
        instance$delegate = lazy;
    }

    public PersonalAsyncInflateManager() {
        Lazy lazy;
        Lazy lazy2;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<Handler>() { // from class: com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager$mHandler$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Handler invoke() {
                return new Handler(Looper.getMainLooper());
            }
        });
        this.mHandler = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new Function0<ConcurrentHashMap<String, JDSoftReference<LinkedBlockingQueue<Object>>>>() { // from class: com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager$mInflateMap$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ConcurrentHashMap<String, JDSoftReference<LinkedBlockingQueue<Object>>> invoke() {
                return new ConcurrentHashMap<>();
            }
        });
        this.mInflateMap = lazy2;
    }

    private final String getInflateKey(int layoutResId, int activityHashCode) {
        return layoutResId + '+' + activityHashCode + "+personal";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler getMHandler() {
        return (Handler) this.mHandler.getValue();
    }

    private final ConcurrentHashMap<String, JDSoftReference<LinkedBlockingQueue<Object>>> getMInflateMap() {
        return (ConcurrentHashMap) this.mInflateMap.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001f A[Catch: all -> 0x001a, TryCatch #0 {all -> 0x001a, blocks: (B:8:0x000f, B:10:0x0015, B:15:0x001f, B:17:0x0023, B:19:0x003d, B:21:0x0044, B:23:0x0048, B:25:0x004e, B:26:0x0051), top: B:32:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003d A[Catch: all -> 0x001a, TryCatch #0 {all -> 0x001a, blocks: (B:8:0x000f, B:10:0x0015, B:15:0x001f, B:17:0x0023, B:19:0x003d, B:21:0x0044, B:23:0x0048, B:25:0x004e, B:26:0x0051), top: B:32:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleFontSize(Context mContext) {
        DisplayMetrics displayMetricsObject;
        Configuration configuration;
        float f2;
        float f3;
        Resources resources;
        if (!PersonalSwitchManager.getAsyncInflateFontScale() || (displayMetricsObject = BaseInfo.getDisplayMetricsObject()) == null) {
            return;
        }
        if (mContext != null) {
            try {
                Resources resources2 = mContext.getResources();
                if (resources2 != null) {
                    configuration = resources2.getConfiguration();
                    f2 = configuration == null ? configuration.fontScale : 0.0f;
                    printLog("\u5f02\u6b65\u52a0\u8f7d\u5e03\u5c40 \u5f53\u524d\u5b57\u53f7" + f2);
                    f3 = (float) 1;
                    if (f2 <= f3) {
                        printLog("\u5f02\u6b65\u52a0\u8f7d\u5e03\u5c40 \u91cd\u7f6e\u5b57\u53f7\uff081\uff09");
                        if (configuration != null) {
                            configuration.fontScale = f3;
                        }
                        if (mContext != null && (resources = mContext.getResources()) != null) {
                            resources.updateConfiguration(configuration, displayMetricsObject);
                        }
                        printLog("\u5f02\u6b65\u52a0\u8f7d\u5e03\u5c40 \u91cd\u7f6e\u5b57\u53f7\uff082\uff09");
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (OKLog.D) {
                    OKLog.d(TAG, "PersonalAsyncInflateManager handleFontSize", th);
                    return;
                }
                return;
            }
        }
        configuration = null;
        if (configuration == null) {
        }
        printLog("\u5f02\u6b65\u52a0\u8f7d\u5e03\u5c40 \u5f53\u524d\u5b57\u53f7" + f2);
        f3 = (float) 1;
        if (f2 <= f3) {
        }
    }

    private final void inflateWithThreadPool(final PersonalAsyncInflateItem item) {
        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager$inflateWithThreadPool$1
            @Override // java.lang.Runnable
            public final void run() {
                Handler mHandler;
                CountDownLatch countDownLatch;
                Handler mHandler2;
                Runnable runnable;
                CountDownLatch countDownLatch2;
                CountDownLatch countDownLatch3;
                if (item.getIsInflating()) {
                    return;
                }
                try {
                    try {
                        if (item.getCallback() == null) {
                            PersonalAsyncInflateManager.this.onAsyncInflateStart(item);
                        }
                        JdSdk jdSdk = JdSdk.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(jdSdk, "JdSdk.getInstance()");
                        Context applicationContext = jdSdk.getApplicationContext();
                        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "JdSdk.getInstance().applicationContext");
                        PersonalAsyncInflateManager.ContextThemeFixWrapper contextThemeFixWrapper = new PersonalAsyncInflateManager.ContextThemeFixWrapper(applicationContext);
                        PersonalAsyncInflateManager.this.handleFontSize(contextThemeFixWrapper);
                        long currentTimeMillis = System.currentTimeMillis();
                        try {
                            item.setInflatedView(new PersonalAsyncInflateManager.BasicInflater(contextThemeFixWrapper).inflate(item.getLayoutResId(), item.getParent(), false));
                        } catch (Throwable th) {
                            if (OKLog.D) {
                                OKLog.d("PersonalAsyncInflateManager", "PersonalAsyncInflateManager inflateWithThreadPool first exception", th);
                            }
                        }
                        if (item.getInflatedView() == null) {
                            item.setInflatedView(new PersonalAsyncInflateManager.BasicInflater(contextThemeFixWrapper).inflate(item.getLayoutResId(), item.getParent(), false));
                            Unit unit = Unit.INSTANCE;
                        }
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        if (OKLog.D) {
                            OKLog.e("PersonalAsyncInflateManager", String.valueOf(item.getLayoutResId()) + " cost: " + currentTimeMillis2 + "ms");
                        }
                        PersonalAsyncInflateManager.this.onAsyncInflateEnd(item, true);
                        PersonalAsyncInflateItem personalAsyncInflateItem = item;
                        if (personalAsyncInflateItem != null && (countDownLatch3 = personalAsyncInflateItem.getCountDownLatch()) != null) {
                            countDownLatch3.countDown();
                        }
                    } catch (RuntimeException e2) {
                        JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalAsyncInflateManager inflateWithThreadPool second exception", e2));
                        if (OKLog.D) {
                            OKLog.d("PersonalAsyncInflateManager", "Failed to inflate resource in the background! Retrying on the UI thread", e2);
                        }
                        PersonalAsyncInflateManager.this.onAsyncInflateEnd(item, false);
                        PersonalAsyncInflateItem personalAsyncInflateItem2 = item;
                        if (personalAsyncInflateItem2 != null && (countDownLatch2 = personalAsyncInflateItem2.getCountDownLatch()) != null) {
                            countDownLatch2.countDown();
                        }
                        if (item.getCallback() == null) {
                            return;
                        }
                        mHandler2 = PersonalAsyncInflateManager.this.getMHandler();
                        runnable = new Runnable() { // from class: com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager$inflateWithThreadPool$1.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                PersonalOnInflateFinishedCallback callback = item.getCallback();
                                if (callback != null) {
                                    callback.onInflateFinished(item);
                                }
                            }
                        };
                    }
                    if (item.getCallback() != null) {
                        mHandler2 = PersonalAsyncInflateManager.this.getMHandler();
                        runnable = new Runnable() { // from class: com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager$inflateWithThreadPool$1.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                PersonalOnInflateFinishedCallback callback = item.getCallback();
                                if (callback != null) {
                                    callback.onInflateFinished(item);
                                }
                            }
                        };
                        mHandler2.post(runnable);
                    }
                } catch (Throwable th2) {
                    PersonalAsyncInflateItem personalAsyncInflateItem3 = item;
                    if (personalAsyncInflateItem3 != null && (countDownLatch = personalAsyncInflateItem3.getCountDownLatch()) != null) {
                        countDownLatch.countDown();
                    }
                    if (item.getCallback() != null) {
                        mHandler = PersonalAsyncInflateManager.this.getMHandler();
                        mHandler.post(new Runnable() { // from class: com.jingdong.common.utils.asyncinflate.PersonalAsyncInflateManager$inflateWithThreadPool$1.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                PersonalOnInflateFinishedCallback callback = item.getCallback();
                                if (callback != null) {
                                    callback.onInflateFinished(item);
                                }
                            }
                        });
                    }
                    throw th2;
                }
            }
        });
    }

    private final boolean isAsyncSwitcherOpen() {
        return PersonalSwitchManager.getAsyncInflateSwitch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAsyncInflateEnd(PersonalAsyncInflateItem item, boolean success) {
        if (item != null) {
            item.setInflating$personallib(false);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "inflate done\uff1f=" + success);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onAsyncInflateStart(PersonalAsyncInflateItem item) {
        LinkedBlockingQueue<Object> linkedBlockingQueue;
        item.setInflating$personallib(true);
        JDSoftReference<LinkedBlockingQueue<Object>> jDSoftReference = getMInflateMap().get(getInflateKey(item.getLayoutResId(), item.getMPageHashCode()));
        if (jDSoftReference != null && (jDSoftReference.get() instanceof LinkedBlockingQueue)) {
            LinkedBlockingQueue<Object> linkedBlockingQueue2 = jDSoftReference.get();
            if (linkedBlockingQueue2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.util.concurrent.LinkedBlockingQueue<kotlin.Any>");
            }
            linkedBlockingQueue = linkedBlockingQueue2;
        } else {
            linkedBlockingQueue = new LinkedBlockingQueue<>(5);
            getMInflateMap().put(getInflateKey(item.getLayoutResId(), item.getMPageHashCode()), new JDSoftReference<>(linkedBlockingQueue));
        }
        try {
            linkedBlockingQueue.put(item);
        } catch (InterruptedException e2) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalAsyncInflateManager onAsyncInflateStart exception", e2));
            if (OKLog.D) {
                OKLog.d(TAG, "Queue is full");
            }
        }
    }

    private final boolean parseStringToBoolean(String strBoolean) {
        return !TextUtils.isEmpty(strBoolean) && TextUtils.equals("1", strBoolean);
    }

    private final void printLog(String log) {
        if (OKLog.D) {
            OKLog.d(TAG, "PersonalAsyncInflateManager printLog " + log);
        }
    }

    @UiThread
    public final void asyncInflate(@Nullable PersonalAsyncInflateItem item) {
        PersonalOnInflateFinishedCallback callback;
        if (!isAsyncSwitcherOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "\u5f02\u6b65\u52a0\u8f7d\u5f00\u5173\u5173\u95ed\uff0c\u505c\u6b62\u5f02\u6b65\u52a0\u8f7d");
            }
            if (item == null || (callback = item.getCallback()) == null) {
                return;
            }
            callback.onInflateFinished(item);
        } else if (item == null || item.getLayoutResId() == 0 || item.getIsInflating()) {
        } else {
            inflateWithThreadPool(item);
        }
    }

    public final void clearData(@NotNull Context context) {
        boolean endsWith$default;
        if (isAsyncSwitcherOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "clearData:--" + context.hashCode());
            }
            String valueOf = String.valueOf(context.hashCode());
            Set<String> keySet = getMInflateMap().keySet();
            Intrinsics.checkExpressionValueIsNotNull(keySet, "mInflateMap.keys");
            for (String str : keySet) {
                Intrinsics.checkExpressionValueIsNotNull(str, "iter.next()");
                String str2 = str;
                endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str2, valueOf, false, 2, null);
                if (endsWith$default) {
                    getMInflateMap().remove(str2);
                    if (OKLog.D) {
                        OKLog.d(TAG, "clearData:" + str2);
                    }
                }
            }
        }
    }

    @NotNull
    public final View getInflatedView(@NotNull Context context, int layoutResId) {
        return getInflatedView(context, layoutResId, null, false);
    }

    @NotNull
    public final View getNewInflatedView(@NotNull Context context, int layoutResId, @Nullable ViewGroup parent, @Nullable ViewGroup.LayoutParams layoutParams) {
        return getInflatedView(context, layoutResId, parent, false, layoutParams);
    }

    @NotNull
    public final View getInflatedView(@NotNull Context context, int layoutResId, @Nullable ViewGroup parent) {
        return getInflatedView(context, layoutResId, parent, false);
    }

    @NotNull
    public final View getInflatedView(@NotNull Context context, int layoutResId, boolean isPreloadNextView) {
        return getInflatedView(context, layoutResId, null, isPreloadNextView);
    }

    @UiThread
    @NotNull
    public final View getInflatedView(@NotNull Context context, int layoutResId, @Nullable ViewGroup parent, boolean isPreloadNextView) {
        return getInflatedView(context, layoutResId, parent, isPreloadNextView, null);
    }

    @UiThread
    @NotNull
    public final View getInflatedView(@NotNull Context context, int layoutResId, @Nullable ViewGroup parent, boolean isPreloadNextView, @Nullable ViewGroup.LayoutParams mLayoutParams) {
        try {
        } catch (Exception e2) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalAsyncInflateManager getInflatedView exception2", e2));
            if (OKLog.D) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("\u5f02\u6b65\u52a0\u8f7dException ", Arrays.copyOf(new Object[]{e2}, 1));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                OKLog.d(TAG, format);
            }
        }
        if (!isAsyncSwitcherOpen()) {
            if (OKLog.D) {
                OKLog.d(TAG, "\u5f02\u6b65\u52a0\u8f7d\u5f00\u5173\u5173\u95ed\uff0c\u5728\u4e3b\u7ebf\u7a0binflate");
            }
            View inflate = ImageUtil.inflate(context, layoutResId, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "ImageUtil.inflate(contex\u2026youtResId, parent, false)");
            return inflate;
        }
        int hashCode = context.hashCode();
        if (isPreloadNextView) {
            asyncInflate(new PersonalAsyncInflateItem(hashCode, layoutResId));
        }
        if (getMInflateMap().containsKey(getInflateKey(layoutResId, hashCode))) {
            JDSoftReference<LinkedBlockingQueue<Object>> jDSoftReference = getMInflateMap().get(getInflateKey(layoutResId, hashCode));
            LinkedBlockingQueue<Object> linkedBlockingQueue = jDSoftReference != null ? jDSoftReference.get() : null;
            if ((linkedBlockingQueue instanceof LinkedBlockingQueue) && linkedBlockingQueue.size() > 0) {
                Object poll = linkedBlockingQueue.poll();
                if (poll instanceof PersonalAsyncInflateItem) {
                    View inflatedView = ((PersonalAsyncInflateItem) poll).getInflatedView();
                    if (inflatedView != null) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "inflateview is success");
                        }
                        boolean z = inflatedView.getParent() == null;
                        if (z) {
                            if (mLayoutParams != null) {
                                inflatedView.setLayoutParams(mLayoutParams);
                                return inflatedView;
                            }
                            return inflatedView;
                        } else if (z) {
                            throw new NoWhenBranchMatchedException();
                        } else {
                            View inflate2 = ImageUtil.inflate(context, layoutResId, parent, false);
                            Intrinsics.checkExpressionValueIsNotNull(inflate2, "ImageUtil.inflate(contex\u2026youtResId, parent, false)");
                            return inflate2;
                        }
                    } else if (((PersonalAsyncInflateItem) poll).getIsInflating()) {
                        try {
                            if (OKLog.D) {
                                OKLog.d(TAG, "inflating");
                            }
                            CountDownLatch countDownLatch = ((PersonalAsyncInflateItem) poll).getCountDownLatch();
                            if (countDownLatch != null) {
                                countDownLatch.await();
                            }
                        } catch (InterruptedException e3) {
                            JdCrashReport.postCaughtException(new IllegalArgumentException("PersonalAsyncInflateManager getInflatedView exception1", e3));
                            if (OKLog.D) {
                                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                                String format2 = String.format("InterruptedException ", Arrays.copyOf(new Object[]{e3}, 1));
                                Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
                                OKLog.d(TAG, format2);
                            }
                        }
                        View inflatedView2 = ((PersonalAsyncInflateItem) poll).getInflatedView();
                        if (inflatedView2 != null) {
                            if (OKLog.D) {
                                OKLog.d(TAG, "inflating wait success");
                            }
                            if (mLayoutParams != null) {
                                inflatedView2.setLayoutParams(mLayoutParams);
                            }
                            return inflatedView2;
                        }
                    }
                }
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, "\u5f02\u6b65\u52a0\u8f7d\u5931\u8d25\uff0c\u5728\u4e3b\u7ebf\u7a0binflate");
        }
        View inflate3 = ImageUtil.inflate(context, layoutResId, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate3, "ImageUtil.inflate(contex\u2026youtResId, parent, false)");
        return inflate3;
    }
}
