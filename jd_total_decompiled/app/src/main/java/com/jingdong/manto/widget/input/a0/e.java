package com.jingdong.manto.widget.input.a0;

import android.text.TextUtils;

/* loaded from: classes16.dex */
public final class e {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Enum a(String str, Class cls) {
        int i2;
        if (cls.isEnum()) {
            i2 = 0;
            for (Object obj : cls.getEnumConstants()) {
                i2 = Math.max(((Enum) obj).name().length(), i2);
            }
        } else {
            i2 = 0;
        }
        if (!TextUtils.isEmpty(str) && str.length() <= i2) {
            String upperCase = str.toUpperCase();
            for (Enum r3 : (Enum[]) cls.getEnumConstants()) {
                if (upperCase.equals(r3.name())) {
                    return r3;
                }
            }
        }
        return null;
    }

    public static Integer a(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            try {
                return Integer.valueOf((int) Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }
}
