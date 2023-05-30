package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class PhoneNumberCheck extends InputCheck {
    static final PhoneNumberCheck INSTANCE = new PhoneNumberCheck();

    private PhoneNumberCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        String charSequence2 = CharSequenceUtil.getNoWhiteSpaceSequence(charSequence).toString();
        if (charSequence2.length() != 11) {
            return false;
        }
        if (z) {
            return CharSequenceUtil.isMaskDigit(charSequence2);
        }
        return CharSequenceUtil.isDigit(charSequence2);
    }
}
