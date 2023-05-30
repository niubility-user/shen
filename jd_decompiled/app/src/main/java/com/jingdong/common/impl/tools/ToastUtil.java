package com.jingdong.common.impl.tools;

import android.content.Context;
import com.jingdong.common.protocol.tools.IToast;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes5.dex */
public class ToastUtil implements IToast {
    @Override // com.jingdong.common.protocol.tools.IToast
    public void longToast(Context context, String str) {
        if (context != null) {
            ToastUtils.longToast(context, str);
        }
    }

    @Override // com.jingdong.common.protocol.tools.IToast
    public void shortToast(Context context, String str) {
        if (context != null) {
            ToastUtils.shortToast(context, str);
        }
    }

    @Override // com.jingdong.common.protocol.tools.IToast
    public void showToast(Context context, String str) {
        if (context != null) {
            ToastUtils.showToast(context, str);
        }
    }

    @Override // com.jingdong.common.protocol.tools.IToast
    public void showToastInCenter(Context context, byte b, String str, int i2) {
        if (context != null) {
            ToastUtils.showToastInCenter(context, b, str, i2);
        }
    }

    @Override // com.jingdong.common.protocol.tools.IToast
    public void showToastY(Context context, String str) {
        if (context != null) {
            ToastUtils.showToastY(context, str);
        }
    }

    @Override // com.jingdong.common.protocol.tools.IToast
    public void showToastInCenter(Context context, int i2, String str, int i3) {
        if (context != null) {
            ToastUtils.showToastInCenter(context, i2, str, i3);
        }
    }

    @Override // com.jingdong.common.protocol.tools.IToast
    public void showToastInCenter(Context context, String str, int i2) {
        if (context != null) {
            ToastUtils.showToastInCenter(context, str, i2);
        }
    }

    @Override // com.jingdong.common.protocol.tools.IToast
    public void showToastInCenter(Context context, String str) {
        if (context != null) {
            ToastUtils.showToastInCenter(context, str);
        }
    }
}
