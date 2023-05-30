package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;

/* loaded from: classes5.dex */
public interface NativeCustomKeyboardModuleListener {
    void bindKeyBoard(Context context, int i2, String str, Activity activity);

    void fromBackGroundToForeGround(Context context, int i2, Activity activity);

    void showSystemKeyboard(Context context);

    void switchSystemKeyboard(Context context, int i2);

    void unBindKeyBoard(Context context, int i2, Activity activity);
}
