package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: classes12.dex */
public enum YogaPrintOptions {
    LAYOUT(1),
    STYLE(2),
    CHILDREN(4);
    
    private final int mIntValue;

    YogaPrintOptions(int i2) {
        this.mIntValue = i2;
    }

    public static YogaPrintOptions fromInt(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 4) {
                    return CHILDREN;
                }
                throw new IllegalArgumentException("Unknown enum value: " + i2);
            }
            return STYLE;
        }
        return LAYOUT;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
