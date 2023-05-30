package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public enum YogaFlexDirection {
    COLUMN(0),
    COLUMN_REVERSE(1),
    ROW(2),
    ROW_REVERSE(3);
    
    private final int mIntValue;

    YogaFlexDirection(int i2) {
        this.mIntValue = i2;
    }

    public static YogaFlexDirection fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return ROW_REVERSE;
                    }
                    throw new IllegalArgumentException("Unknown enum value: " + i2);
                }
                return ROW;
            }
            return COLUMN_REVERSE;
        }
        return COLUMN;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
