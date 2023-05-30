package com.google.zxing.pdf417.decoder;

/* loaded from: classes12.dex */
final class Codeword {
    private static final int BARCODE_ROW_UNKNOWN = -1;
    private final int bucket;
    private final int endX;
    private int rowNumber = -1;
    private final int startX;
    private final int value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Codeword(int i2, int i3, int i4, int i5) {
        this.startX = i2;
        this.endX = i3;
        this.bucket = i4;
        this.value = i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getBucket() {
        return this.bucket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getEndX() {
        return this.endX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRowNumber() {
        return this.rowNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getStartX() {
        return this.startX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getValue() {
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWidth() {
        return this.endX - this.startX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasValidRowNumber() {
        return isValidRowNumber(this.rowNumber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidRowNumber(int i2) {
        return i2 != -1 && this.bucket == (i2 % 3) * 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRowNumber(int i2) {
        this.rowNumber = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRowNumberAsRowIndicatorColumn() {
        this.rowNumber = ((this.value / 30) * 3) + (this.bucket / 3);
    }

    public String toString() {
        return this.rowNumber + "|" + this.value;
    }
}
