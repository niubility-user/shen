package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public enum YogaPositionType {
    RELATIVE(0),
    ABSOLUTE(1);
    
    private final int mIntValue;

    YogaPositionType(int i2) {
        this.mIntValue = i2;
    }

    public static YogaPositionType fromInt(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return ABSOLUTE;
            }
            throw new IllegalArgumentException("Unknown enum value: " + i2);
        }
        return RELATIVE;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
