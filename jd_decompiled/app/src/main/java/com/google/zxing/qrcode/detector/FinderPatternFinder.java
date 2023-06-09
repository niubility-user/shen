package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class FinderPatternFinder {
    private static final int CENTER_QUORUM = 2;
    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final int[] crossCheckStateCount;
    private boolean hasSkipped;
    private final BitMatrix image;
    private final List<FinderPattern> possibleCenters;
    private final ResultPointCallback resultPointCallback;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class CenterComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private CenterComparator(float f2) {
            this.average = f2;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            if (finderPattern2.getCount() == finderPattern.getCount()) {
                float abs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
                float abs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
                if (abs < abs2) {
                    return 1;
                }
                return abs == abs2 ? 0 : -1;
            }
            return finderPattern2.getCount() - finderPattern.getCount();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class FurthestFromAverageComparator implements Comparator<FinderPattern>, Serializable {
        private final float average;

        private FurthestFromAverageComparator(float f2) {
            this.average = f2;
        }

        @Override // java.util.Comparator
        public int compare(FinderPattern finderPattern, FinderPattern finderPattern2) {
            float abs = Math.abs(finderPattern2.getEstimatedModuleSize() - this.average);
            float abs2 = Math.abs(finderPattern.getEstimatedModuleSize() - this.average);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }
    }

    public FinderPatternFinder(BitMatrix bitMatrix) {
        this(bitMatrix, null);
    }

    private static float centerFromEnd(int[] iArr, int i2) {
        return ((i2 - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
        if (r18 >= r6) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r17 < r6) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r18 < r6) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r16.image.get(r18 - r6, r17 - r6) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
        if (r4[1] > r19) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0042, code lost:
        r4[1] = r4[1] + 1;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004a, code lost:
        if (r17 < r6) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004c, code lost:
        if (r18 < r6) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
        if (r4[1] <= r19) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0054, code lost:
        if (r17 < r6) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0056, code lost:
        if (r18 < r6) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
        if (r16.image.get(r18 - r6, r17 - r6) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r4[0] > r19) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0068, code lost:
        r4[0] = r4[0] + 1;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0072, code lost:
        if (r4[0] <= r19) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0075, code lost:
        r6 = r16.image.getHeight();
        r9 = r16.image.getWidth();
        r10 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0082, code lost:
        r11 = r17 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0084, code lost:
        if (r11 >= r6) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0086, code lost:
        r12 = r18 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0088, code lost:
        if (r12 >= r9) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0090, code lost:
        if (r16.image.get(r12, r11) == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0092, code lost:
        r4[2] = r4[2] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009a, code lost:
        if (r11 >= r6) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009e, code lost:
        if ((r18 + r10) < r9) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a2, code lost:
        r11 = r17 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a5, code lost:
        if (r11 >= r6) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a7, code lost:
        r13 = r18 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a9, code lost:
        if (r13 >= r9) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b1, code lost:
        if (r16.image.get(r13, r11) != false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00b5, code lost:
        if (r4[3] >= r19) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b7, code lost:
        r4[3] = r4[3] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00bf, code lost:
        if (r11 >= r6) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00c3, code lost:
        if ((r18 + r10) >= r9) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00c7, code lost:
        if (r4[3] < r19) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ca, code lost:
        r11 = r17 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00cd, code lost:
        if (r11 >= r6) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00cf, code lost:
        r14 = r18 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d1, code lost:
        if (r14 >= r9) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00d9, code lost:
        if (r16.image.get(r14, r11) == false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00dd, code lost:
        if (r4[4] >= r19) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00df, code lost:
        r4[4] = r4[4] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00e9, code lost:
        if (r4[4] < r19) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00eb, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0102, code lost:
        if (java.lang.Math.abs(((((r4[0] + r4[1]) + r4[2]) + r4[3]) + r4[4]) - r20) >= (r20 * 2)) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0108, code lost:
        if (foundPatternCross(r4) == false) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x010a, code lost:
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean crossCheckDiagonal(int r17, int r18, int r19, int r20) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckDiagonal(int, int, int, int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r2[1] <= r13) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r3 < 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r0.get(r3, r12) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r2[0] > r13) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r2[0] <= r13) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        if (r0.get(r11, r12) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        if (r11 != r1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006d, code lost:
        if (r11 >= r1) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r0.get(r11, r12) != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        if (r2[3] >= r13) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0079, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0081, code lost:
        if (r11 == r1) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0085, code lost:
        if (r2[3] < r13) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0089, code lost:
        if (r11 >= r1) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        if (r0.get(r11, r12) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (r2[4] >= r13) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009f, code lost:
        if (r2[4] < r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a1, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b7, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < r14) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b9, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00be, code lost:
        if (foundPatternCross(r2) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c4, code lost:
        return centerFromEnd(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:?, code lost:
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckHorizontal(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 197
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckHorizontal(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r2[1] <= r13) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
        if (r3 < 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0045, code lost:
        if (r0.get(r12, r3) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0049, code lost:
        if (r2[0] > r13) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r2[0] <= r13) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0057, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0058, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
        if (r0.get(r12, r11) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0069, code lost:
        if (r11 != r1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006d, code lost:
        if (r11 >= r1) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
        if (r0.get(r12, r11) != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0077, code lost:
        if (r2[3] >= r13) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0079, code lost:
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0081, code lost:
        if (r11 == r1) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0085, code lost:
        if (r2[3] < r13) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0089, code lost:
        if (r11 >= r1) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        if (r0.get(r12, r11) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (r2[4] >= r13) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x009f, code lost:
        if (r2[4] < r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a1, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b9, code lost:
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < (r14 * 2)) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00bb, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00c0, code lost:
        if (foundPatternCross(r2) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00c6, code lost:
        return centerFromEnd(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:?, code lost:
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:?, code lost:
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float crossCheckVertical(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 199
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.qrcode.detector.FinderPatternFinder.crossCheckVertical(int, int, int, int):float");
    }

    private int findRowSkip() {
        if (this.possibleCenters.size() <= 1) {
            return 0;
        }
        FinderPattern finderPattern = null;
        for (FinderPattern finderPattern2 : this.possibleCenters) {
            if (finderPattern2.getCount() >= 2) {
                if (finderPattern != null) {
                    this.hasSkipped = true;
                    return ((int) (Math.abs(finderPattern.getX() - finderPattern2.getX()) - Math.abs(finderPattern.getY() - finderPattern2.getY()))) / 2;
                }
                finderPattern = finderPattern2;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean foundPatternCross(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return false;
            }
            i2 += i4;
        }
        if (i2 < 7) {
            return false;
        }
        float f2 = i2 / 7.0f;
        float f3 = f2 / 2.0f;
        return Math.abs(f2 - ((float) iArr[0])) < f3 && Math.abs(f2 - ((float) iArr[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    private int[] getCrossCheckStateCount() {
        int[] iArr = this.crossCheckStateCount;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean haveMultiplyConfirmedCenters() {
        int size = this.possibleCenters.size();
        float f2 = 0.0f;
        int i2 = 0;
        float f3 = 0.0f;
        for (FinderPattern finderPattern : this.possibleCenters) {
            if (finderPattern.getCount() >= 2) {
                i2++;
                f3 += finderPattern.getEstimatedModuleSize();
            }
        }
        if (i2 < 3) {
            return false;
        }
        float f4 = f3 / size;
        Iterator<FinderPattern> it = this.possibleCenters.iterator();
        while (it.hasNext()) {
            f2 += Math.abs(it.next().getEstimatedModuleSize() - f4);
        }
        return f2 <= f3 * 0.05f;
    }

    private FinderPattern[] selectBestPatterns() throws NotFoundException {
        int size = this.possibleCenters.size();
        if (size >= 3) {
            float f2 = 0.0f;
            if (size > 3) {
                Iterator<FinderPattern> it = this.possibleCenters.iterator();
                float f3 = 0.0f;
                float f4 = 0.0f;
                while (it.hasNext()) {
                    float estimatedModuleSize = it.next().getEstimatedModuleSize();
                    f3 += estimatedModuleSize;
                    f4 += estimatedModuleSize * estimatedModuleSize;
                }
                float f5 = f3 / size;
                float sqrt = (float) Math.sqrt((f4 / r0) - (f5 * f5));
                Collections.sort(this.possibleCenters, new FurthestFromAverageComparator(f5));
                float max = Math.max(0.2f * f5, sqrt);
                int i2 = 0;
                while (i2 < this.possibleCenters.size() && this.possibleCenters.size() > 3) {
                    if (Math.abs(this.possibleCenters.get(i2).getEstimatedModuleSize() - f5) > max) {
                        this.possibleCenters.remove(i2);
                        i2--;
                    }
                    i2++;
                }
            }
            if (this.possibleCenters.size() > 3) {
                Iterator<FinderPattern> it2 = this.possibleCenters.iterator();
                while (it2.hasNext()) {
                    f2 += it2.next().getEstimatedModuleSize();
                }
                Collections.sort(this.possibleCenters, new CenterComparator(f2 / this.possibleCenters.size()));
                List<FinderPattern> list = this.possibleCenters;
                list.subList(3, list.size()).clear();
            }
            return new FinderPattern[]{this.possibleCenters.get(0), this.possibleCenters.get(1), this.possibleCenters.get(2)};
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final FinderPatternInfo find(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        boolean z2 = map != null && map.containsKey(DecodeHintType.PURE_BARCODE);
        int height = this.image.getHeight();
        int width = this.image.getWidth();
        int i2 = (height * 3) / 228;
        if (i2 < 3 || z) {
            i2 = 3;
        }
        int[] iArr = new int[5];
        int i3 = i2 - 1;
        boolean z3 = false;
        while (i3 < height && !z3) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i4 = 0;
            int i5 = 0;
            while (i4 < width) {
                if (this.image.get(i4, i3)) {
                    if ((i5 & 1) == 1) {
                        i5++;
                    }
                    iArr[i5] = iArr[i5] + 1;
                } else if ((i5 & 1) != 0) {
                    iArr[i5] = iArr[i5] + 1;
                } else if (i5 == 4) {
                    if (foundPatternCross(iArr)) {
                        if (handlePossibleCenter(iArr, i3, i4, z2)) {
                            if (this.hasSkipped) {
                                z3 = haveMultiplyConfirmedCenters();
                            } else {
                                int findRowSkip = findRowSkip();
                                if (findRowSkip > iArr[2]) {
                                    i3 += (findRowSkip - iArr[2]) - 2;
                                    i4 = width - 1;
                                }
                            }
                            iArr[0] = 0;
                            iArr[1] = 0;
                            iArr[2] = 0;
                            iArr[3] = 0;
                            iArr[4] = 0;
                            i2 = 2;
                            i5 = 0;
                        } else {
                            iArr[0] = iArr[2];
                            iArr[1] = iArr[3];
                            iArr[2] = iArr[4];
                            iArr[3] = 1;
                            iArr[4] = 0;
                        }
                    } else {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                    }
                    i5 = 3;
                } else {
                    i5++;
                    iArr[i5] = iArr[i5] + 1;
                }
                i4++;
            }
            if (foundPatternCross(iArr) && handlePossibleCenter(iArr, i3, width, z2)) {
                i2 = iArr[0];
                if (this.hasSkipped) {
                    z3 = haveMultiplyConfirmedCenters();
                }
            }
            i3 += i2;
        }
        FinderPattern[] selectBestPatterns = selectBestPatterns();
        ResultPoint.orderBestPatterns(selectBestPatterns);
        return new FinderPatternInfo(selectBestPatterns);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BitMatrix getImage() {
        return this.image;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<FinderPattern> getPossibleCenters() {
        return this.possibleCenters;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean handlePossibleCenter(int[] iArr, int i2, int i3, boolean z) {
        boolean z2 = false;
        int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int centerFromEnd = (int) centerFromEnd(iArr, i3);
        float crossCheckVertical = crossCheckVertical(i2, centerFromEnd, iArr[2], i4);
        if (!Float.isNaN(crossCheckVertical)) {
            int i5 = (int) crossCheckVertical;
            float crossCheckHorizontal = crossCheckHorizontal(centerFromEnd, i5, iArr[2], i4);
            if (!Float.isNaN(crossCheckHorizontal) && (!z || crossCheckDiagonal(i5, (int) crossCheckHorizontal, iArr[2], i4))) {
                float f2 = i4 / 7.0f;
                int i6 = 0;
                while (true) {
                    if (i6 >= this.possibleCenters.size()) {
                        break;
                    }
                    FinderPattern finderPattern = this.possibleCenters.get(i6);
                    if (finderPattern.aboutEquals(f2, crossCheckVertical, crossCheckHorizontal)) {
                        this.possibleCenters.set(i6, finderPattern.combineEstimate(crossCheckVertical, crossCheckHorizontal, f2));
                        z2 = true;
                        break;
                    }
                    i6++;
                }
                if (!z2) {
                    FinderPattern finderPattern2 = new FinderPattern(crossCheckHorizontal, crossCheckVertical, f2);
                    this.possibleCenters.add(finderPattern2);
                    ResultPointCallback resultPointCallback = this.resultPointCallback;
                    if (resultPointCallback != null) {
                        resultPointCallback.foundPossibleResultPoint(finderPattern2);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public FinderPatternFinder(BitMatrix bitMatrix, ResultPointCallback resultPointCallback) {
        this.image = bitMatrix;
        this.possibleCenters = new ArrayList();
        this.crossCheckStateCount = new int[5];
        this.resultPointCallback = resultPointCallback;
    }
}
