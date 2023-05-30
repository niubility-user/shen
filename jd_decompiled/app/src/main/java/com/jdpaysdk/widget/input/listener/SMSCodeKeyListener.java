package com.jdpaysdk.widget.input.listener;

import android.text.SpannableStringBuilder;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;
import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class SMSCodeKeyListener extends AbsKeyListener {
    public SMSCodeKeyListener(@NonNull AbsEditText absEditText) {
        super(absEditText);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
        return Character.isDigit(i3) || CharSequenceUtil.isEnLetter(i3);
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
    public int getInputType() {
        return 2;
    }

    @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
    public void onInputError(int i2, CharSequence charSequence) {
        this.editText.setError("\u9650\u5236\u8f93\u5165\u6570\u5b57\u53ca\u82f1\u6587\u5b57\u6bcd");
    }
}
