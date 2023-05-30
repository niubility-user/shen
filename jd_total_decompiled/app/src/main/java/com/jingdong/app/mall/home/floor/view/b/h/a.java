package com.jingdong.app.mall.home.floor.view.b.h;

import android.graphics.Color;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.jd.dynamic.DYConstants;

/* loaded from: classes4.dex */
public class a {
    public static boolean a(int i2, int i3, int i4, int i5) {
        return ((float) ((i2 >> 16) & 255)) > ((float) i3) && ((float) ((i2 >> 8) & 255)) > ((float) i4) && ((float) (i2 & 255)) > ((float) i5);
    }

    public static int b(int i2, int i3) {
        return c(i2, i3, 255);
    }

    public static int c(int i2, int i3, int i4) {
        return i3 < 0 ? i2 : i2 & ((((int) (Math.min(i3, i4) * (255.0f / i4))) << 24) | ViewCompat.MEASURED_SIZE_MASK);
    }

    public static int d(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return Color.parseColor(TextUtils.split(str.trim(), DYConstants.DY_REGEX_COMMA)[0].trim());
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    public static int[] e(String str, int... iArr) {
        if (TextUtils.isEmpty(str)) {
            return iArr;
        }
        try {
            String[] split = TextUtils.split(str.trim(), DYConstants.DY_REGEX_COMMA);
            int[] iArr2 = new int[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                iArr2[i2] = Color.parseColor(split[i2].trim());
            }
            return iArr2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return iArr;
        }
    }

    public static int[] f(int[] iArr, int... iArr2) {
        if (iArr == null || iArr.length < 1) {
            return new int[]{0};
        }
        int max = Math.max(iArr2.length, 2);
        int[] iArr3 = new int[max];
        for (int i2 = 0; i2 < max; i2++) {
            iArr3[i2] = b(iArr[Math.min(i2, iArr.length - 1)], iArr2[Math.min(i2, iArr2.length - 1)]);
        }
        return iArr3;
    }

    public static int[] g(String str, int... iArr) {
        if (TextUtils.isEmpty(str)) {
            return iArr;
        }
        try {
            String[] split = TextUtils.split(str.trim(), DYConstants.DY_REGEX_COMMA);
            int[] iArr2 = new int[split.length];
            for (int i2 = 0; i2 < split.length; i2++) {
                iArr2[i2] = Integer.parseInt(split[i2].trim());
            }
            return iArr2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return iArr;
        }
    }

    public static void h(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr.length <= 0 || iArr2.length != 2) {
            return;
        }
        iArr2[0] = iArr[0];
        iArr2[1] = iArr[iArr.length > 1 ? (char) 1 : (char) 0];
    }
}
