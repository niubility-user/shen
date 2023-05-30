package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public enum YogaMeasureMode {
    UNDEFINED(0),
    EXACTLY(1),
    AT_MOST(2);
    
    private final int mIntValue;

    YogaMeasureMode(int i2) {
        this.mIntValue = i2;
    }

    public static YogaMeasureMode fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return AT_MOST;
                }
                throw new IllegalArgumentException("Unknown enum value: " + i2);
            }
            return EXACTLY;
        }
        return UNDEFINED;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
