package com.jingdong.common.utils.pre;

import com.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.utils.pre.utils.PersonalPreUtils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0003\u001b\u001c\u001dB\t\b\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ-\u0010\b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005H\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\fH\u0007\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0018\u001a\u00020\u00138B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u001e"}, d2 = {"Lcom/jingdong/common/utils/pre/PersonalPreLoader;", "", "T", "", "functionId", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;", "observable", "", "preLoad", "(Ljava/lang/String;Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;)V", "destroy", "(Ljava/lang/String;)V", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "addListener", "(Ljava/lang/String;Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;)V", "", "taskSuccess", "(Ljava/lang/String;)Z", "Lcom/jingdong/common/utils/pre/utils/PersonalPreUtils;", "preUtils$delegate", "Lkotlin/Lazy;", "getPreUtils", "()Lcom/jingdong/common/utils/pre/utils/PersonalPreUtils;", "preUtils", "<init>", "()V", "DataCallBack", "DataChangeListener", "LoadDataObservable", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalPreLoader {
    public static final PersonalPreLoader INSTANCE = new PersonalPreLoader();

    /* renamed from: preUtils$delegate  reason: from kotlin metadata */
    private static final Lazy preUtils;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000H&\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataCallBack;", "T", "", "httpResponse", "", "onSuccess", "(Ljava/lang/Object;)V", "onError", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public interface DataCallBack<T> {
        void onError();

        void onSuccess(@Nullable T httpResponse);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001f\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataChangeListener;", "", "T", "response", "", "change", "(Ljava/lang/Object;)V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public interface DataChangeListener {
        <T> void change(@Nullable T response);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H&\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/jingdong/common/utils/pre/PersonalPreLoader$LoadDataObservable;", "T", "", "Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataCallBack;", "callback", "", TrackLoadSettingsAtom.TYPE, "(Lcom/jingdong/common/utils/pre/PersonalPreLoader$DataCallBack;)V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public interface LoadDataObservable<T> {
        void load(@NotNull DataCallBack<T> callback);
    }

    static {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<PersonalPreUtils>() { // from class: com.jingdong.common.utils.pre.PersonalPreLoader$preUtils$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PersonalPreUtils invoke() {
                return new PersonalPreUtils();
            }
        });
        preUtils = lazy;
    }

    private PersonalPreLoader() {
    }

    @JvmStatic
    public static final void addListener(@NotNull String functionId, @NotNull DataChangeListener listener) {
        INSTANCE.getPreUtils().addListener(functionId, listener);
    }

    @JvmStatic
    public static final void destroy(@NotNull String functionId) {
        INSTANCE.getPreUtils().destroy(functionId);
    }

    private final PersonalPreUtils getPreUtils() {
        return (PersonalPreUtils) preUtils.getValue();
    }

    @JvmStatic
    public static final <T> void preLoad(@NotNull String functionId, @NotNull LoadDataObservable<T> observable) {
        INSTANCE.getPreUtils().preLoad(functionId, observable);
    }

    @JvmStatic
    public static final boolean taskSuccess(@NotNull String functionId) {
        return INSTANCE.getPreUtils().taskSuccess(functionId);
    }
}
