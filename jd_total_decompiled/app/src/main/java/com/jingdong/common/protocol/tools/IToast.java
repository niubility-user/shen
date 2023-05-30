package com.jingdong.common.protocol.tools;

import android.content.Context;

/* loaded from: classes5.dex */
public interface IToast {
    public static final byte EXCLAMATION = 1;
    public static final byte TICK = 2;

    void longToast(Context context, String str);

    void shortToast(Context context, String str);

    void showToast(Context context, String str);

    void showToastInCenter(Context context, byte b, String str, int i2);

    void showToastInCenter(Context context, int i2, String str, int i3);

    void showToastInCenter(Context context, String str);

    void showToastInCenter(Context context, String str, int i2);

    void showToastY(Context context, String str);
}
