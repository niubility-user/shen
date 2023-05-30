package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public enum YogaOverflow {
    VISIBLE(0),
    HIDDEN(1),
    SCROLL(2);
    
    private final int mIntValue;

    YogaOverflow(int i2) {
        this.mIntValue = i2;
    }

    public static YogaOverflow fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return SCROLL;
                }
                throw new IllegalArgumentException("Unknown enum value: " + i2);
            }
            return HIDDEN;
        }
        return VISIBLE;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
