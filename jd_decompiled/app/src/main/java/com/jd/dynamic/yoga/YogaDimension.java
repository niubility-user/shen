package com.jd.dynamic.yoga;

/* loaded from: classes13.dex */
public enum YogaDimension {
    WIDTH(0),
    HEIGHT(1);
    
    private final int mIntValue;

    YogaDimension(int i2) {
        this.mIntValue = i2;
    }

    public static YogaDimension fromInt(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return HEIGHT;
            }
            throw new IllegalArgumentException("Unknown enum value: " + i2);
        }
        return WIDTH;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
