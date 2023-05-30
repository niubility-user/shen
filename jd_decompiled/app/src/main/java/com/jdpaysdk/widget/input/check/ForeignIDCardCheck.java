package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class ForeignIDCardCheck extends InputCheck {
    static final ForeignIDCardCheck INSTANCE = new ForeignIDCardCheck();

    private ForeignIDCardCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        String charSequence2 = CharSequenceUtil.getNoWhiteSpaceSequence(charSequence).toString();
        if (charSequence2.length() != 15) {
            return false;
        }
        return z ? CharSequenceUtil.isMaskEnLetter(charSequence2.substring(0, 3)) && CharSequenceUtil.isMaskDigit(charSequence2.substring(3)) : CharSequenceUtil.isEnLetter(charSequence2.substring(0, 3)) && CharSequenceUtil.isDigit(charSequence2.substring(3));
    }
}
