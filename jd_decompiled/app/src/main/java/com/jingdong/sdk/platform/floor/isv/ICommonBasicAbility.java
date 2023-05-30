package com.jingdong.sdk.platform.floor.isv;

import android.content.Context;
import android.widget.FrameLayout;

/* loaded from: classes10.dex */
public interface ICommonBasicAbility {
    void closeDialog();

    void refresh();

    void sendMta(MtaData mtaData);

    void showDialog(FrameLayout frameLayout, double d);

    void showToastShortNormal(Context context, String str);
}
