package com.jdpaysdk.widget.input.check;

import com.jdpaysdk.widget.util.CharSequenceUtil;
import java.util.Calendar;

/* loaded from: classes18.dex */
public class ValidDateCheck extends InputCheck {
    static final ValidDateCheck INSTANCE = new ValidDateCheck();

    private ValidDateCheck() {
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        return charSequence != null && charSequence.length() == 4 && CharSequenceUtil.isDigit(charSequence);
    }

    public boolean checkWithCurrentDate(CharSequence charSequence) {
        if (check(charSequence)) {
            Calendar calendar = Calendar.getInstance();
            int i2 = calendar.get(2);
            int i3 = calendar.get(1);
            String charSequence2 = charSequence.toString();
            try {
                return Integer.parseInt(charSequence2.substring(2)) != Integer.parseInt(String.valueOf(i3).substring(2)) || Integer.parseInt(charSequence2.substring(0, 2)) >= i2 + 1;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
