package com.jingdong.sdk.eldermode.util;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jdcn.biz.client.BankCardConstants;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\r\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\r\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\r\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0005\u001a\r\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\t\u001a\u0017\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\r\u0010\u000e\u001a\u0017\u0010\u000f\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u000f\u0010\u000e\u001a'\u0010\u0014\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0012\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u001d\u0010\u0016\u001a\u00020\f2\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0012\u00a2\u0006\u0004\b\u0016\u0010\u0017\"\u0016\u0010\u0018\u001a\u00020\u00008\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\"\u0016\u0010\u001a\u001a\u00020\u00008\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0019\u00a8\u0006\u001b"}, d2 = {"", "getElderMode", "()I", "", "isElderMode", "()Z", "isElderModeEnable", "", "getUemps", "()Ljava/lang/String;", "Lcom/jingdong/sdk/eldermode/util/OnElderModeChangeListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "", "addElderModeChangeListener", "(Lcom/jingdong/sdk/eldermode/util/OnElderModeChangeListener;)V", "removeElderModeChangeListener", "Landroidx/lifecycle/LifecycleOwner;", BankCardConstants.KEY_OWNER, "Landroidx/lifecycle/Observer;", "observer", "addElderModeObserver", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Observer;)V", "removeElderModeObserver", "(Landroidx/lifecycle/Observer;)V", "MODE_ELDER", "I", "MODE_NORMAL", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes7.dex */
public final /* synthetic */ class JDElderModeUtils__JDElderModeUtilsKt {
    public static final void addElderModeChangeListener(@Nullable OnElderModeChangeListener onElderModeChangeListener) {
        JDElderModeManager.INSTANCE.addElderModeChangeListener(onElderModeChangeListener);
    }

    public static final void addElderModeObserver(@Nullable LifecycleOwner lifecycleOwner, @Nullable Observer<Integer> observer) {
        JDElderModeManager.INSTANCE.addElderModeObserver(lifecycleOwner, observer);
    }

    public static final int getElderMode() {
        return JDElderModeManager.INSTANCE.getElderMode();
    }

    @NotNull
    public static final String getUemps() {
        return JDBModeUtils.getCurrentMode() + '-' + JDElderModeManager.INSTANCE.getRealElderUser() + '-' + JDBModeUtils.getAdviseVersion();
    }

    public static final boolean isElderMode() {
        return JDElderModeManager.INSTANCE.isElderMode();
    }

    public static final boolean isElderModeEnable() {
        return JDElderModeManager.INSTANCE.getElderModeEnable();
    }

    public static final void removeElderModeChangeListener(@Nullable OnElderModeChangeListener onElderModeChangeListener) {
        JDElderModeManager.INSTANCE.removeElderModeChangeListener(onElderModeChangeListener);
    }

    public static final void removeElderModeObserver(@Nullable Observer<Integer> observer) {
        JDElderModeManager.INSTANCE.removeElderModeObserver(observer);
    }
}
