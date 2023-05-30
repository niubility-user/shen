package com.jdpaysdk.widget.util;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes18.dex */
public class CharSequenceUtil {
    static final int NBSP_CODE_POINT = 160;

    @NonNull
    public static CharSequence getNoWhiteSpaceSequence(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return SpannableStringUtil.createBuilder();
        }
        SpannableStringBuilder createBuilder = SpannableStringUtil.createBuilder(charSequence);
        SpannableStringUtil.removeWhiteSpace(createBuilder);
        return createBuilder;
    }

    public static boolean hasSpace(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            if (Character.isSpaceChar(codePointAt)) {
                return true;
            }
            i2 += Character.charCount(codePointAt);
        }
        return false;
    }

    public static char highSurrogate(int i2) {
        return (char) ((i2 >>> 10) + 55232);
    }

    public static boolean isDigit(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            if (!Character.isDigit(codePointAt)) {
                return false;
            }
            i2 += Character.charCount(codePointAt);
        }
        return true;
    }

    public static boolean isEmpty(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return true;
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            if (!Character.isWhitespace(codePointAt) && codePointAt != 160) {
                return false;
            }
            i2 += Character.charCount(codePointAt);
        }
        return true;
    }

    public static boolean isEnLetter(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            if (!isEnLetter(codePointAt)) {
                return false;
            }
            i2 += Character.charCount(codePointAt);
        }
        return true;
    }

    public static boolean isEnLetterOrDigit(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            if (!Character.isDigit(codePointAt) && !isEnLetter(codePointAt)) {
                return false;
            }
            i2 += Character.charCount(codePointAt);
        }
        return true;
    }

    public static boolean isMaskDigit(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            if (!Character.isDigit(codePointAt) && codePointAt != 42) {
                return false;
            }
            i2 += Character.charCount(codePointAt);
        }
        return true;
    }

    public static boolean isMaskEnLetter(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            if (!isEnLetter(codePointAt) && codePointAt != 42) {
                return false;
            }
            i2 += Character.charCount(codePointAt);
        }
        return true;
    }

    public static char lowSurrogate(int i2) {
        return (char) ((i2 & R2.attr.icon_font_color) + 56320);
    }

    public static boolean isEnLetter(int i2) {
        return ((6 >> Character.getType(i2)) & 1) != 0;
    }
}
