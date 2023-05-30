package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;

/* loaded from: classes18.dex */
public class CVVCheck extends InputCheck {
    static final CVVCheck INSTANCE = new CVVCheck();

    private CVVCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        if (charSequence != null && charSequence.length() == 3) {
            if (z) {
                return CharSequenceUtil.isMaskDigit(charSequence);
            }
            return CharSequenceUtil.isDigit(charSequence);
        }
        return false;
    }
}
