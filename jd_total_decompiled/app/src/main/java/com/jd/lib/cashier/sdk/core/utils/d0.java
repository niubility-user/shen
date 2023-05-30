package com.jd.lib.cashier.sdk.core.utils;

import android.text.TextUtils;

/* loaded from: classes14.dex */
public class d0 {
    public static boolean a(CharSequence charSequence, CharSequence charSequence2) {
        return !(TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(charSequence2) || !TextUtils.equals(charSequence, charSequence2)) || (TextUtils.isEmpty(charSequence) && TextUtils.isEmpty(charSequence2));
    }
}
