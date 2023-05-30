package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class BankCardCheck extends InputCheck {
    static final BankCardCheck INSTANCE = new BankCardCheck();

    private BankCardCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        String charSequence2;
        int length;
        if (charSequence != null && (length = (charSequence2 = CharSequenceUtil.getNoWhiteSpaceSequence(charSequence).toString()).length()) >= 12 && length <= 21) {
            if (z) {
                return CharSequenceUtil.isMaskDigit(charSequence2);
            }
            return CharSequenceUtil.isDigit(charSequence2);
        }
        return false;
    }
}
