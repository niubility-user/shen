package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class SMSCodeCheck extends InputCheck {
    static final SMSCodeCheck INSTANCE = new SMSCodeCheck();

    private SMSCodeCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        return charSequence != null && charSequence.length() == 6 && CharSequenceUtil.isEnLetterOrDigit(charSequence);
    }
}
