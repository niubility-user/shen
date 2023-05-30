package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes12.dex */
public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int decodeCode(BitArray bitArray, int[] iArr, int i2) throws NotFoundException {
        OneDReader.recordPattern(bitArray, i2, iArr);
        float f2 = MAX_AVG_VARIANCE;
        int i3 = -1;
        int i4 = 0;
        while (true) {
            int[][] iArr2 = CODE_PATTERNS;
            if (i4 >= iArr2.length) {
                break;
            }
            float patternMatchVariance = OneDReader.patternMatchVariance(iArr, iArr2[i4], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < f2) {
                i3 = i4;
                f2 = patternMatchVariance;
            }
            i4++;
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int i2 = nextSet;
        boolean z = false;
        int i3 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 == 5) {
                    float f2 = MAX_AVG_VARIANCE;
                    int i4 = -1;
                    for (int i5 = 103; i5 <= 105; i5++) {
                        float patternMatchVariance = OneDReader.patternMatchVariance(iArr, CODE_PATTERNS[i5], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < f2) {
                            i4 = i5;
                            f2 = patternMatchVariance;
                        }
                    }
                    if (i4 >= 0 && bitArray.isRange(Math.max(0, i2 - ((nextSet - i2) / 2)), i2, false)) {
                        return new int[]{i2, nextSet, i4};
                    }
                    i2 += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, 4);
                    iArr[4] = 0;
                    iArr[5] = 0;
                    i3--;
                } else {
                    i3++;
                }
                iArr[i3] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARN: Removed duplicated region for block: B:74:0x0124 A[FALL_THROUGH, PHI: r17
      0x0124: PHI (r17v8 boolean) = 
      (r17v5 boolean)
      (r17v5 boolean)
      (r17v5 boolean)
      (r17v5 boolean)
      (r17v13 boolean)
      (r17v13 boolean)
      (r17v13 boolean)
      (r17v13 boolean)
     binds: [B:67:0x010f, B:69:0x0113, B:73:0x011f, B:72:0x011b, B:43:0x00c2, B:45:0x00c7, B:49:0x00d3, B:48:0x00cf] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x013d A[PHI: r17
      0x013d: PHI (r17v9 boolean) = (r17v5 boolean), (r17v13 boolean) binds: [B:67:0x010f, B:43:0x00c2] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.google.zxing.oned.OneDReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Result decodeRow(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException, ChecksumException {
        char c2;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        boolean z5 = map != null && map.containsKey(DecodeHintType.ASSUME_GS1);
        int[] findStartPattern = findStartPattern(bitArray);
        int i3 = findStartPattern[2];
        ArrayList arrayList = new ArrayList(20);
        arrayList.add(Byte.valueOf((byte) i3));
        switch (i3) {
            case 103:
                c2 = 'e';
                break;
            case 104:
                c2 = 'd';
                break;
            case 105:
                c2 = 'c';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        StringBuilder sb = new StringBuilder(20);
        int i4 = 6;
        int[] iArr = new int[6];
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = true;
        boolean z9 = false;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        char c3 = c2;
        int i8 = findStartPattern[0];
        int i9 = findStartPattern[1];
        char c4 = c3;
        while (!z7) {
            int decodeCode = decodeCode(bitArray, iArr, i9);
            arrayList.add(Byte.valueOf((byte) decodeCode));
            if (decodeCode != 106) {
                z8 = true;
            }
            if (decodeCode != 106) {
                i6++;
                i3 += i6 * decodeCode;
            }
            int i10 = i9;
            for (int i11 = 0; i11 < i4; i11++) {
                i10 += iArr[i11];
            }
            switch (decodeCode) {
                case 103:
                case 104:
                case 105:
                    throw FormatException.getFormatInstance();
                default:
                    switch (c4) {
                        case 'c':
                            if (decodeCode >= 100) {
                                if (decodeCode != 106) {
                                    z8 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 100:
                                            z = false;
                                            c4 = 'd';
                                            break;
                                        case 101:
                                            z = false;
                                            c4 = 'e';
                                            break;
                                        case 102:
                                            if (z5) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                } else {
                                                    sb.append((char) 29);
                                                }
                                            }
                                        default:
                                            z = false;
                                            break;
                                    }
                                } else {
                                    z = false;
                                    z7 = true;
                                    break;
                                }
                            } else {
                                if (decodeCode < 10) {
                                    sb.append('0');
                                }
                                sb.append(decodeCode);
                            }
                            z = false;
                        case 'd':
                            if (decodeCode < 96) {
                                if (z6 == z4) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                                z = false;
                                z6 = false;
                                break;
                            } else {
                                if (decodeCode != 106) {
                                    z8 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 98:
                                            z2 = z4;
                                            z3 = true;
                                            c4 = 'e';
                                            break;
                                        case 99:
                                            z2 = z4;
                                            z3 = false;
                                            c4 = 'c';
                                            break;
                                        case 100:
                                            if (z4 || !z6) {
                                                if (z4 && z6) {
                                                    z3 = false;
                                                    z2 = false;
                                                }
                                                z2 = z4;
                                                z3 = false;
                                                z6 = true;
                                                break;
                                            } else {
                                                z3 = false;
                                                z2 = true;
                                            }
                                            z6 = false;
                                            break;
                                        case 101:
                                            z2 = z4;
                                            z3 = false;
                                            c4 = 'e';
                                            break;
                                        case 102:
                                            if (z5) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                } else {
                                                    sb.append((char) 29);
                                                }
                                            }
                                        default:
                                            z2 = z4;
                                            z3 = false;
                                            break;
                                    }
                                    boolean z10 = z2;
                                    z = z3;
                                    z4 = z10;
                                    break;
                                }
                                z2 = z4;
                                z3 = false;
                                z7 = true;
                                boolean z102 = z2;
                                z = z3;
                                z4 = z102;
                            }
                        case 'e':
                            if (decodeCode < 64) {
                                if (z6 == z4) {
                                    sb.append((char) (decodeCode + 32));
                                } else {
                                    sb.append((char) (decodeCode + 32 + 128));
                                }
                            } else if (decodeCode >= 96) {
                                if (decodeCode != 106) {
                                    z8 = false;
                                }
                                if (decodeCode != 106) {
                                    switch (decodeCode) {
                                        case 98:
                                            z2 = z4;
                                            z3 = true;
                                            c4 = 'd';
                                            break;
                                        case 100:
                                            z2 = z4;
                                            z3 = false;
                                            c4 = 'd';
                                            break;
                                        case 101:
                                            if (z4 || !z6) {
                                                if (z4 && z6) {
                                                    z3 = false;
                                                    z2 = false;
                                                }
                                                z2 = z4;
                                                z3 = false;
                                                z6 = true;
                                                break;
                                            } else {
                                                z3 = false;
                                                z2 = true;
                                            }
                                            z6 = false;
                                            break;
                                        case 102:
                                            if (z5) {
                                                if (sb.length() == 0) {
                                                    sb.append("]C1");
                                                } else {
                                                    sb.append((char) 29);
                                                }
                                            }
                                    }
                                    boolean z1022 = z2;
                                    z = z3;
                                    z4 = z1022;
                                    break;
                                }
                                z2 = z4;
                                z3 = false;
                                z7 = true;
                                boolean z10222 = z2;
                                z = z3;
                                z4 = z10222;
                            } else if (z6 == z4) {
                                sb.append((char) (decodeCode - 64));
                            } else {
                                sb.append((char) (decodeCode + 64));
                            }
                            z = false;
                            z6 = false;
                            break;
                        default:
                            z = false;
                            break;
                    }
                    if (z9) {
                        c4 = c4 == 'e' ? 'd' : 'e';
                    }
                    z9 = z;
                    i4 = 6;
                    i8 = i9;
                    i9 = i10;
                    i7 = i5;
                    i5 = decodeCode;
            }
        }
        int i12 = i9 - i8;
        int nextUnset = bitArray.getNextUnset(i9);
        if (bitArray.isRange(nextUnset, Math.min(bitArray.getSize(), ((nextUnset - i8) / 2) + nextUnset), false)) {
            int i13 = i7;
            if ((i3 - (i6 * i13)) % 103 == i13) {
                int length = sb.length();
                if (length != 0) {
                    if (length > 0 && z8) {
                        if (c4 == 'c') {
                            sb.delete(length - 2, length);
                        } else {
                            sb.delete(length - 1, length);
                        }
                    }
                    float f2 = (findStartPattern[1] + findStartPattern[0]) / 2.0f;
                    float f3 = i8 + (i12 / 2.0f);
                    int size = arrayList.size();
                    byte[] bArr = new byte[size];
                    for (int i14 = 0; i14 < size; i14++) {
                        bArr[i14] = ((Byte) arrayList.get(i14)).byteValue();
                    }
                    float f4 = i2;
                    return new Result(sb.toString(), bArr, new ResultPoint[]{new ResultPoint(f2, f4), new ResultPoint(f3, f4)}, BarcodeFormat.CODE_128);
                }
                throw NotFoundException.getNotFoundInstance();
            }
            throw ChecksumException.getChecksumInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
