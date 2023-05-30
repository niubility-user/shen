package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public enum YogaLayoutType {
    LAYOUT(0),
    MEASURE(1),
    CACHED_LAYOUT(2),
    CACHED_MEASURE(3);
    
    private final int mIntValue;

    YogaLayoutType(int i2) {
        this.mIntValue = i2;
    }

    public static YogaLayoutType fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return CACHED_MEASURE;
                    }
                    throw new IllegalArgumentException("Unknown enum value: " + i2);
                }
                return CACHED_LAYOUT;
            }
            return MEASURE;
        }
        return LAYOUT;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
