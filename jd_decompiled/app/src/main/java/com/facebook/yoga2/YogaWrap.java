package com.facebook.yoga2;

@DoNotStrip
/* loaded from: classes12.dex */
public enum YogaWrap {
    NO_WRAP(0),
    WRAP(1),
    WRAP_REVERSE(2);
    
    private final int mIntValue;

    YogaWrap(int i2) {
        this.mIntValue = i2;
    }

    public static YogaWrap fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return WRAP_REVERSE;
                }
                throw new IllegalArgumentException("Unknown enum value: " + i2);
            }
            return WRAP;
        }
        return NO_WRAP;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
