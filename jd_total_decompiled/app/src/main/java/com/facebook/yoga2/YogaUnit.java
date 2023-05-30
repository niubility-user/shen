package com.facebook.yoga2;

@DoNotStrip
/* loaded from: classes12.dex */
public enum YogaUnit {
    UNDEFINED(0),
    POINT(1),
    PERCENT(2),
    AUTO(3);
    
    private final int mIntValue;

    YogaUnit(int i2) {
        this.mIntValue = i2;
    }

    public static YogaUnit fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        return AUTO;
                    }
                    throw new IllegalArgumentException("Unknown enum value: " + i2);
                }
                return PERCENT;
            }
            return POINT;
        }
        return UNDEFINED;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
