package com.jingdong.common.eldermode;

import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.eldermode.helper.IElderModeToast;
import com.jingdong.sdk.jdtoast.ToastUtils;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes5.dex */
public class JDElderModeToastImpl implements IElderModeToast {
    @Override // com.jingdong.sdk.eldermode.helper.IElderModeToast
    public void showToast(@NotNull String str) {
        ToastUtils.showToast(JdSdk.getInstance().getApplication(), str);
    }
}
