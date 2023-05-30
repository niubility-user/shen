package com.jingdong.sdk.eldermode.util;

import com.jingdong.common.address.AddressConstant;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001a\u00020\u00008\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\"\u0016\u0010\b\u001a\u00020\u00008\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007\"\u0016\u0010\t\u001a\u00020\u00008\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0007\"\u0016\u0010\n\u001a\u00020\u00008\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0007\u00a8\u0006\u000b"}, d2 = {"", "flags", AddressConstant.INTENT_EXTAS_CHECK_FLAG_KEY, "", "hasFlag", "(II)Z", "SUPPORT_MODE_DARK_MODE", "I", "SUPPORT_MODE_ELDER_DARK_LARGE_SIZE", "SUPPORT_MODE_ELDER_MODE", "SUPPORT_MODE_LARGE_SIZE", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes7.dex */
final /* synthetic */ class JDElderModeUtils__JDElderModeUiUtilsKt {
    public static final boolean hasFlag(int i2, int i3) {
        return (i2 & i3) == i3;
    }
}
