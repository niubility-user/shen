package com.jd.lib.productdetail.core.iconfont;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.IconGradientDrawable;
import com.jingdong.wireless.iconfont.JDIconFontUtil;
import com.jingdong.wireless.iconfont.widget.IconImpl;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes15.dex */
public class PDIconFontUtil {
    public static final String PD_ICON_PATH = "fonts".concat(File.separator).concat("productdetail.ttf");

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface IconFontType {
        public static final String ICON_FONT_COMMON = "icon_font_common";
        public static final String ICON_FONT_PD = "icon_font_pd";
    }

    public static IconDrawable getIconDrawable(Context context, String str, int i2) {
        String str2 = JDIconFontUtil.COMMON_PATH;
        str.hashCode();
        if (str.equals(IconFontType.ICON_FONT_PD)) {
            str2 = PD_ICON_PATH;
        } else {
            str.equals("icon_font_common");
        }
        return new IconDrawable(context, new IconImpl("", context.getString(i2)), str2);
    }

    public static IconGradientDrawable getIconDrawableHasBg(Context context, String str, int i2) {
        String str2 = JDIconFontUtil.COMMON_PATH;
        str.hashCode();
        if (str.equals(IconFontType.ICON_FONT_PD)) {
            str2 = PD_ICON_PATH;
        } else {
            str.equals("icon_font_common");
        }
        return new IconGradientDrawable(context, new IconImpl("", context.getString(i2)), str2);
    }

    public static IconDrawable setImageIcon(ImageView imageView, int i2) {
        return setImageIcon(imageView, i2, IconFontType.ICON_FONT_PD);
    }

    public static IconDrawable setImageIcon(ImageView imageView, int i2, String str) {
        if (imageView == null) {
            return null;
        }
        IconDrawable color = getIconDrawable(imageView.getContext(), str, i2).color(Color.parseColor(JDDarkUtil.COLOR_0000000));
        imageView.setImageDrawable(color);
        return color;
    }

    public static IconDrawable getIconDrawable(Context context, int i2) {
        return getIconDrawable(context, IconFontType.ICON_FONT_PD, i2);
    }
}
