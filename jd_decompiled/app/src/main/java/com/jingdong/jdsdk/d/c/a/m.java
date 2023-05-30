package com.jingdong.jdsdk.d.c.a;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil;

/* loaded from: classes14.dex */
public class m implements IFontsUtil {
    private static m a;

    private m() {
    }

    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            if (a == null) {
                a = new m();
            }
            mVar = a;
        }
        return mVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public void changeTextFont(@NonNull TextView textView, int i2) {
        FontsUtil.changeTextFont(textView, i2);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public int getMultiBold() {
        return 4097;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public int getMultiLight() {
        return 4098;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public int getMultiRegular() {
        return 4099;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public Typeface getTypeFace(@NonNull Context context, int i2) {
        return FontsUtil.getTypeFace(context, i2);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public void changeTextFont(@NonNull TextView textView) {
        FontsUtil.changeTextFont(textView);
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.utils.IFontsUtil
    public Typeface getTypeFace(@NonNull Context context) {
        return FontsUtil.getTypeFace(context);
    }
}
