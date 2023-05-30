package com.tencent.tencentmap.mapsdk.maps.model;

/* loaded from: classes9.dex */
public enum MapFontSize {
    tiny(0, 4),
    small(1, 3),
    normal(2, 0),
    large(3, 1),
    huge(4, 2);
    
    private int engineCode;
    private int fontSize;

    MapFontSize(int i2, int i3) {
        this.fontSize = i2;
        this.engineCode = i3;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public int getValue() {
        return this.engineCode;
    }
}
