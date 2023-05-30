package com.jdpaysdk.widget.input.listener;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;

/* loaded from: classes18.dex */
public class IDCardKeyListener extends AbsKeyListener {
    public IDCardKeyListener(@NonNull AbsEditText absEditText) {
        super(absEditText);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
        if (i4 == 120 || i4 == 88) {
            return false;
        }
        if (i3 == 42) {
            return true;
        }
        if (i2 == 0) {
            return i3 >= 49 && i3 <= 57;
        } else if (i2 == 17) {
            return Character.isDigit(i3) || i3 == 120 || i3 == 88;
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
        this.editText.setError("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u8eab\u4efd\u8bc1\u53f7");
    }
}
