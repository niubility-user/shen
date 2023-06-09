package com.google.zxing.oned.rss;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.OneDReader;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public final class RSS14Reader extends AbstractRSSReader {
    private final List<Pair> possibleLeftPairs = new ArrayList();
    private final List<Pair> possibleRightPairs = new ArrayList();
    private static final int[] OUTSIDE_EVEN_TOTAL_SUBSET = {1, 10, 34, 70, 126};
    private static final int[] INSIDE_ODD_TOTAL_SUBSET = {4, 20, 48, 81};
    private static final int[] OUTSIDE_GSUM = {0, 161, R2.attr.framePosition, R2.attr.textAppearanceOverline, R2.color.C_Orange_02};
    private static final int[] INSIDE_GSUM = {0, 336, R2.attr.ijkandvrplayer_progressHeight, R2.attr.percentHeight};
    private static final int[] OUTSIDE_ODD_WIDEST = {8, 6, 4, 3, 1};
    private static final int[] INSIDE_ODD_WIDEST = {2, 4, 6, 8};
    private static final int[][] FINDER_PATTERNS = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};

    private static void addOrTally(Collection<Pair> collection, Pair pair) {
        if (pair == null) {
            return;
        }
        boolean z = false;
        Iterator<Pair> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Pair next = it.next();
            if (next.getValue() == pair.getValue()) {
                next.incrementCount();
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        collection.add(pair);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0036, code lost:
        if (r1 < 4) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x004d, code lost:
        if (r1 < 4) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004f, code lost:
        r10 = false;
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0052, code lost:
        r10 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void adjustOddEvenCounts(boolean z, int i2) throws NotFoundException {
        boolean z2;
        boolean z3;
        boolean z4;
        int count = AbstractRSSReader.count(getOddCounts());
        int count2 = AbstractRSSReader.count(getEvenCounts());
        int i3 = (count + count2) - i2;
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = (count & 1) == z;
        boolean z8 = (count2 & 1) == 1;
        if (z) {
            if (count > 12) {
                z2 = false;
                z3 = true;
            } else {
                z2 = count < 4;
                z3 = false;
            }
            if (count2 <= 12) {
            }
            z4 = true;
        } else {
            if (count > 11) {
                z2 = false;
                z3 = true;
            } else {
                z2 = count < 5;
                z3 = false;
            }
            if (count2 <= 10) {
            }
            z4 = true;
        }
        if (i3 == 1) {
            if (z7) {
                if (z8) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z6 = z2;
                z3 = true;
            } else if (!z8) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z6 = z2;
                z4 = true;
            }
        } else if (i3 == -1) {
            if (z7) {
                if (z8) {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (!z8) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z6 = z2;
                z5 = true;
            }
        } else if (i3 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            if (z7) {
                if (!z8) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (count >= count2) {
                    z6 = z2;
                    z5 = true;
                    z3 = true;
                }
                z4 = true;
            } else if (z8) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z6 = z2;
            }
        }
        if (z6) {
            if (!z3) {
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (z3) {
            AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (z5) {
            if (!z4) {
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (z4) {
            AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }

    private static boolean checkChecksum(Pair pair, Pair pair2) {
        int checksumPortion = (pair.getChecksumPortion() + (pair2.getChecksumPortion() * 16)) % 79;
        int value = (pair.getFinderPattern().getValue() * 9) + pair2.getFinderPattern().getValue();
        if (value > 72) {
            value--;
        }
        if (value > 8) {
            value--;
        }
        return checksumPortion == value;
    }

    private static Result constructResult(Pair pair, Pair pair2) {
        String valueOf = String.valueOf((pair.getValue() * 4537077) + pair2.getValue());
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - valueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(valueOf);
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int charAt = sb.charAt(i3) - '0';
            if ((i3 & 1) == 0) {
                charAt *= 3;
            }
            i2 += charAt;
        }
        int i4 = 10 - (i2 % 10);
        if (i4 == 10) {
            i4 = 0;
        }
        sb.append(i4);
        ResultPoint[] resultPoints = pair.getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = pair2.getFinderPattern().getResultPoints();
        return new Result(String.valueOf(sb.toString()), null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_14);
    }

    private DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z) throws NotFoundException {
        int[] dataCharacterCounters = getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z) {
            OneDReader.recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            OneDReader.recordPattern(bitArray, finderPattern.getStartEnd()[1] + 1, dataCharacterCounters);
            int i2 = 0;
            for (int length = dataCharacterCounters.length - 1; i2 < length; length--) {
                int i3 = dataCharacterCounters[i2];
                dataCharacterCounters[i2] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i3;
                i2++;
            }
        }
        int i4 = z ? 16 : 15;
        float count = AbstractRSSReader.count(dataCharacterCounters) / i4;
        int[] oddCounts = getOddCounts();
        int[] evenCounts = getEvenCounts();
        float[] oddRoundingErrors = getOddRoundingErrors();
        float[] evenRoundingErrors = getEvenRoundingErrors();
        for (int i5 = 0; i5 < dataCharacterCounters.length; i5++) {
            float f2 = dataCharacterCounters[i5] / count;
            int i6 = (int) (0.5f + f2);
            if (i6 < 1) {
                i6 = 1;
            } else if (i6 > 8) {
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                oddCounts[i7] = i6;
                oddRoundingErrors[i7] = f2 - i6;
            } else {
                evenCounts[i7] = i6;
                evenRoundingErrors[i7] = f2 - i6;
            }
        }
        adjustOddEvenCounts(z, i4);
        int i8 = 0;
        int i9 = 0;
        for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
            i8 = (i8 * 9) + oddCounts[length2];
            i9 += oddCounts[length2];
        }
        int i10 = 0;
        int i11 = 0;
        for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
            i10 = (i10 * 9) + evenCounts[length3];
            i11 += evenCounts[length3];
        }
        int i12 = i8 + (i10 * 3);
        if (!z) {
            if ((i11 & 1) == 0 && i11 <= 10 && i11 >= 4) {
                int i13 = (10 - i11) / 2;
                int i14 = INSIDE_ODD_WIDEST[i13];
                return new DataCharacter((RSSUtils.getRSSvalue(evenCounts, 9 - i14, false) * INSIDE_ODD_TOTAL_SUBSET[i13]) + RSSUtils.getRSSvalue(oddCounts, i14, true) + INSIDE_GSUM[i13], i12);
            }
            throw NotFoundException.getNotFoundInstance();
        } else if ((i9 & 1) == 0 && i9 <= 12 && i9 >= 4) {
            int i15 = (12 - i9) / 2;
            int i16 = OUTSIDE_ODD_WIDEST[i15];
            return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i16, false) * OUTSIDE_EVEN_TOTAL_SUBSET[i15]) + RSSUtils.getRSSvalue(evenCounts, 9 - i16, true) + OUTSIDE_GSUM[i15], i12);
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private Pair decodePair(BitArray bitArray, boolean z, int i2, Map<DecodeHintType, ?> map) {
        try {
            FinderPattern parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i2, z, findFinderPattern(bitArray, 0, z));
            ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (resultPointCallback != null) {
                float f2 = (r2[0] + r2[1]) / 2.0f;
                if (z) {
                    f2 = (bitArray.getSize() - 1) - f2;
                }
                resultPointCallback.foundPossibleResultPoint(new ResultPoint(f2, i2));
            }
            DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, true);
            DataCharacter decodeDataCharacter2 = decodeDataCharacter(bitArray, parseFoundFinderPattern, false);
            return new Pair((decodeDataCharacter.getValue() * R2.attr.ptrIsAutoDark) + decodeDataCharacter2.getValue(), decodeDataCharacter.getChecksumPortion() + (decodeDataCharacter2.getChecksumPortion() * 4), parseFoundFinderPattern);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private int[] findFinderPattern(BitArray bitArray, int i2, boolean z) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        boolean z2 = false;
        while (i2 < size) {
            z2 = !bitArray.get(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = i2;
        int i4 = 0;
        while (i2 < size) {
            if (bitArray.get(i2) ^ z2) {
                decodeFinderCounters[i4] = decodeFinderCounters[i4] + 1;
            } else {
                if (i4 != 3) {
                    i4++;
                } else if (AbstractRSSReader.isFinderPattern(decodeFinderCounters)) {
                    return new int[]{i3, i2};
                } else {
                    i3 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i4--;
                }
                decodeFinderCounters[i4] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i2, boolean z, int[] iArr) throws NotFoundException {
        int i3;
        int i4;
        boolean z2 = bitArray.get(iArr[0]);
        int i5 = iArr[0] - 1;
        while (i5 >= 0 && (bitArray.get(i5) ^ z2)) {
            i5--;
        }
        int i6 = i5 + 1;
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = iArr[0] - i6;
        int parseFinderValue = AbstractRSSReader.parseFinderValue(decodeFinderCounters, FINDER_PATTERNS);
        int i7 = iArr[1];
        if (z) {
            i3 = (bitArray.getSize() - 1) - i7;
            i4 = (bitArray.getSize() - 1) - i6;
        } else {
            i3 = i7;
            i4 = i6;
        }
        return new FinderPattern(parseFinderValue, new int[]{i6, iArr[1]}, i4, i3, i2);
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException {
        addOrTally(this.possibleLeftPairs, decodePair(bitArray, false, i2, map));
        bitArray.reverse();
        addOrTally(this.possibleRightPairs, decodePair(bitArray, true, i2, map));
        bitArray.reverse();
        int size = this.possibleLeftPairs.size();
        for (int i3 = 0; i3 < size; i3++) {
            Pair pair = this.possibleLeftPairs.get(i3);
            if (pair.getCount() > 1) {
                int size2 = this.possibleRightPairs.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    Pair pair2 = this.possibleRightPairs.get(i4);
                    if (pair2.getCount() > 1 && checkChecksum(pair, pair2)) {
                        return constructResult(pair, pair2);
                    }
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        this.possibleLeftPairs.clear();
        this.possibleRightPairs.clear();
    }
}
