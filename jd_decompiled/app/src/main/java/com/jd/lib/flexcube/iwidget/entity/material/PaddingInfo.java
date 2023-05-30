package com.jd.lib.flexcube.iwidget.entity.material;

import android.graphics.Rect;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.lib.flexcube.iwidget.b.b;

/* loaded from: classes14.dex */
public class PaddingInfo {
    public String detached;
    public boolean disableUp;
    public String lrValue;
    public String ltrbValue;
    public String tbValue;
    public String value;

    public int getLeftAndRightValue() {
        int f2;
        if ("1".equals(this.detached)) {
            if (TextUtils.isEmpty(this.ltrbValue)) {
                f2 = b.f(this.lrValue, 0);
            } else {
                String[] split = this.ltrbValue.split(DYConstants.DY_REGEX_COMMA);
                if (split != null || split.length == 4) {
                    return b.f(split[0], 0) + b.f(split[2], 0);
                }
                return 0;
            }
        } else {
            f2 = b.f(this.value, 0);
        }
        return f2 * 2;
    }

    public int getMultipleSize(String str, float f2) {
        int f3 = b.f(str, 0);
        return this.disableUp ? (int) (f3 * f2) : b.d(f3, f2);
    }

    public Rect getPadding(float f2, boolean z) {
        this.disableUp = z;
        return getPadding(f2);
    }

    public Rect getPadding(float f2) {
        int multipleSize;
        int i2;
        if ("1".equals(this.detached)) {
            if (TextUtils.isEmpty(this.ltrbValue)) {
                multipleSize = getMultipleSize(this.lrValue, f2);
                i2 = getMultipleSize(this.tbValue, f2);
            } else {
                String[] split = this.ltrbValue.split(DYConstants.DY_REGEX_COMMA);
                if (split != null && split.length == 4) {
                    return new Rect(getMultipleSize(split[0], f2), getMultipleSize(split[1], f2), getMultipleSize(split[2], f2), getMultipleSize(split[3], f2));
                }
                return new Rect(0, 0, 0, 0);
            }
        } else {
            multipleSize = getMultipleSize(this.value, f2);
            i2 = multipleSize;
        }
        return new Rect(multipleSize, i2, multipleSize, i2);
    }
}
