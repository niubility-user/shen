package com.jdpaysdk.widget.input.listener;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;

/* loaded from: classes18.dex */
public class BirthdayKeyListener extends AbsKeyListener {
    public BirthdayKeyListener(@NonNull AbsEditText absEditText) {
        super(absEditText);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
        if (i2 == 4) {
            return i3 == 48 || i3 == 49;
        } else if (i2 == 6) {
            return i3 >= 48 && i3 <= 51;
        } else {
            return Character.isDigit(i3);
        }
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
    public int getInputType() {
        return 2;
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public void onInputError(int i2, CharSequence charSequence) {
        if (i2 == 4) {
            this.editText.setError("\u8bf7\u8f93\u5165\u6708\u4efd");
        } else if (i2 == 6) {
            this.editText.setError("\u8bf7\u8f93\u5165\u65e5\u671f");
        } else {
            this.editText.setError("\u9650\u5236\u8f93\u5165\u6570\u5b57");
        }
    }
}
