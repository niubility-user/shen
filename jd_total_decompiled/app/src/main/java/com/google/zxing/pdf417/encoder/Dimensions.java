package com.google.zxing.pdf417.encoder;

/* loaded from: classes12.dex */
public final class Dimensions {
    private final int maxCols;
    private final int maxRows;
    private final int minCols;
    private final int minRows;

    public Dimensions(int i2, int i3, int i4, int i5) {
        this.minCols = i2;
        this.maxCols = i3;
        this.minRows = i4;
        this.maxRows = i5;
    }

    public int getMaxCols() {
        return this.maxCols;
    }

    public int getMaxRows() {
        return this.maxRows;
    }

    public int getMinCols() {
        return this.minCols;
    }

    public int getMinRows() {
        return this.minRows;
    }
}
