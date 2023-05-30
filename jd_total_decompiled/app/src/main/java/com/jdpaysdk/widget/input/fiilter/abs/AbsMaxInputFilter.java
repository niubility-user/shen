package com.jdpaysdk.widget.input.fiilter.abs;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;

/* loaded from: classes18.dex */
public abstract class AbsMaxInputFilter extends AbsInputFilter {
    protected abstract int getMaxLength();

    @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsInputFilter
    protected final CharSequence realFilter(@NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i2, int i3) {
        int maxLength = getMaxLength() - (spannableStringBuilder2.length() - (i3 - i2));
        return (maxLength <= -1 || spannableStringBuilder.length() <= maxLength) ? spannableStringBuilder : spannableStringBuilder.subSequence(0, maxLength);
    }
}
