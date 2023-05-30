package com.jingdong.common.utils.text;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.utils.CommonBase;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u0000 &2\u00020\u0001:\u0002&'B\t\b\u0012\u00a2\u0006\u0004\b%\u0010\u0010J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u0011\u001a\u00020\u000bH\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0012\u0010\u0004J\u001d\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001bR\u001d\u0010\"\u001a\u00020\u001d8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0018\u0010#\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010$\u00a8\u0006("}, d2 = {"Lcom/jingdong/common/utils/text/TextScaleModeHelper;", "", "", "getScaleMode", "()Ljava/lang/String;", "", "isOld", "getScaleModeByStatus", "(Z)Ljava/lang/String;", "Lcom/jingdong/common/utils/text/OnTextScaleModeChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "", "addOnTextSizeChangeListener", "(Lcom/jingdong/common/utils/text/OnTextScaleModeChangeListener;)V", "removeOnTextSizeChangeListener", "dispatchOnTextSizeChange$personallib", "()V", "dispatchOnTextSizeChange", "getTextSizeScaleMode", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "originalSP", "getScaleSize", "(Landroid/content/Context;F)F", "currentTextScaleMode", "setLastTextScaleMode$personallib", "(Ljava/lang/String;)V", "setLastTextScaleMode", "Lcom/jingdong/common/utils/text/TextSizeObserver;", "textSizeObserver$delegate", "Lkotlin/Lazy;", "getTextSizeObserver", "()Lcom/jingdong/common/utils/text/TextSizeObserver;", "textSizeObserver", "lastTextScaleMode", "Ljava/lang/String;", "<init>", "Companion", "SingletonHolder", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public class TextScaleModeHelper {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    @NotNull
    private static final TextScaleModeHelper instance = SingletonHolder.INSTANCE.getHolder();
    private static volatile Handler mainThreadHandler;
    private String lastTextScaleMode;

    /* renamed from: textSizeObserver$delegate  reason: from kotlin metadata */
    private final Lazy textSizeObserver;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/jingdong/common/utils/text/TextScaleModeHelper$Companion;", "", "Lcom/jingdong/common/utils/text/TextScaleModeHelper;", "instance", "Lcom/jingdong/common/utils/text/TextScaleModeHelper;", "getInstance", "()Lcom/jingdong/common/utils/text/TextScaleModeHelper;", "Landroid/os/Handler;", "mainThreadHandler", "Landroid/os/Handler;", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final TextScaleModeHelper getInstance() {
            return TextScaleModeHelper.instance;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c2\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/utils/text/TextScaleModeHelper$SingletonHolder;", "", "Lcom/jingdong/common/utils/text/TextScaleModeHelper;", "holder", "Lcom/jingdong/common/utils/text/TextScaleModeHelper;", "getHolder", "()Lcom/jingdong/common/utils/text/TextScaleModeHelper;", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    private static final class SingletonHolder {
        public static final SingletonHolder INSTANCE = new SingletonHolder();
        @NotNull
        private static final TextScaleModeHelper holder = new TextScaleModeHelper(null);

        private SingletonHolder() {
        }

        @NotNull
        public final TextScaleModeHelper getHolder() {
            return holder;
        }
    }

    public /* synthetic */ TextScaleModeHelper(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final String getScaleMode() {
        String scaleModeByStatus;
        if (TextSizeSharedPreferences.getSharePreferences().contains(ScaleModeConstants.TEXT_SCALE_MODE_KEY)) {
            scaleModeByStatus = getScaleModeByStatus(false);
        } else {
            scaleModeByStatus = getScaleModeByStatus(true);
            TextSizeSharedPreferences.getSharePreferences().edit().putString(ScaleModeConstants.TEXT_SCALE_MODE_KEY, scaleModeByStatus).apply();
        }
        return scaleModeByStatus == null || scaleModeByStatus.length() == 0 ? ScaleModeConstants.TEXT_SCALE_MODE_STANDARD : scaleModeByStatus;
    }

    private final String getScaleModeByStatus(boolean isOld) {
        if (isOld) {
            return CommonBase.getJdSharedPreferences().getString(ScaleModeConstants.TEXT_SCALE_MODE_KEY, ScaleModeConstants.TEXT_SCALE_MODE_STANDARD);
        }
        if (isOld) {
            throw new NoWhenBranchMatchedException();
        }
        return TextSizeSharedPreferences.getSharePreferences().getString(ScaleModeConstants.TEXT_SCALE_MODE_KEY, ScaleModeConstants.TEXT_SCALE_MODE_STANDARD);
    }

    public final TextSizeObserver getTextSizeObserver() {
        return (TextSizeObserver) this.textSizeObserver.getValue();
    }

    public final void addOnTextSizeChangeListener(@NotNull OnTextScaleModeChangeListener r2) {
        getTextSizeObserver().addOnTextSizeChangeListener(r2);
    }

    public final void dispatchOnTextSizeChange$personallib() {
        Handler handler = mainThreadHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.jingdong.common.utils.text.TextScaleModeHelper$dispatchOnTextSizeChange$1
                {
                    TextScaleModeHelper.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    TextSizeObserver textSizeObserver;
                    textSizeObserver = TextScaleModeHelper.this.getTextSizeObserver();
                    textSizeObserver.dispatchOnTextSizeChange();
                }
            });
        }
    }

    public final float getScaleSize(@NotNull Context r2, float originalSP) {
        float f2 = 2;
        return TextScaleSizeHelper.dynamicFontSize(r2, originalSP * f2) / f2;
    }

    @NotNull
    public final String getTextSizeScaleMode() {
        String str = this.lastTextScaleMode;
        if (str == null || str.length() == 0) {
            this.lastTextScaleMode = getScaleMode();
        }
        String str2 = this.lastTextScaleMode;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        return str2;
    }

    public final void removeOnTextSizeChangeListener(@NotNull OnTextScaleModeChangeListener r2) {
        getTextSizeObserver().removeOnTextSizeChangeListener(r2);
    }

    public final void setLastTextScaleMode$personallib(@NotNull String currentTextScaleMode) {
        this.lastTextScaleMode = currentTextScaleMode;
    }

    private TextScaleModeHelper() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<TextSizeObserver>() { // from class: com.jingdong.common.utils.text.TextScaleModeHelper$textSizeObserver$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final TextSizeObserver invoke() {
                return new TextSizeObserver();
            }
        });
        this.textSizeObserver = lazy;
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }
}
