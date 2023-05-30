package com.jdpaysdk.widget.input.check;

import android.text.TextUtils;
import com.jdpaysdk.widget.util.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes18.dex */
public class BirthdayCheck extends InputCheck {
    static final BirthdayCheck INSTANCE = new BirthdayCheck();
    private static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

    private BirthdayCheck() {
    }

    private boolean isFormatRight(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Date parse = yyyyMMdd.parse(str);
            if (parse == null) {
                return false;
            }
            return str.equals(yyyyMMdd.format(parse));
        } catch (ParseException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.jdpaysdk.widget.input.check.InputCheck
    public boolean check(CharSequence charSequence, boolean z) {
        if (TextUtils.isEmpty(charSequence)) {
            return false;
        }
        return isFormatRight(charSequence.toString());
    }

    public boolean checkWithCurrentDate(CharSequence charSequence) {
        if (check(charSequence)) {
            try {
                Date parse = yyyyMMdd.parse(charSequence.toString());
                if (parse == null) {
                    return false;
                }
                return DateUtil.compareCurrentDate(parse) <= 0;
            } catch (ParseException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
