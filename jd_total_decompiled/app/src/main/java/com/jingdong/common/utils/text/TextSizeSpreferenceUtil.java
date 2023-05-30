package com.jingdong.common.utils.text;

import com.jingdong.common.utils.CommonBase;

/* loaded from: classes6.dex */
public class TextSizeSpreferenceUtil {
    public static final String KEY_TEXT_SIZE_SHOWED = "key_text_size_showed";

    public static boolean getTextSizeShowed() {
        return CommonBase.getJdSharedPreferences().getBoolean(KEY_TEXT_SIZE_SHOWED, false);
    }

    public static void putTextSizeShowed(boolean z) {
        CommonBase.getJdSharedPreferences().edit().putBoolean(KEY_TEXT_SIZE_SHOWED, z).apply();
    }
}
