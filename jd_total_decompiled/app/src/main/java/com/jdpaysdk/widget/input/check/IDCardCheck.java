package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class IDCardCheck extends InputCheck {
    static final IDCardCheck INSTANCE = new IDCardCheck();

    private IDCardCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        String charSequence2 = CharSequenceUtil.getNoWhiteSpaceSequence(charSequence).toString();
        int length = charSequence2.length();
        if (z) {
            if (length == 15) {
                return CharSequenceUtil.isMaskDigit(charSequence2);
            }
            if (length == 18) {
                char charAt = charSequence2.charAt(17);
                return CharSequenceUtil.isMaskDigit(charSequence2.substring(0, 17)) && (Character.isDigit(charAt) || charAt == 'X' || charAt == 'x' || charAt == '*');
            }
        } else if (length == 15) {
            return CharSequenceUtil.isDigit(charSequence2);
        } else {
            if (length == 18) {
                char charAt2 = charSequence2.charAt(17);
                return CharSequenceUtil.isDigit(charSequence2.substring(0, 17)) && (Character.isDigit(charAt2) || charAt2 == 'X' || charAt2 == 'x');
            }
        }
        return false;
    }
}
