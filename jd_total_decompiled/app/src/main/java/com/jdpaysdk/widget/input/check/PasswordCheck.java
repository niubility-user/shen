package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class PasswordCheck extends InputCheck {
    static final PasswordCheck INSTANCE = new PasswordCheck();

    private PasswordCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        int length;
        return charSequence != null && (length = charSequence.length()) >= 6 && length <= 20 && !CharSequenceUtil.hasSpace(charSequence);
    }
}
