package com.jdpaysdk.widget.util;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public class SpannableStringUtil {
    @NonNull
    public static SpannableStringBuilder createBuilder() {
        return createBuilder("");
    }

    public static void removeWhiteSpace(@NonNull SpannableStringBuilder spannableStringBuilder) {
        int i2 = 0;
        while (i2 < spannableStringBuilder.length()) {
            int codePointAt = Character.codePointAt(spannableStringBuilder, i2);
            int charCount = Character.charCount(codePointAt);
            if (Character.isWhitespace(codePointAt) || codePointAt == 160) {
                spannableStringBuilder.delete(i2, charCount + i2);
            } else {
                i2 += charCount;
            }
        }
    }

    @NonNull
    public static SpannableStringBuilder createBuilder(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        return createBuilder(charSequence, 0, charSequence.length());
    }

    @NonNull
    public static SpannableStringBuilder createBuilder(@Nullable CharSequence charSequence, int i2, int i3) {
        if (charSequence == null) {
            charSequence = "";
        }
        CharSequence charSequence2 = charSequence;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.replace(0, 0, charSequence2, i2, i3);
        return spannableStringBuilder;
    }
}
