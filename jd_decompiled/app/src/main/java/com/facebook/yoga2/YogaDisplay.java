package com.facebook.yoga2;

@DoNotStrip
/* loaded from: classes12.dex */
public enum YogaDisplay {
    FLEX(0),
    NONE(1);
    
    private final int mIntValue;

    YogaDisplay(int i2) {
        this.mIntValue = i2;
    }

    public static YogaDisplay fromInt(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return NONE;
            }
            throw new IllegalArgumentException("Unknown enum value: " + i2);
        }
        return FLEX;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
