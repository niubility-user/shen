package com.jingdong.sdk.eldermode.util;

import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\"$\u0010\u0001\u001a\u0004\u0018\u00010\u00008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0012\n\u0004\b\u0001\u0010\u0002\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006\"$\u0010\f\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00008F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\"\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"", "cachedFloatingWindowSwitch", "Ljava/lang/Boolean;", "getCachedFloatingWindowSwitch$JDElderModeUtils__JDElderModeFloatWindowUtilsKt", "()Ljava/lang/Boolean;", "setCachedFloatingWindowSwitch$JDElderModeUtils__JDElderModeFloatWindowUtilsKt", "(Ljava/lang/Boolean;)V", "value", "getElderFloatingWindowSwitch", "()Z", "setElderFloatingWindowSwitch", "(Z)V", "elderFloatingWindowSwitch", "", "CACHE_KEY_ELDER_FLOATING_WINDOW_ENABLE", "Ljava/lang/String;", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes7.dex */
public final /* synthetic */ class JDElderModeUtils__JDElderModeFloatWindowUtilsKt {
    private static final String CACHE_KEY_ELDER_FLOATING_WINDOW_ENABLE = "elder_floating_window_enable";
    private static Boolean cachedFloatingWindowSwitch;

    public static final boolean getElderFloatingWindowSwitch() {
        if (JDElderModeUtils.isElderMode()) {
            Boolean bool = cachedFloatingWindowSwitch;
            if (bool != null) {
                return bool.booleanValue();
            }
            IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
            boolean z = !Intrinsics.areEqual(helper != null ? helper.getString(CACHE_KEY_ELDER_FLOATING_WINDOW_ENABLE) : null, "0");
            cachedFloatingWindowSwitch = Boolean.valueOf(z);
            return z;
        }
        return false;
    }

    public static final void setElderFloatingWindowSwitch(boolean z) {
        cachedFloatingWindowSwitch = Boolean.valueOf(z);
        IElderModeHelper helper = JDElderModeManager.INSTANCE.getHelper();
        if (helper != null) {
            helper.putString(CACHE_KEY_ELDER_FLOATING_WINDOW_ENABLE, z ? "1" : "0");
        }
    }
}
