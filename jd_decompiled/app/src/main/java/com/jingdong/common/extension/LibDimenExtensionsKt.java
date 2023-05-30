package com.jingdong.common.extension;

import com.jingdong.sdk.utils.DPIUtil;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\b\u0005\"\u0017\u0010\u0004\u001a\u00020\u0001*\u00020\u00008F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0017\u0010\u0004\u001a\u00020\u0001*\u00020\u00018F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"", "", "getDp", "(F)I", "dp", "(I)I", "personallib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class LibDimenExtensionsKt {
    public static final int getDp(float f2) {
        return DPIUtil.dip2px(f2);
    }

    public static final int getDp(int i2) {
        return getDp(i2);
    }
}
