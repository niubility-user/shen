package com.jdpaysdk.widget.input.fiilter.abs;

import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.span.IFormatSpan;
import com.jdpaysdk.widget.util.SpannableStringUtil;
import java.util.List;

/* loaded from: classes18.dex */
public abstract class FormatFilter implements InputFilter {
    private void applySpan(@NonNull Spannable spannable, @NonNull Spannable spannable2, int i2, int i3) {
        int length = spannable.length();
        int i4 = length - (i3 - i2);
        int i5 = length + i2;
        for (Integer num : getFormatIndexList(spannable2.length() + i4)) {
            if (num.intValue() < i2) {
                spannable2.setSpan(createSpan(), num.intValue(), num.intValue() + 1, 33);
            } else if (num.intValue() < i5) {
                int intValue = num.intValue() - i2;
                spannable.setSpan(createSpan(), intValue, intValue + 1, 33);
            } else {
                int intValue2 = num.intValue() - i4;
                spannable2.setSpan(createSpan(), intValue2, intValue2 + 1, 33);
            }
        }
    }

    private void removeFormatSpan(@NonNull CharSequence charSequence) {
        if (charSequence instanceof Spannable) {
            Spannable spannable = (Spannable) charSequence;
            for (IFormatSpan iFormatSpan : (IFormatSpan[]) spannable.getSpans(0, spannable.length(), IFormatSpan.class)) {
                spannable.removeSpan(iFormatSpan);
            }
        }
    }

    protected abstract IFormatSpan createSpan();

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        SpannableStringBuilder createBuilder = SpannableStringUtil.createBuilder(charSequence, i2, i3);
        SpannableStringUtil.removeWhiteSpace(createBuilder);
        removeFormatSpan(createBuilder);
        removeFormatSpan(spanned);
        if (spanned instanceof Spannable) {
            applySpan(createBuilder, (Spannable) spanned, i4, i5);
        } else if (TextUtils.isEmpty(spanned)) {
            applySpan(createBuilder, SpannableStringUtil.createBuilder(), i4, i5);
        }
        return createBuilder;
    }

    protected abstract List<Integer> getFormatIndexList(int i2);
}
