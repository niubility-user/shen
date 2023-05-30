package com.jingdong.common.utils.text;

import com.jingdong.common.cart.clean.CartCleanConstants;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0010\u0010\tJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\u0006J\r\u0010\b\u001a\u00020\u0004\u00a2\u0006\u0004\b\b\u0010\tR#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\n8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lcom/jingdong/common/utils/text/TextSizeObserver;", "", "Lcom/jingdong/common/utils/text/OnTextScaleModeChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "", "addOnTextSizeChangeListener", "(Lcom/jingdong/common/utils/text/OnTextScaleModeChangeListener;)V", "removeOnTextSizeChangeListener", "dispatchOnTextSizeChange", "()V", "Ljava/util/concurrent/CopyOnWriteArrayList;", "mOnTextScaleModeChangeListeners$delegate", "Lkotlin/Lazy;", "getMOnTextScaleModeChangeListeners", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "mOnTextScaleModeChangeListeners", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class TextSizeObserver {

    /* renamed from: mOnTextScaleModeChangeListeners$delegate  reason: from kotlin metadata */
    private final Lazy mOnTextScaleModeChangeListeners;

    public TextSizeObserver() {
        Lazy lazy;
        lazy = LazyKt__LazyJVMKt.lazy(new Function0<CopyOnWriteArrayList<OnTextScaleModeChangeListener>>() { // from class: com.jingdong.common.utils.text.TextSizeObserver$mOnTextScaleModeChangeListeners$2
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CopyOnWriteArrayList<OnTextScaleModeChangeListener> invoke() {
                return new CopyOnWriteArrayList<>();
            }
        });
        this.mOnTextScaleModeChangeListeners = lazy;
    }

    private final CopyOnWriteArrayList<OnTextScaleModeChangeListener> getMOnTextScaleModeChangeListeners() {
        return (CopyOnWriteArrayList) this.mOnTextScaleModeChangeListeners.getValue();
    }

    public final void addOnTextSizeChangeListener(@NotNull OnTextScaleModeChangeListener listener) {
        if (getMOnTextScaleModeChangeListeners().contains(listener)) {
            return;
        }
        getMOnTextScaleModeChangeListeners().add(listener);
    }

    public final void dispatchOnTextSizeChange() {
        if (getMOnTextScaleModeChangeListeners().isEmpty()) {
            return;
        }
        Iterator<OnTextScaleModeChangeListener> it = getMOnTextScaleModeChangeListeners().iterator();
        while (it.hasNext()) {
            it.next().onTextScaleModeChanged();
        }
    }

    public final void removeOnTextSizeChangeListener(@NotNull OnTextScaleModeChangeListener listener) {
        getMOnTextScaleModeChangeListeners().remove(listener);
    }
}
