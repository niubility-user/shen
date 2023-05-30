package com.google.zxing.datamatrix.encoder;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
final class DataMatrixSymbolInfo144 extends SymbolInfo {
    public DataMatrixSymbolInfo144() {
        super(false, R2.attr.previewAdapteSize, 620, 22, 22, 36, -1, 62);
    }

    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getDataLengthForInterleavedBlock(int i2) {
        if (i2 <= 8) {
            return R2.anim.out_to_bottom_slow;
        }
        return 155;
    }

    @Override // com.google.zxing.datamatrix.encoder.SymbolInfo
    public int getInterleavedBlockCount() {
        return 10;
    }
}
