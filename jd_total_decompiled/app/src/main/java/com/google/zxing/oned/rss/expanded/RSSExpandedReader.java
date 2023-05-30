package com.google.zxing.oned.rss.expanded;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.OneDReader;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public final class RSSExpandedReader extends AbstractRSSReader {
    private static final int FINDER_PAT_A = 0;
    private static final int FINDER_PAT_B = 1;
    private static final int FINDER_PAT_C = 2;
    private static final int FINDER_PAT_D = 3;
    private static final int FINDER_PAT_E = 4;
    private static final int FINDER_PAT_F = 5;
    private static final int MAX_PAIRS = 11;
    private final List<ExpandedPair> pairs = new ArrayList(11);
    private final List<ExpandedRow> rows = new ArrayList();
    private final int[] startEnd = new int[2];
    private boolean startFromEven = false;
    private static final int[] SYMBOL_WIDEST = {7, 5, 4, 3, 1};
    private static final int[] EVEN_TOTAL_SUBSET = {4, 20, 52, 104, 204};
    private static final int[] GSUM = {0, R2.attr.actionTextColorAlpha, R2.attr.maxWidth, R2.color.UN_Link, R2.color.jdpay_widget_primary_dark};
    private static final int[][] FINDER_PATTERNS = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] WEIGHTS = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, R2.anim.manto_translate_dialog_out, 7, 21, 63}, new int[]{R2.anim.slide_in_from_left, R2.anim.message_center_dialog_out, 13, 39, 117, 140, 209, 205}, new int[]{R2.anim.slide_out_from_left, R2.anim.out_to_right, 49, R2.anim.miaosha_dropdown_out, 19, 57, R2.anim.pop_right_bottom_out, 91}, new int[]{62, R2.anim.slide_down, R2.anim.lib_cashier_sdk_fragment_right_out, R2.anim.slide_out_to_top, 169, 85, 44, 132}, new int[]{R2.anim.settlement_dialog_right_exit, 133, R2.anim.slide_in_from_bottom_medium_time, R2.anim.manto_translate_dialog_in, 4, 12, 36, 108}, new int[]{113, 128, R2.anim.pop_right_top_out, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, R2.anim.pickerview_dialog_scale_in, 52, R2.anim.out_to_bottom_slow}, new int[]{46, R2.anim.lib_cashier_sdk_pop_out_animation_bottom, 203, R2.anim.slide_in_from_bottom, R2.anim.live_pop_rotate_amin, 206, R2.anim.slide_out_to_right, R2.anim.pop_left_bottom_out}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, R2.anim.popup_center_enter, 106, 107, 110, 119, R2.anim.miaosha_dropdown_in}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, R2.anim.lib_cashier_sdk_pop_in_animation_bottom, 200, 178, 112, 125, R2.anim.pop_in}, new int[]{70, 210, 208, 202, R2.anim.settlement_dialog_right_enter, 130, R2.anim.popwin_anim_alpha_out, 115}, new int[]{134, R2.anim.slide_in_from_top_medium_time, 151, 31, 93, 68, 204, R2.anim.slide_in_from_top}, new int[]{R2.anim.mtrl_bottom_sheet_slide_in, 22, 66, R2.anim.slide_out_to_top_medium_time, R2.anim.pop_right_top_in, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, R2.anim.mtrl_bottom_sheet_slide_out, 25, 75, 14, 42, 126, R2.anim.pop_left_top_in}, new int[]{79, 26, 78, 23, 69, 207, R2.anim.slide_out_top_dongdong, R2.anim.popup_anim_feedback}, new int[]{103, 98, 83, 38, 114, 131, R2.anim.settlement_dialog_bottom_enter, 124}, new int[]{161, 61, R2.anim.settlement_dialog_bottom_exit, 127, 170, 88, 53, R2.anim.pickerview_dialog_scale_out}, new int[]{55, R2.anim.pop_left_bottom_in, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, R2.anim.slide_out_to_bottom, 160, 58, R2.anim.popdown_anim_feedback, 100, 89}};
    private static final int[][] FINDER_PATTERN_SEQUENCES = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};

    private void adjustOddEvenCounts(int i2) throws NotFoundException {
        boolean z;
        boolean z2;
        boolean z3;
        int count = AbstractRSSReader.count(getOddCounts());
        int count2 = AbstractRSSReader.count(getEvenCounts());
        int i3 = (count + count2) - i2;
        boolean z4 = true;
        boolean z5 = (count & 1) == 1;
        boolean z6 = (count2 & 1) == 0;
        if (count > 13) {
            z = false;
            z2 = true;
        } else {
            z = count < 4;
            z2 = false;
        }
        if (count2 > 13) {
            z3 = true;
        } else {
            r3 = count2 < 4;
            z3 = false;
        }
        if (i3 == 1) {
            if (z5) {
                if (z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
                z4 = z;
                z2 = true;
            } else if (!z6) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z4 = z;
                z3 = true;
            }
        } else if (i3 == -1) {
            if (z5) {
                if (z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
            } else if (!z6) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z4 = z;
                r3 = true;
            }
        } else if (i3 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            if (z5) {
                if (!z6) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (count >= count2) {
                    z4 = z;
                    r3 = true;
                    z2 = true;
                }
                z3 = true;
            } else if (z6) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                z4 = z;
            }
        }
        if (z4) {
            if (!z2) {
                AbstractRSSReader.increment(getOddCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (z2) {
            AbstractRSSReader.decrement(getOddCounts(), getOddRoundingErrors());
        }
        if (r3) {
            if (!z3) {
                AbstractRSSReader.increment(getEvenCounts(), getOddRoundingErrors());
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        if (z3) {
            AbstractRSSReader.decrement(getEvenCounts(), getEvenRoundingErrors());
        }
    }

    private boolean checkChecksum() {
        ExpandedPair expandedPair = this.pairs.get(0);
        DataCharacter leftChar = expandedPair.getLeftChar();
        DataCharacter rightChar = expandedPair.getRightChar();
        if (rightChar == null) {
            return false;
        }
        int checksumPortion = rightChar.getChecksumPortion();
        int i2 = 2;
        for (int i3 = 1; i3 < this.pairs.size(); i3++) {
            ExpandedPair expandedPair2 = this.pairs.get(i3);
            checksumPortion += expandedPair2.getLeftChar().getChecksumPortion();
            i2++;
            DataCharacter rightChar2 = expandedPair2.getRightChar();
            if (rightChar2 != null) {
                checksumPortion += rightChar2.getChecksumPortion();
                i2++;
            }
        }
        return ((i2 + (-4)) * 211) + (checksumPortion % 211) == leftChar.getValue();
    }

    private List<ExpandedPair> checkRows(boolean z) {
        List<ExpandedPair> list = null;
        if (this.rows.size() > 25) {
            this.rows.clear();
            return null;
        }
        this.pairs.clear();
        if (z) {
            Collections.reverse(this.rows);
        }
        try {
            list = checkRows(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.rows);
        }
        return list;
    }

    static Result constructResult(List<ExpandedPair> list) throws NotFoundException, FormatException {
        String parseInformation = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(list)).parseInformation();
        ResultPoint[] resultPoints = list.get(0).getFinderPattern().getResultPoints();
        ResultPoint[] resultPoints2 = list.get(list.size() - 1).getFinderPattern().getResultPoints();
        return new Result(parseInformation, null, new ResultPoint[]{resultPoints[0], resultPoints[1], resultPoints2[0], resultPoints2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private void findNextPair(BitArray bitArray, List<ExpandedPair> list, int i2) throws NotFoundException {
        int[] decodeFinderCounters = getDecodeFinderCounters();
        decodeFinderCounters[0] = 0;
        decodeFinderCounters[1] = 0;
        decodeFinderCounters[2] = 0;
        decodeFinderCounters[3] = 0;
        int size = bitArray.getSize();
        if (i2 < 0) {
            i2 = list.isEmpty() ? 0 : list.get(list.size() - 1).getFinderPattern().getStartEnd()[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.startFromEven) {
            z = !z;
        }
        boolean z2 = false;
        while (i2 < size) {
            z2 = !bitArray.get(i2);
            if (!z2) {
                break;
            }
            i2++;
        }
        boolean z3 = z2;
        int i3 = 0;
        int i4 = i2;
        while (i2 < size) {
            if (bitArray.get(i2) ^ z3) {
                decodeFinderCounters[i3] = decodeFinderCounters[i3] + 1;
            } else {
                if (i3 == 3) {
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    if (AbstractRSSReader.isFinderPattern(decodeFinderCounters)) {
                        int[] iArr = this.startEnd;
                        iArr[0] = i4;
                        iArr[1] = i2;
                        return;
                    }
                    if (z) {
                        reverseCounters(decodeFinderCounters);
                    }
                    i4 += decodeFinderCounters[0] + decodeFinderCounters[1];
                    decodeFinderCounters[0] = decodeFinderCounters[2];
                    decodeFinderCounters[1] = decodeFinderCounters[3];
                    decodeFinderCounters[2] = 0;
                    decodeFinderCounters[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                decodeFinderCounters[i3] = 1;
                z3 = !z3;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int getNextSecondBar(BitArray bitArray, int i2) {
        if (bitArray.get(i2)) {
            return bitArray.getNextSet(bitArray.getNextUnset(i2));
        }
        return bitArray.getNextUnset(bitArray.getNextSet(i2));
    }

    private static boolean isNotA1left(FinderPattern finderPattern, boolean z, boolean z2) {
        return (finderPattern.getValue() == 0 && z && z2) ? false : true;
    }

    private static boolean isPartialRow(Iterable<ExpandedPair> iterable, Iterable<ExpandedRow> iterable2) {
        boolean z;
        boolean z2;
        Iterator<ExpandedRow> it = iterable2.iterator();
        do {
            z = false;
            if (!it.hasNext()) {
                return false;
            }
            ExpandedRow next = it.next();
            Iterator<ExpandedPair> it2 = iterable.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                ExpandedPair next2 = it2.next();
                Iterator<ExpandedPair> it3 = next.getPairs().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = false;
                        break;
                    } else if (next2.equals(it3.next())) {
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    break;
                }
            }
        } while (!z);
        return true;
    }

    private static boolean isValidSequence(List<ExpandedPair> list) {
        boolean z;
        for (int[] iArr : FINDER_PATTERN_SEQUENCES) {
            if (list.size() <= iArr.length) {
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        z = true;
                        break;
                    } else if (list.get(i2).getFinderPattern().getValue() != iArr[i2]) {
                        z = false;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private FinderPattern parseFoundFinderPattern(BitArray bitArray, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        if (z) {
            int i6 = this.startEnd[0] - 1;
            while (i6 >= 0 && !bitArray.get(i6)) {
                i6--;
            }
            int i7 = i6 + 1;
            int[] iArr = this.startEnd;
            i5 = iArr[0] - i7;
            i3 = iArr[1];
            i4 = i7;
        } else {
            int[] iArr2 = this.startEnd;
            int i8 = iArr2[0];
            int nextUnset = bitArray.getNextUnset(iArr2[1] + 1);
            i3 = nextUnset;
            i4 = i8;
            i5 = nextUnset - this.startEnd[1];
        }
        int[] decodeFinderCounters = getDecodeFinderCounters();
        System.arraycopy(decodeFinderCounters, 0, decodeFinderCounters, 1, decodeFinderCounters.length - 1);
        decodeFinderCounters[0] = i5;
        try {
            return new FinderPattern(AbstractRSSReader.parseFinderValue(decodeFinderCounters, FINDER_PATTERNS), new int[]{i4, i3}, i4, i3, i2);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private static void removePartialRows(List<ExpandedPair> list, List<ExpandedRow> list2) {
        boolean z;
        Iterator<ExpandedRow> it = list2.iterator();
        while (it.hasNext()) {
            ExpandedRow next = it.next();
            if (next.getPairs().size() != list.size()) {
                Iterator<ExpandedPair> it2 = next.getPairs().iterator();
                while (true) {
                    z = false;
                    boolean z2 = true;
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    ExpandedPair next2 = it2.next();
                    Iterator<ExpandedPair> it3 = list.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z2 = false;
                            break;
                        } else if (next2.equals(it3.next())) {
                            break;
                        }
                    }
                    if (!z2) {
                        break;
                    }
                }
                if (z) {
                    it.remove();
                }
            }
        }
    }

    private static void reverseCounters(int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length / 2; i2++) {
            int i3 = iArr[i2];
            int i4 = (length - i2) - 1;
            iArr[i2] = iArr[i4];
            iArr[i4] = i3;
        }
    }

    private void storeRow(int i2, boolean z) {
        boolean z2 = false;
        int i3 = 0;
        boolean z3 = false;
        while (true) {
            if (i3 >= this.rows.size()) {
                break;
            }
            ExpandedRow expandedRow = this.rows.get(i3);
            if (expandedRow.getRowNumber() > i2) {
                z2 = expandedRow.isEquivalent(this.pairs);
                break;
            } else {
                z3 = expandedRow.isEquivalent(this.pairs);
                i3++;
            }
        }
        if (z2 || z3 || isPartialRow(this.pairs, this.rows)) {
            return;
        }
        this.rows.add(i3, new ExpandedRow(this.pairs, i2, z));
        removePartialRows(this.pairs, this.rows);
    }

    DataCharacter decodeDataCharacter(BitArray bitArray, FinderPattern finderPattern, boolean z, boolean z2) throws NotFoundException {
        int[] dataCharacterCounters = getDataCharacterCounters();
        dataCharacterCounters[0] = 0;
        dataCharacterCounters[1] = 0;
        dataCharacterCounters[2] = 0;
        dataCharacterCounters[3] = 0;
        dataCharacterCounters[4] = 0;
        dataCharacterCounters[5] = 0;
        dataCharacterCounters[6] = 0;
        dataCharacterCounters[7] = 0;
        if (z2) {
            OneDReader.recordPatternInReverse(bitArray, finderPattern.getStartEnd()[0], dataCharacterCounters);
        } else {
            OneDReader.recordPattern(bitArray, finderPattern.getStartEnd()[1], dataCharacterCounters);
            int i2 = 0;
            for (int length = dataCharacterCounters.length - 1; i2 < length; length--) {
                int i3 = dataCharacterCounters[i2];
                dataCharacterCounters[i2] = dataCharacterCounters[length];
                dataCharacterCounters[length] = i3;
                i2++;
            }
        }
        float count = AbstractRSSReader.count(dataCharacterCounters) / 17;
        float f2 = (finderPattern.getStartEnd()[1] - finderPattern.getStartEnd()[0]) / 15.0f;
        if (Math.abs(count - f2) / f2 <= 0.3f) {
            int[] oddCounts = getOddCounts();
            int[] evenCounts = getEvenCounts();
            float[] oddRoundingErrors = getOddRoundingErrors();
            float[] evenRoundingErrors = getEvenRoundingErrors();
            for (int i4 = 0; i4 < dataCharacterCounters.length; i4++) {
                float f3 = (dataCharacterCounters[i4] * 1.0f) / count;
                int i5 = (int) (0.5f + f3);
                if (i5 < 1) {
                    if (f3 < 0.3f) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    i5 = 1;
                } else if (i5 > 8) {
                    if (f3 > 8.7f) {
                        throw NotFoundException.getNotFoundInstance();
                    }
                    i5 = 8;
                }
                int i6 = i4 / 2;
                if ((i4 & 1) == 0) {
                    oddCounts[i6] = i5;
                    oddRoundingErrors[i6] = f3 - i5;
                } else {
                    evenCounts[i6] = i5;
                    evenRoundingErrors[i6] = f3 - i5;
                }
            }
            adjustOddEvenCounts(17);
            int value = (((finderPattern.getValue() * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
            int i7 = 0;
            int i8 = 0;
            for (int length2 = oddCounts.length - 1; length2 >= 0; length2--) {
                if (isNotA1left(finderPattern, z, z2)) {
                    i7 += oddCounts[length2] * WEIGHTS[value][length2 * 2];
                }
                i8 += oddCounts[length2];
            }
            int i9 = 0;
            for (int length3 = evenCounts.length - 1; length3 >= 0; length3--) {
                if (isNotA1left(finderPattern, z, z2)) {
                    i9 += evenCounts[length3] * WEIGHTS[value][(length3 * 2) + 1];
                }
            }
            int i10 = i7 + i9;
            if ((i8 & 1) == 0 && i8 <= 13 && i8 >= 4) {
                int i11 = (13 - i8) / 2;
                int i12 = SYMBOL_WIDEST[i11];
                return new DataCharacter((RSSUtils.getRSSvalue(oddCounts, i12, true) * EVEN_TOTAL_SUBSET[i11]) + RSSUtils.getRSSvalue(evenCounts, 9 - i12, false) + GSUM[i11], i10);
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i2, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.pairs.clear();
        this.startFromEven = false;
        try {
            return constructResult(decodeRow2pairs(i2, bitArray));
        } catch (NotFoundException unused) {
            this.pairs.clear();
            this.startFromEven = true;
            return constructResult(decodeRow2pairs(i2, bitArray));
        }
    }

    List<ExpandedPair> decodeRow2pairs(int i2, BitArray bitArray) throws NotFoundException {
        while (true) {
            try {
                this.pairs.add(retrieveNextPair(bitArray, this.pairs, i2));
            } catch (NotFoundException e2) {
                if (this.pairs.isEmpty()) {
                    throw e2;
                }
                if (checkChecksum()) {
                    return this.pairs;
                }
                boolean z = !this.rows.isEmpty();
                storeRow(i2, false);
                if (z) {
                    List<ExpandedPair> checkRows = checkRows(false);
                    if (checkRows != null) {
                        return checkRows;
                    }
                    List<ExpandedPair> checkRows2 = checkRows(true);
                    if (checkRows2 != null) {
                        return checkRows2;
                    }
                }
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    List<ExpandedRow> getRows() {
        return this.rows;
    }

    @Override // com.google.zxing.oned.OneDReader, com.google.zxing.Reader
    public void reset() {
        this.pairs.clear();
        this.rows.clear();
    }

    ExpandedPair retrieveNextPair(BitArray bitArray, List<ExpandedPair> list, int i2) throws NotFoundException {
        FinderPattern parseFoundFinderPattern;
        DataCharacter dataCharacter;
        boolean z = list.size() % 2 == 0;
        if (this.startFromEven) {
            z = !z;
        }
        int i3 = -1;
        boolean z2 = true;
        do {
            findNextPair(bitArray, list, i3);
            parseFoundFinderPattern = parseFoundFinderPattern(bitArray, i2, z);
            if (parseFoundFinderPattern == null) {
                i3 = getNextSecondBar(bitArray, this.startEnd[0]);
            } else {
                z2 = false;
            }
        } while (z2);
        DataCharacter decodeDataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, true);
        if (!list.isEmpty() && list.get(list.size() - 1).mustBeLast()) {
            throw NotFoundException.getNotFoundInstance();
        }
        try {
            dataCharacter = decodeDataCharacter(bitArray, parseFoundFinderPattern, z, false);
        } catch (NotFoundException unused) {
            dataCharacter = null;
        }
        return new ExpandedPair(decodeDataCharacter, dataCharacter, parseFoundFinderPattern, true);
    }

    private List<ExpandedPair> checkRows(List<ExpandedRow> list, int i2) throws NotFoundException {
        while (i2 < this.rows.size()) {
            ExpandedRow expandedRow = this.rows.get(i2);
            this.pairs.clear();
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.pairs.addAll(list.get(i3).getPairs());
            }
            this.pairs.addAll(expandedRow.getPairs());
            if (isValidSequence(this.pairs)) {
                if (checkChecksum()) {
                    return this.pairs;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(expandedRow);
                try {
                    return checkRows(arrayList, i2 + 1);
                } catch (NotFoundException unused) {
                    continue;
                }
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
