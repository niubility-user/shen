package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

/* loaded from: classes12.dex */
public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException, FormatException {
        int[] rowHeights;
        if (detectionResultRowIndicatorColumn == null || (rowHeights = detectionResultRowIndicatorColumn.getRowHeights()) == null) {
            return null;
        }
        int max = getMax(rowHeights);
        int i2 = 0;
        int i3 = 0;
        for (int i4 : rowHeights) {
            i3 += max - i4;
            if (i4 > 0) {
                break;
            }
        }
        Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        for (int i5 = 0; i3 > 0 && codewords[i5] == null; i5++) {
            i3--;
        }
        for (int length = rowHeights.length - 1; length >= 0; length--) {
            i2 += max - rowHeights[length];
            if (rowHeights[length] > 0) {
                break;
            }
        }
        for (int length2 = codewords.length - 1; i2 > 0 && codewords[length2] == null; length2--) {
            i2--;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(i3, i2, detectionResultRowIndicatorColumn.isLeft());
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        int[] value = barcodeValueArr[0][1].getValue();
        int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (value.length != 0) {
            if (value[0] != barcodeColumnCount) {
                barcodeValueArr[0][1].setValue(barcodeColumnCount);
            }
        } else if (barcodeColumnCount >= 1 && barcodeColumnCount <= 928) {
            barcodeValueArr[0][1].setValue(barcodeColumnCount);
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static int adjustCodewordStartColumn(BitMatrix bitMatrix, int i2, int i3, boolean z, int i4, int i5) {
        int i6 = z ? -1 : 1;
        int i7 = i4;
        for (int i8 = 0; i8 < 2; i8++) {
            while (true) {
                if (((z && i7 >= i2) || (!z && i7 < i3)) && z == bitMatrix.get(i7, i5)) {
                    if (Math.abs(i4 - i7) > 2) {
                        return i4;
                    }
                    i7 += i6;
                }
            }
            i6 = -i6;
            z = !z;
        }
        return i7;
    }

    private static boolean checkCodewordSkew(int i2, int i3, int i4) {
        return i3 + (-2) <= i2 && i2 <= i4 + 2;
    }

    private static int correctErrors(int[] iArr, int[] iArr2, int i2) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i2 / 2) + 3) && i2 >= 0 && i2 <= 512) {
            return errorCorrection.decode(iArr, i2, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) throws FormatException {
        int rowNumber;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, detectionResult.getBarcodeRowCount(), detectionResult.getBarcodeColumnCount() + 2);
        for (int i2 = 0; i2 < barcodeValueArr.length; i2++) {
            for (int i3 = 0; i3 < barcodeValueArr[i2].length; i3++) {
                barcodeValueArr[i2][i3] = new BarcodeValue();
            }
        }
        int i4 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.getCodewords()) {
                    if (codeword != null && (rowNumber = codeword.getRowNumber()) >= 0) {
                        if (rowNumber < barcodeValueArr.length) {
                            barcodeValueArr[rowNumber][i4].setValue(codeword.getValue());
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    }
                }
            }
            i4++;
        }
        return barcodeValueArr;
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] createBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, createBarcodeMatrix);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < detectionResult.getBarcodeRowCount(); i2++) {
            int i3 = 0;
            while (i3 < detectionResult.getBarcodeColumnCount()) {
                int i4 = i3 + 1;
                int[] value = createBarcodeMatrix[i2][i4].getValue();
                int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * i2) + i3;
                if (value.length == 0) {
                    arrayList.add(Integer.valueOf(barcodeColumnCount));
                } else if (value.length == 1) {
                    iArr[barcodeColumnCount] = value[0];
                } else {
                    arrayList3.add(Integer.valueOf(barcodeColumnCount));
                    arrayList2.add(value);
                }
                i3 = i4;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size];
        for (int i5 = 0; i5 < size; i5++) {
            iArr2[i5] = (int[]) arrayList2.get(i5);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int i2, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws FormatException, ChecksumException {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        int i3 = 100;
        while (true) {
            int i4 = i3 - 1;
            if (i3 > 0) {
                for (int i5 = 0; i5 < length; i5++) {
                    iArr[iArr3[i5]] = iArr4[i5][iArr5[i5]];
                }
                try {
                    return decodeCodewords(iArr, i2, iArr2);
                } catch (ChecksumException unused) {
                    if (length == 0) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    int i6 = 0;
                    while (true) {
                        if (i6 >= length) {
                            break;
                        } else if (iArr5[i6] < iArr4[i6].length - 1) {
                            iArr5[i6] = iArr5[i6] + 1;
                            break;
                        } else {
                            iArr5[i6] = 0;
                            if (i6 == length - 1) {
                                throw ChecksumException.getChecksumInstance();
                            }
                            i6++;
                        }
                    }
                    i3 = i4;
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i2, int i3) throws NotFoundException, FormatException, ChecksumException {
        DetectionResultColumn detectionResultRowIndicatorColumn;
        int i4;
        int i5;
        int i6;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2 = null;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn3 = null;
        DetectionResult detectionResult = null;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        for (int i7 = 0; i7 < 2; i7++) {
            if (resultPoint != null) {
                detectionResultRowIndicatorColumn2 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i2, i3);
            }
            if (resultPoint3 != null) {
                detectionResultRowIndicatorColumn3 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i2, i3);
            }
            detectionResult = merge(detectionResultRowIndicatorColumn2, detectionResultRowIndicatorColumn3);
            if (detectionResult != null) {
                if (i7 == 0 && detectionResult.getBoundingBox() != null && (detectionResult.getBoundingBox().getMinY() < boundingBox.getMinY() || detectionResult.getBoundingBox().getMaxY() > boundingBox.getMaxY())) {
                    boundingBox = detectionResult.getBoundingBox();
                } else {
                    detectionResult.setBoundingBox(boundingBox);
                    break;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        int barcodeColumnCount = detectionResult.getBarcodeColumnCount() + 1;
        detectionResult.setDetectionResultColumn(0, detectionResultRowIndicatorColumn2);
        detectionResult.setDetectionResultColumn(barcodeColumnCount, detectionResultRowIndicatorColumn3);
        boolean z = detectionResultRowIndicatorColumn2 != null;
        int i8 = i2;
        int i9 = i3;
        for (int i10 = 1; i10 <= barcodeColumnCount; i10++) {
            int i11 = z ? i10 : barcodeColumnCount - i10;
            if (detectionResult.getDetectionResultColumn(i11) == null) {
                if (i11 != 0 && i11 != barcodeColumnCount) {
                    detectionResultRowIndicatorColumn = new DetectionResultColumn(boundingBox);
                } else {
                    detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, i11 == 0);
                }
                detectionResult.setDetectionResultColumn(i11, detectionResultRowIndicatorColumn);
                int i12 = -1;
                int minY = boundingBox.getMinY();
                int i13 = -1;
                while (minY <= boundingBox.getMaxY()) {
                    int startColumn = getStartColumn(detectionResult, i11, minY, z);
                    if (startColumn >= 0 && startColumn <= boundingBox.getMaxX()) {
                        i4 = startColumn;
                    } else if (i13 == i12) {
                        i5 = i13;
                        i6 = minY;
                        i13 = i5;
                        minY = i6 + 1;
                        i12 = -1;
                    } else {
                        i4 = i13;
                    }
                    i5 = i13;
                    int i14 = minY;
                    Codeword detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, i4, i14, i8, i9);
                    i6 = i14;
                    if (detectCodeword != null) {
                        detectionResultRowIndicatorColumn.setCodeword(i6, detectCodeword);
                        i8 = Math.min(i8, detectCodeword.getWidth());
                        i9 = Math.max(i9, detectCodeword.getWidth());
                        i13 = i4;
                        minY = i6 + 1;
                        i12 = -1;
                    }
                    i13 = i5;
                    minY = i6 + 1;
                    i12 = -1;
                }
            }
        }
        return createDecoderResult(detectionResult);
    }

    private static DecoderResult decodeCodewords(int[] iArr, int i2, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length != 0) {
            int i3 = 1 << (i2 + 1);
            int correctErrors = correctErrors(iArr, iArr2, i3);
            verifyCodewordCount(iArr, i3);
            DecoderResult decode = DecodedBitStreamParser.decode(iArr, String.valueOf(i2));
            decode.setErrorsCorrected(Integer.valueOf(correctErrors));
            decode.setErasures(Integer.valueOf(iArr2.length));
            return decode;
        }
        throw FormatException.getFormatInstance();
    }

    private static Codeword detectCodeword(BitMatrix bitMatrix, int i2, int i3, boolean z, int i4, int i5, int i6, int i7) {
        int i8;
        int decodedValue;
        int codeword;
        int adjustCodewordStartColumn = adjustCodewordStartColumn(bitMatrix, i2, i3, z, i4, i5);
        int[] moduleBitCount = getModuleBitCount(bitMatrix, i2, i3, z, adjustCodewordStartColumn, i5);
        if (moduleBitCount == null) {
            return null;
        }
        int bitCountSum = PDF417Common.getBitCountSum(moduleBitCount);
        if (z) {
            i8 = adjustCodewordStartColumn + bitCountSum;
        } else {
            for (int i9 = 0; i9 < moduleBitCount.length / 2; i9++) {
                int i10 = moduleBitCount[i9];
                moduleBitCount[i9] = moduleBitCount[(moduleBitCount.length - 1) - i9];
                moduleBitCount[(moduleBitCount.length - 1) - i9] = i10;
            }
            adjustCodewordStartColumn -= bitCountSum;
            i8 = adjustCodewordStartColumn;
        }
        if (checkCodewordSkew(bitCountSum, i6, i7) && (codeword = PDF417Common.getCodeword((decodedValue = PDF417CodewordDecoder.getDecodedValue(moduleBitCount)))) != -1) {
            return new Codeword(adjustCodewordStartColumn, i8, getCodewordBucketNumber(decodedValue), codeword);
        }
        return null;
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata;
        BarcodeMetadata barcodeMetadata2;
        if (detectionResultRowIndicatorColumn == null || (barcodeMetadata = detectionResultRowIndicatorColumn.getBarcodeMetadata()) == null) {
            if (detectionResultRowIndicatorColumn2 == null) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.getBarcodeMetadata();
        } else if (detectionResultRowIndicatorColumn2 == null || (barcodeMetadata2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata()) == null || barcodeMetadata.getColumnCount() == barcodeMetadata2.getColumnCount() || barcodeMetadata.getErrorCorrectionLevel() == barcodeMetadata2.getErrorCorrectionLevel() || barcodeMetadata.getRowCount() == barcodeMetadata2.getRowCount()) {
            return barcodeMetadata;
        } else {
            return null;
        }
    }

    private static int[] getBitCountForCodeword(int i2) {
        int[] iArr = new int[8];
        int i3 = 0;
        int i4 = 7;
        while (true) {
            int i5 = i2 & 1;
            if (i5 != i3) {
                i4--;
                if (i4 < 0) {
                    return iArr;
                }
                i3 = i5;
            }
            iArr[i4] = iArr[i4] + 1;
            i2 >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int i2) {
        return getCodewordBucketNumber(getBitCountForCodeword(i2));
    }

    private static int getMax(int[] iArr) {
        int i2 = -1;
        for (int i3 : iArr) {
            i2 = Math.max(i2, i3);
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002a, code lost:
        if (r10 == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002c, code lost:
        if (r11 == r9) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002e, code lost:
        if (r10 != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
        if (r11 != r8) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0033, code lost:
        if (r4 != 7) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0036, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:?, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] getModuleBitCount(com.google.zxing.common.BitMatrix r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r10 == 0) goto L9
            r3 = 1
            goto La
        L9:
            r3 = -1
        La:
            r4 = 0
            r5 = r10
        Lc:
            if (r10 == 0) goto L10
            if (r11 < r9) goto L14
        L10:
            if (r10 != 0) goto L28
            if (r11 < r8) goto L28
        L14:
            if (r4 >= r0) goto L28
            boolean r6 = r7.get(r11, r12)
            if (r6 != r5) goto L23
            r6 = r1[r4]
            int r6 = r6 + r2
            r1[r4] = r6
            int r11 = r11 + r3
            goto Lc
        L23:
            int r4 = r4 + 1
            r5 = r5 ^ 1
            goto Lc
        L28:
            if (r4 == r0) goto L38
            if (r10 == 0) goto L2e
            if (r11 == r9) goto L32
        L2e:
            if (r10 != 0) goto L36
            if (r11 != r8) goto L36
        L32:
            r7 = 7
            if (r4 != r7) goto L36
            goto L38
        L36:
            r7 = 0
            return r7
        L38:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.getModuleBitCount(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int[]");
    }

    private static int getNumberOfECCodeWords(int i2) {
        return 2 << i2;
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int i2, int i3) {
        int endX;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z);
        int i4 = 0;
        while (i4 < 2) {
            int i5 = i4 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            for (int y = (int) resultPoint.getY(); y <= boundingBox.getMaxY() && y >= boundingBox.getMinY(); y += i5) {
                Codeword detectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, i2, i3);
                if (detectCodeword != null) {
                    detectionResultRowIndicatorColumn.setCodeword(y, detectCodeword);
                    if (z) {
                        endX = detectCodeword.getStartX();
                    } else {
                        endX = detectCodeword.getEndX();
                    }
                    x = endX;
                }
            }
            i4++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static int getStartColumn(DetectionResult detectionResult, int i2, int i3, boolean z) {
        int i4 = z ? 1 : -1;
        int i5 = i2 - i4;
        Codeword codeword = isValidBarcodeColumn(detectionResult, i5) ? detectionResult.getDetectionResultColumn(i5).getCodeword(i3) : null;
        if (codeword != null) {
            return z ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(i2).getCodewordNearby(i3);
        if (codewordNearby != null) {
            return z ? codewordNearby.getStartX() : codewordNearby.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, i5)) {
            codewordNearby = detectionResult.getDetectionResultColumn(i5).getCodewordNearby(i3);
        }
        if (codewordNearby != null) {
            return z ? codewordNearby.getEndX() : codewordNearby.getStartX();
        }
        int i6 = 0;
        while (true) {
            i2 -= i4;
            if (isValidBarcodeColumn(detectionResult, i2)) {
                for (Codeword codeword2 : detectionResult.getDetectionResultColumn(i2).getCodewords()) {
                    if (codeword2 != null) {
                        return (z ? codeword2.getEndX() : codeword2.getStartX()) + (i4 * i6 * (codeword2.getEndX() - codeword2.getStartX()));
                    }
                }
                i6++;
            } else {
                BoundingBox boundingBox = detectionResult.getBoundingBox();
                return z ? boundingBox.getMinX() : boundingBox.getMaxX();
            }
        }
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int i2) {
        return i2 >= 0 && i2 <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException, FormatException {
        BarcodeMetadata barcodeMetadata;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        for (int i2 = 0; i2 < barcodeValueArr.length; i2++) {
            formatter.format("Row %2d: ", Integer.valueOf(i2));
            for (int i3 = 0; i3 < barcodeValueArr[i2].length; i3++) {
                BarcodeValue barcodeValue = barcodeValueArr[i2][i3];
                if (barcodeValue.getValue().length == 0) {
                    formatter.format("        ", null);
                } else {
                    formatter.format("%4d(%2d)", Integer.valueOf(barcodeValue.getValue()[0]), barcodeValue.getConfidence(barcodeValue.getValue()[0]));
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    private static void verifyCodewordCount(int[] iArr, int i2) throws FormatException {
        if (iArr.length >= 4) {
            int i3 = iArr[0];
            if (i3 > iArr.length) {
                throw FormatException.getFormatInstance();
            }
            if (i3 == 0) {
                if (i2 < iArr.length) {
                    iArr[0] = iArr.length - i2;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
            return;
        }
        throw FormatException.getFormatInstance();
    }

    private static int getCodewordBucketNumber(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }
}
