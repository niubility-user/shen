package com.jd.lib.babel.servicekit.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import com.jd.lib.babel.servicekit.iservice.IFonts;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.wireless.iconfont.JDIconFontUtil;

/* loaded from: classes13.dex */
public class FontUtil implements IFonts {
    @Override // com.jd.lib.babel.servicekit.iservice.IFonts
    public void changeTextFont(@NonNull TextView textView, int i2) {
        if (i2 == 4097) {
            FontsUtil.changeTextFont(textView, 4097);
        } else if (i2 != 4098) {
            FontsUtil.changeTextFont(textView, 4099);
        } else {
            FontsUtil.changeTextFont(textView, 4098);
        }
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IFonts
    public Typeface getTypeFace(@NonNull Context context, int i2) {
        if (i2 != 4097) {
            if (i2 != 4098) {
                return FontsUtil.getTypeFace(context, 4099);
            }
            return FontsUtil.getTypeFace(context, 4098);
        }
        return FontsUtil.getTypeFace(context, 4097);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IFonts
    public void setImageIcon(ImageView imageView, String str, String str2) {
        JDIconFontUtil.setImageIcon(imageView, str, str2);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IFonts
    public void setImageIcon(ImageView imageView, String str, String str2, @ColorInt int i2) {
        JDIconFontUtil.setImageIcon(imageView, str, str2, i2);
    }
}
