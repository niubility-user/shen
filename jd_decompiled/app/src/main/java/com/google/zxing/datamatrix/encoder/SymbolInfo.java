package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public class SymbolInfo {
    static final SymbolInfo[] PROD_SYMBOLS;
    private static SymbolInfo[] symbols;
    private final int dataCapacity;
    private final int dataRegions;
    private final int errorCodewords;
    public final int matrixHeight;
    public final int matrixWidth;
    private final boolean rectangular;
    private final int rsBlockData;
    private final int rsBlockError;

    static {
        SymbolInfo[] symbolInfoArr = {new SymbolInfo(false, 3, 5, 8, 8, 1), new SymbolInfo(false, 5, 7, 10, 10, 1), new SymbolInfo(true, 5, 7, 16, 6, 1), new SymbolInfo(false, 8, 10, 12, 12, 1), new SymbolInfo(true, 10, 11, 14, 6, 2), new SymbolInfo(false, 12, 12, 14, 14, 1), new SymbolInfo(true, 16, 14, 24, 10, 1), new SymbolInfo(false, 18, 14, 16, 16, 1), new SymbolInfo(false, 22, 18, 18, 18, 1), new SymbolInfo(true, 22, 18, 16, 10, 2), new SymbolInfo(false, 30, 20, 20, 20, 1), new SymbolInfo(true, 32, 24, 16, 14, 2), new SymbolInfo(false, 36, 24, 22, 22, 1), new SymbolInfo(false, 44, 28, 24, 24, 1), new SymbolInfo(true, 49, 28, 22, 14, 2), new SymbolInfo(false, 62, 36, 14, 14, 4), new SymbolInfo(false, 86, 42, 16, 16, 4), new SymbolInfo(false, 114, 48, 18, 18, 4), new SymbolInfo(false, 144, 56, 20, 20, 4), new SymbolInfo(false, R2.anim.popdown_anim_feedback, 68, 22, 22, 4), new SymbolInfo(false, 204, 84, 24, 24, 4, 102, 42), new SymbolInfo(false, 280, 112, 14, 14, 16, 140, 56), new SymbolInfo(false, R2.attr.alignContent, 144, 16, 16, 16, 92, 36), new SymbolInfo(false, R2.attr.bg, 192, 18, 18, 16, 114, 48), new SymbolInfo(false, R2.attr.checked_is_bold, 224, 20, 20, 16, 144, 56), new SymbolInfo(false, R2.attr.contentPaddingLeft, 272, 22, 22, 16, R2.anim.popdown_anim_feedback, 68), new SymbolInfo(false, R2.attr.endIconDrawable, 336, 24, 24, 16, R2.anim.lib_cashier_sdk_fragment_right_out, 56), new SymbolInfo(false, R2.attr.indeterminateProgressStyle, 408, 18, 18, 36, R2.anim.popup_anim_feedback, 68), new SymbolInfo(false, R2.attr.loadProgressColor, R2.attr.btn_icon, 20, 20, 36, R2.anim.pop_center_out, 62), new DataMatrixSymbolInfo144()};
        PROD_SYMBOLS = symbolInfoArr;
        symbols = symbolInfoArr;
    }

    public SymbolInfo(boolean z, int i2, int i3, int i4, int i5, int i6) {
        this(z, i2, i3, i4, i5, i6, i2, i3);
    }

    public static SymbolInfo lookup(int i2) {
        return lookup(i2, SymbolShapeHint.FORCE_NONE, true);
    }

    public static void overrideSymbolSet(SymbolInfo[] symbolInfoArr) {
        symbols = symbolInfoArr;
    }

    public int getCodewordCount() {
        return this.dataCapacity + this.errorCodewords;
    }

    public final int getDataCapacity() {
        return this.dataCapacity;
    }

    public int getDataLengthForInterleavedBlock(int i2) {
        return this.rsBlockData;
    }

    public final int getErrorCodewords() {
        return this.errorCodewords;
    }

    public final int getErrorLengthForInterleavedBlock(int i2) {
        return this.rsBlockError;
    }

    final int getHorizontalDataRegions() {
        int i2 = this.dataRegions;
        int i3 = 1;
        if (i2 != 1) {
            i3 = 2;
            if (i2 != 2 && i2 != 4) {
                if (i2 != 16) {
                    if (i2 == 36) {
                        return 6;
                    }
                    throw new IllegalStateException("Cannot handle this number of data regions");
                }
                return 4;
            }
        }
        return i3;
    }

    public int getInterleavedBlockCount() {
        return this.dataCapacity / this.rsBlockData;
    }

    public final int getSymbolDataHeight() {
        return getVerticalDataRegions() * this.matrixHeight;
    }

    public final int getSymbolDataWidth() {
        return getHorizontalDataRegions() * this.matrixWidth;
    }

    public final int getSymbolHeight() {
        return getSymbolDataHeight() + (getVerticalDataRegions() * 2);
    }

    public final int getSymbolWidth() {
        return getSymbolDataWidth() + (getHorizontalDataRegions() * 2);
    }

    final int getVerticalDataRegions() {
        int i2 = this.dataRegions;
        if (i2 == 1 || i2 == 2) {
            return 1;
        }
        if (i2 != 4) {
            if (i2 != 16) {
                if (i2 == 36) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
            return 4;
        }
        return 2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.rectangular ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.matrixWidth);
        sb.append('x');
        sb.append(this.matrixHeight);
        sb.append(", symbol size ");
        sb.append(getSymbolWidth());
        sb.append('x');
        sb.append(getSymbolHeight());
        sb.append(", symbol data size ");
        sb.append(getSymbolDataWidth());
        sb.append('x');
        sb.append(getSymbolDataHeight());
        sb.append(", codewords ");
        sb.append(this.dataCapacity);
        sb.append('+');
        sb.append(this.errorCodewords);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SymbolInfo(boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.rectangular = z;
        this.dataCapacity = i2;
        this.errorCodewords = i3;
        this.matrixWidth = i4;
        this.matrixHeight = i5;
        this.dataRegions = i6;
        this.rsBlockData = i7;
        this.rsBlockError = i8;
    }

    public static SymbolInfo lookup(int i2, SymbolShapeHint symbolShapeHint) {
        return lookup(i2, symbolShapeHint, true);
    }

    public static SymbolInfo lookup(int i2, boolean z, boolean z2) {
        return lookup(i2, z ? SymbolShapeHint.FORCE_NONE : SymbolShapeHint.FORCE_SQUARE, z2);
    }

    private static SymbolInfo lookup(int i2, SymbolShapeHint symbolShapeHint, boolean z) {
        return lookup(i2, symbolShapeHint, null, null, z);
    }

    public static SymbolInfo lookup(int i2, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2, boolean z) {
        for (SymbolInfo symbolInfo : symbols) {
            if (!(symbolShapeHint == SymbolShapeHint.FORCE_SQUARE && symbolInfo.rectangular) && ((symbolShapeHint != SymbolShapeHint.FORCE_RECTANGLE || symbolInfo.rectangular) && ((dimension == null || (symbolInfo.getSymbolWidth() >= dimension.getWidth() && symbolInfo.getSymbolHeight() >= dimension.getHeight())) && ((dimension2 == null || (symbolInfo.getSymbolWidth() <= dimension2.getWidth() && symbolInfo.getSymbolHeight() <= dimension2.getHeight())) && i2 <= symbolInfo.dataCapacity)))) {
                return symbolInfo;
            }
        }
        if (z) {
            throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + i2);
        }
        return null;
    }
}
