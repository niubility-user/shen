package com.jingdong.jdsdk.utils;

import android.content.Context;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.IconGradientDrawable;
import com.jingdong.wireless.iconfont.JDIconFontUtil;
import com.jingdong.wireless.iconfont.widget.IconImpl;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes14.dex */
public class IconFontUtils {
    public static final String SEARCH_PATH = "fonts".concat(File.separator).concat("search.ttf");

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface IconFontType {
        public static final String ICON_FONT_COMMON = "icon_font_common";
        public static final String ICON_FONT_SEARCH = "icon_font_search";
    }

    public static IconDrawable getIconDrawable(Context context, String str, int i2) {
        String str2 = JDIconFontUtil.COMMON_PATH;
        str.hashCode();
        if (!str.equals("icon_font_common") && str.equals(IconFontType.ICON_FONT_SEARCH)) {
            str2 = SEARCH_PATH;
        }
        return new IconDrawable(context, new IconImpl("", context.getString(i2)), str2);
    }

    public static IconGradientDrawable getIconDrawableHasBg(Context context, String str, int i2) {
        String str2 = JDIconFontUtil.COMMON_PATH;
        str.hashCode();
        if (!str.equals("icon_font_common") && str.equals(IconFontType.ICON_FONT_SEARCH)) {
            str2 = SEARCH_PATH;
        }
        return new IconGradientDrawable(context, new IconImpl("", context.getString(i2)), str2);
    }
}
