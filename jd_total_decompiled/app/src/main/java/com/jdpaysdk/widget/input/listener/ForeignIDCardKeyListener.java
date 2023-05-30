package com.jdpaysdk.widget.input.listener;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;
import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class ForeignIDCardKeyListener extends AbsKeyListener {
    public ForeignIDCardKeyListener(@NonNull AbsEditText absEditText) {
        super(absEditText);
    }

    private int getHeadLetterCount(@NonNull SpannableStringBuilder spannableStringBuilder) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < spannableStringBuilder.length()) {
            int codePointAt = Character.codePointAt(spannableStringBuilder, i2);
            int charCount = Character.charCount(codePointAt);
            if (!CharSequenceUtil.isEnLetter(codePointAt)) {
                break;
            }
            i3++;
            i2 += charCount;
        }
        return i3;
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
        return i3 == 42 || Character.isDigit(i3) || CharSequenceUtil.isEnLetter(i3);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
    public int getInputType() {
        return 2;
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public void onInputError(int i2, CharSequence charSequence) {
        if (i2 < 3) {
            this.editText.setError("\u524d\u4e09\u4f4d\u4e3a\u82f1\u6587\u5b57\u6bcd");
        } else {
            this.editText.setError("4-15\u4f4d\u4e3a\u6570\u5b57");
        }
    }
}
