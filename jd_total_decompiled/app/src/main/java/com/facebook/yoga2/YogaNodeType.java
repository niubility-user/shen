package com.facebook.yoga2;

@DoNotStrip
/* loaded from: classes12.dex */
public enum YogaNodeType {
    DEFAULT(0),
    TEXT(1);
    
    private final int mIntValue;

    YogaNodeType(int i2) {
        this.mIntValue = i2;
    }

    public static YogaNodeType fromInt(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return TEXT;
            }
            throw new IllegalArgumentException("Unknown enum value: " + i2);
        }
        return DEFAULT;
    }

    public int intValue() {
        return this.mIntValue;
    }
}
