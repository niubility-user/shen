package com.jdpaysdk.widget.input.listener;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;

/* loaded from: classes18.dex */
public class QweKeyListener extends AbsKeyListener {
    public QweKeyListener(@NonNull AbsEditText absEditText) {
        super(absEditText);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
        return true;
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
    public int getInputType() {
        return 1;
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public void onInputError(int i2, CharSequence charSequence) {
    }
}
