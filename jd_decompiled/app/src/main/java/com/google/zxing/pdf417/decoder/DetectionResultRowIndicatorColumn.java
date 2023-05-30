package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.ResultPoint;

/* loaded from: classes12.dex */
final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {
    private final boolean isLeft;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DetectionResultRowIndicatorColumn(BoundingBox boundingBox, boolean z) {
        super(boundingBox);
        this.isLeft = z;
    }

    private void removeIncorrectCodewords(Codeword[] codewordArr, BarcodeMetadata barcodeMetadata) {
        for (int i2 = 0; i2 < codewordArr.length; i2++) {
            Codeword codeword = codewordArr[i2];
            if (codewordArr[i2] != null) {
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (rowNumber > barcodeMetadata.getRowCount()) {
                    codewordArr[i2] = null;
                } else {
                    if (!this.isLeft) {
                        rowNumber += 2;
                    }
                    int i3 = rowNumber % 3;
                    if (i3 != 0) {
                        if (i3 != 1) {
                            if (i3 == 2 && value + 1 != barcodeMetadata.getColumnCount()) {
                                codewordArr[i2] = null;
                            }
                        } else if (value / 3 != barcodeMetadata.getErrorCorrectionLevel() || value % 3 != barcodeMetadata.getRowCountLowerPart()) {
                            codewordArr[i2] = null;
                        }
                    } else if ((value * 3) + 1 != barcodeMetadata.getRowCountUpperPart()) {
                        codewordArr[i2] = null;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int adjustCompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        Codeword[] codewords = getCodewords();
        setRowNumbers();
        removeIncorrectCodewords(codewords, barcodeMetadata);
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int imageRowToCodewordIndex = imageRowToCodewordIndex((int) topLeft.getY());
        int imageRowToCodewordIndex2 = imageRowToCodewordIndex((int) bottomLeft.getY());
        float rowCount = (imageRowToCodewordIndex2 - imageRowToCodewordIndex) / barcodeMetadata.getRowCount();
        int i2 = -1;
        int i3 = 0;
        int i4 = 1;
        while (imageRowToCodewordIndex < imageRowToCodewordIndex2) {
            if (codewords[imageRowToCodewordIndex] != null) {
                Codeword codeword = codewords[imageRowToCodewordIndex];
                int rowNumber = codeword.getRowNumber() - i2;
                if (rowNumber == 0) {
                    i3++;
                } else {
                    if (rowNumber == 1) {
                        i4 = Math.max(i4, i3);
                        i2 = codeword.getRowNumber();
                    } else if (rowNumber < 0 || codeword.getRowNumber() >= barcodeMetadata.getRowCount() || rowNumber > imageRowToCodewordIndex) {
                        codewords[imageRowToCodewordIndex] = null;
                    } else {
                        if (i4 > 2) {
                            rowNumber *= i4 - 2;
                        }
                        boolean z = rowNumber >= imageRowToCodewordIndex;
                        for (int i5 = 1; i5 <= rowNumber && !z; i5++) {
                            z = codewords[imageRowToCodewordIndex - i5] != null;
                        }
                        if (z) {
                            codewords[imageRowToCodewordIndex] = null;
                        } else {
                            i2 = codeword.getRowNumber();
                        }
                    }
                    i3 = 1;
                }
            }
            imageRowToCodewordIndex++;
        }
        double d = rowCount;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    int adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        BoundingBox boundingBox = getBoundingBox();
        ResultPoint topLeft = this.isLeft ? boundingBox.getTopLeft() : boundingBox.getTopRight();
        ResultPoint bottomLeft = this.isLeft ? boundingBox.getBottomLeft() : boundingBox.getBottomRight();
        int imageRowToCodewordIndex = imageRowToCodewordIndex((int) topLeft.getY());
        int imageRowToCodewordIndex2 = imageRowToCodewordIndex((int) bottomLeft.getY());
        float rowCount = (imageRowToCodewordIndex2 - imageRowToCodewordIndex) / barcodeMetadata.getRowCount();
        Codeword[] codewords = getCodewords();
        int i2 = -1;
        int i3 = 0;
        int i4 = 1;
        while (imageRowToCodewordIndex < imageRowToCodewordIndex2) {
            if (codewords[imageRowToCodewordIndex] != null) {
                Codeword codeword = codewords[imageRowToCodewordIndex];
                codeword.setRowNumberAsRowIndicatorColumn();
                int rowNumber = codeword.getRowNumber() - i2;
                if (rowNumber == 0) {
                    i3++;
                } else {
                    if (rowNumber == 1) {
                        i4 = Math.max(i4, i3);
                        i2 = codeword.getRowNumber();
                    } else if (codeword.getRowNumber() >= barcodeMetadata.getRowCount()) {
                        codewords[imageRowToCodewordIndex] = null;
                    } else {
                        i2 = codeword.getRowNumber();
                    }
                    i3 = 1;
                }
            }
            imageRowToCodewordIndex++;
        }
        double d = rowCount;
        Double.isNaN(d);
        return (int) (d + 0.5d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarcodeMetadata getBarcodeMetadata() {
        Codeword[] codewords = getCodewords();
        BarcodeValue barcodeValue = new BarcodeValue();
        BarcodeValue barcodeValue2 = new BarcodeValue();
        BarcodeValue barcodeValue3 = new BarcodeValue();
        BarcodeValue barcodeValue4 = new BarcodeValue();
        for (Codeword codeword : codewords) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
                int value = codeword.getValue() % 30;
                int rowNumber = codeword.getRowNumber();
                if (!this.isLeft) {
                    rowNumber += 2;
                }
                int i2 = rowNumber % 3;
                if (i2 == 0) {
                    barcodeValue2.setValue((value * 3) + 1);
                } else if (i2 == 1) {
                    barcodeValue4.setValue(value / 3);
                    barcodeValue3.setValue(value % 3);
                } else if (i2 == 2) {
                    barcodeValue.setValue(value + 1);
                }
            }
        }
        if (barcodeValue.getValue().length == 0 || barcodeValue2.getValue().length == 0 || barcodeValue3.getValue().length == 0 || barcodeValue4.getValue().length == 0 || barcodeValue.getValue()[0] < 1 || barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] < 3 || barcodeValue2.getValue()[0] + barcodeValue3.getValue()[0] > 90) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeValue.getValue()[0], barcodeValue2.getValue()[0], barcodeValue3.getValue()[0], barcodeValue4.getValue()[0]);
        removeIncorrectCodewords(codewords, barcodeMetadata);
        return barcodeMetadata;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] getRowHeights() throws FormatException {
        BarcodeMetadata barcodeMetadata = getBarcodeMetadata();
        if (barcodeMetadata == null) {
            return null;
        }
        adjustIncompleteIndicatorColumnRowNumbers(barcodeMetadata);
        int rowCount = barcodeMetadata.getRowCount();
        int[] iArr = new int[rowCount];
        for (Codeword codeword : getCodewords()) {
            if (codeword != null) {
                int rowNumber = codeword.getRowNumber();
                if (rowNumber < rowCount) {
                    iArr[rowNumber] = iArr[rowNumber] + 1;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLeft() {
        return this.isLeft;
    }

    void setRowNumbers() {
        for (Codeword codeword : getCodewords()) {
            if (codeword != null) {
                codeword.setRowNumberAsRowIndicatorColumn();
            }
        }
    }

    @Override // com.google.zxing.pdf417.decoder.DetectionResultColumn
    public String toString() {
        return "IsLeft: " + this.isLeft + '\n' + super.toString();
    }
}
