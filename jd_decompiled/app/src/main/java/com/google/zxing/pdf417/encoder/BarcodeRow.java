package com.google.zxing.pdf417.encoder;

/* loaded from: classes12.dex */
final class BarcodeRow {
    private int currentLocation = 0;
    private final byte[] row;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeRow(int i2) {
        this.row = new byte[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBar(boolean z, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.currentLocation;
            this.currentLocation = i4 + 1;
            set(i4, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getScaledRow(int i2) {
        int length = this.row.length * i2;
        byte[] bArr = new byte[length];
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i3] = this.row[i3 / i2];
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(int i2, byte b) {
        this.row[i2] = b;
    }

    void set(int i2, boolean z) {
        this.row[i2] = z ? (byte) 1 : (byte) 0;
    }
}
