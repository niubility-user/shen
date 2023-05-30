package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes12.dex */
public abstract class OneDReader implements Reader {
    /* JADX WARN: Removed duplicated region for block: B:41:0x007d A[Catch: ReaderException -> 0x00c6, TryCatch #0 {ReaderException -> 0x00c6, blocks: (B:39:0x0077, B:41:0x007d, B:43:0x008e), top: B:63:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00c5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Result doDecode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        OneDReader oneDReader;
        Map<DecodeHintType, ?> map2;
        int i2;
        EnumMap enumMap = map;
        int width = binaryBitmap.getWidth();
        int height = binaryBitmap.getHeight();
        BitArray bitArray = new BitArray(width);
        int i3 = height >> 1;
        char c2 = 0;
        int i4 = 1;
        boolean z = enumMap != null && enumMap.containsKey(DecodeHintType.TRY_HARDER);
        int max = Math.max(1, height >> (z ? 8 : 5));
        int i5 = z ? height : 15;
        int i6 = 0;
        while (i6 < i5) {
            int i7 = i6 + 1;
            int i8 = i7 / 2;
            if (!((i6 & 1) == 0)) {
                i8 = -i8;
            }
            int i9 = (i8 * max) + i3;
            if (i9 < 0 || i9 >= height) {
                break;
            }
            try {
                bitArray = binaryBitmap.getBlackRow(i9, bitArray);
                int i10 = 0;
                while (i10 < 2) {
                    try {
                        if (i10 == i4) {
                            bitArray.reverse();
                            if (enumMap != null) {
                                DecodeHintType decodeHintType = DecodeHintType.NEED_RESULT_POINT_CALLBACK;
                                if (enumMap.containsKey(decodeHintType)) {
                                    EnumMap enumMap2 = new EnumMap(DecodeHintType.class);
                                    enumMap2.putAll(enumMap);
                                    enumMap2.remove(decodeHintType);
                                    oneDReader = this;
                                    enumMap = enumMap2;
                                    Result decodeRow = oneDReader.decodeRow(i9, bitArray, enumMap);
                                    if (i10 != i4) {
                                        decodeRow.putMetadata(ResultMetadataType.ORIENTATION, 180);
                                        ResultPoint[] resultPoints = decodeRow.getResultPoints();
                                        if (resultPoints != null) {
                                            map2 = enumMap;
                                            float f2 = width;
                                            try {
                                                i2 = width;
                                            } catch (ReaderException unused) {
                                                i2 = width;
                                                i10++;
                                                enumMap = map2;
                                                width = i2;
                                                c2 = 0;
                                                i4 = 1;
                                            }
                                            try {
                                                resultPoints[0] = new ResultPoint((f2 - resultPoints[c2].getX()) - 1.0f, resultPoints[c2].getY());
                                            } catch (ReaderException unused2) {
                                                i10++;
                                                enumMap = map2;
                                                width = i2;
                                                c2 = 0;
                                                i4 = 1;
                                            }
                                            try {
                                                resultPoints[1] = new ResultPoint((f2 - resultPoints[1].getX()) - 1.0f, resultPoints[1].getY());
                                            } catch (ReaderException unused3) {
                                                continue;
                                                i10++;
                                                enumMap = map2;
                                                width = i2;
                                                c2 = 0;
                                                i4 = 1;
                                            }
                                        }
                                    }
                                    return decodeRow;
                                }
                            }
                        }
                        Result decodeRow2 = oneDReader.decodeRow(i9, bitArray, enumMap);
                        if (i10 != i4) {
                        }
                        return decodeRow2;
                    } catch (ReaderException unused4) {
                        map2 = enumMap;
                    }
                    oneDReader = this;
                }
            } catch (NotFoundException unused5) {
            }
            i6 = i7;
            width = width;
            c2 = 0;
            i4 = 1;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float patternMatchVariance(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = i2;
        float f4 = f3 / i3;
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            float f7 = iArr2[i5] * f4;
            float f8 = iArr[i5];
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void recordPattern(BitArray bitArray, int i2, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i3 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int size = bitArray.getSize();
        if (i2 < size) {
            boolean z = !bitArray.get(i2);
            while (i2 < size) {
                if (bitArray.get(i2) ^ z) {
                    iArr[i3] = iArr[i3] + 1;
                } else {
                    i3++;
                    if (i3 == length) {
                        break;
                    }
                    iArr[i3] = 1;
                    z = !z;
                }
                i2++;
            }
            if (i3 != length) {
                if (i3 != length - 1 || i2 != size) {
                    throw NotFoundException.getNotFoundInstance();
                }
                return;
            }
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void recordPatternInReverse(BitArray bitArray, int i2, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean z = bitArray.get(i2);
        while (i2 > 0 && length >= 0) {
            i2--;
            if (bitArray.get(i2) != z) {
                length--;
                z = !z;
            }
        }
        if (length < 0) {
            recordPattern(bitArray, i2 + 1, iArr);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    public abstract Result decodeRow(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    @Override // com.google.zxing.Reader
    public void reset() {
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return doDecode(binaryBitmap, map);
        } catch (NotFoundException e2) {
            if ((map != null && map.containsKey(DecodeHintType.TRY_HARDER)) && binaryBitmap.isRotateSupported()) {
                BinaryBitmap rotateCounterClockwise = binaryBitmap.rotateCounterClockwise();
                Result doDecode = doDecode(rotateCounterClockwise, map);
                Map<ResultMetadataType, Object> resultMetadata = doDecode.getResultMetadata();
                int i2 = 270;
                if (resultMetadata != null) {
                    ResultMetadataType resultMetadataType = ResultMetadataType.ORIENTATION;
                    if (resultMetadata.containsKey(resultMetadataType)) {
                        i2 = (((Integer) resultMetadata.get(resultMetadataType)).intValue() + 270) % R2.attr.additionBottom;
                    }
                }
                doDecode.putMetadata(ResultMetadataType.ORIENTATION, Integer.valueOf(i2));
                ResultPoint[] resultPoints = doDecode.getResultPoints();
                if (resultPoints != null) {
                    int height = rotateCounterClockwise.getHeight();
                    for (int i3 = 0; i3 < resultPoints.length; i3++) {
                        resultPoints[i3] = new ResultPoint((height - resultPoints[i3].getY()) - 1.0f, resultPoints[i3].getX());
                    }
                }
                return doDecode;
            }
            throw e2;
        }
    }
}
