package com.jdpaysdk.widget.input.listener;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;

/* loaded from: classes18.dex */
public class ValidDateKeyListener extends AbsKeyListener {
    public ValidDateKeyListener(@NonNull AbsEditText absEditText) {
        super(absEditText);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
        if (i2 == 0) {
            return i3 == 48 || i3 == 49;
        }
        return Character.isDigit(i3);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
    public int getInputType() {
        return 2;
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public void onInputError(int i2, CharSequence charSequence) {
        if (i2 == 0) {
            this.editText.setError("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u6708\u4efd");
        } else {
            this.editText.setError("\u9650\u5236\u8f93\u5165\u6570\u5b57");
        }
    }
}
