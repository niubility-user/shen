package com.jdpaysdk.widget.input.fiilter.abs;

import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.util.SpannableStringUtil;

/* loaded from: classes18.dex */
public abstract class AbsInputFilter implements InputFilter {
    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        return realFilter(SpannableStringUtil.createBuilder(charSequence, i2, i3), SpannableStringUtil.createBuilder(spanned), i4, i5);
    }

    protected abstract CharSequence realFilter(@NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i2, int i3);
}
