package com.jdpaysdk.widget.input.check;

import android.widget.TextView;

/* loaded from: classes18.dex */
public abstract class InputCheck {
    public static BankCardCheck getBankCardCheck() {
        return BankCardCheck.INSTANCE;
    }

    public static BirthdayCheck getBirthdayCheck() {
        return BirthdayCheck.INSTANCE;
    }

    public static CVVCheck getCVVCheck() {
        return CVVCheck.INSTANCE;
    }

    public static ForeignIDCardCheck getForeignIDCardCheck() {
        return ForeignIDCardCheck.INSTANCE;
    }

    public static IDCardCheck getIDCardCheck() {
        return IDCardCheck.INSTANCE;
    }

    public static NameCheck getNameCheck() {
        return NameCheck.INSTANCE;
    }

    public static PasswordCheck getPasswordCheck() {
        return PasswordCheck.INSTANCE;
    }

    public static PhoneNumberCheck getPhoneNumberCheck() {
        return PhoneNumberCheck.INSTANCE;
    }

    public static SMSCodeCheck getSMSCodeCheck() {
        return SMSCodeCheck.INSTANCE;
    }

    public static ValidDateCheck getValidDateCheck() {
        return ValidDateCheck.INSTANCE;
    }

    public final boolean check(TextView textView) {
        if (textView == null) {
            return false;
        }
        return check(textView.getText(), false);
    }

    public abstract boolean check(CharSequence charSequence, boolean z);

    public final boolean check(TextView textView, boolean z) {
        if (textView == null) {
            return false;
        }
        return check(textView.getText(), z);
    }

    public final boolean check(CharSequence charSequence) {
        return check(charSequence, false);
    }
}
