package com.jingdong.sdk.eldermode.util;

import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"", "area", "", "onElderOverseasAreaChanged", "(I)V", "eldermodelib"}, k = 5, mv = {1, 4, 0}, xs = "com/jingdong/sdk/eldermode/util/JDElderModeUtils")
/* loaded from: classes7.dex */
public final /* synthetic */ class JDElderModeUtils__JDElderModeOverseasUtilsKt {
    public static final void onElderOverseasAreaChanged(int i2) {
        JDElderModeManager.INSTANCE.onOverseasAreaChanged$eldermodelib(i2);
    }
}
