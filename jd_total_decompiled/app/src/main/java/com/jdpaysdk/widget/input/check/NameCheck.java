package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class NameCheck extends InputCheck {
    static final NameCheck INSTANCE = new NameCheck();
    public static final int MAX_NAME_LENGHT = 50;

    private NameCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        int length = CharSequenceUtil.getNoWhiteSpaceSequence(charSequence).toString().length();
        return length >= 2 && length <= 50;
    }
}
