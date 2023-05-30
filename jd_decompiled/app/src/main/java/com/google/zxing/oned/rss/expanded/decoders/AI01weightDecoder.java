package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public abstract class AI01weightDecoder extends AI01decoder {
    public AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    protected abstract void addWeightCode(StringBuilder sb, int i2);

    protected abstract int checkWeight(int i2);

    public final void encodeCompressedWeight(StringBuilder sb, int i2, int i3) {
        int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(i2, i3);
        addWeightCode(sb, extractNumericValueFromBitArray);
        int checkWeight = checkWeight(extractNumericValueFromBitArray);
        int i4 = 100000;
        for (int i5 = 0; i5 < 5; i5++) {
            if (checkWeight / i4 == 0) {
                sb.append('0');
            }
            i4 /= 10;
        }
        sb.append(checkWeight);
    }
}
