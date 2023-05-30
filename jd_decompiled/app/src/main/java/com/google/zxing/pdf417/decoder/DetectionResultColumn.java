package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

/* loaded from: classes12.dex */
class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultColumn(BoundingBox boundingBox) {
        this.boundingBox = new BoundingBox(boundingBox);
        this.codewords = new Codeword[(boundingBox.getMaxY() - boundingBox.getMinY()) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final BoundingBox getBoundingBox() {
        return this.boundingBox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Codeword getCodeword(int i2) {
        return this.codewords[imageRowToCodewordIndex(i2)];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Codeword getCodewordNearby(int i2) {
        Codeword codeword;
        Codeword codeword2;
        Codeword codeword3 = getCodeword(i2);
        if (codeword3 != null) {
            return codeword3;
        }
        for (int i3 = 1; i3 < 5; i3++) {
            int imageRowToCodewordIndex = imageRowToCodewordIndex(i2) - i3;
            if (imageRowToCodewordIndex >= 0 && (codeword2 = this.codewords[imageRowToCodewordIndex]) != null) {
                return codeword2;
            }
            int imageRowToCodewordIndex2 = imageRowToCodewordIndex(i2) + i3;
            Codeword[] codewordArr = this.codewords;
            if (imageRowToCodewordIndex2 < codewordArr.length && (codeword = codewordArr[imageRowToCodewordIndex2]) != null) {
                return codeword;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Codeword[] getCodewords() {
        return this.codewords;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int imageRowToCodewordIndex(int i2) {
        return i2 - this.boundingBox.getMinY();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setCodeword(int i2, Codeword codeword) {
        this.codewords[imageRowToCodewordIndex(i2)] = codeword;
    }

    public String toString() {
        Formatter formatter = new Formatter();
        int i2 = 0;
        for (Codeword codeword : this.codewords) {
            if (codeword == null) {
                formatter.format("%3d:    |   %n", Integer.valueOf(i2));
                i2++;
            } else {
                formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i2), Integer.valueOf(codeword.getRowNumber()), Integer.valueOf(codeword.getValue()));
                i2++;
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
